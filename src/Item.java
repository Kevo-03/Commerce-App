public abstract class Item implements Service 
{
    private String type;
    private String itemName; 
    private double itemPrice; 
    private int itemQuantity;
    private Provider itemProvider;

    public Item(String name, double price, int quantity, Provider provider, String type)
    {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setProvider(provider);
        setType(type);
    }

    public abstract void updateQuantity(int purchasedQuantity);
  

    public  abstract double calculateVolumetricWeight();


    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public void setName(String name)
    {
        itemName = name;
    }

    public String getName()
    {
        return itemName;
    }

    public void setPrice(double price)
    {
        itemPrice = price;
    }

    public double getPrice()
    {
        return itemPrice;
    }

    public void setQuantity(int quantity)
    {
        itemQuantity = quantity;
    }

    public int getQuantity()
    {
        return itemQuantity;
    }

    public void setProvider(Provider provider)
    {
        itemProvider = provider;
    }

    public Provider getProvider()
    {
        return itemProvider;
    }
}
