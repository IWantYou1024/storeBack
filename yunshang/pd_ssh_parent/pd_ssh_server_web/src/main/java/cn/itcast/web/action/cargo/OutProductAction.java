package cn.itcast.web.action.cargo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.domain.cargo.ContractProduct;
import cn.itcast.service.ContractProductService;
import cn.itcast.utils.DownloadUtil;
import cn.itcast.utils.UtilFuns;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Results({ @Result(name = "toedit", location = "/WEB-INF/pages/cargo/outproduct/jOutProduct.jsp") })
public class OutProductAction extends BaseAction {

	private String inputDate;

	@Autowired
	private ContractProductService contractProductService;

	/**
	 * 进入出货表的打印页面
	 */
	@Action("outProductAction_toedit")
	public String toedit() throws Exception {
		return "toedit";
	}

	/**
	 * 使用模板 2003版本
	 */
	@Action("outProductAction_print")
	public String print2() throws Exception {
		// 获取模板
		String path = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tOUTPRODUCT.xls");
		FileInputStream in = new FileInputStream(new File(path));
		Workbook wb = new HSSFWorkbook(in);
		// 获取Sheet
		Sheet sheet = wb.getSheetAt(0);
		// 设置一些通用变量
		Row nRow = null;
		Cell nCell = null;
		int cellNo = 1, rowNo = 0;

		// ==================制作大标题
		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(1);
		nCell.setCellValue(inputDate.replace("-0", "-").replace("-", "年") + "月份出货表");

		// =================制作小标题
		rowNo++;

		// 获取样式
		nRow = sheet.getRow(rowNo++);
		List<CellStyle> styleList = new ArrayList<>();
		for (int i = 1; i < 9; i++) {
			nRow.getCell(i).getCellStyle();
			styleList.add(nRow.getCell(i).getCellStyle());
		}
		rowNo--;

		// ================内容制作
		List<ContractProduct> cpList = contractProductService.findByShipTime(inputDate);

		if (cpList.size() == 0 || cpList == null) {
			return NONE;
		}

		for (ContractProduct cp : cpList) {
			nRow = sheet.createRow(rowNo++); // 创建行对象

			cellNo = 1;
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getContract().getCustomName()); // 设置单元格内容
			nCell.setCellStyle(styleList.get(0));

			// 订单号
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getContract().getContractNo()); // 设置单元格内容
			nCell.setCellStyle(styleList.get(1));

