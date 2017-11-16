package cn.itcast.domain.cargo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
/*委托单
 * */
/*
 * */
@Table(name="SHIPPING_ORDER_C")
@Entity
@DynamicInsert(true)	// 获取到系统的当前的时间，作为值。
@DynamicUpdate(true)	// 没有用
public class ShippingOrder implements Serializable{
	@Id
	@Column(name="SHIPPING_ORDER_ID")
	@GeneratedValue(generator="system-assigned")
	@GenericGenerator(name="system-assigned",strategy="assigned")
	private String shippingOrderId;//委托单ID
	@Column(name="ORDER_TYPE")
	private String orderType;//海运/空运
	@Column(name="SHIPPER")
	private String shipper;//货主
	
	@Column(name="CONSIGNEE")
	private String consignee;//提单抬头
	@Column(name="NOTIFY_PARTY")
	private String notifyParty;//正本通知人
	@Column(name="LC_NO")
	private String lcNo;//信用证
	
	@Column(name="PORT_OF_LOADING")
	private String portOfLoading;//装运港
	@Column(name="PORT_OF_TRANS")
	private String portOfTrans;//转船港
	@Column(name="PORT_OF_DISCHARGE")
	private String portOfDischarge;//卸货港
	
	@Column(name="LOADING_DATE")
	private Date loadingDate;//装期
	@Column(name="LIMIT_DATE")
	private Date limitDate;//效期
	@Column(name="IS_BATCH")
	private String isBatch;//是否分批
	
	@Column(name="IS_TRANS")
	private String isTrans;//是否转船
	@Column(name="COPY_NUM")
	private String copyNum;//份数
	@Column(name="REMARK")
	private String remark;//扼要说明
	
	@Column(name="SPECIAL_CONDITION")
	private String specialCondition;//运输要求
	@Column(name="FREIGHT")
	private String freight;//运费说明
	@Column(name="CHECK_BY")
	private String checkBy;//复核人
	
	@Column(name="STATE")
	private Integer state;//状态 0-草稿 1-已上报  2-已开发票

	@Column(name="CREATE_BY")
	private String createBy;//创建人
	@Column(name="CREATE_DEPT")
	private String createDept;//创建部门
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;//创建日期
	
	public String getShippingOrderId() {
		return shippingOrderId;
	}
	public void setShippingOrderId(String shippingOrderId) {
		this.shippingOrderId = shippingOrderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getNotifyParty() {
		return notifyParty;
	}
	public void setNotifyParty(String notifyParty) {
		this.notifyParty = notifyParty;
	}
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	public String getPortOfLoading() {
		return portOfLoading;
	}
	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}
	public String getPortOfTrans() {
		return portOfTrans;
	}
	public void setPortOfTrans(String portOfTrans) {
		this.portOfTrans = portOfTrans;
	}
	public String getPortOfDischarge() {
		return portOfDischarge;
	}
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}
	public Date getLoadingDate() {
		return loadingDate;
	}
	public void setLoadingDate(Date loadingDate) {
		this.loadingDate = loadingDate;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public String getIsBatch() {
		return isBatch;
	}
	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}
	public String getIsTrans() {
		return isTrans;
	}
	public void setIsTrans(String isTrans) {
		this.isTrans = isTrans;
	}
	public String getCopyNum() {
		return copyNum;
	}
	public void setCopyNum(String copyNum) {
		this.copyNum = copyNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSpecialCondition() {
		return specialCondition;
	}
	public void setSpecialCondition(String specialCondition) {
		this.specialCondition = specialCondition;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
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
