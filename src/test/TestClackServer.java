package test;
import main.ClackServer;

public class TestClackServer {
    private static final int server_num_1 = 1;

    private static final int server_num_2 = 2;

    private static final int copy_server_num = 3;

    public static void main(String[] args) {

        ClackServer first_server = new ClackServer(server_num_1);

        ClackServer second_server = new ClackServer(server_num_2);

        ClackServer copy_server = new ClackServer(copy_server_num);


        System.out.println("SERVER NUMBER: " + first_server.getPort());
        System.out.println("SERVER HASHCODE: " + first_server.hashCode());
        System.out.println(first_server);

    }
}
