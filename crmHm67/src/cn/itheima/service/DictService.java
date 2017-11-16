package cn.itheima.service;

import java.util.List;

import cn.itheima.pojo.BaseDict;

public interface DictService {
	
	// 根据字典的种类code查询它的详细code信息
	public List<BaseDict> findDictInfoByTypeCode(String typeCode) throws Exception;

}
