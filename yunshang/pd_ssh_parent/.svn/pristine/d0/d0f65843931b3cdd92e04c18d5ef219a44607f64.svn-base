package cn.itcast.domain.cargo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 装箱单实体类
 * 可以使用数据库配置一对一关系,也可以设置属性,提高可读性
 * @author hgj
 *
 */


@Entity
@Table(name = "PACKING_LIST_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PackingList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  @Id
	  @Column(name = "PACKING_LIST_ID")
	  @GeneratedValue(generator = "system-assigned")
	  @GenericGenerator(name = "system-assigned", strategy = "assigned")
	  private String packingListId;//编号
	  
	  @Column(name = "SELLER")
	  private String seller;//卖方
	   
	   @Column(name = "BUYER")
	   private String buyer;//买方
	   
	   @Column(name = "INVOICE_NO")
	   private String invoiceNo;//发票号
	   
	   @Column(name = "INVOICE_DATE")
	   @Temporal(TemporalType.TIMESTAMP)
	   private Date invoiceDate;//发票日期
	   
	   @Column(name = "MARKS")
	   private String marks;//唛头
	   
	   @Column(name = "DESCRIPTIONS")
	   private String descriptions;//描述
	   
	   // 打断字段, 相关的报运单id集合
	   @Column(name = "EXPORT_IDS")
	   private String exportIds;//报运ID集合
	   
	   @Column(name = "EXPORT_NOS")
	   private String exportNos;//报运NO集合x,y|z,h
	   
	   @Column(name = "STATE")
	   private int state;//0草稿 1已上报
	   
	   @Column(name = "CREATE_BY")
	   private String createBy;// 创建者id
	   
	   @Column(name = "CREATE_DEPT")
	   private String createDept; // 创建者所在部门的id
	   
	   @Column(name = "CREATE_TIME")
	   @Temporal(TemporalType.TIMESTAMP)
	   private Date createTime; // 创建时间
	   
	   
	   //配置一对一关系
	   //委托单
	   @OneToOne(cascade = CascadeType.ALL)
	   @JoinColumn(name = "PACKING_LIST_ID")
		private ShippingOrder shippingOrder=new ShippingOrder(); // 装箱与委托单信息 一对一
	   
	   //发票单
	   @OneToOne(cascade = CascadeType.ALL)
	   @JoinColumn(name = "PACKING_LIST_ID")
		private Invoice invoice=new Invoice(); // 装箱与发票单 一对一
	   
	   //财务
	   @OneToOne(cascade = CascadeType.ALL)
	   @JoinColumn(name = "PACKING_LIST_ID")
		private Finance finance=new Finance(); // 装箱与财务 一对一

	public String getPackingListId() {
		return packingListId;
	}

	public void setPackingListId(String packingListId) {
		this.packingListId = packingListId;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getExportIds() {
		return exportIds;
	}

	public void setExportIds(String exportIds) {
		this.exportIds = exportIds;
	}

	public String getExportNos() {
		return exportNos;
	}

	public void setExportNos(String exportNos) {
		this.exportNos = exportNos;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
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

	public ShippingOrder getShippingOrder() {
		return shippingOrder;
	}

	public void setShippingOrder(ShippingOrder shippingOrder) {
		this.shippingOrder = shippingOrder;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Finance getFinance() {
		return finance;
	}

	public void setFinance(Finance finance) {
		this.finance = finance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	   
	   
	   
	  
}
