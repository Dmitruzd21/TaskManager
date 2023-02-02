package ru.netology.javacore;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Todos todos = Todos.getInstance();
        TodoServer server = new TodoServer(8989, todos);
        server.start();
    }
}
