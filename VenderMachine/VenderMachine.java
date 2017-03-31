import java.util.Scanner;

public class VenderMachine {

	public static void main(String[] args) 
	{
		//initiate product
		String [] name= {"Apple Juice", "Hersey Chocolate"};
		double [] price = {1.0, 1.0};
		String [] container = {"Carton-thinRectangle","Bag-medium"};
		String [] unit = {"ounces", "ounces"};
		double [] size = {6.0,4.0};
		int [] quantity = {10, 10};
		Product [] item=new Product[name.length];
		
		//load product
		for (int i=0; i<item.length;i++ )
		{
			item[i] = new Product();
			item[i].setName(name[i]);
			item[i].setPrice(price[i]);
			item[i].setContainer(container[i]);
			item[i].setUnit(unit[i]);
			item[i].setSize(size[i]);
			item[i].setQuantity(quantity[i]);						
		}
		
		//initiate currency
		String handler="USD";
		String [] type={"PaperCurrency","PaperCurrency","CoinCurrency","CoinCurrency"};
		String [] currencyName={"5 Dollar Bill","1 Dollar Bill","50 cent","Quarter"};
		double [] value={5,1,0.5,0.25};
		String [] currencySize={"medium","medium","large","medium"};
		String [] material={"paper","paper","metal","metal"};
		int [] currencyQuantity={8,5,0,0};
		
		Currency [] currency=new Currency[currencyName.length];
		
		for(int i=0; i<currency.length;i++ )
		{
			currency[i] = new Currency();
			currency[i].setType(type[i]);
			currency[i].setHandler(handler);
			currency[i].setName(currencyName[i]);
			currency[i].setValue(value[i]);
			currency[i].setSize(currencySize[i]);
			currency[i].setMaterial(material[i]);
			currency[i].setQuantity(currencyQuantity[i]);
		}		
		machineCommand(item, currency);
	}
	
	
	
	public static void ShowCommand()
	{
		System.out.print("Available Commands\n\n"
				 + "Show Commands:     0\n"
				 + "Display Inventory: 1\n"
				 + "Display Currency:  2\n"
				 + "Purchase Item:     3\n"
				 + "Exit:             -1\n\n\n"
				 + "Command: ");		
	}
	
	
	public static void displayProduct(Product item[])
	{
		for(int i=0;i<item.length ;i++)
		{
			System.out.println(i + ": " + item[i].toSting() );
		}
		System.out.println();		
	}
	
	public static void displayCurrency(Currency currency[])
	{
		System.out.println("Currency Handler: " + currency[0].getHandler());
		System.out.println("Handles Paper Currency: " + isCurrencyPaperMaterial(currency));
		System.out.println("Handles Coins Currency: " + isCurrencyMetalMaterial(currency));
		System.out.println("Provides change paper: " + isProvidePaperMaterial(currency));
		System.out.println("Provides change coin: " + isProvideMetalMaterial(currency));
		for(int i=0;i<currency.length;i++)
		{
			System.out.println(currency[i].toString() + " Quantity: " + currency[i].getQuantity() + " in stock");
		}
		System.out.println();
		
	}
	
	public static void makePurchase(double itemPrice, double payValue, Product item[], int itemNumber, int diffCurrency,
									Currency currency[], int currencyType[], int currencyCount[])
	{
		if (itemPrice<=payValue && item[itemNumber].getQuantity()>0)
		{
			item[itemNumber].setQuantity(item[itemNumber].getQuantity()-1);
			for (int k=0; k<diffCurrency; k++)
			{
				currency[currencyType[k]].setQuantity(currency[currencyType[k]].getQuantity() + currencyCount[k]);
			}
			System.out.println("\nItem Purchased");
			
		}
		else if(itemPrice>payValue)
		{
			System.out.println("Inadaquate funds\tPaid: " + payValue +"item cost: "+ itemPrice +
								"Purchase failed.\tTry another time");
			
		}
		else if(item[itemNumber].getQuantity()>0)
		{
			System.out.println("inadaquate item for purchase\nTry other items");
		}
		

	}
	
