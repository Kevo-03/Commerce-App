import java.util.ArrayList;
import java.util.List;

public class Order implements Comparable<Order>
{
   private List<Service> services = new ArrayList<Service>();
    private Service orderService;
    private String orderType;
    private String orderName;
    private int quantity;
    private String destinationRegion;
    private String destinationCity;
    private int destinationPostCode;
    private boolean success;//I included this variable to determine whether to add the order to the order arraylist in the InventoryTest or not.
    private static int orderID = 0;
    private int specificID;

    public Order(List<Service> services, String serviceName, int quantity, String region, String city, int postCode)
    {
        this.services = services;
        setCity(city);
        setRegion(region);
        setPostCode(postCode);
        orderID++;
        int ID = orderID;
        setSpecificID(ID);
        success = checkStorage(services,serviceName,quantity);
    }

    public int compareTo(Order order)
    {
        return (int) (order.calculateOrderCost() - calculateOrderCost());
    }

 
    public boolean checkStorage(List<Service> services, String serviceName, int quantity)
    {
        for(int i = 0; i < services.size(); i++)
        {
            if(services.get(i) instanceof PhysicalItem)
            {
                PhysicalItem physical = (PhysicalItem)  services.get(i);
                if(physical.getName().equals(serviceName))
                {
                    if(physical.getQuantity() >= quantity)
                    {
                        setType("Physical");
                        setOrderName(serviceName);
                        setQuantity(quantity);
                        setService(physical,quantity);
                        physical.updateQuantity(quantity);
                        //System.out.println("Order has been placed successfully");
                        return true;
                    }
                    else
                    {
                        //System.out.println("Not enough stock in inventory");
                        return false;
                    }
                } 
                else
                {
                    continue;
                }
            }
            else if (services.get(i) instanceof DigitalItem)
            {
                DigitalItem digital = (DigitalItem)  services.get(i);  
                if(digital.getName().equals(serviceName))
                {
                    if(digital.getQuantity() >= quantity)
                    {
                        setType("Digital");
                        setOrderName(serviceName);
                        setQuantity(quantity);
                        setService(digital,quantity);
                        digital.updateQuantity(quantity);
                        //System.out.println("Order has been placed successfully");
                        return true;
                    }
                    else
                    {
                        //System.out.println("Not enough stock in inventory");
                        return false;
                    }
                } 
                else
                {
                    continue;
                }  
            }
            else if (services.get(i) instanceof FoodDelivery)
            {
                FoodDelivery food = (FoodDelivery) services.get(i);
                if(food.getFoodName().equals(serviceName))
                {
                    if(food.getCount() >= quantity)
                    {
                        setType("FoodDelivery");
                        setOrderName(serviceName);
                        setQuantity(quantity);
                        setService(food, quantity);
                        food.updateCount(quantity);
                        //System.out.println("Order has been placed successfully");
                        return true;
                    }
                    else
                    {
                        //System.out.println("Not enough stock in inventory");
                        return false;
                    }
                }
                else
                {
                    continue;
                }  
            }
            else
            {
                Streaming stream = (Streaming) services.get(i);
                if(stream.getTitle().equals(serviceName))
                {
                    setType("Streaming");
                    setOrderName(serviceName);
                    setService(stream, quantity);
                    //System.out.println("Order has been placed successfully");
                    return true;
                }
            }
           
        }
        
        //System.out.println("Item not found in inventory");
        return false;
    }


    public double calculateOrderCost()
    {
        return getService().calculateService();
    }

    public String displayOrderInfo()
    {
        if(getService() instanceof PhysicalItem)
        {
            PhysicalItem physical = (PhysicalItem) getService();
            String info = "The total cost of " + physical.getQuantity() + " " + physical.getName() + " order is: " + calculateOrderCost();
            return info;
            //System.out.printf("The total cost of %d %s order is: $%.2f\n",physical.getQuantity(), physical.getName(), calculateOrderCost());
        }
        else if(getService() instanceof DigitalItem)
        {
            DigitalItem digital = (DigitalItem) getService();
            String info = "The total cost of " + digital.getQuantity() + " " + digital.getName() + " order is: " + calculateOrderCost();
            return info;
            //System.out.printf("The total cost of %d %s order is: $%.2f\n",digital.getQuantity(), digital.getName(), calculateOrderCost());
        }
        else if(getService() instanceof FoodDelivery)
        {
            FoodDelivery food = (FoodDelivery) getService();
            String info = "The total cost of " + food.getCount() + " " + food.getFoodName() + " order is: " + calculateOrderCost();
            return info;
            //System.out.printf("The total cost of %d %s order is: $%.2f\n",food.getCount(), food.getFoodName(), calculateOrderCost());
        }
        else 
        {
            Streaming stream = (Streaming) getService();
            String info = "The total cost of " + stream.getTitle() + " order is: " + calculateOrderCost();
            return info;
            //System.out.printf("The total cost of %s order is: $%.2f\n", stream.getTitle(), calculateOrderCost());
        }
    }

    public void setService(Service service, int quantity)
    {
        if(service instanceof PhysicalItem)
        {
            PhysicalItem physical = (PhysicalItem) service;
            String name = physical.getName();
            double price = physical.getPrice();
            //int quantity = physical.getQuantity();
            Provider provider = physical.getProvider();
            String type = physical.getType();
            double width = physical.getWidth(); 
            double length = physical.getLength();
            double height = physical.getHeigth();
            orderService = new PhysicalItem(name, price, quantity, provider, type, width, length, height);
        }
        else if(service instanceof DigitalItem)
        {
            DigitalItem digital = (DigitalItem) service ;
            String name = digital.getName();
            double price = digital.getPrice();
            //int quantity = digital.getQuantity();
            Provider provider = digital.getProvider();
            String type = digital.getType();
            double space = digital.getSpace();
            orderService = new DigitalItem(name, price, quantity, provider, type, space);
        }
        else if(service instanceof FoodDelivery)
        {
            FoodDelivery food = (FoodDelivery) service;
            String foodName = food.getFoodName();
            String restaurantName = food.getRestaurantName();  
            double mealPrice = food.getPrice();
            //int count = food.getCount();
            orderService = new FoodDelivery(foodName, restaurantName, mealPrice, quantity);
        }
        else 
        {
            Streaming stream = (Streaming) service;
            String title = stream.getTitle();
            String category = stream.getCategory();
            double costPerDay = stream.getCost();
            int days = stream.getDays();
            orderService = new Streaming(title, category, costPerDay, days);
        }
    }

    public Service getService()
    {
        return orderService;
    }

    public void setType(String type)
    {
        orderType = type;
    }

    public String getType()
    {
        return orderType;
    }

    public void setOrderName(String orderName)
    {
        this.orderName = orderName;
    }

    public String getItemName()
    {
        return orderName;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public boolean getSuccess()
    {
        return success;
    }

    public List<Service> getInventory()
    {
        return services;
    }

    public void setRegion(String region)
    {
        destinationRegion = region;
    }

    public String getRegion()
    {
        return destinationRegion;
    }

    public void setCity(String city)
    {
        destinationCity = city;
    }

    public String getCity()
    {
        return destinationCity;
    }

    public void setPostCode(int postCode)
    {
        destinationPostCode = postCode;
    }
   
    public int getPostCode()
    {
        return destinationPostCode;
    }

    public void setSpecificID(int id)
    {
        specificID = id;
    }
 
    public int getSpecificID()
    {
        return specificID;
    }

}
