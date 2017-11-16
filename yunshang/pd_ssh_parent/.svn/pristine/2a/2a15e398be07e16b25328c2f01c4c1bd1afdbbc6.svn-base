package cn.itcast.dao.sysadmin;

import org.springframework.data.jpa.repository.Query;

import cn.itcast.dao.BaseDao;
import cn.itcast.domain.sysadmin.Role;

public interface RoleDao extends BaseDao<Role> {

	@Query("select max(orderNo) from Role")
	Long queryMax();

}
