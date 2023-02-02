package ru.netology.javacore.commands;
import ru.netology.javacore.Todos;
import java.util.List;

public class StateOfTodos {
    private List<String> tasks;

    public void setState() {
        this.tasks = Todos.getInstance().getTasks();
       // System.out.println("Hash объекта stateOfTodos: " + this.hashCode());
        System.out.println("Сохраняю: " + tasks);
    }

    public List<String> getState() {
       // System.out.println("Hash объекта stateOfTodos: " + this.hashCode());
        System.out.println("Отдаю:" + tasks);
        return tasks;
    }
}
