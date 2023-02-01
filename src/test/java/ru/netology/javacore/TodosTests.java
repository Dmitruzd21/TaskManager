package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodosTests {
    private Todos todos;
    private String swimming = "Плавание";
    private String reading = "Чтение";
    private String javaCourses = "Курсы java";
    private String cleaning = "Уборка";
    private String running = "Пробежка";
    private String acrobatics = "Акробатика";
    private String singing = "Пение";
    private String cooking = "Кулинария";
    private String tasksInAlphabetOrder = new StringBuilder()
            .append(acrobatics).append(" ")
            .append(javaCourses).append(" ")
            .append(singing).append(" ")
            .append(swimming).append(" ")
            .append(running).append(" ")
            .append(cleaning).append(" ")
            .append(reading).append(" ")
            .toString();
    private String tasksInAlphabetOrderWithoutCleaning = new StringBuilder()
            .append(acrobatics).append(" ")
            .append(javaCourses).append(" ")
            .append(singing).append(" ")
            .append(swimming).append(" ")
            .append(running).append(" ")
            .append(reading).append(" ")
            .toString();

    @BeforeEach
    public void createTodos() {
        todos = new Todos();
    }

    @Test
    public void shouldAddTask() {
        todos.addTask(swimming);
        todos.addTask(reading);
        todos.addTask(javaCourses);
        todos.addTask(cleaning);
        todos.addTask(running);
        todos.addTask(acrobatics);
        todos.addTask(singing);
        String actual = todos.getAllTasks();
        String expected = tasksInAlphabetOrder;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteTask() {
        todos.addTask(swimming);
        todos.addTask(reading);
        todos.addTask(javaCourses);
        todos.addTask(cleaning);
        todos.addTask(running);
        todos.addTask(acrobatics);
        todos.addTask(singing);
        todos.removeTask(cleaning);
        String actual = todos.getAllTasks();
        String expected = tasksInAlphabetOrderWithoutCleaning;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotGetMoreThanSevenTasks() {
        todos.addTask(swimming);
        todos.addTask(reading);
        todos.addTask(javaCourses);
        todos.addTask(cleaning);
        todos.addTask(running);
        todos.addTask(acrobatics);
        todos.addTask(singing);
        todos.addTask(cooking);
        String actual = todos.getAllTasks();
        String expected = tasksInAlphabetOrder;
        Assertions.assertEquals(expected, actual);
    }
}
