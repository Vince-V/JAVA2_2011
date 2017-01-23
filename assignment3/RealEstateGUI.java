// Vince V
// INSY 4305
// Homework 3
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;	

public class RealEstateGUI extends JFrame implements Serializable
{
	String output="";
	String outputOwnersPropertyLists="";
	Owner[] ownerArray = new Owner[10];
	int counter = -1;
	int prePopCounter=0;
	
	private JLabel jlblChooseOne;
	private JLabel jlblName;
	private JLabel jlblStreet;
	private JLabel jlblCity;
	private JLabel jlblState;
	private JLabel jlblZip;
	private JLabel jlblSelectTypeOfProperty;
	private JLabel jlblTypeOfCommercialProperty;
	private JLabel jlblDatePurchased;
	private JLabel jlblPurchasedBy;
	
	private JTextField txtFieldName;
	private JTextField txtFieldStreet;
	private JTextField txtFieldCity;
	private JTextField txtFieldState;
	private JTextField txtFieldZip;
	private JTextField txtFieldDatePurchased;
	private JTextField txtFieldPurchasedBy;
	
	private JCheckBox cProperty;
	private JCheckBox rProperty;
	
	private JRadioButton industryRB;
	private JRadioButton shoppingRB;
	private JRadioButton hazardRB;
	
	private JComboBox chooseOneJCB;
	
	private JButton submitJB;
	private JButton finishJB;

	private JPanel buttonPanelTypeofProperty = new JPanel();// panel for check boxes for type of property
	private JPanel commercialTypePanel = new JPanel(); // panel for check boxes for commercial type
	
	private final String[] chooseOne = {"Select One","Owner","Property"};
	
	private ButtonGroup cpTypeGroup = new ButtonGroup();
	
	private ObjectOutputStream outP;
		
