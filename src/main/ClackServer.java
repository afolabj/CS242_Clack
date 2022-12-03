package main;

import data.*;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 * The ClackServer class is a blueprint for a ClackServer object that contains information about the
 * port number that clients connect to, a boolean representing whether the server needs to be
 * closed or not, and ClackData objects representing data sent to and received from the client. The
 * server class does not need to know the host name (as the server program runs on its own computer),
 * it just needs to know what port the clients connect to. In our application, all clients will connect
 * to a single port.
 */
public class ClackServer {
    private static final int DEFAULT_PORT = 8000;  // The default port number
    private static final String KEY = "HELLO";  // The default key for encryption and decryption
    private int port;
    private boolean closeConnection;
    private ArrayList<ServerSideClientIO> serverSideClientIOList;

    /**
     * The constructor that sets the port number.
     * Should set dataToReceiveFromClient and dataToSendToClient as null.
     *
     * @param port an int representing the port number on the server connected to
     * @throws IllegalArgumentException
     */
    public ClackServer(int port) throws IllegalArgumentException{
        if(port < 1024){
            throw new IllegalArgumentException("please make port must be greater than 1024");
        }
        this.port = port;
        this.closeConnection = false;
        this.serverSideClientIOList = new ArrayList<ServerSideClientIO>();
    }

    /**
     * The default constructor that sets the port to the default port number 7000.
     * The default port number should be set up as constant (e.g., DEFAULT_PORT).
     * This constructor should call another constructor.
     */
    public ClackServer(){
        this(DEFAULT_PORT);
    }

    /**
     * Starts the server to get connections from the client and echo the client's data.
     * Does not return anything.
     * Must catch all relevant exceptions separately and
     * print out messages to standard error for each exception.
     */
    public void start(){
        try {
            ServerSocket skt = new ServerSocket(this.port);

            while (!this.closeConnection) {
                Socket clientSocket = skt.accept();
                System.out.println("A client is connected");
                ServerSideClientIO ssc = new ServerSideClientIO(this, clientSocket);
                Runnable countdownRunnable = ssc;
                Thread countdownThread = new Thread(countdownRunnable);
                countdownThread.start();
                serverSideClientIOList.add(ssc);
            }
            skt.close();

        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        } catch (SecurityException se) {
            System.err.println("Security Exception occurred");
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal Argument Exception occurred");
        }
    }

    /**
     * A synchronized method that iterates through the list
     * For every object in the list, call the object’s setDataToSendToClient() method to set the
     * instance variable ‘dataToSendToClient’ in that object, and then call the object’s
     * sendData() method to force the object to send the data to the corresponding client
     *
     * @param dataToBroadcastToClients
     */
    public synchronized void broadcast(ClackData dataToBroadcastToClients){
        for (int i = 0; i < serverSideClientIOList.size(); i++) {
            serverSideClientIOList.get(i).setDataToSendToClient(dataToBroadcastToClients);
            serverSideClientIOList.get(i).sendData();
        }
    }

    /**
     * A synchronized method that takes in a single ServerSideClientIO
     * object, and removes this object from the list ‘serverSideClientIOList’
     *
     * @param serverSideClientToRemove
     */
    public synchronized void remove(ServerSideClientIO serverSideClientToRemove){
        serverSideClientIOList.remove(serverSideClientToRemove);
    }

    public int getPort(){ return this.port;} //returns the port



    public void sendUsersList() {
        String users = "";
        int user = 0;

        for (int i = 0; i < serverSideClientIOList.size(); i++)
        {
            String username =  serverSideClientIOList.get(i).getUserName();
            if (!username.isEmpty()) {
                user++;
                users = users + "Client " + user + ": " + serverSideClientIOList.get(i).getUserName() + "\n";
            }}
        System.out.println(users);

        ClackData userListData = new MessageClackData("Server",users,KEY,ClackData.CONSTANT_SENDMESSAGE);
        broadcast(userListData);
    }


    /**
     * Overridden functions
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.port,this.closeConnection);
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClackServer)) {
            return false;
        }

        ClackServer objClackServer = (ClackServer) obj;
        return this.port == objClackServer.port && this.closeConnection == objClackServer.closeConnection;
    }
    @Override
    public String toString() {
        return "PORT:" + this.port + "\n" +
               "CONNECTION STATUS: " + (this.closeConnection ? "Closed" : "Open");
    }

    /**
     * Main Method that uses command line arguments
     * to create a new ClackServer Object and
     * starts ClackServer
     */
    public static void main(String args[]) {
        ClackServer clackServer;
        if (args.length == 0) {
            clackServer = new ClackServer();
        } else {
            int port = Integer.parseInt(args[0]);
            clackServer = new ClackServer(port);
        }
        clackServer.start();
    }
}
