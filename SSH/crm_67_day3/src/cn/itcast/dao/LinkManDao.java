package cn.itcast.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.entity.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> getAll();

	void delete(LinkMan linkMan);

	LinkMan load(Long lkmId);

	void update(LinkMan linkMan);

	List<LinkMan> findAll(DetachedCriteria dc);
	
	List<LinkMan> findAllByPage(DetachedCriteria dc,int firstResult,int maxResult);

	int getAllRecordNum(DetachedCriteria dc);

}
