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

public class TodoServer {
    private int port;
    private Todos todos;
    private final String addOperation = "ADD";
    private final String removeOperation = "REMOVE";
    private final String restore = "RESTORE";
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
            case addOperation:
                Command addTaskCommand = new AddTaskCommand();
                addTaskCommand.execute(task.getTask());
                commandStack.push(addTaskCommand);
                break;
            case removeOperation:
                Command removeTaskCommand = new RemoveTaskCommand();
                removeTaskCommand.execute(task.getTask());
                commandStack.push(removeTaskCommand);
                break;
            case restore:
               // commandStack.forEach((x) -> System.out.println("Hash объекта команды: " + x.hashCode()));
                Command command = commandStack.pop();
                command.unExecute();
                break;
            default:
                System.out.println("Сервер не может распознать операцию!");
                break;
        }
    }
}
