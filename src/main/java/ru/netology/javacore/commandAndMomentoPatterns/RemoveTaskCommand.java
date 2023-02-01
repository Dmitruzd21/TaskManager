package ru.netology.javacore.commandAndMomentoPatterns;

import ru.netology.javacore.Todos;

public class RemoveTaskCommand implements Command {
    Todos todos;
    Memento memento;

    public RemoveTaskCommand(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void execute(String task) {
        memento = new Memento();
        memento.setState(todos);
        todos.removeTask(task);
    }

    @Override
    public void unExecute() {
        this.todos = memento.getState();
    }
}

