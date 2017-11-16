package cn.itcast.web.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.StatChartService;
import cn.itcast.utils.SysConstant;
import cn.itcast.utils.UtilFuns;

/**
 * fastMenu拦截器, 获取访问的ActionName并拼接字符串存入redis
 */
public class FastMenuInterceptor extends MethodFilterInterceptor {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private StatChartService statChartService;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获取当前用户, 主要用于将用户id作为键进行redis的存储
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);

		if (user != null) {
			// 获取当前actionName  获取的是@Action注解中的value值
			String actionName = invocation.getProxy().getActionName();
			// 获取对应用户的快捷菜单json字符串
			String jsonString = redisTemplate.opsForValue().get(user.getId());

			List<Map> parse = null;

			if (UtilFuns.isNotEmpty(jsonString)) {
				// json不为空则解析为List对象
				parse = JSON.parseArray(jsonString, Map.class);
			} else {
				// 为空则new一个
				parse = new ArrayList<>();
			}

			// 从模块表中查询指定的值用于和actionName进行比较
			String sql = "select t.cpermission, t.curl from module_p t where t.layer_num = 2";
			List<Map<String, Object>> list = statChartService.getData(sql);
			// 遍历数据比较, 并将最近点击的在模块表中有curl的2级模块保存进List中
			for (Map<String, Object> map : list) {
				if (map.get("CURL") != null && map.get("CURL").toString().contains(actionName)) {
					if (!parse.contains(map)) {
						if (parse.size() >= 3) {
							parse.remove(0);
						}
						parse.add(map);
					}
				}
			}
			// 将List转为json字符串并存入redis中
			jsonString = JSON.toJSONString(parse);
			redisTemplate.opsForValue().set(user.getId(), jsonString);
		}

		return invocation.invoke();

	}

}
