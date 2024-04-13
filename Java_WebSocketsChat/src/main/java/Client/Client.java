package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.username = username;
            out = new BufferedWriter( new OutputStreamWriter(this.socket.getOutputStream()));
            in = new BufferedReader( new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            shutdown();
        }
    }

    public void sendMessage() {
        try {
            //initial message from client
            out.write(username);
            out.newLine();
            out.flush();

            Scanner sc =  new Scanner(System.in);
            while (socket.isConnected() && !socket.isClosed()) {
                String message = sc.nextLine();
                out.write(message);
                out.newLine();
                out.flush();
                if (message.equals("/q")) {
                    shutdown();
                }
            }

        } catch (IOException e) {
            shutdown();
        }
    }

    public void listenForMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;

                while (socket.isConnected() && !socket.isClosed()) {
                    try {
                        message = in.readLine();
                        if (message == null) {
                            System.out.println("LOL");
                            shutdown();
                            break;
                        }
                        System.out.println(message);
                    } catch (IOException e) {
                        shutdown();
                    }
                }
            }
        }).start();
    }

    private void shutdown() {
        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went terribly wrong!");
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username!");
        String username = sc.nextLine();
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username);
        client.listenForMessages();
        client.sendMessage();
    }
}
