package cn.itcast.web.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.domain.cargo.Export;
import cn.itcast.domain.cargo.PackingList;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.ExportService;
import cn.itcast.service.PackingListService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/cargo/packinglist/jPackingListListPage.jsp"),
		@Result(name = "toview", location = "/WEB-INF/pages/cargo/packinglist/jPackingListView.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/cargo/packinglist/jPackingListCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/cargo/packinglist/jPackingListUpdate.jsp"),
		@Result(name = "packingListList", location = "/WEB-INF/pages/cargo/export/jPackingListListPage.jsp"),
		@Result(name = "success", type = "redirect", location = "packingListAction_list") })
public class PackingListAction extends BaseAction<PackingList> {

	@Autowired
	private PackingListService packingListService;
	@Autowired
	private ExportService exportService;

	/**
	 * 跳转装箱单列表
	 */
	@Action("packingListAction_list")
	public String list() throws Exception {
		//查询所有状态的装箱单,并显示
		Page<PackingList> page2 = packingListService.findPage(null,
				new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("packingListAction_list");
		super.push(page);
		return "list";
	}

	/**
	 * 跳转查看装箱单
	 */
	@Action("packingListAction_toview")
	public String toview() throws Exception {
		PackingList packingList = packingListService.get(model.getPackingListId());
		super.push(packingList);
		return "toview";
	}

	/**
	 * 跳转新增装箱单
	 */
	@Action("packingListAction_tocreate")
	public String tocreate() throws Exception {
		//分页查询所有的已报运的报运单,state=2
		Specification<Export> spec=new Specification<Export>() {
			@Override
			public Predicate toPredicate(Root<Export> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.get("state").as(Integer.class), 2);
			}
		};
		
		Page<Export> page2 = exportService.findPage(spec, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("packingListAction_tocreate");
		super.push(page);
		
		return "tocreate";
	}

	/**
	 * 新增装运单
	 */
	@Action("packingListAction_insert")
	public String insert() throws Exception {
		// 保存创建信息
//		model.setCreateBy(getCurUser().getId());
//		model.setCreateDept(getCurUser().getDept().getId());
		packingListService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转更新装箱单
	 */
	@Action("packingListAction_toupdate")
	public String toupdate() throws Exception {
		//将模型的数据回显

		PackingList packingList = packingListService.get(model.getPackingListId());
		
		//获取被勾选的报运单ids字符串
		String exportIdsStr = packingList.getExportIds();
		//压栈
		super.put("exportIdsStr", exportIdsStr);
		super.push(packingList);
		
		//分页查询所有的已报运和已装箱的报运单,state=2和state=3
		//并在页面上勾选自己有的已装箱的
		Specification<Export> spec=new Specification<Export>() {
			@Override
			public Predicate toPredicate(Root<Export> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate p1 = cb.equal(root.get("state").as(Integer.class), 2);
				Predicate p2 = cb.equal(root.get("state").as(Integer.class), 3);
				return cb.or(p1,p2);
			}
		};
		
		Page<Export> page2 = exportService.findPage(spec, new PageRequest(page.getPageNo()-1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("packingListAction_toupdate");
		super.push(page);
		
		
		return "toupdate";
	}

	/**
	 * 更新装箱单
	 */
	@Action("packingListAction_update")
	public String update() throws Exception {
		//走更新
		
		//查询原来的装箱单
		PackingList packingList = packingListService.get(model.getPackingListId());
		//修改原来的报运单的状态为2
		if(UtilFuns.isNotEmpty(packingList.getExportIds())){
			String[] cids = packingList.getExportIds().split(", ");
					//更新报运单状态
				exportService.updateState(cids, 2);
		
		}
		
		//更新装箱单数据
		
		packingList.setBuyer(model.getBuyer());
		packingList.setSeller(model.getSeller());
		packingList.setMarks(model.getMarks());
		packingList.setDescriptions(model.getDescriptions());
		
		//重新将页面勾选的报运单状态,修改为3
		if(UtilFuns.isNotEmpty(model.getExportIds())){
			String[] cids = model.getExportIds().split(", ");
					//更新报运单状态,已装箱
				exportService.updateState(cids, 3);
		}
		//更新报运单
		packingList.setExportIds(model.getExportIds());
		
//		// 保存更新信息
//		packingList.setUpdateBy(getCurUser().getId());
//
		packingListService.saveOrUpdate(packingList);
		return SUCCESS;
	}


	/**
	 * 提交装箱单, 修改状态为1
	 */
	@Action("packingListAction_submit")
	public String submit() throws Exception {
		String[] plId = model.getPackingListId().split(", ");
		packingListService.updateState(plId, 1);
		return SUCCESS;
	}

	/**
	 * 取消装箱单, 修改状态为0
	 */
	@Action("packingListAction_cancel")
	public String cancel() throws Exception {
		packingListService.updateState(model.getPackingListId().split(", "), 0);
		return SUCCESS;
	}

	/**
	 * 删除装箱单
	 */
	@Action("packingListAction_delete")
	public String delete() throws Exception {
		packingListService.delete(model.getPackingListId().split(", "));
		return SUCCESS;
	}
	
	
	/**
	 * 使用模板 2003版本
	 */
	@Action("packingListAction_print")
	public String print() throws Exception {
		//查找数据
		//查询原来的装箱单
		PackingList p = packingListService.get(model.getPackingListId());
		
		// 获取模板
		String path = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tPARKINGLIST.xls");
		FileInputStream in = new FileInputStream(new File(path));
		Workbook wb = new HSSFWorkbook(in);
		// 获取Sheet
		Sheet sheet = wb.getSheetAt(0);
		// 设置一些通用变量
		Row nRow = null;
		Cell nCell = null;

		// ==================填充seller数据
		nRow = sheet.getRow(3);
		nCell = nRow.getCell(0);
		if(UtilFuns.isNotEmpty(p.getSeller())){
			nCell.setCellValue(p.getSeller());
		}
		
		// =================获取Buyer数据
		nRow = sheet.getRow(8);
		nCell = nRow.getCell(0);
		if(UtilFuns.isNotEmpty(p.getBuyer())){
			nCell.setCellValue(p.getBuyer());
		}
		
		// =================获取Invoice No.数据
		nRow = sheet.getRow(15);
		nCell = nRow.getCell(0);
		if(UtilFuns.isNotEmpty(p.getInvoiceNo())){
			nCell.setCellValue(p.getInvoiceNo());
		}
		
		// =================获取Date数据
		nCell = nRow.getCell(3);
		if(UtilFuns.isNotEmpty(p.getInvoiceDate())){
			nCell.setCellValue(UtilFuns.dateTimeFormat(p.getInvoiceDate()));
		}
		
		// =================获取Marks and Nos数据
		nRow = sheet.getRow(19);
		nCell = nRow.getCell(0);
		if(UtilFuns.isNotEmpty(p.getMarks()+p.getExportNos())){
			nCell.setCellValue(p.getMarks()+p.getExportNos());
		}
		
		// =================获取Descriptions数据
		nCell = nRow.getCell(2);
		if(UtilFuns.isNotEmpty(p.getDescriptions())){
			nCell.setCellValue(p.getDescriptions());
		}

		
		// =====================下载Excel文件
		DownloadUtil downloadUtil = new DownloadUtil();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		HttpServletResponse response = ServletActionContext.getResponse();
		downloadUtil.download(baos, response, "装箱单"+".xls");

		return NONE;
	}

	
	
	

	
	@Override
	public PackingList setModel() {
		return new PackingList();
	}

}
