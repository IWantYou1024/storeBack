package cn.itcast.web.action.baseinfo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.cargo.Product;
import cn.itcast.service.FactoryService;
import cn.itcast.service.ProductService;
import cn.itcast.service.UserService;
import cn.itcast.web.action.BaseAction;

@Namespace("/baseinfo")
@Results({
		@Result(name="list",location="/WEB-INF/pages/baseinfo/product/jProductList.jsp"),
		@Result(name="toview",location="/WEB-INF/pages/baseinfo/product/jProductView.jsp"),
		@Result(name="tocreate",location="/WEB-INF/pages/baseinfo/product/jProductCreate.jsp"),
		@Result(name="toupdate",location="/WEB-INF/pages/baseinfo/product/jProductUpdate.jsp"),
		@Result(name="success",type="redirect",location="productAction_list")
		})
public class ProductAction extends BaseAction<Product> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FactoryService factoryService;

	/**
	 * 查询产品列表
	 * @return
	 * @throws Exception
	 */
	@Action("productAction_list")
	public String list() throws Exception {
		//查询分页数据
		Page<Product> page2 = productService.findPage(null,
				new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		//转换page
		super.parsePage(page, page2);
		//设置page的url
		page.setUrl("productAction_list");
		//将page压入值栈
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看详细信息
	 * @return
	 * @throws Exception
	 */
	@Action("productAction_toview")
	public String toview() throws Exception {
		//查询当前产品并压入值栈
		Product product = productService.get(model.getId());
		super.push(product);
		return "toview";
	}
	
	/**
	 * 跳转新增页面
	 * @return
	 * @throws Exception
	 */
	@Action("productAction_tocreate")
	public String tocreate() throws Exception {
		//设置查询工厂条件 state==1
		Specification<Factory> spec = new Specification<Factory>() {
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		//查询工厂列表
		List<Factory> factoryList = factoryService.find(spec);
		super.put("factoryList", factoryList);
		return "tocreate";
	}
	
	/**
	 * 新增产品功能
	 * @return
	 * @throws Exception
	 */
	@Action("productAction_insert")
	public String insert() throws Exception {
		productService.saveOrUpdate(model);
		return SUCCESS;
	}
	
	/**
	 * 跳转修改页面
	 * @return
	 * @throws Exception
	 */
	@Action("productAction_toupdate")
	public String toupdate() throws Exception {
		//查询当前产品并压入值栈
		Product product = productService.get(model.getId());
		super.push(product);
		//设置查询工厂条件 state==1
		Specification<Factory> spec = new Specification<Factory>() {
			public Predicate toPredicate(Root<Factory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("state").as(Integer.class), 1);
			}
		};
		//查询工厂列表
		List<Factory> factoryList = factoryService.find(spec);
		super.put("factoryList", factoryList);
		return "toupdate";
	}
	
	/**
	 * 修改产品
	 * @return
	 * @throws Exception
	 */
	@Action("productAction_update")
	public String update() throws Exception {
		//根据id查询当前产品
		Product product = productService.get(model.getId());
		//设置修改的属性
		product.setFactory(model.getFactory());
		product.setFactoryName(model.getFactoryName());
		product.setProductNo(model.getProductNo());
		product.setProductImage(model.getProductImage());
		product.setColor(model.getColor());
		product.setQty(model.getQty());
		product.setPackingUnit(model.getPackingUnit());
		product.setSizeLenght(model.getSizeLenght());
		product.setSizeWidth(model.getSizeWidth());
		product.setSizeHeight(model.getSizeHeight());
		product.setPrice(model.getPrice());
		product.setDescription(model.getDescription());
		product.setRemark(model.getRemark());
		//修改产品
		productService.saveOrUpdate(product);
		return SUCCESS;
	}
	
	/**
	 * 删除产品
	 * @return
	 * @throws Exception
	 */
	@Action("productAction_delete")
	public String delete() throws Exception {
		productService.delete(model.getId().split(", "));;
		return SUCCESS;
	}
	
	@Override
	public Product setModel() {
		return new Product();
	}
	
}
