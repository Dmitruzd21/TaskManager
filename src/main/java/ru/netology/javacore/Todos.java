package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos { // Синглтон
    private static Todos todos;
    private List<String> tasks = new ArrayList<>();
    private int tasksLimit = 7;

    private Todos() {};

    public static Todos getInstance() {
        if (todos == null) todos = new Todos();
        return todos;
    }

    public void addTask(String task) {
        if (tasks.size() < tasksLimit) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> allTasks = tasks.stream().sorted().collect(Collectors.toList());
        for (String task : allTasks) {
            stringBuilder.append(task).append(" ");
        }
        return stringBuilder.toString();
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
}
