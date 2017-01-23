// Vince V
// INSY 4305
// Homework 3

import java.io.*;
import javax.swing.*;
import java.util.*;

public class ReadObjects extends JFrame	implements Serializable
{
	public static void main(String[] args)
	{
		ReadObjects rO = new ReadObjects();
		rO.setSize(200,200);
		rO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rO.setVisible(false);
		System.exit(0);
	}
	public ReadObjects()
	{
		super("Read Object File");
		
		readObj();
	}
	
	public void readObj()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		int result = fileChooser.showOpenDialog(this);
		
		if(result == fileChooser.CANCEL_OPTION)
			System.exit(1);
		
		File fileName = fileChooser.getSelectedFile();
		
		Owner record;
		String objectType;
		
		try
		{
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
			
			while(true)
			{
				record = (Owner)input.readObject();
				objectType = record.getClass().toString();
				
				System.out.println("Object type:"+objectType+"\n" +record+"\n");
			} // end while
			
			
		}
		catch(EOFException eofe)
		{
			return;
		}
		catch(ClassNotFoundException cnfe)
		{
			System.err.println("Cannot create object");
		}
		catch(IOException ioe)
		{
			System.err.println("Error reading from file");
		}
	}
}
