package cn.itheima.dao;

import java.util.List;

import cn.itheima.pojo.Customer;
import cn.itheima.pojo.CustomerQueryVo;

public interface CustomerDao {
	
	// 根据查询条件查询客户信息
	public List<Customer> findCustomerByQueryVo(CustomerQueryVo vo) throws Exception;
	
	// 根据查询条件查询客户信息总件数
	public Integer findCustomerByQueryVoCount(CustomerQueryVo vo) throws Exception;
	
	// 根据id查询客户详细信息
	public Customer findCustomerById(Long id) throws Exception;
	
	// 根据id更新客户信息
	public void updateCustomerById(Customer cust) throws Exception;
	
	// 根据id删除客户信息
	public void deleteCustomerById(Long id) throws Exception;

}
