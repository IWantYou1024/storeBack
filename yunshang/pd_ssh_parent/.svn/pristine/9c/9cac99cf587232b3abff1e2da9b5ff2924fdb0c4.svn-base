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

import cn.itcast.domain.BaseEntity;

/**
 * 发票
 * @Description: InvoiceService接口
 * @Author: rent
 * @Company: http://java.itcast.cn
 * @CreateDate: 2017-10-15 10:15:39
 */
@Entity
@Table(name="INVOICE_C")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Invoice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "INVOICE_ID")
	@GeneratedValue(generator = "system-assigned")
	@GenericGenerator(name = "system-assigned", strategy = "assigned")
	private String id;	//'PACKING_LIST_ID值';
	
	@Column(name="SC_NO")
	private String scNo;//合同单号
	
	@Column(name="BL_NO")//发票号
	private String blNo;
	@Column(name="TRADE_TERMS")
	private String tradeTerms;	//贸易条款
	@Column(name="STATE")
	private Integer state;//0草稿  1已上报
	
	@Column(name = "CREATE_BY")
	protected String createBy; // 创建者id
	@Column(name = "CREATE_DEPT")
	protected String createDept; // 创建者所在部门的id
	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createTime; // 创建时间
	

	public String getScNo() {
		return this.scNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}

	public String getBlNo() {
		return this.blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getTradeTerms() {
		return this.tradeTerms;
	}

	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}

	public Integer getState() {
		return this.state;
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
