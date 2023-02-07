package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private TreeSet<String> tasks = new TreeSet<>();
    public static final int MAX_TASKS = 7;

    public void addTask(String task) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
        }

    }

    public void removeTask(String task) throws IllegalStateException  {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("Список задач пустой: операция удаления невозможна");
        }
        tasks.remove(task);
    }

    public String getAllTasks() {
        String allTasks = tasks.stream().sorted().collect(Collectors.joining(" "));
        return allTasks;
    }

    public void setTasks(TreeSet<String> tasks) {
        this.tasks = tasks;
    }

    public TreeSet<String> getTasks() {
        return tasks;
    }
}
