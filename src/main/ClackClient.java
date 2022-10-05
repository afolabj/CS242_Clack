package main;
import data.*;
import java.util.Objects;

// the class represents the client user
public class ClackClient {
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;

    //constructor for username,hostname,port
    public ClackClient(String userName,String host_1, int port_1){
        this.userName = userName;
        this.hostName = host_1;
        this.port = port_1;
        this.closeConnection = false;
        this.dataToSendToServer = null;
        this.dataToReceiveFromServer = null;
    }

    //constructor sets port to default port number 7000
    public ClackClient(String userName, String host_1){
        this(userName,host_1,7000);
    }

    //constructor sets the host name to be "localhost
    public ClackClient(String userName){
        this(userName,"localhost");
    }

    //default constructor sets anonymous user
    public ClackClient(){
        this("unknown");
    }

    // Declarations of code doesn't have to return anything
    public void start(){}
    public void sendData(){}
    public void receiveData(){}
    public void printData(){}

    //return username
    public String getUserName(){
        return this.userName;
    }

    //return host name
    public String getHostName(){
        return this.hostName;
    }

    //return port
    public int getPort(){
        return this.port;
    }


    // Overridden functions
    public int hashCode(){
        return Objects.hash(userName,hostName,port,closeConnection,dataToSendToServer,dataToReceiveFromServer);
    }
    public boolean equals(Object obj){
        ClackClient objClackClient = (ClackClient) obj;
        return this.userName == objClackClient.userName &&
                this.hostName == objClackClient.hostName &&
                this.port == objClackClient.port &&
                this.closeConnection == objClackClient.closeConnection &&
                this.dataToSendToServer == objClackClient.dataToSendToServer &&
                this.dataToReceiveFromServer == objClackClient.dataToReceiveFromServer;
    }
    public String toString(){
        return "USERNAME: " + userName + "\n" +
               "HOSTNAME: " + hostName + "\n" +
               "PORT: " + port + "\n" +
               "CLOSE_CONNECTION:" + closeConnection + "\n" +
               "DATA_TO_SEND: " + dataToSendToServer + "\n" +
               "DATA_RECEIVE_FROM: " + dataToReceiveFromServer;
    }
}
