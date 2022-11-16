package main;

import data.*;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 * The ClackClient class represents the client user. A ClackClient object contains the username,
 * host name of the server connected to, port number connected to, and a boolean designating
 * whether the connection is open or not. The ClackClient object will also have two ClackData
 * objects representing data sent to the server and data received from the server.
 */
public class ClackClient {
    private static final int DEFAULT_PORT = 7000;  // The default port number

    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    private Scanner inFromStd;
    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;

    private static final String KEY = "HELLO";

    /**
     * The constructor to set up the username, host name, and port.
     * The connection should be set to be open (closeConnection = false).
     * Should set dataToSendToServer and dataToReceiveFromServer as null.
     *
     * @param userName a string representing the username of the client
     * @param hostName a string representing the host name of the server
     * @param port     an int representing the port number on the server connected to
     */
    public ClackClient(String userName, String hostName, int port) {
        if (userName == null) {
            throw new IllegalArgumentException("please change username from null");
        }
        if (hostName == null) {
            throw new IllegalArgumentException("please change hostname from null");
        }
        if (port < 1024) {
            throw new IllegalArgumentException("please make port must be greater than 1024");
        }

        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = false;
        this.dataToSendToServer = null;
        this.dataToReceiveFromServer = null;
        this.inFromServer = null;
        this.outToServer = null;
    }

    /**
     * The constructor to set up the port to the default port number 7000.
     * The default port number should be set up as constant (e.g., DEFAULT_PORT).
     * This constructor should call another constructor.
     *
     * @param userName a string representing the username of the client
     * @param hostName a string representing the host name of the server
     */
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, DEFAULT_PORT);
    }

    /**
     * The constructor that sets the host name to be "localhost"
     * (i.e., the server and the client programs run on the same computer).
     * This constructor should call another constructor.
     *
     * @param userName a string representing the username of the client
     */
    public ClackClient(String userName) {
        this(userName, "localhost");
    }

    /**
     * The default constructor that sets the anonymous user.
     * This constructor should call another constructor.
     */
    public ClackClient() {
        this("Anon");
    }

    /**
     * Starts the client.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public void start() {
        try {
            Socket skt = new Socket(this.hostName, this.port);
//            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(skt.getInputStream()));
//            PrintWriter outFromClient  = new PrintWriter(skt.getOutputStream(),true);

            inFromStd = new Scanner(System.in);
            while (!closeConnection) {
                    this.readClientData();
                    this.sendData();
                    this.receiveData();
                    this.printData();
                    dataToReceiveFromServer = dataToSendToServer;
            }
            inFromStd.close();
            skt.close();
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        }
    }

    /**
     * Reads the data from the client.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public void readClientData() {
        String fileName;
        String input = inFromStd.nextLine();
        if (input.equals("DONE")) {
            this.closeConnection = true;
        } else if (input.contains("SENDFILE")) {
            fileName = input.substring(9, input.length());
            this.dataToSendToServer = new FileClackData(this.userName, fileName, ClackData.CONSTANT_SENDFILE);
            try {
                ((FileClackData) this.dataToSendToServer).readFileContents(KEY);
            } catch (IOException ioe) {
                this.dataToSendToServer = null;
                System.err.println("IO exception occurred");
            }
        } else if (input.equals("LISTUSERS")) {
        } else {
            this.dataToSendToServer = new MessageClackData(this.userName, input, KEY, ClackData.CONSTANT_SENDMESSAGE);
        }
    }

    /**
     * Prints the received data to the standard output.
     */
    public void printData() {
        if (this.dataToReceiveFromServer == null) return;

        System.out.println("Data : " + this.dataToReceiveFromServer.getData());
        System.out.println("Date and time : " + this.dataToReceiveFromServer.getDate());
        System.out.println("from : " + this.dataToReceiveFromServer.getUserName());
    }


    /**
     * writes out the ClackData object
     * Catches all relevant exception objects and prints out
     * messages to standard errors in the event of exceptions.
     */
    public void sendData() {
        try{
            outToServer.writeObject(this.dataToSendToServer);
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        }
    }

    /**
     *  Reads in a ClackData object from the ObjectInputStream
     * Catches all relevant exception objects and prints
     * out messages to standard errors in the event of exceptions.
     */
    public void receiveData() {
        try{
            this.dataToReceiveFromServer = (ClackData) inFromServer.readObject();
        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        } catch (ClassNotFoundException cnf) {
            System.err.println ("Class not found");
        }
    }

    public String getUserName() {
        return this.userName;
    } //returns username
    public String getHostName() {
        return this.hostName;
    } //return host name
    public int getPort() {
        return this.port;
    } //return port

    /**
     * Overridden functions
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.userName, this.hostName, this.port, this.closeConnection, this.dataToSendToServer, this.dataToReceiveFromServer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClackClient)) {
            return false;
        }

        ClackClient objClackClient = (ClackClient) obj;
        return this.userName == objClackClient.userName &&
                this.hostName == objClackClient.hostName &&
                this.port == objClackClient.port &&
                this.closeConnection == objClackClient.closeConnection &&
                this.dataToSendToServer == objClackClient.dataToSendToServer &&
                this.dataToReceiveFromServer == objClackClient.dataToReceiveFromServer;
    }

    @Override
    public String toString() {
        return "USERNAME: " + this.userName + "\n" +
                "HOSTNAME: " + this.hostName + "\n" +
                "PORT: " + this.port + "\n" +
                "CONNECTION STATUS:" + (this.closeConnection ? "Closed" : "Open") + "\n" +
                "DATA_TO_SEND: " + this.dataToSendToServer + "\n" +
                "DATA_RECEIVE_FROM: " + this.dataToReceiveFromServer;
    }
}
