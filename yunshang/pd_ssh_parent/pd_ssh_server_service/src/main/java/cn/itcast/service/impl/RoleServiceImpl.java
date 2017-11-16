package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.sysadmin.RoleDao;
import cn.itcast.domain.sysadmin.Role;
import cn.itcast.service.RoleService;
import cn.itcast.utils.UtilFuns;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	private RoleDao roleDao;

	@Autowired
	public void init(RoleDao roleDao) {
		super.baseDao = roleDao;
		this.roleDao = roleDao;
	}

	@Override
	public void saveOrUpdate(Role entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			Integer maxOrderNo = roleDao.queryMax().intValue();
			entity.setOrderNo(maxOrderNo + 1);
		}
		roleDao.save(entity);
	}

}
