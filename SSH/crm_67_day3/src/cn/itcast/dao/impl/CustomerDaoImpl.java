package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;

/**
 * 进行数据库操作使用：hibernateTemplate
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Customer customer) {
		hibernateTemplate.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) hibernateTemplate.find("select new Customer(custId,custName) from Customer");
	}

	/**
	 * 调用hibernateTemplate的删除方法
	 */
	@Override
	public void delete(Customer customer) {
		hibernateTemplate.delete(customer);
	}

	@Override
	public Customer get(Long custId) {
		return hibernateTemplate.load(Customer.class, custId);
	}

	/**
	 * 调用hibernateTemplate的更新方法
	 */
	@Override
	public void update(Customer customer) {
		hibernateTemplate.update(customer);
	}

	/**
	 * 调用hibernateTemplate的的方法查询数据库
	 * 
	 */
	@Override
	public List<Customer> findAllByCriteria(DetachedCriteria dc) {
		return (List<Customer>) hibernateTemplate.findByCriteria(dc);
	}

}
