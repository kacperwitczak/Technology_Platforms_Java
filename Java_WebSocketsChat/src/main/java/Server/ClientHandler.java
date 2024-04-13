package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader in;
    private BufferedWriter out;
    private String clientUsername;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();

    public ClientHandler(Socket client) {
        try {
            this.client = client;
            this.in =  new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            this.clientUsername = in.readLine();
            clients.add(this);
            System.out.println("New connection from: " + client.getInetAddress().getHostAddress());
            sendMessageForClients(this.clientUsername + " joined the chat!");
            sendNotification("You joined the chat!");
        } catch (IOException e) {
            shutdown();
        }
    }



    @Override
    public void run() {
        String message;

        while (!client.isClosed()) {
            try {
                message = in.readLine();
                if (message.equals("/q")) {
                    shutdown();
                    break;
                } else {
                    sendMessageForClients(this.clientUsername + ": " + message);
                }
            } catch (IOException e) {
                shutdown();
                break;
            }

        }
    }

    public void shutdown() {
        removeClientHandler();
        try {
            this.client.close();
            this.in.close();
            this.out.close();
        } catch (IOException e ) {
            e.printStackTrace();
            System.out.println("Something went terribly wrong!");
        }
        System.out.println("User left the chat: " + client.getInetAddress().getHostAddress());
    }

    private void removeClientHandler() {
        clients.remove(this);
        sendMessageForClients(this.clientUsername +  " left the chat!");
    }

    private void sendMessageForClients(String message) {
        for (var c : clients) {
            try {
                if (!c.clientUsername.equals(this.clientUsername)) {
                    c.out.write(message);
                    c.out.newLine();
                    c.out.flush();
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }

    private void sendNotification(String message) throws IOException {
        this.out.write(message);
        this.out.newLine();
        this.out.flush();
    }
}
