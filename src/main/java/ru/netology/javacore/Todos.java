package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private List<String> tasks = new ArrayList<>();
    private int tasksLimit = 7;

    public void addTask(String task) {
        if (tasks.size() < tasksLimit) tasks.add(task);
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> allTasks = tasks.stream().sorted().limit(tasksLimit).collect(Collectors.toList());
        for (String task : allTasks) {
            stringBuilder.append(task)
                    .append(" ");
        }
        return stringBuilder.toString();
    }
}
