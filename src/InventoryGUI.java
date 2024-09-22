import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.*;

import javax.swing.*;

public class InventoryGUI extends JPanel 
{
    private JButton displayServices;
    private JButton displayOrders;
    private JButton displayShippings;
    private JButton threadSearch;
    private JMenuBar menuBar;
    private JTextArea textArea;
    private List<Service> services = new ArrayList<Service>();
    private List<Order> orders = new ArrayList<Order>();

    public InventoryGUI()
    {
        JMenu fileMenu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
	    fileMenu.add (exit);

        JMenu inventoryMenu = new JMenu("Inventory");
		JMenuItem addService = new JMenuItem("Add Service");
	    inventoryMenu.add (addService);

        JMenu customerMenu = new JMenu("Customer");
		JMenuItem addOrder = new JMenuItem("Add Order");
	    customerMenu.add (addOrder);

        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(inventoryMenu);
        menuBar.add(customerMenu);

        displayServices = new JButton("Display Services");
	    displayOrders = new JButton("Display Order Costs");
        displayShippings = new JButton("Display Shipping Fees");
        threadSearch = new JButton("Multithread Search");

        textArea = new JTextArea(5, 5);
         
        setPreferredSize(new Dimension (535, 396));
        setLayout(null);

        add(displayServices);
        add(displayOrders);
        add(displayShippings);
        add(threadSearch);
        add(menuBar);
        add(textArea);

        displayServices.setBounds(60, 35, 185, 20);
        displayOrders.setBounds(280, 35, 185, 20);
        displayShippings.setBounds(60, 60, 185, 20);
        threadSearch.setBounds(280,60,185,20);
        menuBar.setBounds(0, 0, 635, 25);
        textArea.setBounds(15, 90, 505, 295);

        addService.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event)
                {
                    String serviceType ="1. Item(Physical/Digital)\n" +
 						"2. Streaming\n" +
 						"3.Food Delivery\n";

                    String addMenu = JOptionPane.showInputDialog(serviceType);
                    
                int addChoice = Integer.parseInt(addMenu);
        

                if(addChoice == 1)
                {
                    String name = JOptionPane.showInputDialog("\nEnter item name: ");
                    
                    String getPrice = JOptionPane.showInputDialog("\nEnter item price: ");
                    double price = Double.parseDouble(getPrice);
                    String getQuantity = JOptionPane.showInputDialog("\nEnter item quantity: ");
                    int quantity = Integer.parseInt(getQuantity);
                    
                    String providerName = JOptionPane.showInputDialog("\nEnter provider name: ");
                    String getCode = JOptionPane.showInputDialog("\nEnter country code: ");
                    int country = Integer.parseInt(getCode);
                    String getArea = JOptionPane.showInputDialog("\nEnter area code: ");
                    int area = Integer.parseInt(getArea);
                    String getPhone = JOptionPane.showInputDialog("\nEnter phone number: ");
                    int phone = Integer.parseInt(getPhone);
                    Provider provider = new Provider(providerName,country,area,phone);
                    String type = JOptionPane.showInputDialog("\nPhysical or Digital Item: ");
                    if(type.equals("Physical"))
                    {
                        String getWidth = JOptionPane.showInputDialog("\nEnter width: ");
                        double width = Double.parseDouble(getWidth);
                        String getLength = JOptionPane.showInputDialog("\nEnter length: ");
                        double length = Double.parseDouble(getLength);
                        String getHeight = JOptionPane.showInputDialog("\nEnter height: ");
                        double height = Double.parseDouble(getHeight);
                        
                        Service service = new PhysicalItem(name,price,quantity,provider,type,width,length,height);
                        services.add(service);
                        JOptionPane.showMessageDialog(null, "Physical item added successfully.");  
                    }
                    else if(type.equals("Digital"))
                    {
                        String getDisk = JOptionPane.showInputDialog("\nEnter disk space: ");
                        double disk = Double.parseDouble(getDisk);
                        
                        Service service = new DigitalItem(name,price,quantity,provider,type,disk);
                        services.add(service);
                        JOptionPane.showMessageDialog(null, "Digital item added successfully.");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid item type.\nPlease try again.");
                    }   
                }
                else if(addChoice == 2)
                {
                    String title = JOptionPane.showInputDialog("Enter title: ");
                    String category = JOptionPane.showInputDialog("Enter category: ");
                    String getCost = JOptionPane.showInputDialog("Enter cost per day: ");
                    double cost = Double.parseDouble(getCost);
                    String getDays = JOptionPane.showInputDialog("\nEnter days: ");
                    int days = Integer.parseInt(getDays);
                    Service service = new Streaming(title, category, cost, days);
                    services.add(service);
                    JOptionPane.showMessageDialog(null, "Streaming added successfully");
                }
                else if(addChoice == 3)
                {
                    String name = JOptionPane.showInputDialog("Enter food name: ");
                    String restaurant = JOptionPane.showInputDialog("Enter restaurant name: ");
                    String getPrice = JOptionPane.showInputDialog("Enter meal price: ");
                    double price = Double.parseDouble(getPrice);
                    String getCount = JOptionPane.showInputDialog("Enter food count: ");
                    int count = Integer.parseInt(getCount);
                  
                    Service service = new FoodDelivery(name, restaurant, price, count);
                    services.add(service);
                    JOptionPane.showMessageDialog(null, "Food delivery added successfully.");
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "Invalid choice! Please try again.");
                }
                }
            }
        );

        addOrder.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event)
                {
                    String name = "";
                    int quantity = 0;
                    int index = 0;
                    boolean checkQuantity = false;
                    while(!checkQuantity)
                    {
                        try
                        {
                            boolean checkName = false;
                            while(!checkName)
                            {
                            
                                name = JOptionPane.showInputDialog("Enter the item you want to purchase");
                                try
                                {
                                    for(int i = 0; i < services.size(); i++)
                                    {
                                        if(services.get(i) instanceof PhysicalItem)
                                        {
                                            PhysicalItem physical = (PhysicalItem) services.get(i);
                                            if(physical.getName().equals(name))
                                            {
                                                index = i;
                                                checkName = true;
                                                break;
                                            }
                                            else
                                            {
                                                continue;
                                            }
                                        }
                                        else if(services.get(i) instanceof DigitalItem)
                                        {
                                            DigitalItem digital = (DigitalItem) services.get(i);
                                            if(digital.getName().equals(name))
                                            {
                                                index = i;
                                                checkName = true;
                                                break;
                                            }
                                            else
                                            {
                                                continue;
                                            }
                                        }
                                        else if(services.get(i) instanceof FoodDelivery)
                                        {
                                            FoodDelivery food = (FoodDelivery) services.get(i);
                                            if(food.getFoodName().equals(name))
                                            {
                                                index = i;
                                                checkName = true;
                                                break;
                                            }
                                            else
                                            {
                                                continue;
                                            }
                                        }
                                        else
                                        {
                                            Streaming stream = (Streaming) services.get(i);
                                            if(stream.getTitle().equals(name))
                                            {
                                                index = i;
                                                checkName = true;
                                                break;
                                            }
                                            else
                                            {
                                                continue;
                                            }
                                        }
                                    }
                                    if(!checkName)
                                        throw new NoSuchItemException();
                                }
                                catch(NoSuchItemException e)
                                {
                                    String err = "Error";
                                    JOptionPane.showMessageDialog(null,"The item you are trying to order is not in inventory. Please select another item" ,err,JOptionPane.ERROR_MESSAGE);

                                }
                            }
                            String getQuantity = JOptionPane.showInputDialog("Enter the quantity: ");
                            quantity = Integer.parseInt(getQuantity);
                            if(services.get(index) instanceof PhysicalItem)
                            {
                                PhysicalItem physical = (PhysicalItem) services.get(index);
                                if(physical.getQuantity() >= quantity)
                                {
                                    checkQuantity = true;
                                }
                                else
                                {
                                    throw new NoSuchItemException();
                                }
                            }
                            else if(services.get(index) instanceof DigitalItem)
                            {
                                DigitalItem digital = (DigitalItem) services.get(index);
                                if(digital.getQuantity() >= quantity)
                                {
                                    checkQuantity = true;
                                }
                                else
                                {
                                    throw new NoSuchItemException();
                                }
                            }
                            else if(services.get(index) instanceof FoodDelivery)
                            {
                                FoodDelivery food = (FoodDelivery) services.get(index);
                                if(food.getCount() >= quantity)
                                {
                                    checkQuantity = true;
                                }
                                else
                                {
                                    throw new NoSuchItemException();
                                }
                            }
                            else 
                            {
                                checkQuantity = true;
                            }
                        }
                        catch(NoSuchItemException e)
                        {
                            String err = "Error";
                            JOptionPane.showMessageDialog(null,"The item you are trying to order is not in inventory. Please select another item" ,err,JOptionPane.ERROR_MESSAGE);
                        }
                    }

                        String region = JOptionPane.showInputDialog("Enter destination region: ");
                        String city = JOptionPane.showInputDialog("Enter destination city: ");
                        System.out.printf("Enter destination post code: ");
                        String getCode = JOptionPane.showInputDialog("Enter destination code: ");
                        int postCode = Integer.parseInt(getCode);
                        Order order = new Order(services,name,quantity,region,city,postCode);
                        if(order.getSuccess())
                        {
                            orders.add(order);
                            JOptionPane.showMessageDialog(null, "Order added successfully");
                        }
                    
                    
                }
            }
        );
        
        exit.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event)
                {
                    System.exit(0);
                }
            }
        );

        displayServices.addActionListener(
            new ActionListener() {

                String print = "";
                public void actionPerformed(ActionEvent event)
                {
                    for(int i = 0; i < services.size(); i++)
                    {
                        print += services.get(i).displayServiceInfo();
                        print += "\n";
                    }
                    textArea.setText(print);
                    print = ""; 
                }   
            }
        );

        displayOrders.addActionListener(
            new ActionListener() {

                String print = "";
                public void actionPerformed(ActionEvent event)
                {
                    for(int i = 0; i < orders.size(); i++)
                    {
                        print += orders.get(i).displayOrderInfo();
                        print += "\n";
                    }
                    textArea.setText(print);
                    print = ""; 
                }   
            }
        );

        displayShippings.addActionListener(
            new ActionListener(){
                String print = "";
                public void actionPerformed(ActionEvent event)
                {
                    List<Service> aboveLimit = new ArrayList<Service>();
                    String getLimit = JOptionPane.showInputDialog("Enter a limit: ");
                    int limit = Integer.parseInt(getLimit);
                    ListIterator< Service> iterator = services.listIterator(); 
                    while(iterator.hasNext())
                    {
                        Service service = iterator.next();
                        if(service.calculateShippingFee() > limit)
                            {
                                aboveLimit.add(service);
                            }
                    }
                    if(aboveLimit.size() == 0)
                    {
                        JOptionPane.showMessageDialog(null,"There is no item with a shipping fee over this limit");
                    }
                    else
                    {
                        print += "items with shipping fees over " + limit +":\n";
                        //System.out.printf("items with shipping fees over %d:\n ", limit);
                        for(Service service : aboveLimit)
                        {
                            if(service instanceof PhysicalItem)
                            {
                                PhysicalItem item = (PhysicalItem) service;
                                print += "Item: " + item.getName() + ", Shipping fee: " + item.calculateShippingFee() + "\n";
                                //System.out.printf("Item: %s, Shipping fee: %f\n",item.getName(),item.calculateShippingFee());
                            }
                            else 
                            {
                                FoodDelivery food = (FoodDelivery) service;
                                print += "Food: " + food.getFoodName() + ", Shipping fee: " + food.calculateShippingFee() + "\n";
                                //System.out.printf("Food: %s, Shipping fee: %f\n",food.getFoodName(),food.calculateShippingFee());
                            }
                        }
                    }
                    textArea.setText(print);
                    print = ""; 
                }
            }
        );

        threadSearch.addActionListener(
            new ActionListener() {
                String print = "";
                public void actionPerformed(ActionEvent event)
                {
                   

                    if(orders.size() < 8)
                    {
                        String message = "Please enter at least 8 orders - now it is only " + orders.size();
                        JOptionPane.showMessageDialog(null, message);
                    }
                    else
                    {
                        ExecutorService executor = Executors.newFixedThreadPool(4);
                         // Create a concurrent result container to store the search results
                        ConcurrentLinkedQueue<Order> searchResults = new ConcurrentLinkedQueue<>();

                        // Create a search query
                        String city = JOptionPane.showInputDialog("Enter city name");

                        // Submit search tasks to the executor
                        for (Order order : orders) {
                            executor.submit(() -> {
                                if (order.getCity().contains(city)) {
                                    // str.compareToIgnoreCase(searchQuery)
                                    searchResults.add(order);
                                }
                            });
                        }

                        // Shutdown the executor and wait for all tasks to complete
                        executor.shutdown();
                        try {
                            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // Print the search results
                        print += "Order IDs for " + city + "\n";
                        //System.out.println("Search results:");
                        for (Order order : searchResults) {
                            print += order.getSpecificID() + " ";
                        }
                    }
                    textArea.setText(print);
                    print = ""; 

                    
                    }
                }
        );
    }
    public static void main(String[] args) {
	    	
        JFrame frame = new JFrame ("Online Shop Inventory Management System");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new InventoryGUI());
        
        
        frame.setMaximumSize(new Dimension(1000, 600));
        frame.setMinimumSize(new Dimension(535, 500));
        
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }

}
