package cn.itcast.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.dao.cargo.ShippingOrderDao;
import cn.itcast.domain.cargo.PackingList;
import cn.itcast.domain.cargo.ShippingOrder;
import cn.itcast.service.PackingListService;
import cn.itcast.service.ShippingOrderService;
import cn.itcast.utils.UtilFuns;



@Service("shippingOrderService")
public class ShippingOrderServiceImpl extends BaseServiceImpl<ShippingOrder> implements ShippingOrderService {

	private ShippingOrderDao shippingOrderDao;
	@Autowired
	private PackingListService packingListService;

	@Autowired
	public void init(ShippingOrderDao shippingOrderDao) {
		super.baseDao = shippingOrderDao;
		this.shippingOrderDao = shippingOrderDao;
	}

	
	public void saveOrUpdate(ShippingOrder entity) {
		shippingOrderDao.save(entity);
	}


	

}
