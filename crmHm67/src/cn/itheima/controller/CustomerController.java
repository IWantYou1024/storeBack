package cn.itheima.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.utils.Page;
import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.CustomerQueryVo;
import cn.itheima.service.CustomerService;
import cn.itheima.service.DictService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private CustomerService custService;
	
	@Value("${resource.customer.dict.source}")
	private String custSource;

	@Value("${resource.customer.dict.industry}")
	private String custIndustry;

	@Value("${resource.customer.dict.level}")
	private String custLevel;
	
	@RequestMapping("/list")
	public String getCustomerList(CustomerQueryVo vo, Model model) throws Exception {
		// 三个下拉列表内容的查询
		// 客户来源(002)
		List<BaseDict> custSourceList = dictService.findDictInfoByTypeCode(custSource);
		// 所属行业(001)
		List<BaseDict> industryList = dictService.findDictInfoByTypeCode(custIndustry);
		// 客户级别(006)
		List<BaseDict> custLevelList = dictService.findDictInfoByTypeCode(custLevel);
		
		
		if (vo != null) {
			// 解决中文乱码
			String custName = vo.getCustName();
			if (custName != null && !"".equals(custName)) {
				vo.setCustName(new String(custName.getBytes("iso8859-1"), "utf-8"));
			}
			
			// 开始行号的计算
			Integer pageNo = vo.getPage();
			if (pageNo == null || pageNo <= 0) {
				vo.setPage(1);
			}
			vo.setStart((vo.getPage() - 1) * vo.getSize());
		}
		
		// 客户信息的查询
		List<Customer> custList = custService.findCustomerByQueryVo(vo);
		
		// 客户信息总件数的查询
		Integer custTotal = custService.findCustomerByQueryVoCount(vo);
		
		// 把查询结果封装成一个page对象(用于分页)
		Page<Customer> page = new Page<Customer>();
		page.setPage(vo.getPage());
		page.setRows(custList);
		page.setSize(vo.getSize());
		page.setTotal(custTotal);
		
		// 把结果返回给前台
		model.addAttribute("page", page);
		
		// 把三个下拉列表的数据返回
		model.addAttribute("fromType", custSourceList);
		model.addAttribute("industryType", industryList);
		model.addAttribute("levelType", custLevelList);
		
		
		// 查询条件的保持
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		
		
		return "customer/customer";
	}
	
	// 根据id查询客户详细信息
	@RequestMapping("/toEdit")
	@ResponseBody
	public Customer getCustomerById(Long id) throws Exception {
		Customer customer = custService.findCustomerById(id);
		return customer;
	}
	
	// 根据id更新客户详细信息
	@RequestMapping("/update")
	@ResponseBody
	public String updateCustomerById(Customer cust) throws Exception {
		custService.updateCustomerById(cust);
		return "ok";
	}
	
	// 根据id删除客户信息
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteCustomerById(Long id) throws Exception {
		custService.deleteCustomerById(id);
		return "ok";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
