package cn.itheima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.CustomerDao;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.CustomerQueryVo;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao custDao;

	@Override
	public List<Customer> findCustomerByQueryVo(CustomerQueryVo vo) throws Exception {
		List<Customer> custList = custDao.findCustomerByQueryVo(vo);
		return custList;
	}

	@Override
	public Integer findCustomerByQueryVoCount(CustomerQueryVo vo) throws Exception {
		Integer count = custDao.findCustomerByQueryVoCount(vo);
		return count;
	}

	@Override
	public Customer findCustomerById(Long id) throws Exception {
		Customer customer = custDao.findCustomerById(id);
		return customer;
	}

	@Override
	public void updateCustomerById(Customer cust) throws Exception {
		custDao.updateCustomerById(cust);
		
	}

	@Override
	public void deleteCustomerById(Long id) throws Exception {
		custDao.deleteCustomerById(id);
		
	}

}
