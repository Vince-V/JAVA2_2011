//Vince V
//INSY 4305
//Homework 5

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientWorker implements Runnable
{
    static Socket connection;
    static ObjectOutputStream output = null; //Write
    static ObjectInputStream input = null; //Read
    Invoice recievedInvoice = null;

    public ClientWorker( Socket conn)
    {
        connection = conn;
    }

    public void run() 
    {
        try
        {
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream( connection.getInputStream());
        }
        catch(Exception err) 
	{
            err.fillInStackTrace();
        }

        read();
        write();

    }//run()

    public void read() 
    {
        Object line = null;

        try 
        {
            line = input.readObject();

            if( line instanceof Invoice )
                recievedInvoice = (Invoice)line;

                System.out.println( "the server read: " + recievedInvoice.getName() + " " + recievedInvoice.getPrice() + " " + recievedInvoice.getQuantity() + ".");

        }
        catch( Exception err) {
            System.out.println("I/O problem while reading");
        }
    }

    public void write() 
    {
        double charge = recievedInvoice.getPrice() * recievedInvoice.getQuantity();
        Object charge2 = (Object)charge;

        try 
        {
            output.writeObject(charge2);
            System.out.println("The server sent: $" + charge);
        }
        catch( Exception err) 
        {
            System.out.println("Error writing.");
        }
    }//write

}//ClientWorker