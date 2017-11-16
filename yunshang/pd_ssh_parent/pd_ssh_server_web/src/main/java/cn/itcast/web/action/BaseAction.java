package cn.itcast.web.action;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.sysadmin.User;
import cn.itcast.utils.Page;
import cn.itcast.utils.SysConstant;

/**
 * @Description:
 * @Author:		传智播客 java学院	传智.宋江
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月31日
 */

//通过RequestAware, SessionAware, ApplicationAware实行接口获得request,session,application对象，action中就可直接调用

/**
 *
 */
@Controller
@ParentPackage("default")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport
		implements RequestAware, SessionAware, ApplicationAware, ModelDriven<T> {
	private static Logger log = Logger.getLogger(BaseAction.class);

	private static final long serialVersionUID = 1L;

	// 模型驱动
	protected T model = this.setModel();

	// page分页
	protected Page page = new Page();

	@Override
	public T getModel() {
		return model;
	}

	// 初始设置model的值, 子类Action重写该方法
	public T setModel() {
		return null;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// 三个域
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	/**
	 * 
	 * 将一个对象放入栈顶
	 */
	public void push(Object obj) {
		ActionContext.getContext().getValueStack().push(obj);
	}

	/**
	 * 放集合放入值栈中 context区域
	 */
	public void put(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	/**
	 * 将spring的page转换成自己的page
	 */
	public void parsePage(Page page, org.springframework.data.domain.Page page2) {
		page.setResults(page2.getContent());
		page.setTotalPage(page2.getTotalPages());
		page.setTotalRecord(page2.getTotalElements());
	}

	/**
	 * 取出当前登录的用户对象
	 */
	public User getCurUser() {
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		return user;
	}

}
