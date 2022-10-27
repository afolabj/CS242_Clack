package data;
import java.util.*;
import java.io.*;
import java.io.IOException;

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

    /**
     * returns decrypted fileContents
     */
    public String getData(String key){ return decrypt(this.fileContents,key); }


    /**
     * Reads and Writes the file contents.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public void readFileContents() throws IOException {
        this.fileContents = "";
        try {
            File myFile = new File("src/test/" + this.fileName);
            BufferedReader br = new BufferedReader(new FileReader(myFile));
            String line;

            while ((line = br.readLine()) != EOS) {
                this.fileContents = this.fileContents +  line + "\n";
            }
            br.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Not found :  " + this.fileName + " (" + fnfe.getMessage() + ")");
        }
    }
    public void readFileContents(String key) throws IOException {
        this.fileContents = "";
        try {
            File myFile = new File("src/test/" + this.fileName);
            BufferedReader br = new BufferedReader(new FileReader(myFile));

            String line;

            while ( (line = br.readLine()) != EOS) {
                fileContents = fileContents + encrypt(line, key);
            }
            br.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Not found: " + this.fileName + "(" + fnfe.getMessage() + ")");
        }
    }

    public void writeFileContents(){
        try {
            File myFile = new File("src/test/" + this.fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));

            bw.write(this.fileContents);
            bw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Not found :  " + this.fileName + "(" + fnfe.getMessage() + ")");
        }
    }
    public void writeFileContents(String key){
        try {
            File myFile = new File("src/test/" + this.fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));

            bw.write(decrypt(this.fileContents, key));
            bw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Not found: " + this.fileName + " (" + fnfe.getMessage() + ")");
        }
    }


    /**
     * Overridden functions
     */
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
