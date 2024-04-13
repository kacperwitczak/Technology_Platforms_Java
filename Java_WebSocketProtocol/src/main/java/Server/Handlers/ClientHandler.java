package Server.Handlers;


import Protocols.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private static int nextClientID = 0;

    private String clientName;
    public ClientHandler(Socket client) {
        try {
            nextClientID++;
            this.client = client;
            this.out = new ObjectOutputStream(this.client.getOutputStream());
            this.in = new ObjectInputStream(this.client.getInputStream());
            System.out.println("New connection from: " + this.client.getInetAddress().getHostAddress());
        } catch (IOException e) {
            shutdown();
        }
    }

    @Override
    public void run() {
        try {
            out.writeInt(nextClientID);
            out.flush();
            out.writeObject("Ready!");
            out.flush();
            Integer n = (Integer)in.readObject();
            System.out.println("Get N: " + n.toString());
            out.writeObject("Ready for messages!");
            out.flush();
            for (int i = 0; i<n;i++) {
                Message m = (Message)in.readObject();
                System.out.println(m);
            }
            out.writeObject("Finished!");
            out.flush();
        } catch (IOException | ClassNotFoundException ignored ) {

        } finally {
            shutdown();
        }

    }

    public void shutdown() {
        try {
            client.close();
            in.close();
            out.close();
        } catch (IOException ignored ){

        }
    }

}
