package test;
import main.ClackServer;

/**
 * TestClackServer
 */

public class TestClackServer {

    private static final int FIRST_SERVER_NUM = 1;

    private static final int SECOND_SERVER_NUM = 2;

//    private static final int COPY_SERVER_NUM = 3;

    public static void main(String[] args) {

        ClackServer first_server = new ClackServer(FIRST_SERVER_NUM);
        ClackServer second_server = new ClackServer();
        ClackServer server = new ClackServer(SECOND_SERVER_NUM);

        System.out.println("Comparing cs_primary_constructor to cs2: " + first_server.equals(server));
        System.out.println("Comparing cs_primary_constructor to cs_primary_constructor: " + first_server.equals(first_server));

        System.out.println("Prints cs_primary_constructor port number: " + first_server.getPort());
        System.out.println("Prints cs_primary_constructor hashcode: " + first_server.hashCode());

        System.out.println(first_server+"\n");

        System.out.println(second_server+"\n");


//        ClackServer copy_server = new ClackServer(COPY_SERVER_NUM); part_1


        System.out.println("SERVER NUMBER: " + first_server.getPort());
        System.out.println("SERVER HASHCODE : " + first_server.hashCode());
        System.out.println(first_server);
    }





//    public static void main(String[] args) {
//        // All three constructors of ClackServer
//        ClackServer clackServer1 = new ClackServer(12345);
//        ClackServer clackServer2 = new ClackServer(7000);
//        ClackServer clackServer3 = new ClackServer();
//        ClackServer clackServer4 = new ClackServer(-99);
//
//        // getPort()
//        System.out.println("clackServer1 getPort(): " + clackServer1.getPort());
//        System.out.println("clackServer2 getPort(): " + clackServer2.getPort());
//        System.out.println("clackServer3 getPort(): " + clackServer3.getPort());
//        System.out.println("clackServer4 getPort(): " + clackServer4.getPort());
//        System.out.println();
//
//        // hashCode()
//        System.out.println("clackServer1 hashCode(): " + clackServer1.hashCode());
//        System.out.println("clackServer2 hashCode(): " + clackServer2.hashCode());
//        System.out.println("clackServer3 hashCode(): " + clackServer3.hashCode());
//        System.out.println("clackServer4 hashCode(): " + clackServer4.hashCode());
//        System.out.println();
//
//        // equals()
//        System.out.println("clackServer1 equals null: " + clackServer1.equals(null)); // false
//        System.out.println("clackServer1 equals clackServer1: " + clackServer1.equals(clackServer1)); // true
//        System.out.println("clackServer1 equals clackServer2: " + clackServer1.equals(clackServer2)); // true
//        System.out.println("clackServer2 equals clackServer1: " + clackServer2.equals(clackServer1)); // true
//        System.out.println("clackServer1 equals clackServer3: " + clackServer1.equals(clackServer3)); // false
//        System.out.println("clackServer3 equals clackServer1: " + clackServer3.equals(clackServer1)); // false
//        System.out.println("clackServer1 equals clackServer4: " + clackServer1.equals(clackServer4)); // false
//        System.out.println();
//
//        // toString()
//        System.out.println("clackServer1:\n" + clackServer1);
//        System.out.println("clackServer2:\n" + clackServer2);
//        System.out.println("clackServer3:\n" + clackServer3);
//        System.out.println("clackServer4:\n" + clackServer4);
//    }
}