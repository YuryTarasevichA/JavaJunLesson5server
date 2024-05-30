package ru.gb.HomeWork.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * Инкапсуляция для клиента на сервере
 */
public class ClientConnection implements Runnable {
    private final Server server;
    private final Socket socket;
    private final Scanner input;
    private final PrintWriter output;
    private final String login;
    private Runnable onCloseHandler;

    public ClientConnection(Socket socket, Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.login = input.nextLine();
    }


    public void sendMessage(String message) {
        output.println(message);
    }

    public String getLogin() {
        return login;
    }


    public void setOnCloseHandler(Runnable onCloseHandler) {
        this.onCloseHandler = onCloseHandler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msgFromClient = input.nextLine();
                if (Objects.equals("exit", msgFromClient)) {
                    System.out.println("Клиент отключился! ");
                    break;
                }
                //TODO: распарсить сообщения и понять, что нужно сделать
                if (msgFromClient.startsWith("@")) {
                    String[] split = msgFromClient.split("\\s+");
                    String loginTo = split[0].substring(1);

                    String pureMessage = msgFromClient.replace("@" + loginTo + " ", "");
                    server.sendMessageToClient(loginTo,"["+ login +"] " + pureMessage);
                } else {
                    server.sendMessageToAll("[" + login + "] " + msgFromClient);
                }
            }

            try {
                close();
            } catch (IOException e) {
                System.err.println("Произошла ошибка во время закрытия сокета: " + e.getMessage());
            }
        } finally {
            if (onCloseHandler != null) {
                onCloseHandler.run();
            }
        }
    }

    public void close() throws IOException {
        socket.close();
    }
}


