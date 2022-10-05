package test;
import main.ClackClient;

public class TestClackClient {

    private static final String client_user_1 = "client_user_1";
    private static final String client_hostname_1 = "client_hostname_1";
    private static final int client_port_1 = 1;

    private static final String client_user_2 = "client_user_2";
    private static final String client_hostname_2 = "client_hostname_2";
    private static final int client_port_2 = 2;


    private static final String client_copy = "client_copy";
    private static final String hostname_copy = "hostname_copy";
    private static final int port_copy = 3;

    public static void main(String[] args) {

        ClackClient first_client = new ClackClient(client_user_1,client_hostname_1, client_port_1);
        ClackClient second_client = new ClackClient(client_user_2, client_hostname_2, client_port_2);
        ClackClient copy_client = new ClackClient(client_copy,hostname_copy, port_copy);

        System.out.println("first client name: " + first_client.getUserName());
        System.out.println("first client hostname: " + first_client.getHostName());
        System.out.println("first client port: "+ first_client.getPort());
        System.out.println("first client hashcode: " + first_client.hashCode());
        System.out.println(first_client);
    }
}
