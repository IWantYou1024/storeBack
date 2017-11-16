package cn.itcast.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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

import cn.itcast.action.utils.Page;
import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;



/**
 * 
 * 使用注解的方式配置动作类，并且交给spring管理
 *
 */

@ParentPackage("struts-default")
@Namespace("/linkman")
@Controller
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	
	private List<LinkMan> linkMans;

	private LinkMan linkMan = new LinkMan();
	
	private List<Customer> customers;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LinkManService linkManService;
	
	private String customerId;
	
	private Page page;
	
	private String num;//查询的页码
	
	private int ps;
	
	/**
	 * 1.跳转到添加页面
	 */
	@Action(value="addLinkManUI",results={@Result(name="success",location="/jsp/linkman/add.jsp")})
	public String addLinkManUI() {
		//1.要查询一下所有的客户列表（）
		customers = customerService.getAllCustomer();
		//* 现在使用的 from Customer 的hql查询数据库（查询的是所有列的数据）
		//* 页面中，只需要id 和 name ，资源的浪费：投影查询
		
		return this.SUCCESS;
	}
	
	/**
	 * 2.添加的动作（表单提交）
	 */
	@Action(value="addLinkMan",results={@Result(name="success",location="/jsp/success.jsp")})
	public String addLinkMan() {
		linkManService.addLinkMan(linkMan);
		return this.SUCCESS;
	}
	
	
	
	/**
	 * 4.删除联系人
	 */
	@Action(value="deleteLinkMan",results={@Result(name="success",location="/jsp/success.jsp")})
	public String deleteLinkMan() {
		//1.只有模型驱动接受页面传递过来的联系人id，封装到linkMan对象中
		//2.调用service方法删之
		linkManService.deleteLinkMan(linkMan);
		return this.SUCCESS;
	}
	
	
	/**
	 * 5.跳转到修改页面，并且回显数据
	 * 		需要接受一个lkmId：使用模型驱动，lkmId会在linkMan这对象中
	 */
	@Action(value="editLinkManUI",results={@Result(name="success",location="/jsp/linkman/edit.jsp")})
	public String editLinkManUI() {
		//1.使用模型驱动，lkmId会在linkMan这对象中
		//2.调用service层方法，使用根据id查询的方式查询linkman对象
		linkMan = linkManService.getLinkManById(linkMan.getLkmId());
		//3.回显数据使用struts2的标签的特性来做，name在值栈中可以找到，自动回显
		//4.查询出的对象，必须要在栈顶：手动压栈！！！
		ValueStack vs = ActionContext.getContext().getValueStack();
		
		//5。要查询一下所有的客户列表（）
		customers = customerService.getAllCustomer();
				
		vs.push(linkMan);
		return this.SUCCESS;
	}
	
	/**
	 * 6.修改动作
	 * 		1.根据模型驱动，封装到一个linkMan对象中
	 * 		2.service的方法进行更新（update：根据id更新）
	 * 		3.* 必须要保证linkMan中有id属性的值
	 * 
	 */
	@Action(value="editLinkMan",results={@Result(name="success",location="/jsp/success.jsp")})
	public String editLinkMan() {
		linkManService.updateLinkMan(linkMan);
		return this.SUCCESS;
	}
	
	/**
	 * 7.根据离线查询，查询全部的客户数据（条件查询）
	 * 		
	 * 		修改：目前一次要查询全部的联系人数据
	 * 				改为分页查询
	 */
	@Action(value="findAllLinkMan",results={@Result(name="success",location="/jsp/linkman/list.jsp")})
	public String findAllLinkMan() {
		//1.查询客户列表
		customers = customerService.getAllCustomer();
		//2.构造离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		//3.根据传递的条件，封装到离线对象中
		if(StringUtils.isNotBlank(linkMan.getLkmName())){
			dc.add(Restrictions.like("lkmName","%"+linkMan.getLkmName()+"%"));
		}
		if(StringUtils.isNotBlank(linkMan.getLkmGender())){
			dc.add(Restrictions.eq("lkmGender",linkMan.getLkmGender()));
		}
		if(StringUtils.isNotBlank(customerId)){
			dc.add(Restrictions.eq("customer.custId",NumberUtils.toLong(customerId)));
		}
		//4.查询
		//linkMans = linkManService.findAllLinkMan(dc);
		
		if(StringUtils.isBlank(num)){
			num = "1";
		}
		
		if(ps == 0) {
			ps =5;
		}
		
		//4.查询之后返回一个page对象
		page = linkManService.findAllLinkMan(dc,num,ps);
		return this.SUCCESS;
	}
	
	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<LinkMan> getLinkMans() {
		return linkMans;
	}

	public void setLinkMans(List<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}
	
	
	
}
