// Vince V
// INSY 4305
// Homework 3
import java.io.*;
public class Address implements Serializable
{
	private String street;
	private String city;
	private String state;
	private int zip;
	
	public Address()
	{
		setStreet("");
		setCity("");
		setState("");
		setZip(0);
	}
	public Address(String str, String c, String sta, int z){
		setStreet(str);
		setCity(c);
		setState(sta);
		setZip(z);
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public String toString(){
		return (" Street: " +street+ " City: " +city+ " State: " +state+ " Zip: " +zip);
	}
}
