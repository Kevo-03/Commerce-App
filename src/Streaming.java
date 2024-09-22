public class Streaming implements Service 
{
    private String title;
    private String category;
    private double costPerDay;
    private int days;
    private String type;
    
    public Streaming(String title, String category, double cost, int days)
    {
        setTitle(title);
        setCategory(category);
        setCost(cost);
        setDays(days);
        setType();
    }

    @Override
    public String displayServiceInfo()
    {
        String info = "Title: " + getTitle() + "\n" + "Category: " + getCategory() + "\n" + "Cost per Day: " + getCost() + "\n" + "Rental Days: " + getDays();
        return info;
        //System.out.printf("Title: %s\nCategory: %s\nCost Per Day: $%.2f\nRental Days: %d\n",getTitle(),getCategory(),getCost(),getDays());
    }

    @Override
    public double calculateService()
    {
        return (getCost()*getDays() + calculateShippingFee());
    }

    @Override
    public double calculateShippingFee()
    {
        return 0;
    }

    public void setType()
    {
        type = "Streaming";
    }

    @Override
    public String getServiceType()
    {
        return type;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCost(double cost)
    {
        costPerDay = cost;
    }

    public double getCost()
    {
        return costPerDay;
    }

    public void setDays(int days)
    {
        this.days = days;
    }

    public int getDays()
    {
        return days;
    }
}
