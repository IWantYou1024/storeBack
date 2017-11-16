package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.sysadmin.ModuleDao;
import cn.itcast.domain.sysadmin.Module;
import cn.itcast.service.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements ModuleService {

	@SuppressWarnings("unused")
	private ModuleDao moduleDao;

	@Autowired
	public void init(ModuleDao moduleDao) {
		super.baseDao = moduleDao;
		this.moduleDao = moduleDao;
	}

}
