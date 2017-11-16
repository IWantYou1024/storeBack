package cn.itcast.web.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.domain.cargo.Finance;
import cn.itcast.domain.cargo.Invoice;
import cn.itcast.domain.cargo.PackingList;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.FinanceService;
import cn.itcast.service.InvoiceService;
import cn.itcast.service.PackingListService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.Page;
import cn.itcast.utils.SysConstant;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/cargo/finance/jFinanceList.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/cargo/finance/jFinanceToCreat.jsp"),
		@Result(name = "insert", type = "redirect", location = "financeAction_list"),
		@Result(name = "update", type = "redirect", location = "financeAction_list"),
		@Result(name = "toview", location = "/WEB-INF/pages/cargo/finance/jFinanceView.jsp"),
		@Result(name = "delete", type = "redirect", location = "financeAction_list"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/cargo/finance/jFinanceToUpdate.jsp"),
		@Result(name = "submit", type = "redirect", location = "financeAction_list"),
		@Result(name = "cancel", type = "redirect", location = "financeAction_list") })
public class FinanceAction extends BaseAction<Finance> {

	@Override
	public Finance setModel() {

		return new Finance();
	}

	@Autowired
	private FinanceService financeService;

	@Autowired
	private PackingListService packingListService;

	@Autowired
	private InvoiceService invoiceService;
	/**
	 * list方法
	 * 		显示所有的已经开出发票提交给财务,并且已经添加进财务的项
	 * @return
	 */
	@Action(value = "financeAction_list")
	public String list() {
		 Specification<Finance> spec = new Specification<Finance>() {
		
		 @Override
		 public Predicate toPredicate(Root<Finance> root, CriteriaQuery<?>
		 query, CriteriaBuilder cb) {
			    Predicate p1 = cb.equal(root.get("state").as(Integer.class), 0);
				Predicate p2 = cb.equal(root.get("state").as(Integer.class), 1);
				return cb.or(p1,p2);
		 }
		 };
		Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());

		org.springframework.data.domain.Page<Finance> findPage = financeService.findPage(spec, pageable);

		page.setResults(findPage.getContent());
		page.setTotalPage(findPage.getTotalPages());
		page.setTotalRecord(findPage.getTotalElements());
		page.setUrl("financeAction_list");

		super.push(page);

