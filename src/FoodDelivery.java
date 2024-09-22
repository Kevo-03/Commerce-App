public class FoodDelivery implements Service
{
    private String foodName;
    private String restaurantName;      
    private double mealPrice;
    private int count;
    private String type;
    
    public FoodDelivery(String fName, String rName, double price, int count)
    {
        setFoodName(fName);
        setRestaurantName(rName);
        setPrice(price);
        setCount(count);
        setType();
    }

    @Override
    public String displayServiceInfo()
    {
        String info = "Food Name: " + getFoodName() + "\n" + "Restaurant Name: " + getRestaurantName() + "\n" + "Meal Price: " + getPrice() + "\n" + "Food Count: " + getCount();
        return info;
        //System.out.printf("Food Name: %s\nRestaurant Name: %s\nMeal Price: $%.2f\nFood Count: %d\n",getFoodName(),getRestaurantName(),getPrice(),getCount());
    }

    @Override
    public double calculateService()
    {
        return (getPrice()*getCount() + calculateShippingFee());
    }

    @Override
    public double calculateShippingFee()
    {
        return 5;
    }

    public void updateCount(int purchasedCount)
    {
        //This function was not specified in the assignment pdf but ı couldn't figure out how can ı update the quantity of a food delivery service
        //after an order is placed without this kind of function so ı added it
        setCount(getCount() - purchasedCount);
    }

    public void setType()
    {
        type = "FoodDelivery";
    }

    @Override
    public String getServiceType()
    {
        return type;
    }

    public String getType()
    {
        return type;
    }

    public void setFoodName(String name)
    {
        foodName = name;
    }

    public String getFoodName()
    {
        return foodName;
    }

     public void setRestaurantName(String name)
    {
        restaurantName = name;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setPrice(double price)
    {
        mealPrice = price;
    }

    public double getPrice()
    {
        return mealPrice;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getCount()
    {
        return count;
    }
}
