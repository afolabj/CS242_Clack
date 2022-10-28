package test;
import data.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *TestClackData
 */

public class TestClackData {

    //FileClackData first username
    private static final String FIRST_USERNAME = "FIRST_USERNAME";
    private static final String FIRST_FILENAME = "part2_document.txt";
    private static final String FIRST_SETFILENAME = "FIRST_SETFILENAME";
    private static final int FIRST_TEST = 1;

    //fileclackdata second username
    private static final String SECOND_USERNAME = "SECOND_USERNAME";
    private static final String SECOND_FILENAME = "SECOND_FILENAME";
    private static final int SECOND_TEST = 2;


    //MessageClackData user_1
    private static final String USER_1 = "USER_1";
    private static final String MESSAGE_1 = "MESSAGE_1";
    private static final String KEY = "HELLO";
    private static final int TYPE_1 = 1;

    //messageclackdata user_2
    private static final String USER_2 = "USER_2";
    private static final String MESSAGE_2 = "MESSAGE_2";
    private static final int TYPE_2 = 2;


    public static void main(String[] args) throws IOException, FileNotFoundException {

        //fileclackdata test
        FileClackData user_1 = new FileClackData(FIRST_USERNAME, FIRST_FILENAME, FIRST_TEST);
        FileClackData user_2 = new FileClackData(SECOND_USERNAME, SECOND_FILENAME, SECOND_TEST);

        // Test two:
        // FileClackData with encryption and decryption: readFileContents() and writeFileContents()
        // FileClackData without encryption and decryption: readFileContents(key) and writeFileContents(key)

        user_1.readFileContents(KEY);
        System.out.println("user_1 vs user_2  " + user_1.equals(user_2));
        System.out.println("\n");
        System.out.println("user_1 vs user_1 " + user_1.equals(user_1));
        System.out.println("\n");

        System.out.println("user_1 getDate: " + user_1.getDate());
        System.out.println("\n");
        System.out.println("user_1: " + user_1.getData(KEY));
        System.out.println("user_1 getFilename: " + user_1.getFileName());
        System.out.println("user_1 Type: " + user_1.getType());
        System.out.println("user_1 getFilename: " + user_1.getFileName());
        System.out.println("\n");


        System.out.println("user_1 hashcode  " + user_1.hashCode());
        System.out.println(user_1);
        System.out.println("\n");


        //messageclackdata test
        //data_with_string
        MessageClackData data_w_s = new MessageClackData(USER_1, MESSAGE_1, KEY, TYPE_1);

        //data_no_stirng
        MessageClackData data_n_s = new MessageClackData(USER_1, MESSAGE_1, TYPE_1);
        MessageClackData data_1 = new MessageClackData(USER_2, MESSAGE_2, TYPE_2);
        MessageClackData data_2 = new MessageClackData();

        System.out.println("data_no_string vs data_1" + data_n_s.equals(data_1));
        System.out.println("data_no_string vs data_no_string " + data_n_s.equals(data_n_s));
        System.out.println("data_no_string getDate " + data_n_s.getDate());
        System.out.println("message data_no_string " + data_n_s.getData(KEY));
        System.out.println("hashcode data_no_string" + data_n_s.hashCode());
        System.out.println("\n");


        System.out.println(data_n_s);
        System.out.println("\n");

        System.out.println(data_2);
        System.out.println("\n");

        System.out.println(data_w_s);
        System.out.println("\n");


        System.out.println("data_with_string getDate " + data_w_s.getData(KEY));
        System.out.println("message data-with-string " + data_w_s.getData(KEY));

    }








