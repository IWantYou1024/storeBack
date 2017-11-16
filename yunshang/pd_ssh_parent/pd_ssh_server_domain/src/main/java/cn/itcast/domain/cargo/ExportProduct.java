package cn.itcast.domain.cargo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EXPORT_PRODUCT_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ExportProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EXPORT_PRODUCT_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	@JoinColumn(name = "EXPORT_ID")
	private Export export; // 报运货物与报运的关系, 多对一

	@ManyToOne
	@JoinColumn(name = "FACTORY_ID")
	private Factory factory; // 报运货物与厂家的关系, 多对一

	@OneToMany(mappedBy = "exportProduct", cascade = CascadeType.ALL)
	private Set<ExtEproduct> extEproducts = new HashSet<>(); // 报运货物与报运附件的关系,
																// 一对多

	@Column(name = "PRODUCT_NO")
	private String productNo; // 货号
	@Column(name = "PACKING_UNIT")
	private String packingUnit; // 包装单位 PCS/SETS
	@Column(name = "CNUMBER")
	private Integer cnumber; // 数量
	@Column(name = "BOX_NUM")
	private Integer boxNum; // 件数
	@Column(name = "GROSS_WEIGHT")
	private Double grossWeight; // 毛重
	@Column(name = "NET_WEIGHT")
	private Double netWeight; // 净重
	@Column(name = "SIZE_LENGTH")
	private Double sizeLength; // 尺寸长
	@Column(name = "SIZE_WIDTH")
	private Double sizeWidth; // 尺寸宽
	@Column(name = "SIZE_HEIGHT")
	private Double sizeHeight; // 尺寸高
	@Column(name = "EX_PRICE")
	private Double exPrice; // 出口单价 sales confirmation 中的价格（手填）
	@Column(name = "PRICE")
	private Double price; // 单价
	@Column(name = "TAX")
	private Double tax; // 含税 收购单价=合同单价
	@Column(name = "ORDER_NO")
	private Integer orderNo; // 排序号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Export getExport() {
		return export;
	}

	public void setExport(Export export) {
		this.export = export;
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	public Set<ExtEproduct> getExtEproducts() {
		return extEproducts;
	}

	public void setExtEproducts(Set<ExtEproduct> extEproducts) {
		this.extEproducts = extEproducts;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getPackingUnit() {
		return packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}

	public Integer getCnumber() {
		return cnumber;
	}

	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}

	public Integer getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getSizeLength() {
		return sizeLength;
	}

	public void setSizeLength(Double sizeLength) {
		this.sizeLength = sizeLength;
	}

	public Double getSizeWidth() {
		return sizeWidth;
	}

	public void setSizeWidth(Double sizeWidth) {
		this.sizeWidth = sizeWidth;
	}

	public Double getSizeHeight() {
		return sizeHeight;
	}

	public void setSizeHeight(Double sizeHeight) {
		this.sizeHeight = sizeHeight;
	}

	public Double getExPrice() {
		return exPrice;
	}

	public void setExPrice(Double exPrice) {
		this.exPrice = exPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}
