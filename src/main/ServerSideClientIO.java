package main;
import data.*;
import java.io.*;
import java.net.*;


public class ServerSideClientIO implements Runnable{
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    /**
     * constructor that takes ClackServer object
     * and Socket object as parameters.
     * sets closeConnection to false and
     * sets all other variables to null
     *
     * @param server
     * @param clientSocket
     */
    public ServerSideClientIO(ClackServer server, Socket clientSocket){
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
        this.server = server;
        this.clientSocket = clientSocket;
    }

    /**
     * Sends data to client.
     * Does not return anything.
     * Must catch all relevant exceptions separately and
     * print out messages to standard error for each exception.
     */
    public void sendData(){
        try {
            this.outToClient.writeObject(this.dataToSendToClient);
        } catch (InvalidClassException ice) {
            System.err.println("Invalid Class Exception occurred");
        } catch (NotSerializableException nse) {
            System.err.println("Not Serializable Exception occurred");
        } catch (IOException ioe) {
            System.err.println("IO Exception occurred");
        }
    }

    //returns username
    public String getUserName() {
        if (dataToReceiveFromClient == null) {
            return "";
        }
        return dataToReceiveFromClient.getUserName();
    }

    /**
     * a simple mutator that sets the variable in
     * this class
     *
     * @param dataToSendToClient
     */
    public ClackData setDataToSendToClient(ClackData dataToSendToClient){
        return this.dataToSendToClient = dataToSendToClient;
    }

    /**
     * Receives data from client.
     * Does not return anything.
     * This method should determine if the connection is to be closed.
     * Must catch all relevant exceptions separately and
     * print out messages to standard error for each exception.
     */
    public void receiveData(){
        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();

            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                this.closeConnection = true;
                this.server.remove(this);
            }

            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS) {
                this.server.sendUsersList();
            }
        } catch (ClassNotFoundException cnf) {
            System.err.println("Class Not Found");
        } catch (InvalidClassException ice) {
            System.err.println("Invalid Class");
        } catch (StreamCorruptedException sce) {
            System.err.println("Stream Corrupted");
        } catch (OptionalDataException ode) {
            System.err.println("Optional Data Exception occurred");
        } catch (IOException ioe) {
            System.err.println("IOException occurred");
        }
    }

    @Override
    public void run(){
        try {
            this.inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            this.outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

            while (!this.closeConnection) {
                this.receiveData();

                if (this.dataToReceiveFromClient.getType() != ClackData.CONSTANT_LISTUSERS &&
                        this.dataToReceiveFromClient.getType() != ClackData.CONSTANT_LOGOUT) {
                    server.broadcast(dataToReceiveFromClient);
                }
            }
            this.outToClient.close();
            this.inFromClient.close();

        } catch (IOException ioe) {
            System.err.println("IO exception occurred");
        } catch (SecurityException se) {
            System.err.println("Security Exception occurred");
        } catch (IllegalArgumentException iae) {
            System.err.println("Illegal Argument Exception occurred");
        }
    }
}