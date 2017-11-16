package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.sysadmin.DeptDao;
import cn.itcast.domain.sysadmin.Dept;
import cn.itcast.service.DeptService;

@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

	@SuppressWarnings("unused")
	private DeptDao deptDao;

	@Autowired
	public void init(DeptDao deptDao) {
		super.baseDao = deptDao;
		this.deptDao = deptDao;
	}

}
