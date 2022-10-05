package data;
import java.util.*;

public class MessageClackData extends ClackData{
    private String message;

    //constructor that sets up username, message and type
    public MessageClackData(String userName, String message_1, int type_1){
        super(userName, type_1);
        this.message = message_1;
    }

    public MessageClackData(){super();}  //default constructor
    public String getData(){return message;}

    // Overridden functions
    public int hashCode(){ return Objects.hash(message);}
    public boolean equals(Object obj){
        MessageClackData objMessageClackData = (MessageClackData) obj;
        return this.message == objMessageClackData.message;
    }
    //Overriding the toString function
    public String toString(){
        return "MESSAGE: " + this.message + "\n" +
               "USERNAME: " + super.username + "\n" +
               "TYPE: " + super.getType() + "\n" +
               "DATE: " + super.getDate();
    }
}

