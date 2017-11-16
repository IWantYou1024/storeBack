package cn.itcast.service;

import cn.itcast.domain.cargo.Export;
import cn.itcast.domain.vo.ExportResult;

public interface ExportService extends BaseService<Export> {

	/**
	 * 修改报运单状态
	 */
	void updateState(String[] ids, int state);

	/**
	 * 电子报运后修改
	 */
	void updateExportE(ExportResult result);

}
