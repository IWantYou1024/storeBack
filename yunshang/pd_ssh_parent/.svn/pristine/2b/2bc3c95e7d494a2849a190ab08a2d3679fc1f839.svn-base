package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.home.MemoDao;
import cn.itcast.domain.home.Memo;
import cn.itcast.service.MemoService;

@Service
public class MemoServiceImpl extends BaseServiceImpl<Memo> implements MemoService {

	private MemoDao memoDao;
	
	@Autowired
	public void init(MemoDao memoDao) {
		super.baseDao = memoDao;
		this.memoDao = memoDao;
	}

	@Override
	public List<Memo> findByDeadLine(String deadLine) {
		return memoDao.findByDeadLine(deadLine);
	}

}
