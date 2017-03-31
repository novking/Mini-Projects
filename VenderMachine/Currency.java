
public class Currency 
{
	//initiate instances
	private String handler;
	private String type;
	private String name;
	private double value;
	private String size;
	private String material;
	private int quantity;
	
	//getters and setters
	public void setHandler(String handler)
	{
		this.handler=handler;
	}

	public String getHandler()
	{
		return this.handler;
	}
	
	public void setType(String type)
	{
		this.type=type;
	}

	public String getType()
	{
		return this.type;
	}

	public void setValue(double value)
	{
		this.value=value;
	}

	public double getValue()
	{
		return this.value;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setSize(String size)
	{
		this.size=size;
	}
	
	public String getSize()
	{
		return this.size;
	}

	public void setMaterial(String material)
	{
		this.material=material;
	}
	
	public String getMaterial()
	{
		return this.material;
	}

	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public String toString()
	{
		return "CurrencyInventory: " + this.type + " CurrencyUnit: " + this.name + " Value: " + this.value + " Size: " + this.size + " Material: " 
				+ this.material ;
	}
}
