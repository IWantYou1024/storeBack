package cn.itcast.web.action.baseinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.itextpdf.text.PageSize;

import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.cargo.Product;
import cn.itcast.service.FactoryService;
import cn.itcast.service.ProductService;
import cn.itcast.web.action.BaseAction;


@Namespace("/baseinfo")
public class FactoryAction extends BaseAction<Factory>{
	
	@Autowired
	private FactoryService factoryService;
	
	public Factory setModel() {
		return new Factory();
	}
	
	//列表
	@Action(value="factoryAction_list",results=@Result(name="list",location="/WEB-INF/pages/baseinfo/factory/jFactoryList.jsp"))
	public String list() throws Exception {
		//条件
		/*Specification<Factory> spec = new Specification<Factory>(){
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.equal(root.get("state").as(Integer.class), 1);
			}};*/
			//分页
		org.springframework.data.domain.Page<Factory> page2 = factoryService.findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("factoryAction_list");
		super.push(page);
		
		return "list";
	}
	
	//查看
	@Action(value="factoryAction_toView",results=@Result(name="toView",location="/WEB-INF/pages/baseinfo/factory/jFactoryView.jsp"))
	public String toView() throws Exception {
		Factory factory = factoryService.get(model.getId());
		super.push(factory);
		
		Specification<Product> spec = new Specification<Product>(){
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.join("factory").get("id").as(String.class), model.getId());
			}};
			Page<Product> page2 = productService.findPage(spec, new PageRequest(page.getPageNo()-1, page.getPageSize()));
			super.parsePage(page, page2);
			page.setUrl("factoryAction_toView");
			
			super.push(page);
			
		return "toView";
	}
	
	//新增跳转
	@Action(value="factoryAction_toCreate",results=@Result(name="toCreate",location="/WEB-INF/pages/baseinfo/factory/jFactoryCreate.jsp"))
	public String toCreate() throws Exception {
		
		return "toCreate";
	}
	
	//新增
	@Action(value="factoryAction_insert",results=@Result(name="success",type="redirect",location="factoryAction_list"))
	public String insert() throws Exception {
		//设置状态
				model.setState(1);
				factoryService.saveOrUpdate(model);
		return SUCCESS;
	}
	
	//修改跳转
	@Action(value="factoryAction_toUpdate",results=@Result(name="toUpdate",location="/WEB-INF/pages/baseinfo/factory/jFactoryUpdate.jsp"))
	public String toUpdate() throws Exception {
		//查询选中工厂信息反显
		Factory factory = factoryService.get(model.getId());
		super.push(factory);
		
			Page<Product> page2 = productService.findPage(null, new PageRequest(page.getPageNo()-1, page.getPageSize()));
			super.parsePage(page, page2);
			page.setUrl("factoryAction_toUpdate");
			
			super.push(page);
		
		return "toUpdate";
	}
	
	//修改
	@Action(value="factoryAction_update",results=@Result(name="success",type="redirect",location="factoryAction_list"))
	public String update() throws Exception {
		//根据ID查询 数据库中的工厂信息
		Factory factory = factoryService.get(model.getId());
		
		//给工厂赋值修改后的数据
		factory.setCtype(model.getCtype());
		factory.setFullName(model.getFullName());
		factory.setFactoryName(model.getFactoryName());
		factory.setContacts(model.getContacts());
		factory.setPhone(model.getPhone());
		factory.setMobile(model.getMobile());
		factory.setFax(model.getFax());
		factory.setAddress(model.getMobile());
		factory.setInspector(model.getInspector());
		factory.setRemark(model.getRemark());
		factory.setORDER_NO(model.getORDER_NO());
		
		//更新
		factoryService.saveOrUpdate(factory);
		
		return SUCCESS;
	}
	
	

	//删除
	@Action(value="factoryAction_delete",results=@Result(name="success",type="redirect",location="factoryAction_list"))
	public String delete() throws Exception {
		String[] ids = model.getId().split(", ");
		factoryService.delete(ids);
		return SUCCESS;
	}
	
	//停用
	@Action(value="factoryAction_toStop",results=@Result(name="success",type="redirect",location="factoryAction_list"))
	public String toStop() throws Exception {
		String[] ids = model.getId().split(", ");
		for (String id : ids) {
			Factory factory = factoryService.get(id);
			factory.setState(0);
			factoryService.saveOrUpdate(factory);
		}
		return SUCCESS;
	}
	//正常
	@Action(value="factoryAction_toNormal",results=@Result(name="success",type="redirect",location="factoryAction_list"))
	public String toNormal() throws Exception {
		String[] ids = model.getId().split(", ");
		for (String id : ids) {
			Factory factory = factoryService.get(id);
			factory.setState(1);
			factoryService.saveOrUpdate(factory);
		}
		return SUCCESS;
	}
	
	@Autowired
	private ProductService productService;
	//查询商品
	@Action(value="factoryAction_productlist",results=@Result(name="list",location="/WEB-INF/pages/baseinfo/product/jProductList.jsp"))
	public String productList() throws Exception {
		Factory factory = factoryService.get(model.getId());
		Set<Product> products = factory.getProducts();
		List<Product> list = new ArrayList<Product>();
		for (Product product : products) {
			list.add(product);
		}
		
		Page<Product> page2 = productService.findPage(null, new PageRequest(page.getPageNo()-1,page.getPageSize()));
		super.parsePage(page, page2);
		page.setResults(list);
		page.setUrl("productAction_list");
		return "list";
	}

}
