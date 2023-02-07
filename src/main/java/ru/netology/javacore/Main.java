package ru.netology.javacore;

import java.io.IOException;

public class Main {
    private static final int PORT = 8989;

    public static void main(String[] args) throws IOException {
        Todos todos = new Todos();
        TodoServer server = new TodoServer(PORT, todos);
        server.start();
    }
}
