// Vince V
// INSY 4305
// Homework 3

public class Residential extends Property 
{
	private double taxRate;
	
	public Residential()
	{
		super();
		setTaxRate(0.0);
	}
	public Residential(double p, Date d, Address a, double t, int dod, double tR)
	{
		super(p, d, a, t, dod);
		setTaxRate(tR);
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) 
	{
		  this.taxRate = taxRate;
	}
	public String toString() {
		return (super.toString() + " Tax Rate " + taxRate + "\n");
	}
	public void calculateTax()
	{
		double t;
		t = taxRate * getPrice();
		
		if(getAddress().getZip() == 76019)
		
		t = t * 1.03;
			
		super.setTax(t);
	}		
}
