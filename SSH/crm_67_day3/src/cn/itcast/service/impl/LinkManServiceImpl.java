package cn.itcast.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.action.utils.Page;
import cn.itcast.dao.LinkManDao;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.LinkManService;

@Service("linkManService")
@Transactional
public class LinkManServiceImpl implements LinkManService {

	@Autowired
	private LinkManDao linkManDao;
	
	@Override
	public void addLinkMan(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<LinkMan> getAllLinkMan() {
		return linkManDao.getAll();
	}

	@Override
	public void deleteLinkMan(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public LinkMan getLinkManById(Long lkmId) {
		return linkManDao.load(lkmId);
	}

	@Override
	public void updateLinkMan(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Page findAllLinkMan(DetachedCriteria dc,String num,int ps) { 
		//1.获取当前查询的页码
		int pageNum = 1;
		
		if(StringUtils.isNotBlank(num)){
			try{
				pageNum = Integer.parseInt(num);
			}catch(Exception e){}
			
		}
		//2.查询总条数
		int count = linkManDao.getAllRecordNum(dc);
		
		//3.封装到page对象中
		Page page = new Page(pageNum,count,ps);
		
		//4.根据分页参数，查询数据库中的数据
		List list = linkManDao.findAllByPage(dc,page.getStartIndex(),page.getPageSize());
		//5.将查询结果封装到page对象中
		page.setRecords(list);
		
		return page;
	}

}
