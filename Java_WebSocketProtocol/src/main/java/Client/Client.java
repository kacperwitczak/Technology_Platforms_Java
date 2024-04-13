package Client;

import Protocols.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int clientID;

    public Client (Socket socket) {
        try {
            this.socket = socket;
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            shutdown();
        }
    }

    public void communicate() {
        if (socket.isConnected() && !socket.isClosed()) {
            try {
                this.clientID = in.readInt();
                mySleep();
                System.out.println((String)in.readObject());
                mySleep();
                Random rand = new Random();
                int max = 10;
                int min = 1;
                Integer n = rand.nextInt((max - min) + 1) + min;;
                out.writeObject(n);
                out.flush();
                mySleep();

                System.out.println((String)in.readObject());
                mySleep();

                for (int i = 0; i<n;i++) {
                    Integer val = rand.nextInt(100000);
                    Message m = new Message(clientID, val.toString());
                    out.writeObject(m);
                    out.flush();
                    mySleep();

                }
                System.out.println((String)in.readObject());
                mySleep();

            } catch (IOException | ClassNotFoundException ignored ) {

            } finally {
                shutdown();
            }
        }
    }

    private void mySleep() {
        try {
            // Sleep for 1 second (1000 milliseconds)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle interruption if needed
            e.printStackTrace();
        }
    }

    private void shutdown() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException ignored ){

        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        Client client = new Client(socket);
        client.communicate();
    }
}
