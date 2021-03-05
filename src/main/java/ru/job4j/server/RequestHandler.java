package ru.job4j.server;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler extends Thread{
    private Socket socket;

    RequestHandler( Socket socket )
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {

        System.out.println( "Start handler..." );
        try {
            socket.getInputStream();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "End handler. Connection closed" );
    }
}
