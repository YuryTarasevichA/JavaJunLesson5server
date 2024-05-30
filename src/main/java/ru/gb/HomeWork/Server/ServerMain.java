package ru.gb.HomeWork.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

// TCP - протокол передачи данных, который гарантирует доставку данных
// UDP - протокол, который не гарантирует передачу данных
// IP - адрес
// TCP-порт
public class ServerMain {
    public static final int SERVER_PORT = 8181;

    public static void main(String[] args) {
        new Server().start(SERVER_PORT);

    }
}
