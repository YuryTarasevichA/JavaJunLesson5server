package ru.gb.http;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.URI;
import java.net.URLConnection;

public class HttpMain {
    public static void main(String[] args) throws IOException {
        URLConnection urlConnection;
        try {
            urlConnection = URI.create("https://github.com/golang/go").toURL().openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        urlConnection.connect();
    }
    // RestTemplate
    // WebClient


    // TCP, UDP - протокол для взаимодействия по сети
    // TLS\SSL (Transport Layer Security \ Secure Socket Layer)
    // HTTP, Protobuf, ... - протоколы-форматы для сообщений
    // HTTPS = HTTP + Secure
}
