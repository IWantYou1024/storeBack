package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.action.utils.Page;
import cn.itcast.entity.LinkMan;

public interface LinkManService {

	void addLinkMan(LinkMan linkMan);

	List<LinkMan> getAllLinkMan();

	void deleteLinkMan(LinkMan linkMan);

	LinkMan getLinkManById(Long lkmId);

	void updateLinkMan(LinkMan linkMan);

	Page findAllLinkMan(DetachedCriteria dc,String num,int ps);
}
