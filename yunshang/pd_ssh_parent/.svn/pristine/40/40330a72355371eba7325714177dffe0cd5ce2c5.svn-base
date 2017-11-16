package cn.itcast.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.ExportProductDao;
import cn.itcast.domain.cargo.ExportProduct;
import cn.itcast.service.ExportProductService;

@Service("exportProductService")
public class ExportProductServiceImpl extends BaseServiceImpl<ExportProduct> implements ExportProductService {

	@SuppressWarnings("unused")
	private ExportProductDao exportProductDao;

	@Autowired
	public void init(ExportProductDao exportProductDao) {
		super.baseDao = exportProductDao;
		this.exportProductDao = exportProductDao;
	}

}
