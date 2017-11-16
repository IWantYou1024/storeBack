package cn.itcast.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.entity.BaseDict;

@Repository("baseDictDao")
public class BaseDictDaoImpl implements BaseDictDao {
	
	@Autowired
	private  HibernateTemplate hibernateTemplate;

	/**
	 * 根据字典表类型，查询所有数据
	 */
	public List<BaseDict> getBaseDictsByType(String type) {
		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dictTypeCode = ?",type);
	}

}