	public RealEstateGUI()
	{
		super("Mav Real Estate");
		setLayout(new GridLayout(11,2));
		
		jlblChooseOne = new JLabel("  Choose One");
		jlblName = new JLabel("  Name");
		jlblStreet = new JLabel("  Street");
		jlblCity = new JLabel("  City");
		jlblState = new JLabel("  State");
		jlblZip = new JLabel("  Zip");
		jlblSelectTypeOfProperty = new JLabel("  Select type of property");
		jlblTypeOfCommercialProperty = new JLabel("  Select type of commercial property");
		jlblSelectTypeOfProperty = new JLabel("  Select type of property");
		jlblDatePurchased = new JLabel("  Date Purchased");
		jlblPurchasedBy = new JLabel("  Purchased By");
		
		txtFieldName = new JTextField();
		txtFieldStreet = new JTextField();
		txtFieldCity = new JTextField();
		txtFieldState = new JTextField();
		txtFieldZip = new JTextField();
		txtFieldDatePurchased = new JTextField();
		txtFieldDatePurchased.setToolTipText("mm/dd/yyyy");
		txtFieldPurchasedBy = new JTextField();
				
		cProperty = new JCheckBox("COMMERCIAL");
		rProperty = new JCheckBox("RESIDENTIAL");
		
		chooseOneJCB = new JComboBox(chooseOne);
		chooseOneJCB.setMaximumRowCount(3);
		
		finishJB = new JButton("FINISH");
		submitJB = new JButton("SUBMIT");
	
		buttonPanelTypeofProperty.setLayout(new GridLayout(1, 3));
		buttonPanelTypeofProperty.add(cProperty);
		buttonPanelTypeofProperty.add(rProperty);
		
		industryRB = new JRadioButton("INDUSTRY");
		cpTypeGroup.add(industryRB);
		shoppingRB = new JRadioButton("SHOPPING");
		cpTypeGroup.add(shoppingRB);
		hazardRB = new JRadioButton("HAZARD");
		cpTypeGroup.add(hazardRB);
		commercialTypePanel.setLayout(new GridLayout(1, 3)); // Panel
		commercialTypePanel.add(industryRB);
		commercialTypePanel.add(shoppingRB);
		commercialTypePanel.add(hazardRB);
		
		add(jlblChooseOne);
		add(chooseOneJCB);
		add(jlblName);
		add(txtFieldName);
		add(jlblStreet);
		add(txtFieldStreet);
		add(jlblCity);
		add(txtFieldCity);
		add(jlblState);
		add(txtFieldState);
		add(jlblZip);
		add(txtFieldZip);
		add(jlblSelectTypeOfProperty);
		add(buttonPanelTypeofProperty);
		add(jlblTypeOfCommercialProperty);
		add(commercialTypePanel);
		add(jlblDatePurchased);
		add(txtFieldDatePurchased);
		add(jlblPurchasedBy);
		add(txtFieldPurchasedBy);
		add(submitJB);
		add(finishJB);
		
		ButtonHandler sHandler = new ButtonHandler();
		FinishHandler fHandler = new FinishHandler();
		submitJB.addActionListener(sHandler);
		finishJB.addActionListener(fHandler);
							
		chooseOneJCB.addItemListener(new ItemListener()
		{
		 public void itemStateChanged(ItemEvent e)
		 {
		    if(e.getStateChange() == ItemEvent.SELECTED)
			{
					if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Owner"))
					{
						jlblName.setEnabled(true);
						txtFieldName.setEditable(true);
						jlblSelectTypeOfProperty.setEnabled(false); 
						jlblTypeOfCommercialProperty.setEnabled(false);
						jlblDatePurchased.setEnabled(false);
						jlblPurchasedBy.setEnabled(false);
						cProperty.setEnabled(false);
						rProperty.setEnabled(false);
						industryRB.setEnabled(false);
						shoppingRB.setEnabled(false);
						hazardRB.setEnabled(false);
						txtFieldDatePurchased.setEditable(false);
						txtFieldPurchasedBy.setEditable(false);
						cpTypeGroup.clearSelection();
						txtFieldName.requestFocus();
						
						txtFieldDatePurchased.setText("");
						txtFieldPurchasedBy.setText("");
					}
				else if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Property"))
				{
						txtFieldName.setText("");
						jlblName.setEnabled(false);
						txtFieldName.setEditable(false);
						
						jlblSelectTypeOfProperty.setEnabled(true); 
						jlblTypeOfCommercialProperty.setEnabled(true);
						jlblDatePurchased.setEnabled(true);
						jlblPurchasedBy.setEnabled(true);
						cProperty.setEnabled(true);
						rProperty.setEnabled(true);
						industryRB.setEnabled(true);
						shoppingRB.setEnabled(true);
						hazardRB.setEnabled(true);
						txtFieldDatePurchased.setEditable(true);
						txtFieldPurchasedBy.setEditable(true);
						txtFieldStreet.requestFocus();
						
						if(rProperty.isSelected())
						{		
								commercialTypePanel.setEnabled(false);
								industryRB.setEnabled(false);
								shoppingRB.setEnabled(false);
								hazardRB.setEnabled(false);
						}
				 }
				else if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Select One"))
				 {
						jlblName.setEnabled(true);
						txtFieldName.setEditable(true);
						jlblSelectTypeOfProperty.setEnabled(true); 
						jlblTypeOfCommercialProperty.setEnabled(true);
						jlblDatePurchased.setEnabled(true);
						jlblPurchasedBy.setEnabled(true);
						cProperty.setEnabled(true);
						rProperty.setEnabled(true);
						industryRB.setEnabled(true);
						shoppingRB.setEnabled(true);
						hazardRB.setEnabled(true);
						txtFieldDatePurchased.setEditable(true);
						txtFieldPurchasedBy.setEditable(true);
						clearAll();
				 }
			}
		 }		
	  }
	  ); // end JComboBox box anonymous listener
		rProperty.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					if(rProperty.isSelected())
					{
						commercialTypePanel.setEnabled(false);
						industryRB.setEnabled(false);
						shoppingRB.setEnabled(false);
						hazardRB.setEnabled(false);
						cProperty.setEnabled(false);
						cpTypeGroup.clearSelection();
					}
				if(e.getStateChange() == ItemEvent.DESELECTED)
					{	
						cProperty.setEnabled(true);
						commercialTypePanel.setEnabled(true);
						industryRB.setEnabled(true);
						shoppingRB.setEnabled(true);
						hazardRB.setEnabled(true);
					}	
			}
		}
		); // end residential check box anonymous listener
		cProperty.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
					if(cProperty.isSelected())
					{
						commercialTypePanel.setEnabled(true);
						industryRB.setEnabled(true);
						shoppingRB.setEnabled(true);
						hazardRB.setEnabled(true);
						rProperty.setEnabled(false);
					}
				if(e.getStateChange() == ItemEvent.DESELECTED)
					{
						rProperty.setEnabled(true);
						cpTypeGroup.clearSelection();
					}
			}
		}
		);  // end commercial check box anonymous listener
		readPurchases();
	    
	} // end constructor
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Commercial.CommercialType type;
			type = Commercial.CommercialType.INDUSTRY;
			Property p1 = new Commercial();
			Property p2 = new Residential();
			String userEnteredOwnerName = txtFieldName.getText();
			boolean flag = searchArray(userEnteredOwnerName);
			String ownerStreet=txtFieldStreet.getText();
			String ownerCity=txtFieldCity.getText();
			String ownerState=txtFieldState.getText();

			if (checkInput())
			{
				int zip = zipChecker(txtFieldZip.getText());
			 	try //try for the submit button handler
			 	{
			    	 
				if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Owner"))
				{
					if(!flag)
					{
							double accountB = searchOwner(userEnteredOwnerName);
							if(accountB > 0)  
							{		
									if(zip <= 0)
									{
											throw new NumberFormatException();
									}
				   	 		
				   	 		ownerArray[prePopCounter] = new Owner(txtFieldName.getText(), new Address(ownerStreet,ownerCity,ownerState,zip),
					 	                   Owner.RatingType.GOOD,accountB);
				   	 		prePopCounter++;
				   	 		clearAll();
				   	 		chooseOneJCB.requestFocus();
							} // end if 
					}
					else
					{
					    		if(zip <= 0)
								{
										throw new NumberFormatException();
								}
						for(int i=0; i<prePopCounter;i++)
						{
							if(ownerArray[i].getName().equalsIgnoreCase(userEnteredOwnerName))
							{
								ownerArray[i].setAddress(new Address(ownerStreet,ownerCity,ownerState, zip));
								 
								ownerArray[i].setAccountBalance(searchOwner(userEnteredOwnerName));
							}
							clearAll();
							chooseOneJCB.requestFocus();
						} // end for
					}
	
			} // end if owner is selected in combo box
								
				if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Property"))
				{	
					String purchaseByString = txtFieldPurchasedBy.getText();
					String userEnteredCity = txtFieldCity.getText();
					
					double price = searchProperty(userEnteredCity);

					if(cProperty.isSelected())
					{
						if(industryRB.isSelected())
						{ 
							type = Commercial.CommercialType.INDUSTRY;
						}
						else if(shoppingRB.isSelected())
						{ 
							type = Commercial.CommercialType.SHOPPING;
						}
						else if(hazardRB.isSelected())
						{ 
							type = Commercial.CommercialType.HAZARD;
						}
				   
						if(price > 0)
						{
							if(zip <= 0)
							{
								throw new NumberFormatException();
							}
							
							p1 = new Commercial(searchProperty(userEnteredCity),parseDate(txtFieldDatePurchased.getText()),
						   		 new Address(txtFieldStreet.getText(),txtFieldCity.getText(),txtFieldState.getText(),zip),
						   		 0, 0, type); 
								p1.calculateTax();
								double p1Taxes = p1.getTax();
								p1.setTax(p1Taxes); 
						}//price >0
						clearAll();
						chooseOneJCB.requestFocus();
						for(int i = 0;i<=prePopCounter; i++)
						{
							if(ownerArray[i].getName().equalsIgnoreCase(purchaseByString))
							{
								ownerArray[i].addToPropertyList(p1);
							}
						}//end for
				    		
							chooseOneJCB.requestFocus();
							
				}//end if commercial
				if(rProperty.isSelected())
				{
				   double newTaxRate = 0.0;
				   if (price< 1000000)
					   newTaxRate = .025;
				   if ((price>= 1000000) && (price < 2000000))
					   newTaxRate = .035;
				   if (price > 2000000)
					   newTaxRate = .05;
				   
				   if(price > 0)
				   {
					    if(zip <= 0)
						{
							throw new NumberFormatException();
						}
					   p2 = new Residential(price, parseDate(txtFieldDatePurchased.getText()),
							   		 new Address(txtFieldStreet.getText(),txtFieldCity.getText(),txtFieldState.getText(),zip),
							   		0, 0, newTaxRate);
					   p2.calculateTax();
					   double p2Taxes = p2.getTax();
					   p2.setTax(p2Taxes);
				   }// end if price>0
				   
				   clearAll();
				   chooseOneJCB.requestFocus();
				   for(int i = 0;i<=prePopCounter; i++)
				   {
					   if(ownerArray[i].getName().equalsIgnoreCase(purchaseByString))
					    {
					    	ownerArray[i].addToPropertyList(p2);
					    }
				   }
  
				}//end if residential
				
				 chooseOneJCB.requestFocus();
			  } // end if property is selected
				
				 

			
			} // end try button handler
				catch(OwnerDoesNotExistException oe)
				{
					JOptionPane.showMessageDialog(null,"Owner does not exist","Exception",JOptionPane.WARNING_MESSAGE);
					txtFieldName.setText("");
					txtFieldName.requestFocus();
				}
				catch(PropertyDoesNotExistException pe)
				{
					JOptionPane.showMessageDialog(null,"Property does not exist","Exception",JOptionPane.WARNING_MESSAGE);
					txtFieldCity.setText("");
					txtFieldCity.requestFocus();
				}
				catch(InsufficientFundsException ife){
					JOptionPane.showMessageDialog(null,"Insufficient Funds","Exception",JOptionPane.WARNING_MESSAGE);
					clearAll();
					chooseOneJCB.requestFocus();
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Invalid Zip", "Exception", JOptionPane.WARNING_MESSAGE);
					txtFieldZip.setText("");
					txtFieldZip.requestFocus();
				}
				catch(NullPointerException npe){}
				
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			} // if input
	  } // and actionPerformed
   }// end ButtonHandler class
		
	private class FinishHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			try
			{
				writeFile();
				
				for(int i=0;i <=prePopCounter;i++)
				{
					output += String.format(ownerArray[i].getName() + ownerArray[i].createTaxInvoice()+" \n");
					
					String properties ="";
					System.out.printf(ownerArray[i].getName()+"'s properties:\n");
					properties += String.format(ownerArray[i].getPropertyList()+"\n");
					System.out.println(properties);
				}
			}
			catch(NullPointerException npe)
			{
			}
			catch(RejectedExecutionException ree)
			{
			}
			JOptionPane.showMessageDialog(null, output,"Tax Invoices", JOptionPane.PLAIN_MESSAGE);
		  System.exit(0);
	    } // end actionPerformed
	} // end inner class FinishHandler
		
	public void clearAll()
	{
		txtFieldName.setText("");
		txtFieldStreet.setText("");
		txtFieldCity.setText(null);
		txtFieldState.setText(null);
		txtFieldZip.setText(null);
		txtFieldDatePurchased.setText(null);
		txtFieldPurchasedBy.setText(null);
		chooseOneJCB.setSelectedIndex(0);
		cProperty.setSelected(false);
		rProperty.setSelected(false);
		cpTypeGroup.clearSelection();
	} // end clearAll
		
	public double searchOwner(String nameIn) throws OwnerDoesNotExistException
	{
	  Scanner input;
	  double abDouble = 0.0;
	  String accountBalanceString = "";
	  boolean ownerExists = false;
	 
	  try
	  {
		input = new Scanner(new File("owners.txt"));
		
		while (input.hasNext())
		{
			String name = input.nextLine();
			if (nameIn.equalsIgnoreCase(name))
				{
				nameIn = name;
				accountBalanceString = input.nextLine();
				abDouble = Double.parseDouble(accountBalanceString);
				ownerExists = true;
				}
		}//end while
	 }//end try
	 catch(IOException ex)
	 {
		 ex.printStackTrace(); 
	 }
	 if(!ownerExists)
		{
			throw new OwnerDoesNotExistException();
		}
	
		 return abDouble;
	 
  }//end searchOwner
	public double searchProperty(String cityIn) throws PropertyDoesNotExistException
	{
		  Scanner input2;
		  String city;
		  double propertyPriceDouble = 0.0;
		  String propertyPriceString = "";
		  boolean propertyExists = false;
		  try
		  {
			 input2 = new Scanner(new File("properties.txt"));
			 while (input2.hasNextLine())
			{
				city = input2.nextLine();
				if (city.equalsIgnoreCase(cityIn))
				{
					cityIn = city;
					propertyPriceString = input2.nextLine();
					propertyPriceDouble = Double.parseDouble(propertyPriceString);
					propertyExists = true;
				}
			} // end while
		  }
		catch(IOException ex)
		 {
			 ex.printStackTrace();}
			 
			if(!propertyExists)
			{ 
				throw new PropertyDoesNotExistException();
			} 
			 return propertyPriceDouble;
		
	}//end searchProperty
	public boolean checkInput()
	{
		if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Select One"))
		{	
			JOptionPane.showMessageDialog(null,"Please select Owner or Property from dropdown box", "Incomplete Form",
										  JOptionPane.WARNING_MESSAGE);
			chooseOneJCB.requestFocus();
			return false;
		}
		if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Owner") && (txtFieldName.getText().equals("")))
		{
			JOptionPane.showMessageDialog(null,"Please enter name", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldName.requestFocus();
			return false;
		}
		if(txtFieldStreet.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter street", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldStreet.requestFocus();
			return false;
		}
		if(txtFieldCity.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter city", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldCity.requestFocus();
			return false;
		}
		if(txtFieldState.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter state", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldState.requestFocus();
			return false;
		}
		if((txtFieldZip.getText().equals("")))
		{
			JOptionPane.showMessageDialog(null,"Please enter zip", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldZip.requestFocus();
			return false;
		}
		if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Property") && ((rProperty.isSelected() == false))&& 
				(cProperty.isSelected() == false))
		{
			JOptionPane.showMessageDialog(null,"Please select type of property", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Property") && ((cProperty.isSelected() == true)) && 
				( shoppingRB.isSelected() == false && industryRB.isSelected()== false && hazardRB.isSelected() == false ))
		{
			JOptionPane.showMessageDialog(null,"Please select type of commercial property", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Property") && (txtFieldDatePurchased.getText().equals("")))
		{
			JOptionPane.showMessageDialog(null,"Please enter date purchased", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldDatePurchased.requestFocus();
			return false;
		}
		if(chooseOne[chooseOneJCB.getSelectedIndex()].equals("Property") && (txtFieldPurchasedBy.getText().equals("")))
		{
			JOptionPane.showMessageDialog(null,"Please enter purchased by", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldPurchasedBy.requestFocus();
			return false;
		}
		return true;
	}//end check Input
	public Date parseDate(String date)
	{
		int index1 = 0; 
		int index2 = 0;
		int returnMonth = 0; 
		int returnYear = 0;
			
		index2 = date.indexOf('/');
		returnMonth = Integer.parseInt(date.substring(index1,index2) );

		index1 = date.indexOf('/');
		index1++;
		index2++;

		index2 = date.lastIndexOf('/');
		int returnDay = Integer.parseInt(date.substring(index1, index2) );

		index2++;
		returnYear = Integer.parseInt(date.substring(index2) );

		Date d = new Date(returnMonth, returnDay, returnYear);
		return d;
	}//end date
	
	 // method to check for valid zip 
    public int zipChecker(String zipcodetxt)
    {
        boolean flag=true;
        int zip=0;
        
        for(int i=0; i<zipcodetxt.length();i++)
        {
            if(Character.isDigit(zipcodetxt.charAt(i)) == false)
            {
            	flag = false;
            }
        }
            
        if(flag == true)
        {
            zip = Integer.parseInt(zipcodetxt);
        }
     
        return zip;
    }
    
    public void writeFile()
    {
    	try // open new .ser file
    	{
    		outP = new ObjectOutputStream(new FileOutputStream("customers.ser"));
    		for(int i = 0;i<prePopCounter; i++)
    		{
    		   outP.writeObject(ownerArray[i]);
    		}
    	}
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    } // end method writeFile()
    
    public void readPurchases()
    {
    	Scanner input;
    	String line;
    	
    	String[] purchaseArray = new String[10];
    	double price=0.0;
    	String datestr="";
    	int dod=0;
    	try
    	{	
    		input = new Scanner(new File("purchases.txt"));
    		
    		while(input.hasNext())
    		{
    			line = input.nextLine();
    			purchaseArray = line.split(",");
    			
    			price=Double.parseDouble(purchaseArray[0]);
    			datestr = (purchaseArray[1].trim()+"/"+purchaseArray[2].trim()+"/"+purchaseArray[3].trim());
    			dod = Integer.parseInt(purchaseArray[8].trim());
				
				   double newTaxRate = 0.0;
				   if (price< 1000000)
					   newTaxRate = .025;
				   if ((price>= 1000000) && (price < 2000000))
					   newTaxRate = .035;
				   if (price > 2000000)
					   newTaxRate = .05;
    			
    			Property p1 = new Residential(price,parseDate(datestr),new Address(purchaseArray[4].trim(),purchaseArray[5].trim(),
    					purchaseArray[6].trim(),Integer.parseInt(purchaseArray[7].trim())),0,dod,newTaxRate);

				   p1.calculateTax();
				   double p2Taxes = p1.getTax();
				   p1.setTax(p2Taxes);
    			ownerArray[prePopCounter] = new Owner();
    			ownerArray[prePopCounter].setName(purchaseArray[9]);
    
    			ownerArray[prePopCounter].setPropertyList(p1);
    			
    			prePopCounter++;
    			
    		}
			
			
			
			

    	}//end try
    	catch(IOException ioe)
    	{
    		ioe.printStackTrace();
    	}
    }

    public boolean searchArray(String name)
    {
    	boolean flag=false;
    	
    	try
    	{
	    	for(int i=0;i <= ownerArray.length;i++)
	    	{
	    		if(ownerArray[i].getName().trim().equalsIgnoreCase(name))
	    		{
	    			flag =true;
	    		}
	    		
	    	}//end for
    	
    	}//end try
    	catch(NullPointerException npe)
    	{	
    	}
    	return flag;
    }//end searchArray
}//end RealEstateGUI class


