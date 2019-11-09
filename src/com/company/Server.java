package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {
    ArrayList<ClientHandler> printerConnections =  new ArrayList<>();
    ArrayList<String> messages =  new ArrayList<>();
    public static void main(String[] args) {
       new Server();
    }

    public Server() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(9876);
            server.setReuseAddress(true);
            while (true) {
                System.out.println("Waiting for client connections");
                Socket client = server.accept();
                System.out.println("New client connected " + client.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(client, this);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}