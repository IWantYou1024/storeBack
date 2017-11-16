package cn.itcast.domain.cargo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.itcast.domain.BaseEntity;

@Entity
@Table(name = "CONTRACT_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Contract extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONTRACT_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id; // 购销合同id

	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
	private Set<ContractProduct> contractProducts = new HashSet<>();

	@Column(name = "OFFEROR")
	private String offeror; // 收购方
	@Column(name = "CONTRACT_NO")
	private String contractNo; // 合同号, 订单号
	@Column(name = "SIGNING_DATE")
	private Date signingDate; // 签单日期
	@Column(name = "INPUT_BY")
	private String inputBy; // 签单人
	@Column(name = "CHECK_BY")
	private String checkBy; // 审单人
	@Column(name = "INSPECTOR")
	private String inspector; // 验货员
	@Column(name = "TOTAL_AMOUNT")
	private Double totalAmount; // 总金额=货物的总金额+附件的总金额 冗余字段
	@Column(name = "CREQUEST")
	private String crequest; // 要求
	@Column(name = "CUSTOM_NAME")
	private String customName; // 客户名称
	@Column(name = "SHIP_TIME")
	private Date shipTime; // 船期
	@Column(name = "IMPORT_NUM")
	private Integer importNum; // 重要程度
	@Column(name = "DELIVERY_PERIOD")
	private Date deliveryPeriod; // 交货日期
	@Column(name = "OLD_STATE")
	private Integer oldState; // 旧的状态, 保运
	@Column(name = "OUT_STATE")
	private Integer outState; // 出货状态, 保运
	@Column(name = "TRADE_TERMS")
	private String tradeTerms; // 贸易条款, FOB
	@Column(name = "PRINT_STYLE")
	private String printStyle; // 打印板式, 1打印 一个货物, 2打印两个货物
	@Column(name = "REMARK")
	private String remark; // 备注
	@Column(name = "STATE")
	private Integer state; // 状态, 0草稿, 1已上报待报运

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<ContractProduct> getContractProducts() {
		return contractProducts;
	}

	public void setContractProducts(Set<ContractProduct> contractProducts) {
		this.contractProducts = contractProducts;
	}

	public String getOfferor() {
		return offeror;
	}

	public void setOfferor(String offeror) {
		this.offeror = offeror;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getSigningDate() {
		return signingDate;
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}

	public String getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCrequest() {
		return crequest;
	}

	public void setCrequest(String crequest) {
		this.crequest = crequest;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	public Integer getImportNum() {
		return importNum;
	}

	public void setImportNum(Integer importNum) {
		this.importNum = importNum;
	}

	public Date getDeliveryPeriod() {
		return deliveryPeriod;
	}

	public void setDeliveryPeriod(Date deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}

	public Integer getOldState() {
		return oldState;
	}

	public void setOldState(Integer oldState) {
		this.oldState = oldState;
	}

	public Integer getOutState() {
		return outState;
	}

	public void setOutState(Integer outState) {
		this.outState = outState;
	}

	public String getTradeTerms() {
		return tradeTerms;
	}

	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}

	public String getPrintStyle() {
		return printStyle;
	}

	public void setPrintStyle(String printStyle) {
		this.printStyle = printStyle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
