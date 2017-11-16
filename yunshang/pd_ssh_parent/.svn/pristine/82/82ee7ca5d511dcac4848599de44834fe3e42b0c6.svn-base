package cn.itcast.domain.home;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.itcast.domain.BaseEntity;

@Entity
@Table(name="FEEDBACK_C")
public class FeedBack{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="FEEDBACK_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	
	@Column(name="INPUT_BY")
	private String inputBy; //提出人
	@Column(name="INPUT_TIME")
	private Date inputTime; //提出时间
	@Column(name="TITLE")
	private String title; //标题
	@Column(name="CONTENT")
	private String content; //内容
	@Column(name="CLASS_TYPE")
	private String classType; //分类
	@Column(name="TEL")
	private String tel; //电话
	@Column(name="ANSWER_BY")
	private String answerBy; //解决人
	@Column(name="ANSWER_TIME")
	private Date answerTime; //解决时间
	@Column(name="SOLVE_METHOD")
	private String solveMethod; //解决办法
	
	
	@Column(name="RESOLUTION")
	private String resolution; //解决方式
	@Column(name="DIFFICULTY")
	private String difficulty; //解决难度
	@Column(name="IS_SHARE")
	private String isShare; //是否公开
	@Column(name="STATE")
	private String state; //状态
	@Column(name="CREATE_BY")
	private String createBy; //创建人
	@Column(name="CREATE_DEPT")
	private String createDept; //创建部门
	@Column(name="CREATE_TIME")
	private Date createTime; //创建日期
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInputBy() {
		return inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAnswerBy() {
		return answerBy;
	}
	public void setAnswerBy(String answerBy) {
		this.answerBy = answerBy;
	}
	public Date getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	public String getSolveMethod() {
		return solveMethod;
	}
	public void setSolveMethod(String solveMethod) {
		this.solveMethod = solveMethod;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getIsShare() {
		return isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
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
	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", inputBy=" + inputBy + ", inputTime=" + inputTime + ", title=" + title
				+ ", content=" + content + ", classType=" + classType + ", tel=" + tel + ", answerBy=" + answerBy
				+ ", answerTime=" + answerTime + ", solveMethod=" + solveMethod + ", resolution=" + resolution
				+ ", difficulty=" + difficulty + ", isShare=" + isShare + ", state=" + state + ", createBy=" + createBy
				+ ", createDept=" + createDept + ", createTime=" + createTime + "]";
	}
	public FeedBack(String id, String inputBy, Date inputTime, String title, String content, String classType,
			String tel, String answerBy, Date answerTime, String solveMethod, String resolution, String difficulty,
			String isShare, String state, String createBy, String createDept, Date createTime) {
		super();
		this.id = id;
		this.inputBy = inputBy;
		this.inputTime = inputTime;
		this.title = title;
		this.content = content;
		this.classType = classType;
		this.tel = tel;
		this.answerBy = answerBy;
		this.answerTime = answerTime;
		this.solveMethod = solveMethod;
		this.resolution = resolution;
		this.difficulty = difficulty;
		this.isShare = isShare;
		this.state = state;
		this.createBy = createBy;
		this.createDept = createDept;
		this.createTime = createTime;
	}
	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
