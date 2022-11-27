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

    public void sendData(){}

    /**
     * a simple mutator that sets the variable in
     * this class
     *
     * @param dataToSendToClient
     */
    public ClackData setDataToSendToClient(ClackData dataToSendToClient){
        return this.dataToSendToClient = dataToSendToClient;
    }
    public void receiveData(){}

    @Override
    public void run(){}
}
