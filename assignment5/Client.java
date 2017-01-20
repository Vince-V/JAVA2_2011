//Vince V
//INSY 4305
//Homework 5

import java.net.*;
import java.io.*;

public class Client 
{
    ObjectInputStream input = null;
    ObjectOutputStream output = null;
    Socket connection = null;

    public void networking() 
    {
        try
        {
            connection = new Socket("localhost", 8000);
            System.out.println("Connection established.(client)");
        }
        catch( UnknownHostException uhe) 
        {
            System.out.println("Unknown host.");
        }
        catch( IOException ioe) {
            System.out.println("I/O problem in connection.");
        }

        try {
            input = new ObjectInputStream( connection.getInputStream() );
            output = new ObjectOutputStream(connection.getOutputStream());
            System.out.println("I/O established.");

        }
        catch( IOException ioe) {
            System.out.println("I/O problem in read/write.");
        }

    }//networking

    public void read() 
    {
        String line = null;
        try{
            line = input.readObject().toString();
            System.out.println("The client recieved: " + line);
        }
        catch( Exception err){
            System.out.println("I/O problem while reading");
        }
    }

    public void write() 
    {
        Invoice i1 = new Invoice("Apple", 5.00, 10);

        try
        {
            output.writeObject(i1);
            System.out.println( "the client sent: " + i1.getName() + " " + i1.getPrice() + " " + i1.getQuantity() + ".");
        }
        catch( Exception err){
            System.out.println("Error writing.");
        }

    }//write

    public void closing()
    {
        try
        {
            input.close();
            output.close();
            connection.close();
        }
        catch( Exception err)
        {
            System.out.println("Closing error.");
        }

    }


    public static void main( String[] args) 
    {
        Client c1 = new Client();

            c1.networking();
            c1.write();
            c1.read();
            c1.closing();

    }
    
}

