package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.LinkManDao;
import cn.itcast.entity.LinkMan;

@Repository("linkManDao")
public class LinkManDaoImpl implements LinkManDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void add(LinkMan linkMan) {
		hibernateTemplate.save(linkMan);
	}

	@Override
	public List<LinkMan> getAll() {
		return (List<LinkMan>) hibernateTemplate.find("from LinkMan");
	}

	@Override
	public void delete(LinkMan linkMan) {
		hibernateTemplate.delete(linkMan);
	}

	/*
	 * 使用load方式会有一个nosession的问题
	 * 解决方案，配置一个叫做OpenSessionInViewFilter
	 */
	@Override
	public LinkMan load(Long lkmId) {
		return hibernateTemplate.load(LinkMan.class, lkmId);
	}

	@Override
	public void update(LinkMan linkMan) {
		hibernateTemplate.update(linkMan);
	}



	@Override
	public int getAllRecordNum(DetachedCriteria dc) {
		//可以在离线对象中，指定统计函数
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(dc);
		return list.isEmpty() ? 0 : list.get(0).intValue();
	}

	@Override
	public List<LinkMan> findAll(DetachedCriteria dc) {
		return (List<LinkMan>) hibernateTemplate.findByCriteria(dc);
	}
	
	/**
	 * hibernateTemplate:
	 * 		findByCriteria 
	 * 				DetachedCriteria:离线查询对象
	 * 				firstResult：从第几条之后查询
	 * 				maxResult：每次查询的条数
	 */
	@Override
	public List<LinkMan> findAllByPage(DetachedCriteria dc,int firstResult,int maxResult) {
		//将统计函数查询的参数，设置为null
		dc.setProjection(null);
		return (List<LinkMan>) hibernateTemplate.findByCriteria(dc,firstResult,maxResult);
	}

}
