package ru.job4j.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RequestHandler extends Thread{

    private Socket socket;

    public RequestHandler( Socket socket ){
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println( "Start handler..." );
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String message = (String) ois.readObject();

            System.out.println("Message Received: " + message);

            ois.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println( "End handler." );
    }
}
