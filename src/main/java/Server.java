import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Server start");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.printf("New connection accepted %s port \n", clientSocket.getPort());
                    final String msg = reader.readLine();
                    writer.println(String.format("Answer on %s, from the port: %d", msg, clientSocket.getPort()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
