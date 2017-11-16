package cn.itcast.web.action.cargo;

import java.util.Date;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.cargo.Export;
import cn.itcast.domain.cargo.ExportProduct;
import cn.itcast.domain.vo.ExportProductVo;
import cn.itcast.domain.vo.ExportResult;
import cn.itcast.domain.vo.ExportVo;
import cn.itcast.service.ExportProductService;
import cn.itcast.service.ExportService;
import cn.itcast.web.action.BaseAction;

@Namespace("/cargo")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/cargo/export/jExportList.jsp"),
		@Result(name = "toview", location = "/WEB-INF/pages/cargo/export/jExportView.jsp"),
		@Result(name = "tocreate", location = "/WEB-INF/pages/cargo/export/jExportCreate.jsp"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/cargo/export/jExportUpdate.jsp"),
		@Result(name = "success", type = "redirect", location = "exportAction_list") })
public class ExportAction extends BaseAction<Export> {

	@Autowired
	private ExportService exportService;
	@Autowired
	private ExportProductService exportProductService;

	private String[] mr_id; // 货物id
	private String[] mr_changed; // 是否改变数据
	private Integer[] mr_orderNo; // 排序号
	private Integer[] mr_cnumber; // 数量
	private Double[] mr_grossWeight; // 毛重
	private Double[] mr_netWeight; // 净重
	private Double[] mr_sizeLength; // 长
	private Double[] mr_sizeWidth; // 宽
	private Double[] mr_sizeHeight; // 高
	private Double[] mr_exPrice; // 出口单价
	private Double[] mr_tax; // 含税

