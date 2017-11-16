package cn.itcast.action.utils;

import java.util.List;

public class Page {
//	1.startIndex
//	2.pageSize
//	3.前一条的页码
//	4.后一条的页码
//	5.当前查询的页码
//	6.一共有多少条记录
//	7.一共有多少页
//	8.查询的数据列表
	private int pageSize = 5;//每页显示的记录条数
	private int totalRecordNum;//一共有多少条记录
	private int totalPageNum;//一共有多少页
	private int currentPageNum;//当前查询的页码
	
	
	
	
	private int startIndex;// 查询第几条之后的记录
	
	
	private int prePageNum;//前一条的页码
	private int nextPageNum;//后一条的页码
	
	private List<Object> records;
	
	/**
	 * 使用page对象的时候，	
	 * 	当前查询页码。，总记录数
	 * 
	 */
	public Page(int currentPageNum,int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
		//计算一共有多少页
		//20 / 5 = 4   21    20 / 5 + 1 = 5
		this.totalPageNum = this.totalRecordNum % pageSize == 0 
				? this.totalRecordNum / pageSize : this.totalRecordNum / pageSize +1;
		this.currentPageNum = currentPageNum >= this.totalPageNum ? this.totalPageNum: currentPageNum;
		this.currentPageNum = this.currentPageNum <=0 ? 1: this.currentPageNum;
		
		/**
		 * select * from table limit startIndex，pageSize
		 * 
		 * 						1				2					3
		 * startIndex		   0       5		5 - 10			10-15
		 * 					 		
		 */
		this.startIndex = (this.currentPageNum -1) * pageSize ;
		
		this.prePageNum = this.currentPageNum <=1 ? 1 : this.currentPageNum-1;
		
		this.nextPageNum = this.currentPageNum  >=  this.totalPageNum ? 
				this.totalPageNum: this.currentPageNum+1;
	}

	
	public Page(int currentPageNum,int totalRecordNum,int pageSize) {
		this.pageSize = pageSize;
		this.totalRecordNum = totalRecordNum;
		//计算一共有多少页
		//20 / 5 = 4   21    20 / 5 + 1 = 5
		this.totalPageNum = this.totalRecordNum % pageSize == 0 
				? this.totalRecordNum / pageSize : this.totalRecordNum / pageSize +1;
		this.currentPageNum = currentPageNum >= this.totalPageNum ? this.totalPageNum: currentPageNum;
		this.currentPageNum = this.currentPageNum <=0 ? 1: this.currentPageNum;
		
		/**
		 * select * from table limit startIndex，pageSize
		 * 
		 * 						1				2					3
		 * startIndex		   0       5		5 - 10			10-15
		 * 					 		
		 */
		this.startIndex = (this.currentPageNum -1) * pageSize ;
		
		this.prePageNum = this.currentPageNum <=1 ? 1 : this.currentPageNum-1;
		
		this.nextPageNum = this.currentPageNum  >=  this.totalPageNum ? 
				this.totalPageNum: this.currentPageNum+1;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPrePageNum() {
		return prePageNum;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalRecordNum() {
		return totalRecordNum;
	}

	public void setTotalRecordNum(int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public List<Object> getRecords() {
		return records;
	}

	public void setRecords(List<Object> records) {
		this.records = records;
	}
}
