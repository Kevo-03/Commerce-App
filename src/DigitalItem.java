public class DigitalItem extends Item
{
    private double diskSpace;
    
    public DigitalItem(String name, double price, int quantity, Provider provider,String type, double space)
    {
        super(name, price, quantity, provider,type);
        setSpace(space);
    }

    @Override
    public void updateQuantity(int purchasedQuantity)
    {
        setQuantity(getQuantity() - purchasedQuantity);
    }

    @Override
    public double calculateVolumetricWeight()
    {
        return 0;
    }

    @Override
    public double calculateShippingFee()
    {
     return 0;
    }

    @Override 
    public double calculateService()
    {
        return (getPrice()*getQuantity());
    }

    @Override
    public String getServiceType()
    {
        return getType();
    }

    @Override 
    public String displayServiceInfo()
    {
        String info = "Item: " + getName() + "\n" + "Price: " + getPrice() + "\n" + "Quantity: " + getQuantity() + "\n" + "Disk Space: " + getSpace();
        return info;
        //System.out.printf("Item : %s\nPrice: $%.2f\nQuantity: %d\nProvider: %s\nProvider Contact: +%s(%s)%s\nDisk Space: %.1f\n",getName(),getPrice(),getQuantity(),getProvider().getName(),getProvider().getCountry(),getProvider().getArea(),getProvider().getPhone(),getSpace());
    }

     public void setSpace(double space)
    {
        diskSpace = space;
    }

    public double getSpace()
    {
        return diskSpace;
    }
}
