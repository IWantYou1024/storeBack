package cn.itcast.web.action;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.domain.home.Memo;
import cn.itcast.domain.sysadmin.User;
import cn.itcast.service.MemoService;

/**
 * @Description:
 * @Author: 传智播客 java学院 传智.宋江
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014年10月31日
 */
@Namespace("/")
@Results({ @Result(name = "title", location = "/WEB-INF/pages/home/title.jsp"),
		@Result(name = "fmain", location = "/WEB-INF/pages/home/fmain.jsp"),
		@Result(name = "toleft", location = "/WEB-INF/pages/${moduleName}/left.jsp"),
		@Result(name = "tomain", location = "/WEB-INF/pages/${moduleName}/main.jsp") })
public class HomeAction extends BaseAction {
	
	@Autowired
	private MemoService memoService;
	
	private String moduleName; // 动态指定跳转的模块，在struts.xml中配置动态的result

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Action("homeAction_fmain")
	public String fmain() {
		return "fmain";
	}

	@Action("homeAction_title")
	public String title() {
		return "title";
	}

	// 转向moduleName指向的模块
	@Action("homeAction_tomain")
	public String tomain() {
		final User curUser = super.getCurUser();
		if (moduleName.equals("home")) {
			Specification<Memo> spec = new Specification<Memo>() {
				public Predicate toPredicate(Root<Memo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Join<Memo, User> join = root.join("user");
					return cb.equal(join.get("id").as(String.class), curUser.getId());
				}
			};
			List<Memo> memoList = memoService.find(spec);
			super.put("memoList", memoList);
		}
		return "tomain";
	}

	@Action("homeAction_toleft")
	public String toleft() {
		return "toleft";
	}
}
