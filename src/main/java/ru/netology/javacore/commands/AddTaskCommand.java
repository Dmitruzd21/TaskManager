package ru.netology.javacore.commands;
import ru.netology.javacore.Todos;

public class AddTaskCommand implements Command {
    private Todos todos;
    private String task;

    public AddTaskCommand(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void execute(String task) {
        this.task = task;
        todos.addTask(task);
    }

    @Override
    public String getTask() {
        return task;
    }
}
