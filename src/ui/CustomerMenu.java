package ui;
import java.util.Scanner;

import Service.CustomerService;
import jdbc.CustomerDBConnection;
import model.Customer;

public class CustomerMenu {
	
	public static void adminMenu()
	{
		System.out.println("Select Option\n"+
	                       "1: See all Customers\n"+  
				            "2: Fetch Customer by email\n"+
	                         "3: LogOff\n");		
	}
	
	public static void customerMenu()
	{
		System.out.println("Select Option\n"+
	                        "1: Show Profile\n"+  
				            "2: Change Password\n"+
				            "3: Edit Profile\n"+
	                        "4: LogOff\n");		
	}
	
	public static void main(String args[])
	{
		CustomerDBConnection  customerDatabase = new CustomerDBConnection();
		CustomerService customerService = new CustomerService(customerDatabase);
		
		String email, city, password, phone, name;
		int choice;
		boolean flag=true;
		
		Scanner sc = new Scanner(System.in);
		
		do
		{
			// Home Page
			System.out.println("Select Operation to perform: \n" + 
	                 "1: Login\n" + 
			         "2: Register Customer \n"+
	                 "3: EXIT");
			
			choice = sc.nextInt();
			
			switch(choice)
			{
			
			case 1: 
						
				System.out.println("enter the email \n");
				email = sc.next();
				System.out.println("enter the password\n");
				password = sc.next();
				
				if(email.equals("admin@gmail.com") && password.equals("admin"))
				{
					adminDashboard(sc, customerService);
				}
				
				else
				{
					try {
						if(customerService.validCredentials(email, password))
							customerDashboard(sc, customerService, email, customerDatabase);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				
				break;
				
			case 2: System.out.println("Register the Customer");
					registration(sc, customerService);
					break;
			
			case 3: System.out.println("Exiting the application");
			        flag = !flag;    
			        break;
			    
				
			default: System.out.println("enter the valid input");
			
			}
			
			
		}while(flag);
				
		
	}  // main ends here
	
	public static void adminDashboard(Scanner sc, CustomerService customerService)
	{
		boolean flag =true;
		
		do 
		{
			System.out.println("\n************ Admin Dashboard************ \n");
			adminMenu();
			int choice = sc.nextInt();
			
			switch(choice)
			{
			case 1:   
				try {
					for(Customer customer: customerService.getAllCustomers())
					{
						System.out.println(customer);
					}
					break;
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				break;
				
			case 2:
				System.out.println("enter the email of customer");
				String email = sc.next();
				
				try {
					Customer cust =  customerService.getCustomerByEmail(email);
					System.out.println("Name: " + cust.getCustName()+
					                   "\n City: "+ cust.getCity() +
					                   "\n phone: "+ cust.getPhone()+
					                   "\n email: "+ cust.getEmail());
				}
				catch(Exception e)
				{e.printStackTrace();}
				break;
				
			case 3: System.out.println("Logging Off...");
			        flag = !flag;
			        break;
			        
				
			default: System.out.println("Wrong choice");
					
			
			}
			
		} while(flag);

	}
	
	public static void customerDashboard(Scanner sc, CustomerService customerService, String email, CustomerDBConnection  customerDatabase)
	{
		boolean flag = true;
		
		do 
		{
			System.out.println("\n************ Customer Dashboard************ \n");
			customerMenu();
			int choice = sc.nextInt();
			
			switch(choice)
			{
			
			case 1: try {
				Customer cust =  customerService.getCustomerByEmail(email);
				System.out.println("Name: " + cust.getCustName()+
				                   "\n City: "+ cust.getCity() +
				                   "\n phone: "+ cust.getPhone()+
				                   "\n email: "+ cust.getEmail());
			}
			catch(Exception e)
			{e.printStackTrace();}
			break;
			
			case 2: 
				try {
					Customer cust = customerService.getCustomerByEmail(email);
					
					 
						System.out.println("Enter the new Password...");
						String newPassword = sc.next();
						cust.setPassword(newPassword);
						customerDatabase.updatepassword(newPassword,  email);
					 

						System.out.println("Password has been updated...");
						break;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
				
			case 3:   editProfile(sc, customerService, email, customerDatabase);
					  break;
			
			case 4: System.out.println("Logging Off...");
			        flag = !flag;
			        break;
				
			default: System.out.println("Wrong choice");
			}
			
			
		}while(flag);
		
	}
	
	
	public static void editProfile(Scanner sc, CustomerService customerService, String email, CustomerDBConnection customerDatabase)
	{
		boolean flag=true;
		
		do {
			System.out.println("\nSelect Option\n"+
                    "1: Edit email\n"+  
		            "2: Edit city\n"+
		            "3: Edit Name\n"+
		            "4: Edit Phone Number\n"+
                    "5: EXIT\n");
			
			int choice = sc.nextInt();
			
			switch(choice)
			{
			case 1:
				
				try {
					Customer cust = customerService.getCustomerByEmail(email);
					
					System.out.println("Old Email: " + cust.getEmail());
					System.out.println("Enter the new email to update: ");
					cust.setEmail(sc.next());
					if(customerDatabase.updateCustomer(cust))
					System.out.println("Email has been updated...");
					break;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 2: 
				try {
					Customer cust = customerService.getCustomerByEmail(email);
					
					System.out.println("Old City: " + cust.getCity());
					System.out.println("Enter the new city to update: ");
					cust.setCity(sc.next());
					if(customerDatabase.updateCustomer(cust))
					System.out.println("City has been updated...");
					break;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
				
			case 3:
				try {
					Customer cust = customerService.getCustomerByEmail(email);
					
					System.out.println("Old Name: " + cust.getCustName());
					System.out.println("Enter the new name to update: ");
					cust.setCustName(sc.next());
					if(customerDatabase.updateCustomer(cust))
					System.out.println("Name has been updated...");
					break;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				
				try {
					Customer cust = customerService.getCustomerByEmail(email);
					
					System.out.println("Old Phone Number: " + cust.getPhone());
					System.out.println("Enter the new Phone Number to update: ");
					cust.setPhone(sc.next());
					if(customerDatabase.updateCustomer(cust))
					System.out.println("Phone Number has been updated...");
					break;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 5: System.out.println("Exiting...");
	                flag = !flag;
	                break;
				
			default: System.out.println("Wrong choice");
				
			}
			
		}while(flag);
	}
	
	public static void registration(Scanner sc, CustomerService customerService)
	{
		System.out.println("Enter the email: ");
		String email = sc.next();
		System.out.println("Enter the Password: ");
		String pass = sc.next();
		System.out.println("Enter the name: ");
		String name = sc.next();
		System.out.println("Enter the Phone Number: ");
		String phone = sc.next();
		System.out.println("Enter the city: ");
		String city = sc.next();
		
		Customer customer = new Customer(email, name, city, phone, pass);
		
		try {
			if(customerService.registerCustomer(customer))
				System.out.println("registration completed...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
