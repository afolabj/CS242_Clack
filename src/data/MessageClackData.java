package data;
import java.util.*;
import java.io.*;


/**
 * The child of ClackData, whose data is a message
 */
public class MessageClackData extends ClackData{
    private String message;

    /**
     * The constructor to set up the instance variables username, message, and type.
     * Should call the super constructor.
     *
     * @param userName a string representing the name of the client user
     * @param message  a string representing instant message
     * @param type     an int representing the data type
     */
    public MessageClackData(String userName, String message, int type){
        super(userName, type);
        this.message = message;
    }

    /**
     * The default constructor.
     * This constructor should call another constructor.
     */
    public MessageClackData(){
        super(ClackData.CONSTANT_SENDMESSAGE);
        this.message = "";
    }

    public MessageClackData(String userName, String message, String key, int type){}

    public String getData(){return this.message;} //returns instant message

    // Overridden functions
    @Override
    public int hashCode(){ return Objects.hash(this.message, this.type, this.userName);}
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessageClackData)) {
            return false;
        }

        MessageClackData objMessageClackData = (MessageClackData) obj;
        return this.message == objMessageClackData.message
               && this.userName == objMessageClackData.userName
               && this.type == objMessageClackData.type;
    }
    //Overriding the toString function
    @Override
    public String toString(){
        return "MESSAGE: " + this.message + "\n" +
               "USERNAME: " + this.userName + "\n" +
               "TYPE: " + this.type + "\n" +
               "DATE: " + this.date.toString();
    }
}

