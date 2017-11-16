package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.entity.Customer;

public interface CustomerService {

	public void addCustomer(Customer customer);
	
	public List<Customer> getAllCustomer();

	public void deleteCustomer(Customer customer);

	public Customer getCustomerById(Long custId);

	public void updateCustomer(Customer customer);

	public List<Customer> findAllCustomer(DetachedCriteria dc);
	
}
