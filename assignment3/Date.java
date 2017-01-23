// Vince V
// INSY 4305
// Homework 3
import java.io.*;
public class Date implements Serializable
{
	private int month;
	private int day;
	private int year;
	
	public Date(){
		setMonth(0);
		setDay(0);
		setYear(0);
	}
	public Date(int m, int d, int y)
	{
		setMonth(m);
		setDay(d);
		setYear(y);
	}
	public int getMonth()
        {
		return month;
	}
	public void setMonth(int month) 
	{
		this.month = month;
	}
	public int getDay() 
	{
		return day;
	}
	public void setDay(int day) 
	{
		this.day = day;
	}
	public int getYear() 
	{
		return year;
	}
	public void setYear(int year) 
	{
		this.year = year;
	}
	
	public String toString(){
		return " Month: " + month + " Day: " + day + " Year: " + year;
	}
}
