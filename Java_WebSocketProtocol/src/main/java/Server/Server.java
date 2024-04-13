package Server;

import Server.Handlers.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket server;
    private ExecutorService pool;

    public Server(ServerSocket server) {
        this.server = server;
        this.pool = Executors.newCachedThreadPool();
    }

    public void start() {
        try {
            System.out.println("SERVER IS RUNNING!");
            while (!server.isClosed()) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                pool.execute(clientHandler);
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    private void shutdown() {
        if (server != null) {
            try {
                server.close();
            } catch (IOException ignored) {

            }
        }
        if (pool != null) {
            pool.shutdown();
        }
    }


    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Server server = new Server(serverSocket);
        server.start();
    }
}
