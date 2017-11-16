package cn.itcast.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.springdao.SqlDao;
import cn.itcast.service.StatChartService;

@Service("statChartService")
public class StatChartServiceImpl implements StatChartService {

	@Autowired
	private SqlDao sqlDao;

	@Override
	public List<Map<String, Object>> getData(String sql) {
		return sqlDao.executeSQL(sql);
	}

}
