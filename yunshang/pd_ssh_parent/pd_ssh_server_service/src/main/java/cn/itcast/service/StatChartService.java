package cn.itcast.service;

import java.util.List;
import java.util.Map;

public interface StatChartService {

	List<Map<String, Object>> getData(String sql);

}
