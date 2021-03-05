package ru.job4j.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PoohServerSocket extends Thread{
    private ServerSocket serverSocket;
    private int port;
    private boolean running = false;

    public PoohServerSocket(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer()
    {
            this.start();
    }

    public void stopServer()
    {
        this.running = false;
        this.interrupt();
    }

    @Override
    public void run(){
        running = true;
        while( running ){
            try{

                System.out.println( "Listening for a connection ..." );

                Socket socket = serverSocket.accept();
                RequestHandler  requestHandler = new RequestHandler( socket );
                requestHandler.start();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
