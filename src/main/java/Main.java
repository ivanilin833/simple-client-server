public class Main {
    public static void main(String[] args) {
        Server server = new Server(8081);
        Client client = new Client("127.0.0.1", 8081);
        Thread servers = new Thread(server);
        Thread clients = new Thread(client);
        servers.start();
        clients.start();
    }
}
