package test;
import data.*;

public class TestClackData {
    private static final String username_1 = "username_1";
    private static final String filename_1 = "filename_1";
    private static final String setfilename_1 = "setfilename_1";
    private static final int test_1 = 5;

    private static final String username_2 = "username_2";
    private static final String filename_2 = "filename_2";
    private static final String setfilename_2 = "setfilename_2";
    private static final int test_2 = 7;

    private static final String username_copy = "username_copy";
    private static final String filename_copy = "filename_copy";
    private static final int value_copy = 9;


    private static final String data_message_1 = "data_message_1";
    private static final String temp_message_1 = "temp_message_1";
    private static final int test_message_1 = 1;

    private static final String data_message_2 = "data_message_2";
    private static final String temp_message_2 = "temp_message_2";
    private static final int test_message_2 = 2;


    private static final String data_message_copy = "data_message_copy ";
    private static final String temp_message_copy = "temp_message_copy";
    private static final int test_message_copy = 3;



    public static void main(String[] args) {

        FileClackData first_data = new FileClackData(username_1,filename_1,test_1);
        FileClackData second_data = new FileClackData(username_2,filename_2,test_2);
        FileClackData copy_data = new FileClackData(username_copy,filename_copy,value_copy);


        MessageClackData first_message = new MessageClackData(username_1,temp_message_1, test_message_1);
        MessageClackData second_message = new MessageClackData(username_2,temp_message_2, test_message_2);
        MessageClackData copy_message = new MessageClackData(username_copy,temp_message_copy, test_message_copy);


        System.out.println("data : " + first_data.getDate());
        System.out.println("first data: " + first_data.getData());
        System.out.println("first data filename: " + first_data.getFileName());
        System.out.println("\n");

        first_data.setFileName(setfilename_1);
        System.out.println("changing name of first data: " + first_data.getFileName());
        System.out.println("first data hashcode: " + first_data.hashCode());
        System.out.println(first_data);
        System.out.println("\n");


        System.out.println("data : " + first_message.getDate());
        System.out.println("first message: " + first_message.getData());
        System.out.println("first message hashcode: " + first_message.hashCode());
        System.out.println(first_message);
    }
}
