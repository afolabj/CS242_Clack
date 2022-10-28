package data;

import java.util.*;
import java.io.*;
import java.net.*;



/**
 * Class ClackData is a superclass that represents the data sent between the client and the
 * server. An object of type ClackData consists of the username of the client user, the date
 * and time at which the data was sent and the data itself, which can either be a message
 * (MessageClackData) or the name and contents of a file (FileClackData). Note that ClackData
 * should not be instantiable.
 *
 */
public abstract class ClackData implements Serializable {

    protected String userName; //name of client user
    protected int type;
    protected Date date; //date when ClackData object was created

    /**
     * For giving a listing of all users connected to this session.
     */
    public static final int CONSTANT_LISTUSERS = 0;

    /**
     * For logging out, i.e., close this client's connection.
     */
    public static final int CONSTANT_LOGOUT = 1;

    /**
     * For sending a message.
     */
    public static final int CONSTANT_SENDMESSAGE = 2;

    /**
     * For sending a file.
     */
    public static final int CONSTANT_SENDFILE = 3;

    private final String upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String lowerLetters = "abcdefghijklmnopqrstuvwxyz";

    /**
     * The constructor to set up the instance variable username and type.
     * The instance variable date should be created automatically here.
     *
     * @param userName a string representing the name of the client user
     * @param type     an int representing the data type
     */
    public ClackData(String userName, int type){
        this.userName = userName;
        this.type = type;
        this.date = new Date();
    }

    /**
     * The constructor to create an anonymous user, whose name should be "Anon".
     * This constructor should call another constructor.
     *
     * @param type an int representing the data type
     */
    public ClackData(int type){this("Anon", type);}

    /**
     * The default constructor.
     * This constructor should call another constructor.
     * type should get defaulted to CONSTANT_LOGOUT.
     */
    public ClackData(){this(CONSTANT_LOGOUT);}
    public int getType(){return this.type;} //returns the type
    public String getUserName(){return this.userName;} //returns the username
    public Date getDate(){return this.date;} //returns the date


    /**
     * The abstract method to return the data contained in this class
     * (contents of instant message or contents of a file).
     *
     * @return data
     */
    public abstract String getData(String key);


    public abstract String getData();


    /**
     * this method takes in an input string to encrypt using a key, and outputs the encrypted string
     * @param inputStringToEncrypt
     * @param key
     * @return encrypted string using a key
     */
    protected String encrypt(String inputStringToEncrypt, String key){
        String encryptedString = "";
        char[] addKey = new char[inputStringToEncrypt.length()];
        int lenKey = key.length();

        int Space = 0;
        for (int i = 0 ; i < inputStringToEncrypt.length(); i++) {
            if (Character.isLetter(inputStringToEncrypt.charAt(i)))
                addKey[i] = key.charAt((i - Space) % lenKey);
            else {
                addKey[i] = inputStringToEncrypt.charAt(i);
                Space++;
            }}

        Space = 0;
        for (int i = 0; i < inputStringToEncrypt.length(); i++) {
            boolean flag = false;
            int keyCharPos, inputCharPos;
            if (Character.isLetter(inputStringToEncrypt.charAt(i))) {
                keyCharPos = upperLetters.indexOf(addKey[i]);
                inputCharPos = lowerLetters.indexOf(inputStringToEncrypt.charAt(i));
                if (inputCharPos == -1) {
                    inputCharPos = upperLetters.indexOf(inputStringToEncrypt.charAt(i));
                    flag = true;
                }
                int encryptedCharIndex = (inputCharPos + keyCharPos) % 26;
                if (flag)
                    encryptedString = encryptedString +  upperLetters.charAt(encryptedCharIndex);
                else
                    encryptedString = encryptedString + lowerLetters.charAt(encryptedCharIndex);
            } else {
                encryptedString = encryptedString + inputStringToEncrypt.charAt(i);
                Space++;
            }
        }
        return encryptedString;
    }


    /**
     * this method takes in an input string to decrypt using a key, and outputs the decrypted string
     * @param inputStringToDecrypt
     * @param key
     * @return decrypted string using a key
     */
    protected String decrypt(String inputStringToDecrypt, String key) {
        String decryptedString = "";
        char[] addKey = new char[inputStringToDecrypt.length()];
        int lenKey = key.length();


        int Space = 0;
        for (int i = 0; i < inputStringToDecrypt.length(); i++) {
            if (Character.isLetter(inputStringToDecrypt.charAt(i)))
                addKey[i] = key.charAt((i - Space) % lenKey);
            else {
                addKey[i] = inputStringToDecrypt.charAt(i);
                Space++;
            }}
        for (int i = 0; i < inputStringToDecrypt.length(); i++) {
            boolean flagStatus = false;
            int keyCharPos, inputCharPos;
            if (Character.isLetter(inputStringToDecrypt.charAt(i))) {
                keyCharPos = upperLetters.indexOf(addKey[i]);
                inputCharPos = lowerLetters.indexOf(inputStringToDecrypt.charAt(i));
                if (inputCharPos == -1) {
                    inputCharPos = upperLetters.indexOf(inputStringToDecrypt.charAt(i));
                    flagStatus = true;
                }
                int decryptedCharIndex = (inputCharPos - keyCharPos + 26) % 26;
                if (flagStatus)
                    decryptedString = decryptedString + upperLetters.charAt(decryptedCharIndex);
                else
                    decryptedString = decryptedString + lowerLetters.charAt(decryptedCharIndex);
            } else {
                decryptedString = decryptedString +  inputStringToDecrypt.charAt(i);
                Space++;
            }}
        return decryptedString;
    }
}
