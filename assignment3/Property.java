// Vince V
// INSY 4305
// Homework 3
import java.io.*;
public abstract class Property implements Serializable
{
	private double price;
	private Date purchaseDate;
	private Address address;
	private double tax;
	private int daysOverDue;
	
	public Property()
	{
		setPrice(0.00);
		setPurchaseDate(new Date());
		setAddress(new Address());
		setTax(0.0);
		setDaysOverDue(0);
	}
	public Property(double p, Date d, Address a, double t, int dod){
		setPrice(p);
		setPurchaseDate(d);
		setAddress(a);
		setTax(t);
		setDaysOverDue(dod);
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public int getDaysOverDue() {
		return daysOverDue;
	}
	public void setDaysOverDue(int daysOverDue) {
		this.daysOverDue = daysOverDue;
	}
	
	public String toString() 
	{
		return ("Price: " + price + " Purchase Date: " + purchaseDate
				+ " Address: " + address + " Tax: " + tax
				+ " Days overdue: " + daysOverDue);
	}
	public abstract void calculateTax();
}
