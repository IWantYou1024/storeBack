package cn.itcast.domain.cargo;

/**
 * 该实体类为财务报运单的实体类 , 与数据库中的FINANCE_C表相对应
 */
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

@Entity
@Table(name = "FINANCE_C")
@DynamicInsert(true) // 动态插入 创建时间这一栏不用手动输入 系统会自动在数据生成的时间把相应的数据写入
@DynamicUpdate(true)
public class Finance implements Serializable {

	private static final long serialVersionUID = 1L;
	// INANCE_ID varchar2(40)
	// id字段为本实体类的主键,因为与装箱单为一对一的关系,所以采用自行维护,让装箱单的id生成后赋给该id
	@Id
	@GeneratedValue(generator = "system-assigned") // 声明主键靠自己维护
	@GenericGenerator(name = "system-assigned", strategy = "assigned")
	@Column(name = "FINANCE_ID")
	private String id;

	// INPUT_DATE datetime,
	// inputDate字段表明的是该财务报运单的的生成时间
	@Column(name = "INPUT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputDate;

	// inputBy字段声明的是 录入 该财务报运单的人员
	// INPUT_BY varchar2(30),
	@Column(name = "INPUT_BY")
	private String inputBy;

	// state字段声明的是该财务报运单的状态
	// STATE int,
	@Column(name = "STATE")
	private Integer state;

	// creatBy字段声明的是该 创建 该财务报运单的人员
	// CREATE_BY varchar2(40),
	@Column(name = "CREATE_BY")
	private String creatBy;

	// creatDept字段声明的是 创建 该财务报运单的 部门
	// CREATE_DEPT varchar2(40),
	@Column(name = "CREATE_DEPT")
	private String creatDept;

	// creatTime字段表明的是该财务报运单的的创建时间
	// CREATE_TIME datetime,
	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creatTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}

	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCreatBy() {
		return creatBy;
	}

	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}

	public String getCreatDept() {
		return creatDept;
	}

	public void setCreatDept(String creatDept) {
		this.creatDept = creatDept;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
