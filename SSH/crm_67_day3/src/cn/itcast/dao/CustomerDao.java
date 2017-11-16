package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.entity.Customer;

public interface CustomerDao {

	void save(Customer customer);

	List<Customer> findAll();

	void delete(Customer customer);

	Customer get(Long custId);

	void update(Customer customer);

	List<Customer> findAllByCriteria(DetachedCriteria dc);

}
