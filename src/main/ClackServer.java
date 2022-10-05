package main;
import data.*;
import java.util.Objects;

public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;


    //Constructor that sets the port
    public ClackServer(int port1){
        this.port = port1;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
    }

    //default constructor that sets port to default 7000 number
    public ClackServer(){
        this(7000);
    }

    //Declarations of code doesn't have to return anything
    public void start(){}
    public void receiveData(){}
    public void sendData(){}

    //returns the port
    public int getPort(){
        return this.port;
    }

    //Overridden functions
    public int hashCode(){
        return Objects.hash(port,closeConnection,dataToReceiveFromClient,dataToSendToClient);
    }
    public boolean equals(Object obj){
        ClackServer objClackServer = (ClackServer) obj;
        return this.port == objClackServer.port &&
                this.closeConnection == objClackServer.closeConnection &&
                this.dataToReceiveFromClient == objClackServer.dataToReceiveFromClient &&
                this.dataToSendToClient == objClackServer.dataToSendToClient;
    }
    public String toString() {
        return "PORT:" + port + "\n" +
               "CLOSE_CONNECTION: " + closeConnection + "\n" +
               "DATA_TO_SEND_SERVER: " + dataToSendToClient + "\n" +
               "DATA_TO_RECEIVE_FROM_SERVER:" + dataToReceiveFromClient;
    }
}
