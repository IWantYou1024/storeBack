package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.BaseDict;

public interface BaseDictDao {

	List<BaseDict> getBaseDictsByType(String string);

}
