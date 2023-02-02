package ru.netology.javacore.commands;
import ru.netology.javacore.Todos;

public class AddTaskCommand implements Command {
    private StateOfTodos stateOfTodos;

    @Override
    public void execute(String task) {
        stateOfTodos = new StateOfTodos();
        stateOfTodos.setState();
        Todos.getInstance().addTask(task);
    }

    @Override
    public void unExecute() {
        stateOfTodos.getState();
    }
}
