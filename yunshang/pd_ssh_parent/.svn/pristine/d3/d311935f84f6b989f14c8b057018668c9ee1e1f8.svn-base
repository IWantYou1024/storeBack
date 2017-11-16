package cn.itcast.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.ExportDao;
import cn.itcast.dao.cargo.PackingListDao;
import cn.itcast.domain.cargo.Export;
import cn.itcast.domain.cargo.PackingList;
import cn.itcast.service.PackingListService;
import cn.itcast.utils.UtilFuns;

@Service("packingListService")
public class PackingListServiceImpl extends BaseServiceImpl<PackingList> implements PackingListService {

	private PackingListDao packingListDao;

	@Autowired
	private ExportDao exportDao;
	
	@Autowired
	public void init(PackingListDao packingListDao) {
		super.baseDao = packingListDao;
		this.packingListDao = packingListDao;
	}
	
	@Override
	public void saveOrUpdate(PackingList entity) {
		if (UtilFuns.isEmpty(entity.getPackingListId())) {
			//没有id,则保存,保存没有的属性
			//先使用uuid给id赋值
			String uuid = UUID.randomUUID().toString().replace("-", "");
			entity.setPackingListId(uuid);
			//再给委托单设置id
			entity.getShippingOrder().setShippingOrderId(uuid);
			//再给发票设置id
			entity.getInvoice().setId(uuid);
			//再给财务设置id
			entity.getFinance().setId(uuid);
			//设置装箱单的状态为草稿
			entity.setState(0);
			   
			//ExportNos设置
			
			//更改报运单的状态
			if(UtilFuns.isNotEmpty(entity.getExportIds())){
			String[] cids = entity.getExportIds().split(", ");
				for (String cid : cids) {
					//查询报运单
					Export export = exportDao.getOne(cid);
					//设置报运的state属性为已装箱
					export.setState(3);
					//更新报运
					exportDao.save(export);
				}
			}
			
		}else{
			
			
		}
		packingListDao.save(entity);
	}

	@Override
	public void updateState(String[] ids, int state) {
		for (String id : ids) {
			PackingList packingList = packingListDao.getOne(id);
			packingList.setState(state);
			packingListDao.save(packingList);
		}
	}



}
