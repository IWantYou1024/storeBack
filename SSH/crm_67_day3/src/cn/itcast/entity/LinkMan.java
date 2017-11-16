package cn.itcast.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*创建联系人表*/
//CREATE TABLE `cst_linkman` (
//  `lkm_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
//  `lkm_name` varchar(16) DEFAULT NULL COMMENT '联系人姓名',
//  `lkm_gender` varchar(10) DEFAULT NULL COMMENT '联系人性别',
//  `lkm_phone` varchar(16) DEFAULT NULL COMMENT '联系人办公电话',
//  `lkm_mobile` varchar(16) DEFAULT NULL COMMENT '联系人手机',
//  `lkm_email` varchar(64) DEFAULT NULL COMMENT '联系人邮箱',
//  `lkm_position` varchar(16) DEFAULT NULL COMMENT '联系人职位',
//  `lkm_memo` varchar(512) DEFAULT NULL COMMENT '联系人备注',
//  `lkm_cust_id` bigint(32) NOT NULL COMMENT '客户id(外键)',

@Entity
@Table(name="cst_linkman")
public class LinkMan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lkm_id")
	private Long lkmId;
	
	@Column(name="lkm_name")
	private String lkmName;
	
	@Column(name="lkm_gender")
	private String lkmGender;
	
	@Column(name="lkm_mobile")
	private String lkmMobile;
	
	@Column(name="lkm_phone")
	private String lkmPhone;
	
	@Column(name="lkm_email")
	private String lkmEmail;
	
	@Column(name="lkm_position")
	private String lkmPosition;
	
	@Column(name="lkm_memo")
	private String lkmMemo;
	
	//创建联系人和客户的关联关系
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="lkm_cust_id",referencedColumnName="cust_id")
	private Customer customer;

	public Long getLkmId() {
		return lkmId;
	}

	public void setLkmId(Long lkmId) {
		this.lkmId = lkmId;
	}

	public String getLkmName() {
		return lkmName;
	}

	public void setLkmName(String lkmName) {
		this.lkmName = lkmName;
	}

	public String getLkmGender() {
		return lkmGender;
	}

	public void setLkmGender(String lkmGender) {
		this.lkmGender = lkmGender;
	}

	public String getLkmMobile() {
		return lkmMobile;
	}

	public void setLkmMobile(String lkmMobile) {
		this.lkmMobile = lkmMobile;
	}

	public String getLkmPhone() {
		return lkmPhone;
	}

	public void setLkmPhone(String lkmPhone) {
		this.lkmPhone = lkmPhone;
	}

	public String getLkmEmail() {
		return lkmEmail;
	}

	public void setLkmEmail(String lkmEmail) {
		this.lkmEmail = lkmEmail;
	}

	public String getLkmPosition() {
		return lkmPosition;
	}

	public void setLkmPosition(String lkmPosition) {
		this.lkmPosition = lkmPosition;
	}

	public String getLkmMemo() {
		return lkmMemo;
	}

	public void setLkmMemo(String lkmMemo) {
		this.lkmMemo = lkmMemo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "LinkMan [lkmId=" + lkmId + ", lkmName=" + lkmName + ", lkmGender=" + lkmGender + ", lkmMobile="
				+ lkmMobile + ", lkmPhone=" + lkmPhone + ", lkmEmail=" + lkmEmail + ", lkmPosition=" + lkmPosition
				+ ", lkmMemo=" + lkmMemo + "]";
	}
}
