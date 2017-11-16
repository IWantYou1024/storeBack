package cn.itcast.domain.sysadmin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.itcast.domain.BaseEntity;

@Entity
@Table(name = "MODULE_P")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Module extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MODULE_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ManyToMany(mappedBy = "modules")
	private Set<Role> roles = new HashSet<>(); // 模块与角色 多对多

	@Column(name = "PARENT_ID")
	private String parentId; // 父模块的编号
	@Column(name = "PARENT_NAME")
	private String parentName; // 父模块的名称 冗余 用空间换时间
	@Column(name = "NAME")
	private String name; // 模块名
	@Column(name = "LAYER_NUM")
	private String layerNum; // 层数
	@Column(name = "IS_LEAF")
	private Integer isLeaf; // 叶子
	@Column(name = "ICO")
	private String ico; // 图片
	@Column(name = "CPERMISSION")
	private String cpermission; // 权限
	@Column(name = "CURL")
	private String curl; // 路径
	@Column(name = "CTYPE")
	private Integer ctype; // 菜单的类型: 主材单, 左侧菜单
	@Column(name = "STATE")
	private Integer state; // 状态
	@Column(name = "BELONG")
	private String belong; // 从属于
	@Column(name = "CWHICH")
	private String cwhich; //
	@Column(name = "QUOTE_NUM")
	private Integer quoteNum; // 引用次数
	@Column(name = "REMARK")
	private String remark; // 备注
	@Column(name = "ORDER_NO")
	private Integer orderNo; // 排序号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLayerNum() {
		return layerNum;
	}

	public void setLayerNum(String layerNum) {
		this.layerNum = layerNum;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public String getCpermission() {
		return cpermission;
	}

	public void setCpermission(String cpermission) {
		this.cpermission = cpermission;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public Integer getCtype() {
		return ctype;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getCwhich() {
		return cwhich;
	}

	public void setCwhich(String cwhich) {
		this.cwhich = cwhich;
	}

	public Integer getQuoteNum() {
		return quoteNum;
	}

	public void setQuoteNum(Integer quoteNum) {
		this.quoteNum = quoteNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}
