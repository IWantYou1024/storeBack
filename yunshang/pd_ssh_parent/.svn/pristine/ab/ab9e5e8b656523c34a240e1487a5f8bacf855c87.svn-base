package cn.itcast.domain.cargo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EXPORT_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Export implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EXPORT_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@OneToMany(mappedBy = "export", cascade = CascadeType.ALL)
	@OrderBy("ORDER_NO")
	private Set<ExportProduct> exportProducts = new HashSet<>(); // 报运下的货物, 一对多

	@Column(name = "INPUT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputDate; // 制单日期
	@Column(name = "CONTRACT_IDS")
	private String contractIds; // 打断字段, 报运相关的多个合同的ID集合串
	@Column(name = "CUSTOMER_CONTRACT")
	private String customerContract; // 合同及确认书号
	@Column(name = "LCNO")
	private String lcno; // 信用证号
	@Column(name = "CONSIGNEE")
	private String consignee; // 收货人及地址
	@Column(name = "MARKS")
	private String marks; // 唛头
	@Column(name = "SHIPMENT_PORT")
	private String shipmentPort; // 装船港
	@Column(name = "DESTINATION_PORT")
	private String destinationPort; // 目的港
	@Column(name = "TRANSPORT_MODE")
	private String transportMode; // 船运SEA/空运AIR 运输方式
	@Column(name = "PRICE_CONDITION")
	private String priceCondition; // FBO/CIF价格条件
	@Column(name = "REMARK")
	private String remark; // 备注
	@Column(name = "BOX_NUMS")
	private Integer boxNums; // 冗余, 为委托服务, 一个报运的总箱数
	@Column(name = "GROSS_WEIGHTS")
	private Double grossWeights; // 冗余, 为委托服务, 一个报运的总毛重
	@Column(name = "MEASUREMENTS")
	private Double measurements; // 冗余, 为委托服务, 一个报运的总体积
	@Column(name = "STATE")
	private Integer state; // 0-草稿 1-已上报  2-已报运 3-装箱 4-委托 5-发票 6-财务

	@Column(name = "CREATE_BY")
	private String createBy; // 创建者的id
	@Column(name = "CREATE_DEPT")
	private String createDept; // 创建者所在部门的id
	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime; // 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<ExportProduct> getExportProducts() {
		return exportProducts;
	}

	public void setExportProducts(Set<ExportProduct> exportProducts) {
		this.exportProducts = exportProducts;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getContractIds() {
		return contractIds;
	}

	public void setContractIds(String contractIds) {
		this.contractIds = contractIds;
	}

	public String getCustomerContract() {
		return customerContract;
	}

	public void setCustomerContract(String customerContract) {
		this.customerContract = customerContract;
	}

	public String getLcno() {
		return lcno;
	}

	public void setLcno(String lcno) {
		this.lcno = lcno;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getShipmentPort() {
		return shipmentPort;
	}

	public void setShipmentPort(String shipmentPort) {
		this.shipmentPort = shipmentPort;
	}

	public String getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getPriceCondition() {
		return priceCondition;
	}

	public void setPriceCondition(String priceCondition) {
		this.priceCondition = priceCondition;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBoxNums() {
		return boxNums;
	}

	public void setBoxNums(Integer boxNums) {
		this.boxNums = boxNums;
	}

	public Double getGrossWeights() {
		return grossWeights;
	}

	public void setGrossWeights(Double grossWeights) {
		this.grossWeights = grossWeights;
	}

	public Double getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Double measurements) {
		this.measurements = measurements;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
