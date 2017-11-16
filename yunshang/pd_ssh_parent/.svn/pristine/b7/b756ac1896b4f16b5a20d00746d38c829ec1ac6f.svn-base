package cn.itcast.dao.home;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.itcast.dao.BaseDao;
import cn.itcast.domain.home.Memo;

public interface MemoDao extends BaseDao<Memo> {

	@Query("from Memo where to_char(deadLine, 'yyyy-MM-dd') = ?1")
	List<Memo> findByDeadLine(String deadLine);

}
