package ru.netology.javacore.commands;
import ru.netology.javacore.Todos;

public class RemoveTaskCommand implements Command {

    private Todos todos;
    private String task;

    public RemoveTaskCommand(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void execute(String task) {
        this.task = task;
        todos.removeTask(task);
    }

    @Override
    public String getTask() {
        return task;
    }
}

