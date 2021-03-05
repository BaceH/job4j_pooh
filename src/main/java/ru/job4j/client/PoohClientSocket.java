package ru.job4j.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PoohClientSocket {
    private int port;
    private String server;

    public PoohClientSocket(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public void startClient() {
        try {
            Socket socket = new Socket(server, port);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            oos.writeObject("Message from client");

            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