		return "list";
	}
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@Action(value = "financeAction_tocreate")
	public String tocreate() {
		User user = (User) ActionContext.getContext().getSession().get(SysConstant.CURRENT_USER_INFO);
		super.push(user);

		Specification<Invoice> spec = new Specification<Invoice>() {

			@Override
			public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate equal = cb.equal(root.get("state").as(Integer.class), "1");
				return equal;
			}
		};
		Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());

		org.springframework.data.domain.Page<Invoice> findPage = invoiceService.findPage(spec, pageable);

		page.setResults(findPage.getContent());
		page.setTotalPage(findPage.getTotalPages());
		page.setTotalRecord(findPage.getTotalElements());
		page.setUrl("/WEB-INF/pages/cargo/finance/jFinanceToCreat.jsp");

		super.push(page);

		return "tocreate";
	}
   /**
 *    添加方法 , 将发票提交的项加入list页面
 * @return
 */
	@Action(value = "financeAction_insert")
	public String insert() {
		// PackingList packingList = packingListService.get(model.getId());
		Invoice invoice = invoiceService.get(model.getId());

		// packingList.setState(4);
		invoice.setState(2);
		model.setState(0);
		financeService.saveOrUpdate(model);

		// packingListService.saveOrUpdate(packingList);
		invoiceService.saveOrUpdate(invoice);
		return "insert";
	}
	/**
	 * 查看财务单的详细信息
	 * @return
	 */
	@Action(value = "financeAction_toview")
	public String toview() {
		Finance finance = financeService.get(model.getId());
		super.push(finance);
		return "toview";
	}
	/**
	 * 删除选中的财务单
	 * @return
	 */
	@Action(value = "financeAction_delete")
	public String delete() {

		String[] ids = model.getId().split(", ");
		for (String id : ids) {
			Invoice invoice = invoiceService.get(id);
			invoice.setState(1);
			invoiceService.saveOrUpdate(invoice);

			PackingList packingList = packingListService.get(id);
			packingList.setState(3);
			packingListService.saveOrUpdate(packingList);

			Finance finance = financeService.get(id);
			finance.setCreatBy(null);
			finance.setCreatDept(null);
			// finance.setCreatTime(null);
			finance.setInputBy(null);
			// finance.setInputDate(null);
			finance.setState(null);

			financeService.saveOrUpdate(finance);
		}

		return "delete";
	}
	/**
	 * 跳转到修改财务单详细信息的页面
	 * @return
	 */
	@Action(value = "financeAction_toupdate")
	public String toupdate() {
		Finance finance = financeService.get(model.getId());
		
		super.push(finance);
		
		

		return "toupdate";
	}
	
	/**
	 * 修改财务单信息
	 * @return
	 */
			
			
	@Action(value = "financeAction_update")
	public String update() {
		
		financeService.saveOrUpdate(model);

		return "update";
	}
	/**
	 * 提交财务单
	 * @return
	 */
	@Action(value = "financeAction_submit")
	public String submit() {
		String[] ids = model.getId().split(", ");
		for (String id : ids) {
			Finance finance = financeService.get(id);
			finance.setState(1);
			financeService.saveOrUpdate(finance);

			Invoice invoice = invoiceService.get(id);
			invoice.setState(2);
			invoiceService.saveOrUpdate(invoice);

			PackingList packingList = packingListService.get(id);
			packingList.setState(4);
			packingListService.saveOrUpdate(packingList);
			super.push(finance);
		}
		return "submit";
	}
	/**
	 * 取消提交财务单
	 * @return
	 */
	@Action(value = "financeAction_cancel")
	public String cancel() {
		String[] ids = model.getId().split(", ");
		for (String id : ids) {
			Finance finance = financeService.get(id);
			finance.setState(0);
			financeService.saveOrUpdate(finance);

			Invoice invoice = invoiceService.get(id);
			invoice.setState(1);
			invoiceService.saveOrUpdate(invoice);

			PackingList packingList = packingListService.get(id);
			packingList.setState(3);
			packingListService.saveOrUpdate(packingList);

			super.push(finance);
		}
		return "cancel";
	}
	
	@Action(value="financeAction_print")
	public String print() throws Exception {
		String[] ids = model.getId().split(", ");
		for (String id : ids) {
		Finance finance = financeService.get(id);
			/**
			 * 使用模板 2003版本
			 */
		UtilFuns utilFuns = new UtilFuns();
				// 获取模板
				String path = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tFINANCE.xls");
				FileInputStream in = new FileInputStream(new File(path));
				Workbook wb = new HSSFWorkbook(in);
				// 获取Sheet
				Sheet sheet = wb.getSheetAt(0);
				// 设置一些通用变量
				Row nRow = null;
				Cell nCell = null;
				
				nRow = sheet.getRow(1);//财务单号
				nCell = nRow.createCell(2);
				if(UtilFuns.isNotEmpty(finance.getId())){
					nCell.setCellValue(finance.getId());
				}
				nRow = sheet.getRow(1);//创建日期
				nCell = nRow.createCell(7);
				if(UtilFuns.isNotEmpty(finance.getCreatTime())){
					nCell.setCellValue(UtilFuns.dateTimeFormat(finance.getCreatTime()));
				}
				nRow = sheet.getRow(1);//创建员
				nCell = nRow.createCell(11);
				if(UtilFuns.isNotEmpty(finance.getCreatBy())){
					nCell.setCellValue(finance.getCreatBy());
				}
				nRow = sheet.getRow(3);//创建部门
				nCell = nRow.createCell(2);
				if(UtilFuns.isNotEmpty(finance.getCreatDept())){
					nCell.setCellValue(finance.getCreatDept());
				}
				nRow = sheet.getRow(3);//录入员
				nCell = nRow.createCell(7);
				if(UtilFuns.isNotEmpty(finance.getInputBy())){
					nCell.setCellValue(finance.getInputBy());
				}
				
				nRow = sheet.getRow(5);//制单日期
				nCell = nRow.createCell(10);
				if(UtilFuns.isNotEmpty(finance.getInputDate())){
					nCell.setCellValue(UtilFuns.dateTimeFormat(finance.getInputDate()));
				}
				
				
				
				// =====================下载Excel文件
				DownloadUtil downloadUtil = new DownloadUtil();

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				wb.write(baos);
				HttpServletResponse response = ServletActionContext.getResponse();
				downloadUtil.download(baos, response, "财务表"  + ".xls");
		}

		return NONE;
	}
}
