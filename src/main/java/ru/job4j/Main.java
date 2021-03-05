package ru.job4j;

import ru.job4j.client.PoohClientSocket;
import ru.job4j.server.PoohServerSocket;

public class Main {
    public static void main( String[] args )
    {
        String server = "localhost";
        int port = 8081;
        if( args[0].equals("server") )
        {
            System.out.println( "Start server...");

            PoohServerSocket poohServerSocket = new PoohServerSocket(port);
            poohServerSocket.startServer();

            try {
                Thread.sleep( 60000 );
                poohServerSocket.stopServer();
            } catch( Exception e ) {
                e.printStackTrace();
            }

        }else if( args[0].equals("client") ){

            System.out.println("Start client...");
            PoohClientSocket poohClientSocket = new PoohClientSocket(server, port);
            poohClientSocket.startClient();

        } else {
            System.out.println("Wrong argument");
            System.exit(0);
        }


    }
}
