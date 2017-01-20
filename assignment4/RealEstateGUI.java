//Vince V
//INSY 4305
//Homework 4
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;	

public class RealEstateGUI extends JFrame implements Serializable
{
	String output="";
	
	private JDesktopPane theDesktop;
	
	private JLabel jlblName;
	private JLabel jlblStreet;
	private JLabel jlblCity;
	private JLabel jlblState;
	private JLabel jlblZip;

	private JLabel jlblStreetProperty;
	private JLabel jlblCityProperty;
	private JLabel jlblStateProperty;
	private JLabel jlblZipProperty;
	private JLabel jlblSelectTypeOfProperty;
	private JLabel jlblTypeOfCommercialProperty;
	private JLabel jlblPropertyPrice;
	private JLabel jlblDatePurchased;
	private JLabel jlblPurchasedBy;
	
	private JTextField txtFieldName;
	private JTextField txtFieldStreet;
	private JTextField txtFieldCity;
	private JTextField txtFieldState;
	private JTextField txtFieldZip;
	private JTextField txtFieldStreetProperty;
	private JTextField txtFieldCityProperty;
	private JTextField txtFieldStateProperty;
	private JTextField txtFieldZipProperty;
	private JTextField txtFieldPropertyPrice;
	private JTextField txtFieldDatePurchased;
	private JTextField txtFieldPurchasedBy;
	
	private JCheckBox cProperty;
	private JCheckBox rProperty;
	
	private JRadioButton industryRB;
	private JRadioButton shoppingRB;
	private JRadioButton hazardRB;

	private JButton submitJBOwner;
	private JButton finishJBOwner;
	private JButton submitJBProperty;
	private JButton finishJBProperty;

	private JPanel buttonPanelTypeofProperty = new JPanel();// panel for check boxes for type of property
	private JPanel commercialTypePanel = new JPanel(); // panel for check boxes for commercial type

	private ButtonGroup cpTypeGroup = new ButtonGroup();
	
	JInternalFrame ownerFrame;
	JInternalFrame propertyFrame;

	private DBMethods dbMethods = new DBMethods();;
			
