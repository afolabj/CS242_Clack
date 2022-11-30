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

    private static final String KEY = "HELLO";  // The default key for encryption and decryption

    /**
     * The constructor to set up the username, host name, and port.
     * The connection should be set to be open (closeConnection = false).
     * Should set dataToSendToServer and dataToReceiveFromServer as null.
     *
     * @param userName a string representing the username of the client
     * @param hostName a string representing the host name of the server
     * @param port     an int representing the port number on the server connected to
     */
    public ClackClient(String userName, String hostName, int port) throws IllegalArgumentException {
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
    public ClackClient(String userName, String hostName) throws IllegalArgumentException {
        this(userName, hostName, DEFAULT_PORT);
    }

    /**
     * The constructor that sets the host name to be "localhost"
     * (i.e., the server and the client programs run on the same computer).
     * This constructor should call another constructor.
     *
     * @param userName a string representing the username of the client
     */
    public ClackClient(String userName) throws IllegalArgumentException {
        this(userName, "localhost");
    }

    /**
     * The default constructor that sets the anonymous user.
     * This constructor should call another constructor.
     */
    public ClackClient() throws IllegalArgumentException {
        this("Anon");
    }

    /**
     * Starts the client.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public void start() {
        try {
            Runnable countdownRunnable = new ClientSideServerListener(this);
            Thread countdownThread = new Thread(countdownRunnable);
            Socket skt = new Socket(this.hostName, this.port);
            this.inFromStd = new Scanner(System.in);
            this.outToServer = new ObjectOutputStream(skt.getOutputStream());
            this.inFromServer = new ObjectInputStream(skt.getInputStream());
            countdownThread.start();

            while (!this.closeConnection) {
                readClientData();
                sendData();
                if (this.closeConnection) {
                    break;
                }
            }
            this.inFromStd.close();
            this.outToServer.close();
            skt.close();
            this.inFromStd.close();

        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        } catch (SecurityException se) {
            System.err.println("Security Exception occurred");
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal Argument Exception occured");
        }
    }

    /**
     * Reads the data from the client.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public void readClientData() {
        String input = this.inFromStd.next();
        if (input.equals("DONE")) {
            this.closeConnection = true;
            this.dataToSendToServer = new MessageClackData(this.userName, input, KEY,
                    ClackData.CONSTANT_LOGOUT);
        } else if (input.contains("SENDFILE")) {
            String fileName = this.inFromStd.next();
            this.dataToSendToServer = new FileClackData(this.userName, fileName, ClackData.CONSTANT_SENDFILE);
            try {
                ((FileClackData) this.dataToSendToServer).readFileContents(KEY);
            } catch (IOException ioe) {
                System.err.println("IO exception occurred");
                this.dataToSendToServer = null;
            }

        } else if (input.equals("LISTUSERS")) {
        } else {
            String message = input + this.inFromStd.nextLine();
            this.dataToSendToServer = new MessageClackData(this.userName, message, KEY, ClackData.CONSTANT_SENDMESSAGE);
        }
    }

    /**
     * Prints the received data to the standard output.
     */
    public void printData() {
        if (this.dataToReceiveFromServer != null){
            System.out.println("Data: " + this.dataToReceiveFromServer.getData(KEY));
            System.out.println("Date: " + this.dataToReceiveFromServer.getDate());
            System.out.println("From: " + this.dataToReceiveFromServer.getUserName());
    }}


    /**
     * writes out the ClackData object
     * Catches all relevant exception objects and prints out
     * messages to standard errors in the event of exceptions.
     */
    public void sendData() {
        try{
            this.outToServer.writeObject(this.dataToSendToServer);
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
            this.dataToReceiveFromServer = (ClackData) this.inFromServer.readObject();
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

    public boolean getCloseConnection(){
        return this.closeConnection;
    }

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

    /**
     * Main Method that uses command line arguments
     * to create a new ClackClient Object and
     * starts ClackClient object
     */
    public static void main(String args[]) {
        ClackClient clackClient;
        if (args.length == 0) {
            clackClient = new ClackClient();
        }
        else {
            Scanner scanner = new Scanner(args[0]);
            scanner.useDelimiter("[@:]");
            String userName = scanner.next();
            if (!scanner.hasNext()) {
                clackClient = new ClackClient(userName);
            } else {
                String hostName = scanner.next();
                if (!scanner.hasNext()) {
                    clackClient = new ClackClient(userName, hostName);
                } else {
                    int port = scanner.nextInt();
                    clackClient = new ClackClient(userName, hostName, port);
                }
            }
            scanner.close();
        }
        clackClient.start();
        }
    }


