import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try (Socket clientSocket = new Socket(this.host, this.port);
                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                writer.println("Message from client " + i);
                System.out.println(reader.readLine());
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
