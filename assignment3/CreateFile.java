// Vince V
// INSY 4305
// Homework 3
import java.io.*;
import java.util.*;

public class CreateFile
{
	public static void main(String[] args)
	{
		Formatter output;
		
			try
			{
				output = new Formatter(new File("purchases.txt"));
								
				output.format("%s","75000,2,2,2010,Smith Street,Euless,Texas,70025,30,Jones");
				output.format("%s","\n124500,5,23,2010,Main Street,Bedford, Texas,70030,60,Smith");
				output.format("%s","\n399000,8,5,2009,Knight Avenue,Seattle,Washington,90685,45,Willis");
				output.format("%s","\n45750,3,4,2011,Josy Lane,Denver,Colorado,28054,0,Sanchez");
				output.format("%s","\n376459,6,14,2009,Allen Street,Arlington,Texas,76019,90,Baker");
				
				output.close();
				
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		
			
	}

}
