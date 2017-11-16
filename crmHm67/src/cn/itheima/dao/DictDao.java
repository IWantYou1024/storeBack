package cn.itheima.dao;

import java.util.List;

import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.CustomerQueryVo;

public interface DictDao {
	
	// 根据字典的种类code查询它的详细code信息
	public List<BaseDict> findDictInfoByTypeCode(String typeCode) throws Exception;

}
