//Vince V
//INSY 4305
//Homework 5

public class Computer implements java.io.Serializable
{
	private String customerName;
	private double price;
	private int quantity;
	
	public Computer()
	{
		setCustomerName("");
		setPrice(0.0);
		setQuantity(0);
	}
	public Computer(String cn, double p, int q)
	{
		setCustomerName(cn);
		setPrice(p);
		setQuantity(q);		
	}
	public String getCustomerName()
	{
		return customerName;
	}
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}
	public double getPrice() 
	{
		return price;
	}
	public void setPrice(double price) 
	{
		this.price = price;
	}
	public int getQuantity() 
	{
		return quantity;
	}
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	@Override
	public String toString() 
	{
		return ("Computer: " + customerName + "Price: " + price
				+ "Quantity: " + quantity);
	}
}