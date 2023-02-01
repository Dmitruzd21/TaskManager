package ru.netology.javacore.commandAndMomentoPatterns;

import ru.netology.javacore.Todos;

public class AddTaskCommand implements Command {
    Todos todos;
    Memento memento;

    public AddTaskCommand(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void execute(String task) {
        memento = new Memento();
        memento.setState(todos);
        todos.addTask(task);
    }

    @Override
    public void unExecute() {
        this.todos = memento.getState();
    }
}
