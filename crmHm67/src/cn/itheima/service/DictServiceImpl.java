package cn.itheima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.DictDao;
import cn.itheima.pojo.BaseDict;
import cn.itheima.pojo.Customer;
import cn.itheima.pojo.CustomerQueryVo;

@Service
public class DictServiceImpl implements DictService {
	
	@Autowired
	private DictDao dictDao;

	@Override
	public List<BaseDict> findDictInfoByTypeCode(String typeCode) throws Exception {
		List<BaseDict> list = dictDao.findDictInfoByTypeCode(typeCode);
		
		return list;
	}

}
