package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.netology.javacore.commands.AddTaskCommand;
import ru.netology.javacore.commands.Command;
import ru.netology.javacore.commands.RemoveTaskCommand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;
import java.util.TreeSet;

public class TodoServer {
    private int port;
    private Todos todos;
    private final String ADD_OPERATION = "ADD";
    private final String REMOVE_OPERATION = "REMOVE";
    private final String RESTORE_OPERATION = "RESTORE";
    private Stack<Command> commandStack = new Stack<>();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) { // стартуем сервер один(!) раз)
            System.out.println("Starting server at " + port + "...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    System.out.println("New connection accepted");
                    String clientRequest = in.readLine();
                    Task task = convertJsonToTask(clientRequest);
                    System.out.println(task.getType());
                    performOperationWithTask(task);
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException ex) {
            System.out.println("Не могу стартовать сервер!");
            ex.printStackTrace();
        }
    }

    public Task convertJsonToTask(String jsonText) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonText, Task.class);
    }

    public void performOperationWithTask(Task task) {
        switch (task.getType()) {
            case ADD_OPERATION:
                Command addTaskCommand = new AddTaskCommand(todos);
                addTaskCommand.execute(task.getTask());
                commandStack.push(addTaskCommand);
                break;
            case REMOVE_OPERATION:
                Command removeTaskCommand = new RemoveTaskCommand(todos);
                removeTaskCommand.execute(task.getTask());
                commandStack.push(removeTaskCommand);
                break;
            case RESTORE_OPERATION:
                try {
                    commandStack.pop();
                    TreeSet<String> previousTasks = new TreeSet<>();
                    for (Command command : commandStack) {
                        if (command.getClass() == AddTaskCommand.class)
                            previousTasks.add(command.getTask());
                    }
                    for (Command command : commandStack) {
                        if (command.getClass() == RemoveTaskCommand.class)
                            previousTasks.remove(command.getTask());
                    }
                    System.out.println(previousTasks);
                    todos.setTasks(previousTasks);
                } catch (Exception ex) {
                    System.out.println("Отмена невозможна!");
                }
                break;
            default:
                System.out.println("Сервер не может распознать операцию!");
                break;
        }
    }
}
