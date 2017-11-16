package cn.itcast.domain.sysadmin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DEPT_P")
public class Dept implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEPT_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@OneToMany(mappedBy = "dept")
	private Set<User> users = new HashSet<User>(); // 部门与用户 一对多

	@Column(name = "DEPT_NAME")
	private String deptName;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "DEPT_ID")
	private Dept parent;

	@Column(name = "STATE")
	private Integer state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Dept getParent() {
		return parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", deptName=" + deptName + ", parent=" + parent + ", state=" + state + "]";
	}

}
