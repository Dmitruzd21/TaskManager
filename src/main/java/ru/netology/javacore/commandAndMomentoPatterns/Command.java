package ru.netology.javacore.commandAndMomentoPatterns;

public interface Command {

    void execute(String task);

    void unExecute();
}
