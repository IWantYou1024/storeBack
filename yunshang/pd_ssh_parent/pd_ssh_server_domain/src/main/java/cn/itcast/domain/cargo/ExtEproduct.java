package cn.itcast.domain.cargo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EXT_EPRODUCT_C")
public class ExtEproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EXT_EPRODUCT_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	@JoinColumn(name = "FACTORY_ID")
	private Factory factory; // 附件与工厂, 多对一

	@ManyToOne
	@JoinColumn(name = "EXPORT_PRODUCT_ID")
	private ExportProduct exportProduct; // 附件与货物, 多对一

	@Column(name = "PRODUCT_NO")
	private String productNo; // 货号
	@Column(name = "PRODUCT_IMAGE")
	private String productImage; // 货物照片
	@Column(name = "PRODUCT_DESC")
	private String productDesc; // 货物描述
	@Column(name = "CNUMBER")
	private Integer cnumber; // 数量
	@Column(name = "PACKING_UNIT")
	private String packingUnit; // 包装单位 PCS/SETS
	@Column(name = "PRICE")
	private Double price; // 单价
	@Column(name = "AMOUNT")
	private Double amount; // 总金额 自动计算:数量x单价
	@Column(name = "PRODUCT_REQUEST")
	private String productRequest; // 要求
	@Column(name = "ORDER_NO")
	private Integer orderNo; // 排序号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	public ExportProduct getExportProduct() {
		return exportProduct;
	}

	public void setExportProduct(ExportProduct exportProduct) {
		this.exportProduct = exportProduct;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getCnumber() {
		return cnumber;
	}

	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}

	public String getPackingUnit() {
		return packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getProductRequest() {
		return productRequest;
	}

	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}
