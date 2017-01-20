//Vince V
//INSY 4305
//Homework 5
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{

   static ServerSocket server;
   static int clientNo = 1;

   public void networking() 
   {
    
    try
    {
        server = new ServerSocket(8000);
        System.out.println("Listening...");
    }
    catch( Exception err) 
    {
        System.out.println("Error: Could not listen to specified port.");
    }

    while(true)
    {
        ClientWorker clientWorker;
        try
        {
            clientWorker = new ClientWorker(server.accept());
	    System.out.println("Start thread for client " + clientNo);
            Thread t1 = new Thread(clientWorker);
            ExecutorService threadManager = Executors.newCachedThreadPool();
            threadManager.execute(t1);
        }
        catch(Exception err) 
        {
            System.out.println("Server fail to accept connection.");
        }
    }

}//networking()

    public static void main( String[] args) 
    {
        Server s1 = new Server();
        s1.networking();      
    }

}//Server

