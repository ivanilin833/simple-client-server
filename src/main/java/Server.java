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
                    writer.println("Write your name");
                    String name = reader.readLine();
                    writer.println("Are you child");
                    String answer = reader.readLine().trim();
                    if (answer.equals("yes")){
                        writer.println(String.format("Welcome to the kids area, %s ! Let's play!", name));
                    }else if (answer.equals("no")){
                        writer.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    } else {
                        writer.println("Wrong answer. Sorry :(");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
