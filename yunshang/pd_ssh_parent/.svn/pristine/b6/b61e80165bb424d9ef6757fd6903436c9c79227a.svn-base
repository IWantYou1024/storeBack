package cn.itcast.domain.cargo;

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
@Table(name = "FACTORY_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Factory extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FACTORY_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "CTYPE")
	private String ctype; // 厂家类型 货物/附件
	@Column(name = "FULL_NAME")
	private String fullName; // 厂家全称
	@Column(name = "FACTORY_NAME")
	private String factoryName; // 厂家简称
	@Column(name = "CONTACTS")
	private String contacts; // 联系人
	@Column(name = "PHONE")
	private String phone; // 电话
	@Column(name = "MOBILE")
	private String mobile; // 手机
	@Column(name = "FAX")
	private String fax; // 传真
	@Column(name = "ADDRESS")
	private String address; // 地址
	@Column(name = "INSPECTOR")
	private String inspector; // 验货员, 公司代表
	@Column(name = "REMARK")
	private String remark; // 说明
	@Column(name = "ORDER_NO")
	private Integer ORDER_NO; // 排序号
	@Column(name = "STATE")
	private Integer state; // 状态, 1正常 0停用
	
	@OneToMany(mappedBy = "factory", cascade = CascadeType.ALL)
	private Set<Product> products;

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getORDER_NO() {
		return ORDER_NO;
	}

	public void setORDER_NO(Integer oRDER_NO) {
		ORDER_NO = oRDER_NO;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