	/**
	 * 出口报运
	 */
	@Action("exportAction_list")
	public String list() throws Exception {
		Specification<Export> spec = new Specification<Export>() {
			public Predicate toPredicate(Root<Export> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		};
		Page<Export> page2 = exportService.findPage(spec, new PageRequest(page.getPageNo() - 1, page.getPageSize()));
		super.parsePage(page, page2);
		page.setUrl("exportAction_list");
		super.push(page);
		return "list";
	}

	/**
	 * 查看报运单
	 */
	@Action("exportAction_toview")
	public String toview() throws Exception {
		Export export = exportService.get(model.getId());
		super.push(export);
		return "toview";
	}

	/**
	 * 跳转新增报运单
	 */
	@Action("exportAction_tocreate")
	public String tocreate() throws Exception {
		return "tocreate";
	}

	/**
	 * 新增报运单
	 */
	@Action("exportAction_insert")
	public String insert() throws Exception {
		exportService.saveOrUpdate(model);
		return SUCCESS;
	}

	/**
	 * 跳转修改报运单
	 */
	@Action("exportAction_toupdate")
	public String toupdate() throws Exception {
		Export export = exportService.get(model.getId());
		super.push(export);
		String mRecordData = this.formatJsStr(export);
		super.put("mRecordData", mRecordData);
		return "toupdate";
	}

	private String formatJsStr(Export export) {
		StringBuilder sb = new StringBuilder();
		Set<ExportProduct> exportProducts = export.getExportProducts();
		for (ExportProduct ep : exportProducts) {
			sb.append("addTRRecord('mRecordTable', '").append(ep.getId());
			sb.append("', '").append(ep.getProductNo());
			sb.append("', '").append(ep.getCnumber());
			sb.append("', '").append(ep.getGrossWeight());
			sb.append("', '").append(ep.getNetWeight());
			sb.append("', '").append(ep.getSizeLength());
			sb.append("', '").append(ep.getSizeWidth());
			sb.append("', '").append(ep.getSizeHeight());
			sb.append("', '").append(ep.getExPrice());
			sb.append("', '").append(ep.getTax());
			sb.append("');");
		}
		return sb.toString();
	}

	/**
	 * 修改报运单
	 */
	@Action("exportAction_update")
	public String update() throws Exception {
		// 数据大小
		for (int i = 0; i < mr_changed.length; i++) {
			// 如果标识为1则修改了
			if (mr_changed[i].equals("1")) {
				// 查询货物数据并修改
				ExportProduct exportProduct = exportProductService.get(mr_id[i]);
				exportProduct.setCnumber(mr_cnumber[i]);
				exportProduct.setGrossWeight(mr_grossWeight[i]);
				exportProduct.setNetWeight(mr_netWeight[i]);
				exportProduct.setSizeLength(mr_sizeLength[i]);
				exportProduct.setSizeWidth(mr_sizeWidth[i]);
				exportProduct.setSizeHeight(mr_sizeHeight[i]);
				exportProduct.setExPrice(mr_exPrice[i]);
				exportProduct.setTax(mr_tax[i]);
				exportProduct.setOrderNo(mr_orderNo[i]);
				// 保存修改后的货物
				exportProductService.saveOrUpdate(exportProduct);
			}
		}
		// 获取报运单并修改数据
		Export export = exportService.get(model.getId());
		export.setInputDate(model.getInputDate());
		export.setLcno(model.getLcno());
		export.setConsignee(model.getConsignee());
		export.setShipmentPort(model.getShipmentPort());
		export.setDestinationPort(model.getDestinationPort());
		export.setTransportMode(model.getTransportMode());
		export.setPriceCondition(model.getPriceCondition());
		export.setMarks(model.getMarks());
		export.setRemark(model.getRemark());
		// 保存修改后的报运单, 冗余字段未修改
		exportService.saveOrUpdate(export);
		return SUCCESS;
	}

	/**
	 * 删除报运单
	 */
	@Action("exportAction_delete")
	public String delete() throws Exception {
		exportService.delete(model.getId().split(", "));
		return SUCCESS;
	}

	/**
	 * 报运单提交 将状态改为1
	 */
	@Action("exportAction_submit")
	public String submit() throws Exception {
		exportService.updateState(model.getId().split(", "), 1);
		return SUCCESS;
	}

	/**
	 * 报运单取消 将状态改为0
	 */
	@Action("exportAction_cancel")
	public String cancel() throws Exception {
		exportService.updateState(model.getId().split(", "), 0);
		return SUCCESS;
	}

	/**
	 * 电子报运
	 */
	@Action("exportAction_exportE")
	public String exportE() throws Exception {
		// 准备数据
		Export export = exportService.get(model.getId());
		ExportVo exportVo = new ExportVo();

		BeanUtils.copyProperties(export, exportVo);
		exportVo.setExportId(export.getId());
		exportVo.setExportDate(new Date());

		Set<ExportProduct> epSets = export.getExportProducts();
		Set<ExportProductVo> products = exportVo.getProducts();

		for (ExportProduct exportProduct : epSets) {
			ExportProductVo exportProductVo = new ExportProductVo();
			BeanUtils.copyProperties(exportProduct, exportProductVo);

			exportProductVo.setExportId(model.getId());
			exportProductVo.setExportProductId(exportProduct.getId());
			exportProductVo.setFactoryId(exportProduct.getFactory().getId());

			products.add(exportProductVo);
		}

		exportVo.setProducts(products);

		// 报运
		WebClient.create("http://localhost:8080/jk_export/ws/export/user").type(MediaType.APPLICATION_JSON)
				.post(exportVo);

		// 返回结果处理
		ExportResult result = WebClient.create("http://localhost:8080/jk_export/ws/export/user/" + model.getId())
				.accept(MediaType.APPLICATION_JSON).get(ExportResult.class);
		exportService.updateExportE(result);

		return SUCCESS;
	}

	public String[] getMr_id() {
		return mr_id;
	}

	public void setMr_id(String[] mr_id) {
		this.mr_id = mr_id;
	}

	public String[] getMr_changed() {
		return mr_changed;
	}

	public void setMr_changed(String[] mr_changed) {
		this.mr_changed = mr_changed;
	}

	public Integer[] getMr_orderNo() {
		return mr_orderNo;
	}

	public void setMr_orderNo(Integer[] mr_orderNo) {
		this.mr_orderNo = mr_orderNo;
	}

	public Integer[] getMr_cnumber() {
		return mr_cnumber;
	}

	public void setMr_cnumber(Integer[] mr_cnumber) {
		this.mr_cnumber = mr_cnumber;
	}

	public Double[] getMr_grossWeight() {
		return mr_grossWeight;
	}

	public void setMr_grossWeight(Double[] mr_grossWeight) {
		this.mr_grossWeight = mr_grossWeight;
	}

	public Double[] getMr_netWeight() {
		return mr_netWeight;
	}

	public void setMr_netWeight(Double[] mr_netWeight) {
		this.mr_netWeight = mr_netWeight;
	}

	public Double[] getMr_sizeLength() {
		return mr_sizeLength;
	}

	public void setMr_sizeLength(Double[] mr_sizeLength) {
		this.mr_sizeLength = mr_sizeLength;
	}

	public Double[] getMr_sizeWidth() {
		return mr_sizeWidth;
	}

	public void setMr_sizeWidth(Double[] mr_sizeWidth) {
		this.mr_sizeWidth = mr_sizeWidth;
	}

	public Double[] getMr_sizeHeight() {
		return mr_sizeHeight;
	}

	public void setMr_sizeHeight(Double[] mr_sizeHeight) {
		this.mr_sizeHeight = mr_sizeHeight;
	}

	public Double[] getMr_exPrice() {
		return mr_exPrice;
	}

	public void setMr_exPrice(Double[] mr_exPrice) {
		this.mr_exPrice = mr_exPrice;
	}

	public Double[] getMr_tax() {
		return mr_tax;
	}

	public void setMr_tax(Double[] mr_tax) {
		this.mr_tax = mr_tax;
	}

	@Override
	public Export setModel() {
		return new Export();
	}

}
