package cn.itcast.web.action.home;

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
import cn.itcast.web.action.BaseAction;

@Namespace("/home")
@Results({ @Result(name = "tocreate", location = "/WEB-INF/pages/home/memo/jMemoCreate.jsp"),
		@Result(name = "query", type = "redirect", location = "memoAction_query"),
		@Result(name = "toupdate", location = "/WEB-INF/pages/home/memo/jMemoUpdate.jsp"),
		@Result(name = "success", location = "/WEB-INF/pages/home/main.jsp") })
public class MemoAction extends BaseAction<Memo> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MemoService memoService;

	/**
	 * 跳转新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("memoAction_tocreate")
	public String tocreate() throws Exception {
		return "tocreate";
	}

	/**
	 * 新增备忘录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("memoAction_insert")
	public String insert() throws Exception {
		model.setUser(super.getCurUser());
		model.setState(1);
		memoService.saveOrUpdate(model);
		return "query";
	}

	/**
	 * 查询备忘录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("memoAction_query")
	public String query() throws Exception {
		final User curUser = super.getCurUser();
		Specification<Memo> spec = new Specification<Memo>() {
			public Predicate toPredicate(Root<Memo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Memo, User> join = root.join("user");
				return cb.equal(join.get("id").as(String.class), curUser.getId());
			}
		};
		List<Memo> memoList = memoService.find(spec);
		super.put("memoList", memoList);

		String str = "refreshMenu();";
		super.put("customerMenuRefresh", str);
		return SUCCESS;
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("memoAction_toupdate")
	public String toupdate() throws Exception {
		Memo memo = memoService.get(model.getId());
		super.push(memo);
		return "toupdate";
	}

	/**
	 * 修改备忘录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("memoAction_update")
	public String update() throws Exception {
		Memo memo = memoService.get(model.getId());
		memo.setDeadLine(model.getDeadLine());
		memo.setMemoContent(model.getMemoContent());
		memoService.saveOrUpdate(memo);
		return "query";
	}

	/**
	 * 修改备忘录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("memoAction_delete")
	public String delete() throws Exception {
		memoService.deleteById(model.getId());
		return "query";
	}

	@Override
	public Memo setModel() {
		return new Memo();
	}

}