	public RealEstateGUI()
	{
		super("Mav Real Estate");	
		
		// establish database connection
		dbMethods.connectDatabase(); 
		
		theDesktop = new JDesktopPane();
				
		jlblName = new JLabel("  Name");
		jlblStreet = new JLabel("  Street");
		jlblCity = new JLabel("  City");
		jlblState = new JLabel("  State");
		jlblZip = new JLabel("  Zip");
		jlblStreetProperty = new JLabel("  Street");
		jlblCityProperty  = new JLabel("  City");
		jlblStateProperty  = new JLabel("  State");
		jlblZipProperty  = new JLabel("  Zip");
		jlblSelectTypeOfProperty = new JLabel("  Select type of property");
		jlblTypeOfCommercialProperty = new JLabel("  Select type of commercial property");
		jlblSelectTypeOfProperty = new JLabel("  Select type of property");
		jlblPropertyPrice = new JLabel("  Property Price"); 
		jlblDatePurchased = new JLabel("  Date Purchased");
		jlblPurchasedBy = new JLabel("  Purchased By");
		
		txtFieldName = new JTextField();
		txtFieldStreet = new JTextField();
		txtFieldCity = new JTextField();
		txtFieldState = new JTextField();
		txtFieldZip = new JTextField();
		txtFieldStreetProperty = new JTextField();
		txtFieldCityProperty = new JTextField();
		txtFieldStateProperty = new JTextField();
		txtFieldZipProperty = new JTextField();
		txtFieldPropertyPrice = new JTextField();
        txtFieldPropertyPrice.setToolTipText("00.00");
		txtFieldDatePurchased = new JTextField();
		txtFieldDatePurchased.setToolTipText("mm/dd/yyyy");
		txtFieldPurchasedBy = new JTextField();
				
		cProperty = new JCheckBox("COMMERCIAL");
		rProperty = new JCheckBox("RESIDENTIAL");
		
		submitJBOwner = new JButton("SUBMIT");
		finishJBOwner = new JButton("FINISH");
		submitJBProperty = new JButton("SUBMIT");
		finishJBProperty = new JButton("FINISH");
		
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
		
		JMenuBar bar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu dbMenu = new JMenu("Database");
		JMenu aboutMenu = new JMenu("About");
		
		JMenuItem ownerItem = new JMenuItem("Owner");
		JMenuItem propertyItem = new JMenuItem("Property");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem writeDB = new JMenuItem("Write to Database");
		JMenuItem readDB = new JMenuItem("Read Database");
		JMenuItem aboutItem = new JMenuItem("Written by Vince");
		
		fileMenu.add(ownerItem);
		fileMenu.add(propertyItem);
		fileMenu.add(exitItem);
		
		dbMenu.add(writeDB);
		dbMenu.add(readDB);
		
		aboutMenu.add(aboutItem);
		
		bar.add(fileMenu);
		bar.add(dbMenu);
		bar.add(aboutMenu);
		
		setJMenuBar(bar);
		
		add(theDesktop);
		
		writeDB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				dbMethods.writeDatabase();
			}
		}
		); 
		readDB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				dbMethods.readDatabase();
			}
		}
		); 
		exitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(0);
				dbMethods.closeDatabase();
			}
		}
		);
		ownerItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				ownerFrame = new JInternalFrame("Owner", true, true, true, true);
				
				OwnerPanel oPanel = new OwnerPanel();
				ownerFrame.add(oPanel);
				ownerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ownerFrame.setSize(500,400);
				theDesktop.add(ownerFrame);
				ownerFrame.setVisible(true);
			}		
		}
		);
		propertyItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				propertyFrame = new JInternalFrame("Property", true, true, true, true);
				
				PropertyPanel pPanel = new PropertyPanel();
				propertyFrame.add(pPanel);
				propertyFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
				propertyFrame.setSize(550, 400);
				theDesktop.add(propertyFrame);
				propertyFrame.setVisible(true);
			}
		}
		);
		ButtonHandlerO sHandlerO = new ButtonHandlerO();
		ButtonHandlerP sHandlerP = new ButtonHandlerP();
		submitJBOwner.addActionListener(sHandlerO);
		submitJBProperty.addActionListener(sHandlerP);
		finishJBOwner.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
					ownerFrame.setVisible(false);
					ownerFrame.dispose();
			}
		});
		finishJBProperty.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
					propertyFrame.setVisible(false);
					propertyFrame.dispose();
			}
		});
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
			} // end itemStateChanged
		} // end cProperty addItemListener
		);  // end commercial check box anonymous listener
		
		dbMethods.deductTaxesFromCurrentOwners(); // to deduct taxes from Owners already in PROPERTIES table

	} // end constructor
	class OwnerPanel extends JPanel
	{
	   public OwnerPanel()
	   {
		   setLayout(new GridLayout(6,2));
		   
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
		   add(submitJBOwner);
		   add(finishJBOwner);
	   }
	} // end class OwnerPanel
	
	class PropertyPanel extends JPanel
	{
		public PropertyPanel()
		{
			setLayout(new GridLayout(10,2));
			
		    add(jlblStreetProperty);
			add(txtFieldStreetProperty);
			add(jlblCityProperty);
			add(txtFieldCityProperty);
			add(jlblStateProperty);
			add(txtFieldStateProperty);
			add(jlblZipProperty);
			add(txtFieldZipProperty);
			add(jlblSelectTypeOfProperty);
			add(buttonPanelTypeofProperty);
			add(jlblTypeOfCommercialProperty);
			add(commercialTypePanel);
			add(jlblDatePurchased);
			add(txtFieldDatePurchased);
			add(jlblPurchasedBy);
			add(txtFieldPurchasedBy);
			add(jlblPropertyPrice);
			add(txtFieldPropertyPrice);
			add(submitJBProperty);
			add(finishJBProperty);
		} // end constructor
	} // end class PropertyPanel
	private class ButtonHandlerO implements ActionListener //Owner Submit Button class
	{
		public void actionPerformed(ActionEvent e) 
		{
			String userEnteredOwnerName = txtFieldName.getText();
			boolean flag = dbMethods.searchOwners(userEnteredOwnerName); 
			String ownerStreet = txtFieldStreet.getText();
			String ownerCity=txtFieldCity.getText();
			String ownerState=txtFieldState.getText();
			String ownerName=txtFieldName.getText();
			
			if (checkInputOwner())
			{
			   int ownerZip = zipChecker(txtFieldZip.getText());
			   try //try for the submit button handler
			   {
  						if (dbMethods.searchDOD(userEnteredOwnerName))
						{	
						   throw new OverdueException();
						}
				
					if(!flag) 
					{
							double accountB = 250000.0;
									if(ownerZip <= 0)
									{
									   throw new NumberFormatException();
									}
						   	 		
				   	 		dbMethods.writeOwner(ownerName,ownerStreet,ownerCity,ownerState,ownerZip, accountB);
				   	 		   
				   	 		clearAll();
					}// end if (!flag)
					else
					{
						if(ownerZip <= 0)
						{
						   throw new NumberFormatException();
						} //end if(zip <= 0)

						JOptionPane.showMessageDialog(null, "Owner is already in the database");
						txtFieldName.setText("");
						txtFieldName.requestFocus();
					} // end else
				} // end try
			   catch(OverdueException ode)
			   {
					JOptionPane.showMessageDialog(null, "Delinquent Customer!", "Exception", JOptionPane.WARNING_MESSAGE);
					txtFieldName.setText("");
					txtFieldName.requestFocus();
			   }
				catch(OwnerDoesNotExistException oe)
				{
					JOptionPane.showMessageDialog(null,"Owner does not exist","Exception",JOptionPane.WARNING_MESSAGE);
					txtFieldName.setText("");
					txtFieldName.requestFocus();
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Invalid Zip", "NumberFormatException", JOptionPane.WARNING_MESSAGE);
					txtFieldZip.setText("");
					txtFieldZip.requestFocus();
					//nfe.printStackTrace();
				}
				catch(NullPointerException npe)
				{
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				} 
			  } // if input
			} // end actionPerformed
	} // end if Owner button class 
	private class ButtonHandlerP implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Commercial.CommercialType type;
			type = Commercial.CommercialType.INDUSTRY;
			String purchaseByString = txtFieldPurchasedBy.getText();
			String pStreet=txtFieldStreetProperty.getText();
			String pCity=txtFieldCityProperty.getText();
			String pState=txtFieldStateProperty.getText();
			double subtractfromAB = 0.0;

			if (checkInputProperty())
			{
			   int pZip = zipChecker(txtFieldZipProperty.getText());
			   try //try for the submit button handler
			   {
				   if (dbMethods.searchDOD(purchaseByString))
				   {		   
				       throw new OverdueException();
				   } // end if overdue owner

					String date = txtFieldDatePurchased.getText();
					double price = Double.parseDouble(txtFieldPropertyPrice.getText());
					boolean flagger = dbMethods.searchOwners(purchaseByString);
					
					if(!flagger)
					{
						throw new OwnerDoesNotExistException();
					} // end if(flagger)
					
					if(price>dbMethods.getPurchaserBalance(purchaseByString))
					{
						throw new InsufficientFundsException();
					}
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
	
								if(pZip <= 0)
								{
									throw new NumberFormatException();
								}
								
								dbMethods.writeProperty(price, "Commercial", type.toString(), date, pStreet, 
										pCity, pState, pZip, 0, purchaseByString);
								
								dbMethods.writeOwnerProperties(purchaseByString);
								
								//Price of the property and its tax to subtract from the owner's balance
								subtractfromAB = (price*(dbMethods.queryTaxRate(type.toString())/100)) + price;
								dbMethods.updateOwnerBalance(purchaseByString, subtractfromAB);
									
							clearAllProperty();

					}//end if commercial
					if(rProperty.isSelected())
					{
						    if(pZip <= 0)
							{
								throw new NumberFormatException();
							}
					   
							dbMethods.writeProperty(price, "Residential", "null", date, pStreet, 
									txtFieldCityProperty.getText(), txtFieldStateProperty.getText(), pZip, 0, purchaseByString);
							
							dbMethods.writeOwnerProperties(purchaseByString);
							
							if(pZip==76109)
							{
								subtractfromAB = ((price*(dbMethods.queryTaxRate("Residential")/100)) * 1.03) + price;
							}
							else
							subtractfromAB = (price*(dbMethods.queryTaxRate("Residential")/100)) + price;// not done
							
							dbMethods.updateOwnerBalance(purchaseByString, subtractfromAB);
					   
					   clearAllProperty();
					}//end if residential

			} // end try button handler
			   catch(OverdueException ode)
			   {
					JOptionPane.showMessageDialog(null, "Delinquent payment history!\nCannot buy property", "Exception", JOptionPane.WARNING_MESSAGE);
					txtFieldPurchasedBy.setText("");
					txtFieldPurchasedBy.requestFocus();
			   }	
			   catch(OwnerDoesNotExistException odne)
				{
				   JOptionPane.showMessageDialog(null, "Owner is not in database","Exception", JOptionPane.WARNING_MESSAGE);
				   txtFieldPurchasedBy.setText("");
				   txtFieldPurchasedBy.requestFocus();
				}
				catch(InsufficientFundsException ife)
				{
					JOptionPane.showMessageDialog(null,"Insufficient Funds","Exception",JOptionPane.WARNING_MESSAGE);
					clearAllProperty();
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Invalid Zip/NumberFormatException", "Exception", JOptionPane.WARNING_MESSAGE);
					txtFieldZipProperty.setText("");
					txtFieldZipProperty.requestFocus();
					//nfe.printStackTrace();
				}
				catch(NullPointerException npe)
				{
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			} // if input
	  } // and actionPerformed
   }// end ButtonHandler class
	public void clearAll()
	{
		txtFieldName.setText("");
		txtFieldStreet.setText("");
		txtFieldCity.setText(null);
		txtFieldState.setText(null);
		txtFieldZip.setText(null);
	} // end clearAll
	
	public void clearAllProperty()
	{
		txtFieldStreetProperty.setText("");
		txtFieldCityProperty.setText(null);
		txtFieldStateProperty.setText(null);
		txtFieldZipProperty.setText(null);
		txtFieldPropertyPrice.setText(null);
		txtFieldDatePurchased.setText(null);
		txtFieldPurchasedBy.setText(null);
		cProperty.setSelected(false);
		rProperty.setSelected(false);
		cpTypeGroup.clearSelection();
	} // end clearAll

	public boolean checkInputOwner()
	{
		if((txtFieldName.getText().equals("")))
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
		return true;
	}//end checkInputOwner
	
	public boolean checkInputProperty()
	{
		if(txtFieldStreetProperty.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter street", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldStreetProperty.requestFocus();
			return false;
		}
		if(txtFieldCityProperty.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter city", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldCityProperty.requestFocus();
			return false;
		}
		if(txtFieldStateProperty.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter state", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldStateProperty.requestFocus();
			return false;
		}
		if((txtFieldZipProperty.getText().equals("")))
		{
			JOptionPane.showMessageDialog(null,"Please enter zip", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldZipProperty.requestFocus();
			return false;
		}
		if(((rProperty.isSelected() == false))&&(cProperty.isSelected() == false))
		{
			JOptionPane.showMessageDialog(null,"Please select type of property", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(((cProperty.isSelected() == true)) && ( shoppingRB.isSelected() == false && industryRB.isSelected()== false && hazardRB.isSelected() == false ))
		{
			JOptionPane.showMessageDialog(null,"Please select type of commercial property", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(txtFieldDatePurchased.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter date purchased", "Incomplete Form",
					  JOptionPane.WARNING_MESSAGE);
			txtFieldDatePurchased.requestFocus();
			return false;
		}
		if(txtFieldPurchasedBy.getText().equals(""))
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
}//end RealEstateGUI class


