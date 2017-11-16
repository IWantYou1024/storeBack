package cn.itheima.pojo;

/**
 * Copyright (C), 2006-2010, Computer Technology info. Co., Ltd.
 * 
 * 客户一览页面的综合查询条件vo
 * 
 * @author Derek Sun
 * @version 1.00
 *
 */
public class CustomerQueryVo {

	private String custName;   // 客户姓名
	private String custSource;   // 客户来源
	private String custIndustry;   // 客户所属行业
	private String custLevel;   // 客户级别
	private Integer page = 1;   // 分页初始页码
	private Integer start;   // 当前页的开始行号
	private Integer size = 10;   // 默认每页显示的记录数


	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustIndustry() {
		return custIndustry;
	}
	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}
}