			// 货号
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getProductNo()); // 设置单元格内容
			nCell.setCellStyle(styleList.get(2));

			// 数量
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getCnumber()); // 设置单元格内容
			nCell.setCellStyle(styleList.get(3));

			// 工厂
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getFactoryName()); // 设置单元格内容
			nCell.setCellStyle(styleList.get(4));

			// 工厂交期
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getDeliveryPeriod())); // 设置单元格内容
			nCell.setCellStyle(styleList.get(5));

			// 船期
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getShipTime())); // 设置单元格内容
			nCell.setCellStyle(styleList.get(6));

			// 贸易条款
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getContract().getTradeTerms()); // 设置单元格内容
			nCell.setCellStyle(styleList.get(7));
		}

		// =====================下载Excel文件
		DownloadUtil downloadUtil = new DownloadUtil();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		HttpServletResponse response = ServletActionContext.getResponse();
		downloadUtil.download(baos, response, "出货表-" + inputDate + ".xls");

		return NONE;
	}

	/**
	 * 实现POI打印 2003版本
	 */
	// @Action("outProductAction_print")
	public String print() throws Exception {
		/** 创建工作簿 */
		Workbook wb = new HSSFWorkbook();
		// 创建工作表Sheet
		Sheet sheet = wb.createSheet();
		// 设置一些通用变量
		Row nRow = null;
		Cell nCell = null;
		int cellNo = 1, rowNo = 0;

		// 设置列宽信息
		sheet.setColumnWidth(0, 1 * 256);
		sheet.setColumnWidth(1, 26 * 256);
		sheet.setColumnWidth(2, 11 * 256);
		sheet.setColumnWidth(3, 29 * 256);
		sheet.setColumnWidth(4, 12 * 256);
		sheet.setColumnWidth(5, 15 * 256);
		sheet.setColumnWidth(6, 10 * 256);
		sheet.setColumnWidth(7, 10 * 256);
		sheet.setColumnWidth(8, 10 * 256);

		// ==================制作大标题
		nRow = sheet.createRow(rowNo++);// 创建行对象
		nRow.setHeightInPoints(36); // 设置行高
		nCell = nRow.createCell(cellNo);// 创建单元格对象
		nCell.setCellValue(inputDate.replace("-0", "-").replace("-", "年") + "月份出货表");
		// 实现第一行单元格合并
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));// 横向合并单元格
		// 设置样式
		nCell.setCellStyle(this.bigTitle(wb));

		// =================制作小标题
		nRow = sheet.createRow(rowNo++);
		nRow.setHeightInPoints(26.25f);// 行高
		String[] titles = { "客户", "订单号", "货号", "数量", "工厂", "工厂交期", "船期", "贸易条款" };
		for (String title : titles) {
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(title);
			nCell.setCellStyle(this.title(wb));
		}

		// ================内容制作
		// 查询符合条件的出货记录
		/*
		 * DateFormat df = new SimpleDateFormat("yyyy-MM"); Date parse =
		 * df.parse(inputDate); final Timestamp ts1 = new
		 * Timestamp(parse.getTime()); parse.setMonth(parse.getMonth() + 1);
		 * final Timestamp ts2 = new Timestamp(parse.getTime());
		 * 
		 * Specification<ContractProduct> spec = new
		 * Specification<ContractProduct>() { public Predicate
		 * toPredicate(Root<ContractProduct> root, CriteriaQuery<?> query,
		 * CriteriaBuilder cb) { Join<ContractProduct, Contract> join =
		 * root.join("contract"); Predicate between =
		 * cb.between(join.get("shipTime").as(Timestamp.class), ts1, ts2);
		 * return between; } }; List<ContractProduct> cpList =
		 * contractProductService.find(spec);
		 */

		List<ContractProduct> cpList = contractProductService.findByShipTime(inputDate);

		if (cpList.size() == 0 || cpList == null) {
			return NONE;
		}

		for (ContractProduct cp : cpList) {
			nRow = sheet.createRow(rowNo++); // 创建行对象
			nRow.setHeightInPoints(24f);

			cellNo = 1;
			// 得到单元格对象 客户
			nCell = nRow.createCell(cellNo++); // 创建单元格对象
			nCell.setCellValue(cp.getContract().getCustomName()); // 设置单元格内容
			nCell.setCellStyle(this.text(wb));

			// 订单号
			nCell = nRow.createCell(cellNo++); // 创建单元格对象
			nCell.setCellValue(cp.getContract().getContractNo()); // 设置单元格内容
			nCell.setCellStyle(this.text(wb));

			// 货号
			nCell = nRow.createCell(cellNo++); // 创建单元格对象
			nCell.setCellValue(cp.getProductNo()); // 设置单元格内容
			nCell.setCellStyle(this.text(wb));

			// 数量
			nCell = nRow.createCell(cellNo++); // 创建单元格对象
			nCell.setCellValue(cp.getCnumber()); // 设置单元格内容
			nCell.setCellStyle(this.text(wb));

			// 工厂
			nCell = nRow.createCell(cellNo++); // 创建单元格对象
			nCell.setCellValue(cp.getFactoryName()); // 设置单元格内容
			nCell.setCellStyle(this.text(wb));

			// 工厂交期
			nCell = nRow.createCell(cellNo++); // 创建单元格对象
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getDeliveryPeriod())); // 设置单元格内容
			nCell.setCellStyle(this.text(wb));

			// 船期
			nCell = nRow.createCell(cellNo++); // 创建单元格对象
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getShipTime())); // 设置单元格内容
			nCell.setCellStyle(this.text(wb));

			// 贸易条款
			nCell = nRow.createCell(cellNo++); // 创建单元格对象
			nCell.setCellValue(cp.getContract().getTradeTerms()); // 设置单元格内容
			nCell.setCellStyle(this.text(wb));
		}

		// =====================下载Excel文件
		DownloadUtil downloadUtil = new DownloadUtil();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		HttpServletResponse response = ServletActionContext.getResponse();
		downloadUtil.download(baos, response, "出货表-" + inputDate + ".xls");
		return NONE;
	}

	// 文字样式
	private CellStyle text(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 10);

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_LEFT); // 横向居左
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中

		style.setBorderTop(CellStyle.BORDER_THIN); // 上细线
		style.setBorderBottom(CellStyle.BORDER_THIN); // 下细线
		style.setBorderLeft(CellStyle.BORDER_THIN); // 左细线
		style.setBorderRight(CellStyle.BORDER_THIN); // 右细线

		return style;
	}

	// 小标题样式
	private CellStyle title(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中

		style.setBorderTop(CellStyle.BORDER_THIN); // 上细线
		style.setBorderBottom(CellStyle.BORDER_THIN); // 下细线
		style.setBorderLeft(CellStyle.BORDER_THIN); // 左细线
		style.setBorderRight(CellStyle.BORDER_THIN); // 右细线

		return style;
	}

	// 大标题样式
	private CellStyle bigTitle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字体加粗

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中
		return style;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

}
