package Service;

import java.util.List;

import DataBase.CustomerDatabase;
import model.Customer;
import jdbc.CustomerDBConnection;
public class CustomerService {
	
	private CustomerDBConnection customerDatabase;
	
	public CustomerService(CustomerDBConnection cutomerDatabase)
	{
		this.customerDatabase = cutomerDatabase;
	}
	
	public List<Customer> getAllCustomers() throws Exception
	{
		if(customerDatabase.getAllCustomers().size() == 0)
			throw new Exception("No customers registered yet");
		
		return this.customerDatabase.getAllCustomers();
	}
	
	public boolean registerCustomer(Customer customer) throws Exception
	{
		if(customer.getEmail() == null || customer.getEmail().isEmpty())
		{
			throw new Exception("email cannot be empty");
		}
		try {customerDatabase.insertCustomer(customer);}
		
		catch (Exception e)
		{
			e.printStackTrace();  
			return false;
		}
		return true;
	}
	
	public Customer getCustomerByEmail(String email) throws Exception
	{
		if(email==null || email.isEmpty())
		{
			throw new Exception("email can't be empty or null");
		}
		 Customer cust = this.customerDatabase.getCustomerByEmail(email);
		 
		 if(cust==null)
			 throw new Exception("Customer with email "+ email + " doesn't exist");
		 
		 return cust;
	}
	
	public boolean validCredentials(String email, String password) throws Exception
	{
		if(email == null || email.isEmpty())
			throw new Exception("email cannot be empty or null");
		
		return this.customerDatabase.login(email, password);
	}

}
