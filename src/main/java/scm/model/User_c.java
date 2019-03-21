package scm.model;

import java.io.Serializable;

public class User_c implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String account;
	private String password;
	private String name;
	private String createDate;
	private String status;
	//private List<String> modelCodeList; //模块名称
	private String filter;
	
	public User_c() {}
	
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
	
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
