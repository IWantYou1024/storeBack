package cn.itcast.domain.cargo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PRODUCT_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Product {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "FACTORY_ID")	//工厂id，与工厂一对多的外键字段
	private Factory factory;
	
	@Column(name = "PRODUCT_NO")
	private String productNo;			//产品编号
	
	@Column(name = "PRODUCT_IMAGE")
	private String productImage;		//产品图片
	
	@Column(name = "DESCRIPTION")
	private String description;			//描述
	
	@Column(name = "FACTORY_NAME")
	private String factoryName;			//工厂名称
	
	@Column(name="PRICE")
	private Double price;				//价格
	
	@Column(name="SIZE_LENGHT")
	private Double sizeLenght;			//尺寸长
	
	@Column(name="SIZE_WIDTH")			
	private Double sizeWidth;			//尺寸宽
	
	@Column(name="SIZE_HEIGHT")
	private Double sizeHeight;			//尺寸高
	
	@Column(name="COLOR")				
	private String color;				//颜色
	
	@Column(name="PACKING")
	private String packing;				//包装
	
	@Column(name="PACKING_UNIT")
	private String packingUnit;			//包装单位
	
	@Column(name = "TYPE20")
	private Double type20;				//集装箱类别20
	
	@Column(name = "TYPE40")
	private Double type40;				//集装箱类别40
	
	@Column(name = "TYPE40HC")
	private Double type40hc;			//集装箱类别40HC
	
	@Column(name = "QTY")
	private Integer qty;				//数量
	
	@Column(name = "CBM")
	private Double cbm;					//体积
	
	@Column(name = "MPSIZE_LENGHT")
	private Double mpsizeLenght;		//大箱尺寸长
	
	@Column(name = "MPSIZE_WIDTH")
	private Double mpsizeWidth;			//大箱尺寸宽
	
	@Column(name = "MPSIZE_HEIGHT")
	private Double mpsizeHeight;		//大箱尺寸高
	
	@Column(name = "REMARK")
	private String remark;				//备注
	
	@Column(name="INPUT_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputTime;				//录入时间
	
	@Column(name = "CREATE_BY")
	private String createBy; // 创建者id
	
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

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSizeLenght() {
		return sizeLenght;
	}

	public void setSizeLenght(Double sizeLenght) {
		this.sizeLenght = sizeLenght;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getPackingUnit() {
		return packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}

	public Double getType20() {
		return type20;
	}

	public void setType20(Double type20) {
		this.type20 = type20;
	}

	public Double getType40() {
		return type40;
	}

	public void setType40(Double type40) {
		this.type40 = type40;
	}

	public Double getType40hc() {
		return type40hc;
	}

	public void setType40hc(Double type40hc) {
		this.type40hc = type40hc;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getCbm() {
		return cbm;
	}

	public void setCbm(Double cbm) {
		this.cbm = sizeLenght * sizeWidth * sizeHeight;
	}

	public Double getMpsizeLenght() {
		return mpsizeLenght;
	}

	public void setMpsizeLenght(Double mpsizeLenght) {
		this.mpsizeLenght = mpsizeLenght;
	}

	public Double getMpsizeWidth() {
		return mpsizeWidth;
	}

	public void setMpsizeWidth(Double mpsizeWidth) {
		this.mpsizeWidth = mpsizeWidth;
	}

	public Double getMpsizeHeight() {
		return mpsizeHeight;
	}

	public void setMpsizeHeight(Double mpsizeHeight) {
		this.mpsizeHeight = mpsizeHeight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
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
