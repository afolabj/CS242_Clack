package data;
import java.util.*;
import java.io.*;

/**
 * The child of ClackData, whose data is the name and contents of a file
 */
public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    /**
     * The constructor to set up the instance variables username, fileName, and type.
     * Should call the super constructor.
     * The instance variable fileContents should be initialized to be null.
     *
     * @param userName a string representing the name of the client user
     * @param fileName a string representing the name of a file
     * @param type     an int representing the data type
     */
    public FileClackData(String userName, String fileName, int type){
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    /**
     * The default constructor.
     * This constructor should call the super constructor.
     */
    public FileClackData(){
        super(ClackData.CONSTANT_SENDFILE);
        this.fileName = "";
        this.fileContents = null;
    }

    public void setFileName(String fileName){this.fileName = fileName;} //sets fileName
    public String getFileName() {
        return this.fileName;
    } //returns fileName
    public String getData(){ return this.fileContents; } //returns fileContents

    /**
     * Reads and Writes the file contents.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public void readFileContents(){}
    public void writeFileContents(){}

    // Overridden functions
    @Override
    public int hashCode(){ return Objects.hash(this.userName, this.type, this.fileName, this.fileContents); }
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FileClackData)) {
            return false;
        }

        FileClackData objFileClackData = (FileClackData) obj;
        return this.fileName == objFileClackData.fileName
               && this.fileContents == objFileClackData.fileContents
               && this.type == objFileClackData.type
               && this.userName == objFileClackData.userName;
    }

    @Override
    public String toString(){
        return "FILENAME:" + this.fileName + "\n" +
               "FILE_CONTENTS:" + this.fileContents + "\n" +
               "USERNAME:" + this.userName + "\n" +
               "DATE:" + this.date.toString() + "\n" +
               "TYPE: " + this.type;
    }
}
