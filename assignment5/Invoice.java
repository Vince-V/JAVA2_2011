//Vince V
//INSY 4305
//Homework 5

import java.io.Serializable;

public class Invoice implements Serializable 
{
    private String name = "";
    private double price =0.0;
    private int quantity = 0;
    
    public Invoice()
    {
        setName("");
        setPrice(0.0);
        setQuantity(0);
    }   

    public Invoice( String name, double price, int quantity) 
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    

    public String getName() 
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
    	
        this.price = price;
    }

    public int getQuantity() 
    {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }    

}
