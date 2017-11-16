package cn.itcast.domain.sysadmin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "LOGIN_LOG_P")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LoginStat {
	@Id
	@Column(name = "LOGIN_LOG_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String logId;
	@Column(name = "LOGIN_NAME")
	private String userName;
	@Column(name = "IP_ADDRESS")
	private String ip_Address;

	@Column(name = "LOGIN_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp_Address() {
		return ip_Address;
	}

	public void setIp_Address(String ip_Address) {
		this.ip_Address = ip_Address;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
