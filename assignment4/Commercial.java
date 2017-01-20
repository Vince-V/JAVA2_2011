//Vince V
//INSY 4305
//Homework 4

public class Commercial extends Property 
{
	public enum CommercialType{HAZARD, INDUSTRY, SHOPPING};
	private CommercialType type;

	public Commercial()
	{
		super();
		setCommercialType(CommercialType.HAZARD);
	}
	public Commercial(double p, Date d, Address a, double t, int dod, CommercialType cType)
	{
		super(p, d, a, t, dod);
		setCommercialType(cType);
	}
	public void setCommercialType(CommercialType cType) 
	{
		type = cType;
	}
	public CommercialType getType() 
	{
		return type;
	}
	
	public String toString()
	{
		return(super.toString()+ " Commercial Type " +type);
	}
	
	public void calculateTax()
	{
		DBMethods dbm = new DBMethods();
		if (type == CommercialType.INDUSTRY  )
			super.setTax(super.getPrice() * (dbm.queryTaxRate("INDUSTRY")/100));
		
			else if (type == CommercialType.SHOPPING )
				super.setTax(super.getPrice() * (dbm.queryTaxRate("SHOPPING")/100));
		
			else if(type == CommercialType.HAZARD )
				super.setTax(super.getPrice() * (dbm.queryTaxRate("HAZARD")/100));	
	}
}
