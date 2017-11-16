package cn.itcast.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import cn.itcast.entity.BaseDict;
import cn.itcast.entity.Customer;
import cn.itcast.service.BaseDictService;
import cn.itcast.service.CustomerService;

@Controller("customerAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/customer")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	
	private List<Customer> customers;
	
	private List<BaseDict> customerSources;
	
	private List<BaseDict> customerLevels;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BaseDictService baseDictService;
	
	private String customerSourceId;
	private String customerLevelId;

	
	@Action(value="addCustomerUI",results={@Result(name="success",location="/jsp/customer/add.jsp")})
	public String addCustomerUI() {
		customerSources = baseDictService.getAllCustomerSource();
		customerLevels = baseDictService.getAllCustomerLevel();
		return this.SUCCESS;
	}
	
	/**
	 * 添加客户的动作
	 */
	@Action(value="addCustomer",results={@Result(name="success",location="/jsp/success.jsp")})
	public String addCustomer() {
		//1.使用模型驱动获的数据
		//2.页面想后台提交数据的时候，只需要写customer中的属性名就可以了
		customerService.addCustomer(customer);
		return this.SUCCESS;
	}
	
	/**
	 * 删除客户的动作
	 */
	@Action(value="deleteCustomer",results={@Result(name="success",location="/jsp/success.jsp")})
	public String deleteCustomer() {
		//1.使用模型驱动获的数据：前台传递过来的客户id已经封装到了customer对象中
		customerService.deleteCustomer(customer);
		return this.SUCCESS;
	}
	
	/**
	 * 跳转到修改页面
	 */
	@Action(value="editCustomerUI",results={@Result(name="success",location="/jsp/customer/edit.jsp")})
	public String editCustomerUI() {
		//1。根据id查询客户
		customer = customerService.getCustomerById(customer.getCustId());
		
		customerSources = baseDictService.getAllCustomerSource();
		customerLevels = baseDictService.getAllCustomerLevel();
		
		//2。需要将客户信息保存到某一个地方（手动压栈）
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.push(customer);
		//3.跳转页面并回显数据
		return this.SUCCESS;
	}
	
	/**
	 * 修改客户的动作
	 */
	@Action(value="editCustomer",results={@Result(name="success",location="/jsp/success.jsp")})
	public String editCustomer() {
		//1。模型驱动封装成customer对象
		//* 根据客户id进行更新，保证客户id不为null
		//2.调用service层代码更新
		customerService.updateCustomer(customer);
		return this.SUCCESS;
	}

	@Action(value="findAllCustomer",results={@Result(name="success",location="/jsp/customer/list.jsp")})
	public String findAllCustomer() {
		customerSources = baseDictService.getAllCustomerSource();
		customerLevels = baseDictService.getAllCustomerLevel();
		//* 普通属性，使用模型驱动
		//1.根据页面传递的数据，判断，如果参数不为空，需要拼接sql语句
		//2.dbc ： criteria ： 在线查询
		//3.detachedcriteria：离线查询
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		//拼装查询条件
		//if(customer.getCustName() != null && !"".equals(customer.getCustName())){
		if(StringUtils.isNotBlank(customer.getCustName())){
			dc.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));//模糊查询
		}
		
		if(StringUtils.isNotBlank(customer.getCustIndustry())){
			dc.add(Restrictions.like("custIndustry", "%"+customer.getCustIndustry()+"%"));//模糊查询
		}
		
		if(StringUtils.isNoneBlank(customerSourceId)) { //判断来源
			dc.add(Restrictions.eq("custSource.dictId", customerSourceId));//模糊查询
		}
		
		if(StringUtils.isNoneBlank(customerLevelId)) { //判断级别
			dc.add(Restrictions.eq("custLevel.dictId", customerLevelId));//模糊查询
		}
		
		customers = customerService.findAllCustomer(dc);
		
		return this.SUCCESS;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<BaseDict> getCustomerSources() {
		return customerSources;
	}

	public void setCustomerSources(List<BaseDict> customerSources) {
		this.customerSources = customerSources;
	}

	public List<BaseDict> getCustomerLevels() {
		return customerLevels;
	}

	public void setCustomerLevels(List<BaseDict> customerLevels) {
		this.customerLevels = customerLevels;
	}

	@Override
	public Customer getModel() {
		return customer;
	}
	

	public String getCustomerSourceId() {
		return customerSourceId;
	}

	public void setCustomerSourceId(String customerSourceId) {
		this.customerSourceId = customerSourceId;
	}

	public String getCustomerLevelId() {
		return customerLevelId;
	}

	public void setCustomerLevelId(String customerLevelId) {
		this.customerLevelId = customerLevelId;
	}
	
	
}
