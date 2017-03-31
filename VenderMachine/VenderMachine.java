import java.util.Scanner;

public class VenderMachine {
	private Product [] loadProduct (String name[], double[] price, String[] container, String[] unit, double[] size, int[] quantity) {
		Product [] items = new Product[name.length];
		for (int i=0; i<items.length;i++ )
		{
			items[i] = new Product();
			items[i].setName(name[i]);
			items[i].setPrice(price[i]);
			items[i].setContainer(container[i]);
			items[i].setUnit(unit[i]);
			items[i].setSize(size[i]);
			items[i].setQuantity(quantity[i]);						
		}
	}

	private static void ShowCommand()  // all the methods should be private in this case. coz the user only need go through 'option' you provide.
	{
		System.out.print("Available Commands\n\n"
				 + "Show Commands:     0\n"
				 + "Display Inventory: 1\n"
				 + "Display Currency:  2\n"
				 + "Purchase items:     3\n"
				 + "Exit:             -1\n\n\n"
				 + "Command: ");		
	}
	
	
	private static void displayProduct(Product items[])
	{
		for(int i = 0; i < items.length; i++) // how many times i have to say. you need the space.
		{	
			String outputMessage = String.format("%d : %s", i, items[i].toSting()); // this is the best practice
			System.out.println(outputMessage);
		}
		System.out.println();		
	}
	
	private static void displayCurrency(Currency currency[])
	{
		System.out.println("Currency Handler: " + currency[0].getHandler()); // what is this? i don't understand...
		System.out.println("Handles Paper Currency: " + checkCurrency(currency, "paper", false)); // 
		System.out.println("Handles Coins Currency: " + checkCurrency(currency, "metal", false));
		System.out.println("Provides change paper: " + checkCurrency(currency, "paper", true));
		System.out.println("Provides change coin: " + checkCurrency(currency, "metal", true));

		for(int i=0;i<currency.length;i++) // spacing
		{
			System.out.println(currency[i].toString() + " Quantity: " + currency[i].getQuantity() + " in stock.");
		}
		System.out.println();
	}
	
	private static void makePurchase(double itemPrice, double payValue, Product items[], int itemNumber, int diffCurrency,
									Currency currency[], int currencyType[], int currencyCount[])
	{
		if (itemPrice<=payValue && items[itemNumber].getQuantity()>0)
		{
			items[itemNumber].setQuantity(items[itemNumber].getQuantity()-1);
			for (int k=0; k<diffCurrency; k++)
			{
				currency[currencyType[k]].setQuantity(currency[currencyType[k]].getQuantity() + currencyCount[k]);
				// why put everything together. you need to separate them. make some intermedia constant or something. I'm not going to read this.
			}
			System.out.println("\nitems Purchased");
		}
		else if(itemPrice>payValue)
		{
			System.out.println("Inadaquate funds\tPaid: " + payValue +"items cost: "+ itemPrice +
								"Purchase failed.\tTry another time");
		}
		else if(items[itemNumber].getQuantity() <= 0) // i believe it's <= instead of >
		{
			System.out.println("inadaquate item for purchase\nTry other item");
		}
	}
	
	//check if changes are enough 
	private static boolean isEnoughChange(double payValue,double itemPrice,Currency currency[])
	{
		double changeValue = payValue-itemPrice;
		for(int i=0; i<currency.length; i++)
		{			
			changeValue -= currency[i].getValue() * currency[i].getQuantity();
			// you dont need while. it should only run once. and it will only run once.

			//minor change. early stop
			if (changeValue <= 0) {
				return true;
			}
		}
		return changeValue<=0;
	}
	
	
	//calculate and get change
	private static void returnChange(double payValue,double itemPrice,Currency currency[])
	{
		double changeValue = payValue-itemPrice;
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
	

	// all this can be combine into one. also, make it private. you don't want others know this method.
	private static boolean checkCurrency (Currency currency[], String currencyType, boolean needQuantityCheck){
		for (int i=0; i<currency.length ;i++)
		{
			if(currency[i].getMaterial() == currencyType)
			{	
				if((needQuantityCheck && currency[i].getQuantity() > 0) || !needQuantityCheck) { 
				// this logic is not that complex, think it through please. you believe in you.
					return true;
				}
			}				
		}
		return false;	
	}
	
	//machine command method
	private static void machineCommand(Product items[],Currency currency[])
	{
		Scanner in=new Scanner(System.in);
		ShowCommand();
		int command=in.nextInt();
		
		while(command!=-1) // really ugly. you can make switch as its own function.
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
					displayProduct(items);
					break;
				}
				case 2:
				{
					displayCurrency(currency);
					break;
				}
				case 3:
				{
					System.out.print("items #: ");
					int itemNumber=in.nextInt();
					double itemPrice=items[itemNumber].getPrice();
					
					System.out.print("How many diffrent bill type?: ");
					int diffCurrency=in.nextInt();
					int [] currencyType=new int[diffCurrency];
					int [] currencyCount=new int[diffCurrency];
					double payValue=0;
					
					for (int k=0; k<diffCurrency; k++)
					{
						for(int i=0; i<currency.length; i++)
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

					// this part design is not so good
					// i would check both if there is enough change and enough item at the same time. this is the 'check' step
					// then do make purchase, which will return change and update item quantity at the same time. this is the 'executation' step.
					// coz you look at your function, all three functions' inputs are almost the same. and makePurchase will still do 'check'. then returnChange will still 'update'
					// you need to make method with clear logic.
					if(isEnoughChange(itemPrice, payValue, currency)) // spacing
					{
						makePurchase( itemPrice,  payValue,  items, itemNumber,  diffCurrency, currency,  currencyType, currencyCount);
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

	public static void main(String[] args)  // put all your method IN FRONT OF main. that's ALWAYS the case.
	{
		//initiate product
		String [] name = {"Apple Juice", "Hersey Chocolate"};
		double [] price = {1.0, 1.0};
		String [] container = {"Carton-thinRectangle","Bag-medium"};
		String [] unit = {"ounces", "ounces"};
		double [] size = {6.0,4.0};
		int [] quantity = {10, 10};

		Product [] items = loadProduct(name, price, container, unit, size, quantity);
		

		// please do the same thing for currency
		//initiate currency
		String handler="USD";
		String [] type={"PaperCurrency","PaperCurrency","CoinCurrency","CoinCurrency"};
		String [] currencyName={"5 Dollar Bill","1 Dollar Bill","50 cent","Quarter"};
		double [] value={5,1,0.5,0.25};
		String [] currencySize={"medium","medium","large","medium"}; // are you sure it's right? currencySize for 50 cents is large compare to bill?
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
		machineCommand(items, currency);
	}
}
