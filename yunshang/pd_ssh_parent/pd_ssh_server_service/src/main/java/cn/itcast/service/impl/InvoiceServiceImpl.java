package cn.itcast.service.impl;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import cn.itcast.dao.cargo.InvoiceDao;
import cn.itcast.domain.cargo.Invoice;
import cn.itcast.service.InvoiceService;

@Service("invoiceService")
public class InvoiceServiceImpl extends BaseServiceImpl<Invoice> implements InvoiceService{

	private InvoiceDao invoiceDao;
	
	@Autowired
	public void init(InvoiceDao invoiceDao) {
		super.baseDao = invoiceDao;
		this.invoiceDao = invoiceDao;
	}
	
}
