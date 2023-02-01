package ru.netology.javacore.commandAndMomentoPatterns;

import ru.netology.javacore.Todos;

public class Memento {
    Todos todos;

    public void setState(Todos todos) {
        this.todos = todos;
    }

    public Todos getState() {
        return todos;
    }
}
