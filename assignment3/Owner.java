// Vince V
// INSY 4305
// Homework 3
import java.util.*;
import javax.swing.*;
import java.text.DecimalFormat;
import java.io.*;

public class Owner implements TaxInvoice, Serializable
{
	private String name;
	private Address address;
	public enum RatingType{GOOD,BAD};
	private RatingType rating;
	private double accountBalance;
	private ArrayList <Property> propertyList = new ArrayList<Property>();
	
	public Owner() 
	{
		setName("");
		setAddress(new Address());
		setRating(RatingType.GOOD);
		setAccountBalance(0.0);
	}
	public Owner(String n, Address a, RatingType rT, double aB ) 
	{
		setName(n);
		setAddress(a);
		setRating(rT);
		setAccountBalance(aB);
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Address getAddress() 
	{
		return address;
	}
	public void setAddress(Address address)
	{
		this.address = address;
	}
	public void setPropertyList(Property p) 
	{
		propertyList.add(p);
	}
	public RatingType getRating() 
	{
		return rating;
	}
	public void setRating(RatingType rating) 
	{
		this.rating = rating;
	}
	public double getAccountBalance() 
	{
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) 
	{
		this.accountBalance = accountBalance;
	}
	public ArrayList<Property> getPropertyList() 
	{
		return propertyList;
	}
	public String toString()
	{
		return ("Name: " + name + " Address " + address + " Rating: " + rating + " Account Balance: " + accountBalance
				+ " \nProperty " + propertyList);
	}
	
	public String createTaxInvoice() 
	{
		//traverse the product list
		double tax=0.0;
		double totalTaxes = 0.0;
		DecimalFormat twoDigits = new DecimalFormat("0.00");
		int numberOfProperty=0;
		
		for (Property p: propertyList)
		{
		  if(p.getClass().getName().equals("Residential"))
		  {
			((Residential)p).calculateTax();
			tax = p.getTax();
			numberOfProperty +=1;
			totalTaxes += tax;
		  }
		 
		  else 
		  {
			p.calculateTax();
			tax = p.getTax();
			numberOfProperty +=1;
			totalTaxes += tax;
	      }
	   }
		return (" Number of Properties " + numberOfProperty + " Total Tax $" +  twoDigits.format(totalTaxes) );
	}
	public void addToPropertyList(Property p) throws InsufficientFundsException
	{
		
		double oldBalance = getAccountBalance();
		
		if(oldBalance > p.getPrice())
	
			propertyList.add(p);
		else
			throw new InsufficientFundsException();
	
		double newBalance = oldBalance - p.getPrice();
		setAccountBalance(newBalance);
			

	}
}

