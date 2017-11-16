package com.itheima.constant;

public interface Constant {
	//写入常量
	//定义常量 用户的状态   用户未激活
	public static final int STORE_USER_STATE_0=0;
	// 用户激活
	public static final int STORE_USER_STATE_1=1;
	public static final int STORE_PRODUCT_IS_HOT_1=1;
	
	//redis中 分类的key
	public static final String STORE_CATEGORY_LIST_JSON="STORE_CATEGORY_LIST_JSON";
	//订单的状态 订单状态  默认为0:未支付  1:已支付  2:已发货   3:已完成
	public static final int STORE_ORDERS_STATE_0=0;
	public static final int STORE_ORDERS_STATE_1=1;
}
