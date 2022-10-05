package data;
import java.util.*;
//the class represents the data between the client and the server

public abstract class ClackData {
    protected String username; //name of client user
    protected int type;
    protected Date date; //date when ClackData object was created

    public final int CONSTANT_LISTUSERS = 0;
    public final int CONSTANT_LOGOUT = 1;
    public final int CONSTANT_SENDMESSAGE = 2;
    public final int CONSTANT_SENDFILE = 3;

    public ClackData(String userName, int type_1){
        this.username = userName;
        this.type = type_1;
        this.date = new Date();
    }

    public ClackData(int type_1){this("Anon", type_1);}  //constructor calling another constructor
    public ClackData(){this(-1);}  //default constructor
    public int getType(){return this.type;}
    public String getUserName(){return this.username;}
    public Date getDate(){return this.date;}

    public abstract String getData();  //Abstract Method
}
