package DataBase;
import java.util.ArrayList;
import java.util.List;
import model.Customer;


public class CustomerDatabase {
	
	private List<Customer>  customers = new ArrayList<>();
	
	public CustomerDatabase()
	{
		customers.add(new Customer("abc@gmail.com", "anuj", "delhi", "654131", "abc"));
		customers.add(new Customer("def@gmail.com", "ram", "punjab", "736598", "def"));
		customers.add(new Customer("ghi@gmail.com", "sam", "pune", "138986", "ghi"));
	}
	
	public List<Customer> getAllCustomers()
	{
		return this.customers;
	}
	
	public boolean insertCustomer(Customer customer) throws Exception
	{
		for(Customer cust: customers)
		{
			if(cust.getEmail().equals(customer.getEmail()))
			{throw new Exception("Customer can't be registered as email already exists");}
		}

		customers.add(customer);
		return true;
	}
	
	public Customer getCustomerByEmail(String email)
	{
		Customer customer = null;
		
		for(Customer cust: customers)
		{
			if(cust.getEmail().equals(email))
			{
				customer = cust;
				break;
			}
		}
		
		return customer;
	}
	
	public boolean login(String email, String password)
	{
		for(Customer cust: customers)
		{
			if(cust.getEmail().equals(email))
			{
				if(cust.getPassword().equals(password))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	

}