          //Part 1

//        // MessageClackData (both constructors)
//        MessageClackData messageClackData1 = new MessageClackData();
//        MessageClackData messageClackData2 =
//                new MessageClackData("testUser1", "testMessage", ClackData.CONSTANT_SENDMESSAGE);
//
//        // FileClackData (both constructors)
//        FileClackData fileClackData1 = new FileClackData();
//        FileClackData fileClackData2 =
//                new FileClackData("testUser2", "filename0", ClackData.CONSTANT_SENDFILE);
//
//        // getType()
//        System.out.println("messageClackData1 getType(): " + messageClackData1.getType());
//        System.out.println("messageClackData2 getType(): " + messageClackData2.getType());
//        System.out.println("fileClackData1 getType(): " + fileClackData1.getType());
//        System.out.println("fileClackData2 getType(): " + fileClackData2.getType());
//        System.out.println();
//
//        // getUserName()
//        System.out.println("messageClackData1 getUserName(): " + messageClackData1.getUserName());
//        System.out.println("messageClackData2 getUserName(): " + messageClackData2.getUserName());
//        System.out.println("fileClackData1 getUserName(): " + fileClackData1.getUserName());
//        System.out.println("fileClackData2 getUserName(): " + fileClackData2.getUserName());
//        System.out.println();
//
//        // getDate()
//        System.out.println("messageClackData1 getDate(): " + messageClackData1.getDate());
//        System.out.println("messageClackData2 getDate(): " + messageClackData2.getDate());
//        System.out.println("fileClackData1 getDate(): " + fileClackData1.getDate());
//        System.out.println("fileClackData2 getDate(): " + fileClackData2.getDate());
//        System.out.println();
//
//        // getData()
//        System.out.println("messageClackData1 getData(): " + messageClackData1.getData());
//        System.out.println("messageClackData2 getData(): " + messageClackData2.getData());
//        System.out.println("fileClackData1 getData(): " + fileClackData1.getData());
//        System.out.println("fileClackData2 getData(): " + fileClackData2.getData());
//        System.out.println();
//
//        // hashCode()
//        System.out.println("messageClackData1 hashCode(): " + messageClackData1.hashCode());
//        System.out.println("messageClackData2 hashCode(): " + messageClackData2.hashCode());
//        System.out.println("fileClackData1 hashCode(): " + fileClackData1.hashCode());
//        System.out.println("fileClackData2 hashCode(): " + fileClackData2.hashCode());
//        System.out.println();
//
//        // equals()
//        System.out.println("messageClackData1 equals null: "
//                + messageClackData1.equals(null));
//        System.out.println("messageClackData1 equals messageClackData1: "
//                + messageClackData1.equals(messageClackData1));
//        System.out.println("messageClackData1 equals messageClackData2: "
//                + messageClackData1.equals(messageClackData2));
//        System.out.println("messageClackData2 equals messageClackData1: "
//                + messageClackData2.equals(messageClackData1));
//        System.out.println("messageClackData1 equals fileClackData1: "
//                + messageClackData1.equals(fileClackData1));
//        System.out.println("fileClackData1 equals null: "
//                + fileClackData1.equals(null));
//        System.out.println("fileClackData1 equals fileClackData1: "
//                + fileClackData1.equals(fileClackData1));
//        System.out.println("fileClackData1 equals fileClackData2: "
//                + fileClackData1.equals(fileClackData2));
//        System.out.println("fileClackData2 equals fileClackData1: "
//                + fileClackData2.equals(fileClackData1));
//        System.out.println("fileClackData1 equals messageClackData1: "
//                + fileClackData1.equals(messageClackData1));
//        System.out.println();
//
//        // toString()
//        System.out.println("messageClackData1:\n" + messageClackData1);
//        System.out.println("messageClackData2:\n" + messageClackData2);
//        System.out.println("fileClackData1:\n" + fileClackData1);
//        System.out.println("fileClackData2:\n" + fileClackData2);
//        System.out.println();
//
//        // getFileName
//        System.out.println("fileClackData1 getFileName(): " + fileClackData1.getFileName());
//        System.out.println("fileClackData2 getFileName(): " + fileClackData2.getFileName());
//        System.out.println();
//
//        // setFileName
//        String filename1 = "filename1";
//        System.out.println("Sets the filename of fileClackData1 to be " + filename1);
//        fileClackData1.setFileName(filename1);
//        System.out.println("fileClackData1 getFileName(): " + fileClackData1.getFileName());
//        System.out.println("fileClackData1 hashCode(): " + fileClackData1.hashCode());
//        System.out.println("fileClackData1 equals fileClackData1: " + fileClackData1.equals(fileClackData1));
//        System.out.println("fileClackData1 equals fileClackData2: " + fileClackData1.equals(fileClackData2));
//        System.out.println("fileClackData1:\n" + fileClackData1);
//        System.out.println();
//
//        String filename2 = "filename2";
//        System.out.println("Sets the filename of fileClackData2 to be " + filename2);
//        fileClackData2.setFileName(filename2);
//        System.out.println("fileClackData2 getFileName(): " + fileClackData2.getFileName());
//        System.out.println("fileClackData2 hashCode(): " + fileClackData2.hashCode());
//        System.out.println("fileClackData2 equals fileClackData2: " + fileClackData2.equals(fileClackData2));
//        System.out.println("fileClackData2 equals fileClackData1: " + fileClackData2.equals(fileClackData1));
//        System.out.println("fileClackData2:\n" + fileClackData2);

}