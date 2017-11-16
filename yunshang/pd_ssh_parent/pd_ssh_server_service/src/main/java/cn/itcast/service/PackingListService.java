package cn.itcast.service;

import cn.itcast.domain.cargo.PackingList;

public interface PackingListService extends BaseService<PackingList> {
	public void updateState(String[] ids, int state);
}
