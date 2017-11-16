package cn.itcast.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.FinanceDao;
import cn.itcast.domain.cargo.Finance;
import cn.itcast.service.FinanceService;

@Service
public class FinanceServiceImpl extends BaseServiceImpl<Finance> implements FinanceService {

	private FinanceDao financeDao;

	@Autowired
	public void init(FinanceDao financeDao) {
		super.baseDao = financeDao;
		this.financeDao = financeDao;
	}

}
