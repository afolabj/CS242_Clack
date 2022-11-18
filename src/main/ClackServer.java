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
    private static final int DEFAULT_PORT = 7000;  // The default port number

    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inputClient;
    private ObjectOutputStream outputClient;


    /**
     * The constructor that sets the port number.
     * Should set dataToReceiveFromClient and dataToSendToClient as null.
     *
     * @param port an int representing the port number on the server connected to
     */
    public ClackServer(int port) throws IllegalArgumentException{
        if(port < 1024){
            throw new IllegalArgumentException("please make port must be greater than 1024");
        }
        this.port = port;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inputClient = null;
        this.outputClient = null;
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
     * Starts the server.
     * Gets connections from the client
     */
    public void start(){
        try {
            ServerSocket skt = new ServerSocket(this.port);
            while (!closeConnection) {
                Socket clientSocket = skt.accept();
                outputClient = new ObjectOutputStream(clientSocket.getOutputStream());
                this.sendData();
                this.receiveData();
            }
            skt.close();
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        }
    }

    /**
     * Reads in a ClackData object from the ObjectInputStream
     * And determines if the connection is to be closed
     * Catches all relevant exception objects
     */

    public void receiveData(){
        try{
            this.dataToReceiveFromClient = (ClackData) inputClient.readObject();
            if(this.dataToReceiveFromClient.getType() == 1)
                closeConnection = !closeConnection;
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        } catch (ClassNotFoundException cnf) {
            System.err.println ("Class not found");
        }
        this.dataToSendToClient = this.dataToReceiveFromClient;
    }

    /**
     * Writes out the ClackData object and
     * Catches all relevant exception objects
     */
    public void sendData(){
        try{
            outputClient.writeObject(this.dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        }
    }

    public int getPort(){ return this.port;} //returns the port


    /**
     * Main Method that uses command line arguments
     * to create a new ClackServer Object and
     * starts ClackServer
     */
    public static void main(String args[]) {
        ClackServer server;
        if (args.length > 0) {
            final String input = args[0];
            server = new ClackServer(Integer.parseInt(input));
        } else {
            server = new ClackServer();
        }
        server.start();
    }


    /**
     * Overridden functions
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.port,this.closeConnection,this.dataToReceiveFromClient,this.dataToSendToClient);
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
        return this.port == objClackServer.port &&
                this.closeConnection == objClackServer.closeConnection &&
                this.dataToReceiveFromClient == objClackServer.dataToReceiveFromClient &&
                this.dataToSendToClient == objClackServer.dataToSendToClient;
    }
    @Override
    public String toString() {
        return "PORT:" + this.port + "\n" +
               "CONNECTION STATUS: " + (this.closeConnection ? "Closed" : "Open") + "\n" +
               "DATA_TO_SEND_SERVER: " + this.dataToSendToClient + "\n" +
               "DATA_TO_RECEIVE_FROM_SERVER:" + this.dataToReceiveFromClient;
    }
}
