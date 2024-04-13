package Server;


import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private ServerSocket server;
    private ExecutorService poll;

    public Server(ServerSocket server) {
        this.server = server;
        poll = Executors.newCachedThreadPool();
    }
    //https://stackoverflow.com/questions/46018534/how-to-get-the-number-of-tasks-in-a-queue-in-executor-service
    //how to get number of tasks in poll queue?

    public void start() {
        try {
            System.out.println("SERVER is running!");
            while (!server.isClosed()) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                poll.execute(clientHandler);
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    private void shutdown() {
        try {
            if (server != null) {
                server.close();
            }
            if (poll != null) {
                poll.shutdown();
            }
        } catch (IOException ignored) {
            System.out.println("Something went terribly wrong!");
        }
    }



    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.start();
    }
}
