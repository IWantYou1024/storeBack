package cn.itcast.dao.cargo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.itcast.dao.BaseDao;
import cn.itcast.domain.cargo.ContractProduct;

public interface ContractProductDao extends BaseDao<ContractProduct> {

	@Query(value = "from ContractProduct where contract.id in (?)")
	List<ContractProduct> findAllByContractIds(String cids);

	// @Query(value = "select t.* from CONTRACT_C c, CONTRACT_PRODUCT_C t where
	// to_char(c.SHIP_TIME,'yyyy-mm') = ? and c.CONTRACT_ID = t.CONTRACT_ID",
	// nativeQuery = true)
	// List<ContractProduct> findByShipTime(String shipTime);

	@Query("from ContractProduct cp where to_char(cp.contract.shipTime,'yyyy-mm') = ?1")
	List<ContractProduct> findByShipTime(String shipTime);

}
