package ru.netology.javacore.commands;
import ru.netology.javacore.Todos;

public class RemoveTaskCommand implements Command {

    private StateOfTodos stateOfTodos;

    @Override
    public void execute(String task) {
        this.stateOfTodos = new StateOfTodos();
        stateOfTodos.setState();
        Todos.getInstance().removeTask(task);
    }

    @Override
    public void unExecute() {
        Todos.getInstance().setTasks(stateOfTodos.getState());
    }
}

