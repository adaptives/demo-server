package com.diycomputerscience.legacy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Server {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress("localhost", 5000));
            System.out.println("Server started, waiting for connections..");
            ExecutorService executer = Executors.newFixedThreadPool(10);
            while (true) {
                Socket client = server.accept();
                System.out.println("Client connection received from: " + client.getInetAddress().getHostName() + ":" + client.getPort());
                executer.submit(new ProtocolHandler(client));
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
