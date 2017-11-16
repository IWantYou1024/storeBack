package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import cn.itcast.dao.cargo.FactoryDao;
import cn.itcast.dao.home.FeedBackDao;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.home.FeedBack;
import cn.itcast.service.FactoryService;
import cn.itcast.service.FeedBackService;
@Service(value="feebBackService")
public class FeebBackServiceImpl extends BaseServiceImpl<FeedBack> implements FeedBackService{

	@SuppressWarnings("unused")
	private FeedBackDao feedBackDao;

	@Autowired
	public void init(FeedBackDao feedBackDao) {
		super.baseDao = feedBackDao;
		this.feedBackDao = feedBackDao;
	}


}