	//check if changes are enough 
	public static boolean isChangeEnough(double payValue,double itemPrice,Currency currency[])
	{
		double changeValue = 0;		
		changeValue = payValue-itemPrice;
		for(int i=0;i<currency.length ;i++)
		{			
			while(changeValue >= currency[i].getValue())
			{
				changeValue -= currency[i].getValue()*currency[i].getQuantity();
			}				
		}
		return changeValue<=0;
	}
	
	
	//calculate and get change
	public static void returnChange(double payValue,double itemPrice,Currency currency[])
	{
		double changeValue=0;
		changeValue= payValue-itemPrice;
		if(changeValue>0)
		{
			System.out.println("Change Amount: $"+changeValue );
			for (int i=0; i<currency.length;i++)
			{
				while(changeValue>=currency[i].getValue() && currency[i].getQuantity()>0)
				{
					
					System.out.println(currency[i].toString());
					changeValue-=currency[i].getValue();
					currency[i].setQuantity(currency[i].getQuantity()-1);															
				}								
			}			
		}
	}
	
	
	public static boolean isCurrencyPaperMaterial(Currency currency[])
	{
		for (int i=0; i<currency.length ;i++)
		{
			if(currency[i].getMaterial()=="paper")
			{
				return true;
			}				
		}
		return false;		
	}
	
	
	public static boolean isCurrencyMetalMaterial(Currency currency[])
	{
		for (int i=0; i<currency.length ;i++)
		{
			if(currency[i].getMaterial()=="metal")
			{
				return true;
			}				
		}
		return false;		
	}
	
	
	public static boolean isProvidePaperMaterial(Currency currency[])
	{
		for (int i=0; i<currency.length ;i++)
		{
			if(currency[i].getQuantity()>0 && currency[i].getMaterial()=="paper")
			{
				return true;
			}				
		}
		return false;		
	}
	
	
	
	public static boolean isProvideMetalMaterial(Currency currency[])
	{
		for (int i=0; i<currency.length ;i++)
		{
			if(currency[i].getQuantity()>0 && currency[i].getMaterial()=="metal")
			{
				return true;
			}				
		}
		return false;		
	}
	
	
	
	
	//machine command method
	public static void machineCommand(Product item[],Currency currency[])
	{
		Scanner in=new Scanner(System.in);
		ShowCommand();
		int command=in.nextInt();
		
		while(command!=-1)
		{
			switch(command)
			{			
				case 0:
				{
					ShowCommand();
					break;
				}	
				case 1:
				{
					displayProduct(item);

					break;
				}
				case 2:
				{
					displayCurrency(currency);
					break;
				}
				case 3:
				{
					System.out.print("Item #: ");
					int itemNumber=in.nextInt();
					double itemPrice=item[itemNumber].getPrice();
					
					System.out.print("How many diffrent bill type?: ");
					int diffCurrency=in.nextInt();
					int [] currencyType=new int[diffCurrency];
					int [] currencyCount=new int[diffCurrency];
					double payValue=0;
					
					for (int k=0; k<diffCurrency; k++)
					{
						for(int i=0;i<currency.length ;i++)
						{
							System.out.println("Currency #:" + i + ": " + currency[i].toString());
						}
						System.out.print("Bill type: ");
						currencyType[k]=in.nextInt();
						System.out.print("Number of currency #" + currencyType[k] + ":" );
						currencyCount[k]=in.nextInt();
						payValue= payValue + currency[currencyType[k]].getValue() * currencyCount[k];
					}
					//make purchase
					if(isChangeEnough(itemPrice,payValue,currency))
					{
						makePurchase( itemPrice,  payValue,  item, itemNumber,  diffCurrency, currency,  currencyType, currencyCount);
						//calculate change
						returnChange(payValue,itemPrice,currency);
						
					}
					else
					{
						System.out.println("Not enough change, transaction cancelled");
					}
					break;										
				}
					
			}
			System.out.print("\nCommand:");
			command=in.nextInt();
			
		}
		
		in.close();
	}
}
