package cn.itcast.web.action.stat;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.itcast.service.StatChartService;
import cn.itcast.utils.FastJsonUtils;
import cn.itcast.web.action.BaseAction;

@Namespace("/stat")
@Results({ @Result(name = "factorysale", location = "/WEB-INF/pages/stat/chart/factorySalePie.jsp"),
		@Result(name = "productsale", location = "/WEB-INF/pages/stat/chart/productSaleColumn.jsp"),
		@Result(name = "onlineInfo", location = "/WEB-INF/pages/stat/chart/onlineinfolineSmooth.jsp"),
		@Result(name = "priceTop10", location = "/WEB-INF/pages/stat/chart/columnCylinders.jsp"),
		@Result(name="ipAcessTimes", location="/WEB-INF/pages/stat/chart/ipAcessTimesWithTrendLines.jsp")})
public class StatChartAction extends BaseAction {

	@Autowired
	private StatChartService statChartService;

	/**
	 * 生产厂家销售情况
	 */
	@Action("statChartAction_factorysale")
	public String factorysale() throws Exception {
		// 使用sql得到统计数据
		String sql = "select factory_name factoryname, sum(amount) sales from contract_product_c group by factory_name";
		List<Map<String, Object>> factoryList = statChartService.getData(sql);
		String jsonString = JSON.toJSONString(factoryList);
		super.put("myChartData", jsonString);

		return "factorysale";
	}

	/**
	 * 产品列表
	 */
	@Action("statChartAction_productsale")
	public String productsale() throws Exception {
		return "productsale";
	}

	/**
	 * ajax请求数据
	 */
	@Action("statChartAction_genProductSale")
	public String genProductSale() throws Exception {
		// 加载数据
		String sql = "select t.product_no productno, t.samount saleamount from (select product_no, sum(amount) samount from contract_product_c group by product_no order by sum(amount) desc) t where rownum <= 15";
		List<Map<String, Object>> productList = statChartService.getData(sql);
		String[] colors = { "#FF0F00", "#FF6600", "#FF9E01", "#FCD202", "#F8FF01", "#B0DE09", "#04D215", "#0D8ECF",
				"#0D52D1", "#2A0CD0", "#8A0CCF" };

		int i = 0;
		for (Map<String, Object> map : productList) {
			map.put("color", colors[i++]);
			if (i > colors.length - 1) {
				i = 0;
			}
		}

		// response返回
		FastJsonUtils.writeJson(ServletActionContext.getResponse(), productList);
		return NONE;
	}

	/**
	 * 系统访问压力
	 */
	@Action("statChartAction_onlineinfo")
	public String onlineInfo() throws Exception {
		// 加载数据
		String sql = "select a.a1 hour, nvl(b.c, 0) value from (select * from online_info_t) a left join (select to_char(login_time, 'HH24') a1, count(*) c from login_log_p group by to_char(login_time, 'HH24')) b on (a.a1 = b.a1) order by a.a1";
		List<Map<String, Object>> onlineList = statChartService.getData(sql);
		String jsonString = JSON.toJSONString(onlineList);
		super.put("onlineJson", jsonString);

		return "onlineInfo";
	}
	
	/**
	 * ip访问次数统计图
	 * @return
	 * @throws Exception
	 */
	@Action("statChartAction_ipAcessTimes")
	public String ipAcessTimes() throws Exception {
		
		// 加载数据
		String sql = "select *from (select ip_Address IP,count(*) AT from Login_Log_p t group by ip_Address order by AT desc ) where rownum <=10 ";
		List<Map<String, Object>> ipAcessList= statChartService.getData(sql);
		String jsonString = JSON.toJSONString(ipAcessList);
		super.put("ipAcessList", jsonString);

		return "ipAcessTimes";
	}
	
	/**
	 * ip访问次数统计图
	 * @return
	 * @throws Exception
	 */
	@Action("statChartAction_priceTop10")
	public String priceTop10() throws Exception {
		
		// 加载数据
		String sql = "select * from (select product_no,price from PRODUCT_C t order by price desc ) where rownum <=10 ";
		List<Map<String, Object>> priceList= statChartService.getData(sql);
		String[] colors = { "#FF0F00", "#FF6600", "#FF9E01", "#FCD202", "#F8FF01", "#B0DE09", "#04D215", "#0D8ECF",
				"#0D52D1", "#2A0CD0", "#8A0CCF" };

		int i = 0;
		for (Map<String, Object> map : priceList) {
			map.put("color", colors[i++]);
			if (i > colors.length - 1) {
				i = 0;
			}
		}

		String jsonString = JSON.toJSONString(priceList);
		super.put("priceList", jsonString);

		return "priceTop10";
	}

}
