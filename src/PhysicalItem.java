public class PhysicalItem extends Item 
{
    private double itemWidth;
    private double itemLength;
    private double itemHeight;

    public PhysicalItem(String name, double price, int quantity, Provider provider,String type, double width, double length, double height)
    {
        super(name, price, quantity, provider,type);
        setWidth(width);
        setLength(length);
        setHeight(height);
    }    

    @Override
    public double calculateVolumetricWeight()
    {
        return (getHeigth()*getLength()*getWidth())/5000;
    }

    @Override
    public double calculateShippingFee()
    {
        if(calculateVolumetricWeight() >= 5)
        {
            return getPrice()*calculateVolumetricWeight()*(0.5);
        }
        
        else if(calculateVolumetricWeight() >= 2)
        {
            return getPrice()*calculateVolumetricWeight()*(0.35);
        }

        else if(calculateVolumetricWeight() >= 1)
        {
            return getPrice()*calculateVolumetricWeight()*(0.25);
        }

        else
        {
            return getPrice()*calculateVolumetricWeight()*(0.10);
        }
    }

    @Override
    public void updateQuantity(int purchasedQuantity)
    {
        setQuantity(getQuantity() - purchasedQuantity);
    }

    @Override 
    public double calculateService()
    {
        return (getPrice()*getQuantity() + calculateShippingFee());
    }

    @Override
    public String getServiceType()
    {
        return getType();
    }

    @Override 
    public String displayServiceInfo()
    {
        String info = "Item: " + getName() + "\n" + "Price: " + getPrice() + "\n" + "Quantity: " + getQuantity();
        return info;
        //System.out.printf("Item : %s\nPrice: $%.2f\nQuantity: %d\nProvider: %s\nProvider Contact: +%s(%s)%s\nWidth: %.1f, Length: %.1f, Height: %.1f\n",getName(),getPrice(),getQuantity(),getProvider().getName(),getProvider().getCountry(),getProvider().getArea(),getProvider().getPhone(),getWidth(),getLength(),getHeigth());
    }

    public void setWidth(double width)
    {
        itemWidth = width;
    }

    public double getWidth()
    {
        return itemWidth;
    }

    public void setLength(double length)
    {
        itemLength = length;
    }

    public double getLength()
    {
        return itemLength;
    }

    public void setHeight(double heigth)
    {
        itemHeight = heigth;
    }

    public double getHeigth()
    {
        return itemHeight;
    }
}
