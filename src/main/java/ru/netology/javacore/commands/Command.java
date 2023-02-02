package ru.netology.javacore.commands;

public interface Command {

    void execute(String task);

    void unExecute();
}
