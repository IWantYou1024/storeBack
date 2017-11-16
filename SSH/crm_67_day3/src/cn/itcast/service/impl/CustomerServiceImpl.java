package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import cn.itcast.service.CustomerService;

@Service("customerService")
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public void addCustomer(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Customer> getAllCustomer() {
		return customerDao.findAll();
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerDao.delete(customer);
	}

	/**
	 * 根据id查询客户
	 */
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Customer getCustomerById(Long custId) {
		return customerDao.get(custId);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Customer> findAllCustomer(DetachedCriteria dc) {
		return customerDao.findAllByCriteria(dc);
	}

}
