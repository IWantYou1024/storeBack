package cn.itcast.dao.cargo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.itcast.dao.BaseDao;
import cn.itcast.domain.cargo.Contract;

public interface ContractDao extends BaseDao<Contract> {

	@Query("from Contract where to_char(deliveryPeriod, 'yyyy-MM-dd') = ?1")
	List<Contract> findByDelivery(String delivery);

}
