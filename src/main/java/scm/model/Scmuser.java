package scm.model;

import java.io.Serializable;

//ÓÃ»§
public class Scmuser implements Serializable{
	private static final long serialVersionUID = 1L;
	private String account;
	private String password;
	private String name;
	private String createDate;
	private String status;
	
	public Scmuser() {}
	
	public Scmuser(String account, String password, String name, String createDate, String status) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.createDate = createDate;
		this.status = status;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
