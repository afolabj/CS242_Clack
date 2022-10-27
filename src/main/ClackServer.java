package main;
import data.*;
import java.util.Objects;

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


    /**
     * The constructor that sets the port number.
     * Should set dataToReceiveFromClient and dataToSendToClient as null.
     *
     * @param port an int representing the port number on the server connected to
     */
    public ClackServer(int port){
        this.port = port;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
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
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public void start(){}

    /**
     * Receives and Sends data to client.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public void receiveData(){}
    public void sendData(){}

    public int getPort(){ return this.port;} //returns the port


    //Overridden functions
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
