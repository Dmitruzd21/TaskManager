package ru.netology.javacore;

public class Task {
    private String type;
    private String task;

    public Task(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }
}
