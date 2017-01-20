//Vince V
//INSY 4305
//Homework 4

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class DBMethods 
{
	private final String URL = "jdbc:mysql://localhost/property";
	private final String USERNAME = "deitel";
	private final String PASSWORD = "deitel";
	
	private Connection connection = null;
	private PreparedStatement insertOwner = null;
	private PreparedStatement insertProperty = null;
	private PreparedStatement insertOwnerProperty = null;
	private PreparedStatement updateBalance = null;
	private ResultSet getRate = null;
	private ResultSet getProp= null;
	private ResultSet getOwner = null;
	private ResultSet getOwnerProp = null;
	private ResultSet getTaxRate = null;
	private ResultSet getData = null;
	private ResultSet getDOD = null;
	private Statement statement = null;

	public DBMethods()
	{
		try
		{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			insertOwner = connection.prepareStatement(
			"INSERT INTO OWNERS" + "(Name, Street, City, State, Zip, Balance)" + "VALUES(?,?,?,?,?,?)");
			
			insertProperty = connection.prepareStatement(
					"INSERT INTO PROPERTIES" + "(Price, Type, Subtype, Date, Street, City, State, Zip, DaysOverDue, Owner)" + 
					"VALUES(?,?,?,STR_TO_DATE(?,'%m/%e/%Y'),?,?,?,?,?,?)");
			
			insertOwnerProperty = connection.prepareStatement("INSERT INTO OWNERPROPERTIES" + "(Owner)" + "VALUES(?)");
			
			updateBalance = connection.prepareStatement("UPDATE OWNERS SET Balance = ? WHERE Name = ?");
														
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
			System.exit(1);
		}
	} // end constructor
	
	public int writeOwner(String name, String street, String city, String state, int zip, double balance)
	{
		int result = 0;
		try
		{
			insertOwner.setString(1, name);
			insertOwner.setString(2, street);
			insertOwner.setString(3, city);
			insertOwner.setString(4, state);
			insertOwner.setInt(5, zip);
			insertOwner.setDouble(6, balance);
			
			result = insertOwner.executeUpdate();
			
		}// end try
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		return result;	
	} // end method writeOwner
	
	public int writeProperty(double price, String type, String subtype, String date, String street,
							         String city, String state, int zip, int dod, String owner)
	{
		int result = 0;
		try
		{
			insertProperty.setDouble(1, price);
			insertProperty.setString(2, type);
			insertProperty.setString(3, subtype);
			insertProperty.setObject(4, date);
			insertProperty.setString(5, street);
			insertProperty.setString(6, city);
			insertProperty.setString(7, state);
			insertProperty.setInt(8, zip);
			insertProperty.setInt(9, dod);
			insertProperty.setString(10, owner);
						
			result = insertProperty.executeUpdate();
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		return result;
	} // end writeProperty
	
	public int writeOwnerProperties(String owner)
	{
		int result = 0;
		try
		{
			insertOwnerProperty.setString(1, owner);
			
			result = insertOwnerProperty.executeUpdate();
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		return result;
	}
	
	public int updateOwnerBalance(String name, double subtractionAmount)
	{
		String ownerToUpdate;
		double balance;
		int result = 0;
		try
		{
			statement = connection.createStatement();
			getData = statement.executeQuery("SELECT Name, Balance FROM OWNERS");
			
		   while(getData.next())
		   {
				ownerToUpdate = getData.getString("Name");
				balance = getData.getDouble("Balance");
				if(ownerToUpdate.equalsIgnoreCase(name))
				{
				   balance -= subtractionAmount	;
				   
				   updateBalance.setDouble(1, balance);
				   updateBalance.setString(2, ownerToUpdate);
				 			   
					updateBalance.executeUpdate();
				}
		    }
		} // end try
			catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
		return result;
	}// end updateOwnerBalance
		
	public double queryTaxRate(String Type)
	{
		String type = "";
		double txRate = 0.0;
		
		try
		{
			statement = connection.createStatement();
			getRate = statement.executeQuery("SELECT Type, Rate FROM TAXRATES");
			
			while(getRate.next())
			{
				type = getRate.getString("Type");
				
				if(Type.equalsIgnoreCase(type))
					txRate = getRate.getDouble("Rate");
			}	
		} // end try
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
		
		return txRate;
	} // end queryTaxRate
	
	public void connectDatabase()
	{
		try
		{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
	
	public void closeDatabase()
	{
		try
		{
		   connection.close();
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public boolean searchOwners(String oName)
	{
		boolean exists = false;
		
		try
		{
			statement = connection.createStatement();
			getData = statement.executeQuery("SELECT Name FROM OWNERS");
			while(getData.next())
			{
				if(oName.equalsIgnoreCase(getData.getString("Name")))
				{
				   exists = true;
				}
				
		    } // end while
		} // end try
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		} 
		return exists;
	} // end search searchPropertyOwners

	public void readDatabase()
	{	
		try
		{
			statement = connection.createStatement();
			
			// print OWNERS table
			getOwner= statement.executeQuery("SELECT * FROM OWNERS");
			ResultSetMetaData ownersMetaData = getOwner.getMetaData();
			int ownerColumns = ownersMetaData.getColumnCount();
			System.out.println( "OWNERS Table of the property database:" );
			
			for( int i = 1; i <=ownerColumns; i++)
				System.out.printf( "%-9s\t",ownersMetaData.getColumnName( i ));
				System.out.println();
		
			while(getOwner.next())
			{
				for(int i=1;i<=ownerColumns;i++)
				{
				   System.out.printf("%-9s\t", getOwner.getObject(i));	
				} // end for
			System.out.println(" ");
			} // end while
			
			// print PROPERTIES table
			getProp = statement.executeQuery("SELECT * FROM PROPERTIES");
			ResultSetMetaData propMetaData = getProp.getMetaData();
			int propCols = propMetaData.getColumnCount();
			System.out.println( "\nPROPERTIES Table of the property database:" );
			
			for( int i = 1; i <=propCols; i++)
				System.out.printf( "%-9s\t",propMetaData.getColumnName( i ));
				System.out.println();
		
			while(getProp.next())
			{
				for(int i=1;i<=propCols;i++)
				{
				   System.out.printf("%-9s\t", getProp.getObject(i));	
				} // end for
			System.out.println(" ");
			} // end while
			
			// print TAXRATES table
			getTaxRate = statement.executeQuery("SELECT * FROM TAXRATES");
			ResultSetMetaData tRatesMetaData = getTaxRate.getMetaData();
			int tRateColumns = tRatesMetaData.getColumnCount();
			System.out.println( "\nTAXRATES Table of the property database:" );
			
			for( int i = 1; i <=tRateColumns; i++)
				System.out.printf( "%-9s\t",tRatesMetaData.getColumnName( i ));
				System.out.println();
		
			while(getTaxRate.next())
			{
				for(int i=1;i<=tRateColumns;i++)
				{
					System.out.printf("%-9s\t", getTaxRate.getObject(i));	
				}
			System.out.println(" ");
			} // end while

			//print OWNERPROPERTIES
			getOwnerProp = statement.executeQuery("SELECT * FROM OWNERPROPERTIES");
			ResultSetMetaData opMetaData = getOwnerProp.getMetaData();
			int opColumns = opMetaData.getColumnCount();
			System.out.println( "\nOWNERPROPERTIES Table of the property database:" );
			
			for( int i = 1; i <=opColumns; i++)
				System.out.printf( "%-9s\t",opMetaData.getColumnName( i ));
				System.out.println();
		
			while(getOwnerProp.next())
			{
				for(int i=1;i<=opColumns;i++)
				{
					System.out.printf("%-9s\t", getOwnerProp.getObject(i));	
				}
			System.out.println(" ");
			} // end while
		} // end try
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}// end readDatabase

	public void writeDatabase()
	{
		double tx = 0.0; 	
		try
		{
		statement = connection.createStatement();
		String output = "";		
		getOwner = statement.executeQuery("SELECT Owner, Type, Zip, Subtype, SUM(Price) as 'price', Count(*) as 'count' FROM PROPERTIES group by Owner");

		output += String.format("");
			while(getOwner.next())
			{	
			 	if(getOwner.getString("Type").equalsIgnoreCase("Residential") && (getOwner.getInt("Zip")==76019))
				{
					tx =  ((getOwner.getDouble("Price") *(queryTaxRate(getOwner.getString("Type"))/100))) * 1.03;
				}
				else if(getOwner.getString("Type").equalsIgnoreCase("Residential"))
				{
					tx = (getOwner.getDouble("Price") * (queryTaxRate(getOwner.getString("Type"))/100));
				}
				else
				{
					tx = (getOwner.getDouble("Price") * (queryTaxRate(getOwner.getString("SubType"))/100));
				}
		 	
			 	output += String.format("%s\t   Number of properties %d Tax $%.2f", getOwner.getString("Owner"), getOwner.getInt("count"), tx );
				output += String.format("\n");
	
			} // end while
			JOptionPane.showMessageDialog(null, output,"Tax Invoices", JOptionPane.PLAIN_MESSAGE);
			
		} // end try
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
	
	public boolean searchDOD(String name)
    {
        String oName = "";
        
        int dod = 0;
        boolean fdod=false;
        
        try
        {
            statement = connection.createStatement();
            getDOD = statement.executeQuery("SELECT Owner, DaysOverDue FROM PROPERTIES");
            
            while(getDOD.next())
            {
                oName = getDOD.getString("Owner");
                
                if(oName.equalsIgnoreCase(name))
                {
                  dod = getDOD.getInt("DaysOverDue");
                }
            
            if (dod > 0) fdod=true;
            }
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return fdod;
    } // end search DOD
	
	public double getPurchaserBalance(String purchaserName)
	{
		double purchaserBalance = 0.0;
		String ownerTableName = "";
		
		try
		{
			statement = connection.createStatement();
			getData = statement.executeQuery("Select Name, Balance FROM OWNERS");
			
			while(getData.next())
			{
				ownerTableName = getData.getString("Name");
				if(ownerTableName.equalsIgnoreCase(purchaserName))
				{
					purchaserBalance = getData.getDouble("Balance");
				}
			}
		 }
	     catch(SQLException sqle)
	     {
	       sqle.printStackTrace();
	     }
		return purchaserBalance;
	}

	public void deductTaxesFromCurrentOwners()
	{
		double tx = 0.0;	
		
		try
		{
			statement = connection.createStatement();
						
		    getOwner = statement.executeQuery("SELECT Owner,  Type, Zip, Subtype, SUM(Price) as 'price', Count(*) as 'count' FROM PROPERTIES group by Owner");

			while(getOwner.next())
			{
								 
			 	if(getOwner.getString("Type").equalsIgnoreCase("Residential") && (getOwner.getInt("Zip")==76019))
				{
					tx =  ((getOwner.getDouble("Price") *(queryTaxRate(getOwner.getString("Type"))/100))) * 1.03;
				}
				else if(getOwner.getString("Type").equalsIgnoreCase("Residential"))
				{
					tx = (getOwner.getDouble("Price") * (queryTaxRate(getOwner.getString("Type"))/100));
				}
				else
				{
					tx = (getOwner.getDouble("Price") * (queryTaxRate(getOwner.getString("SubType"))/100));
				}
			 	updateOwnerBalance(getOwner.getString("Owner"), tx);
						 	
			} // end while
			//JOptionPane.showMessageDialog(null, "Taxes have been deducted from current owners\n                      Click OK to continue", "Notice", JOptionPane.WARNING_MESSAGE);
			
		}// end try
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
		} 
	}
} // end class 




