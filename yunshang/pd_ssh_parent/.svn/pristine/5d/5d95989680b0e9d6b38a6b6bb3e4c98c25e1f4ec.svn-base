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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CONTRACT_PRODUCT_C")
public class ContractProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRACT_PRODUCT_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToOne
	@JoinColumn(name = "CONTRACT_ID")
	private Contract contract; // 货物与合同, 多对一

	@ManyToOne
	@JoinColumn(name = "FACTORY_ID")
	private Factory factory; // 货物与厂家, 多对一

	@OneToMany(mappedBy = "contractProduct", cascade = CascadeType.ALL)
	private Set<ExtCproduct> extCproducts = new HashSet<>(); // 货物与附件, 一对多

	@Column(name = "FACTORY_NAME")
	private String factoryName; // 冗余
	@Column(name = "PRODUCT_NO")
	private String productNo; // 货号
	@Column(name = "PRODUCT_IMAGE")
	private String productImage; // 图片路径
	@Column(name = "PRODUCT_DESC")
	private String productDesc; // 货物描述
	@Column(name = "LOADING_RATE")
	private String loadingRate; // 报运, 装箱率 1/3
	@Column(name = "BOX_NUM")
	private Integer boxNum; // 报运, 箱数 100
	@Column(name = "PACKING_UNIT")
	private String packingUnit; // 包装单位, PCS/SETS 支/箱
	@Column(name = "CNUMBER")
	private Integer cnumber; // 数量
	@Column(name = "OUT_NUMBER")
	private Integer outNumber; // 报运 出货数量
	@Column(name = "FINISHED")
	private Integer finished; // 报运 是否完成
	@Column(name = "PRODUCT_REQUEST")
	private String productRequest; // 要求
	@Column(name = "PRICE")
	private Double price; // 单价
	@Column(name = "AMOUNT")
	private Double amount; // 总金额, 冗余
	@Column(name = "ORDER_NO")
	private Integer orderNo; // 排序号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	public Set<ExtCproduct> getExtCproducts() {
		return extCproducts;
	}

	public void setExtCproducts(Set<ExtCproduct> extCproducts) {
		this.extCproducts = extCproducts;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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

	public String getLoadingRate() {
		return loadingRate;
	}

	public void setLoadingRate(String loadingRate) {
		this.loadingRate = loadingRate;
	}

	public Integer getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
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

	public Integer getOutNumber() {
		return outNumber;
	}

	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}

	public Integer getFinished() {
		return finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	public String getProductRequest() {
		return productRequest;
	}

	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}
