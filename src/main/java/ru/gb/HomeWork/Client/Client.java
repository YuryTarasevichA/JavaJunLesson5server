package ru.gb.HomeWork.Client;

import ru.gb.HomeWork.Server.ServerMain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException {
        try (Scanner console = new Scanner(System.in);
             Socket server = new Socket("localhost", ServerMain.SERVER_PORT);
             Scanner in = new Scanner(server.getInputStream());
             PrintWriter out = new PrintWriter(server.getOutputStream(), true)) {

            System.out.println("Введите ваш логин: ");
            String login = console.nextLine();
            out.println(login);
            System.out.println("Подключение к серверу успешно!");

            // поток на чтение
            new Thread(() -> {
                while (true) {
                    if (in.hasNextLine()) {
                        String message = in.nextLine();
                        System.out.println("Получено новое сообщение: " + message);
                    }
                }
            }).start();

            // Поток на запись
            new Thread(() -> {
                while (true) {
                    String inputFromConsole = console.nextLine();
                    out.println(inputFromConsole);
                    if (inputFromConsole.equalsIgnoreCase("exit")) {
                        System.out.println("Выход из чата...");
                        break;
                    }
                }
            }).start();

        } catch (IOException e) {
            System.err.println("Ошибка при работе с сервером: " + e.getMessage());
        }
    }
}

