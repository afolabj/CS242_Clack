package main;

public class ClientSideServerListener implements Runnable{
    private ClackClient client;

    /**
     * constructor that takes in a ClackClient object ‘client’ as
     * parameter
     * @param client
     */
    public ClientSideServerListener( ClackClient client){
        this.client = client;
    }

    /**
     * method from overridden from Runnable interface
     */
    @Override
    public void run() {
        while (!client.getCloseConnection()){
            client.receiveData();
            client.printData();
        }
    }
}
