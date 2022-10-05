package data;
import java.util.*;


public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    //Constructors
    public FileClackData(String userName, String file_1, int type_1){
        super(userName, type_1);
        fileName = file_1;
        fileContents = null;
    }
    public FileClackData(){ super();}

    //sets FileName and gets FileName
    public void setFileName(String file_1){this.fileName = file_1;}
    public String getFileName() {
        return fileName;
    }
    public String getData(){ return null;}

    // Declarations of code doesn't have to return anything
    public void readFileContents(){}
    public void writeFileContents(){}

    // Overridden functions
    public int hashCode(){return Objects.hash(fileName,fileContents);}
    public boolean equals(Object obj){
        FileClackData objFileClackData = (FileClackData) obj;
        return this.fileName == objFileClackData.fileName && this.fileContents == objFileClackData.fileContents;
    }
    public String toString(){
        return "FILENAME:" + fileName + "\n" +
               "FILE_CONTENTS:" + fileContents + "\n" +
               "USERNAME:" + super.username + "\n" +
               "DATE:" + super.getDate() + "\n" +
               "TYPE: " + super.getType();
    }
}
