package cn.itcast.dao.springdao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.utils.UtilFuns;

@Repository("sqlDao")
public class SqlDao {
	private static Logger log = Logger.getLogger(SqlDao.class);
	private UtilFuns utilFuns = new UtilFuns();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> executeSQL(String sql) {
		log.debug(sql);
		return jdbcTemplate.queryForList(sql);
	}
}
