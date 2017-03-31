
public class Product 
{
	//initiate instances
	private String name;
	private double price;
	private String container;
	private String unit;
	private double size;
	private int quantity;
	
	//getters and setters
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setPrice(double price)
	{
		this.price=price;
	}
	
	public void setContainer(String container)
	{
		this.container=container;
	}
	
	public void setUnit(String unit)
	{
		this.unit=unit;
	}
	
	public void setSize(double size)
	{
		this.size=size;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public String getContainer()
	{
		return this.container;
	}
	
	public String getUnit()
	{
		return this.unit;
	}
	
	public double getSize()
	{
		return this.size;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public String toSting()
	{
		return "Inventoryitem: Name: " + this.name + " Price: " + this.price + " Container: " + this.container 
				+ " Unit: " + unit + " Size: " + size + " Quantity: " + quantity + " in stock" ;
	}
	
	
	
}







