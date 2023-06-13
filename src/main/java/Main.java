public class Main {
    public static void main(String[] args) {
        Server server = new Server(8081);
        Client client = new Client("netology.homework", 8081);
        Thread servers = new Thread(server);
        Thread clients = new Thread(client);
        servers.start();
        clients.start();
    }
}
