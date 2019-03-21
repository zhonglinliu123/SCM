package scm.model;

import java.io.Serializable;

public class Customer_c implements Serializable {

	private static final long serialVersionUID =1L;
	
	private String customerCode;
	private String customerName;
	private String password;
	private String contactor;
	private String address;
	private String postcode="";
	private String tel;
	private String fax="";
	private String createDate;
	
	public Customer_c() {}

	
	public Customer_c(String customerCode, String customerName, String password, String contactor, String address,
			String postcode, String tel, String fax, String createDate) {
		super();
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.password = password;
		this.contactor = contactor;
		this.address = address;
		this.postcode = postcode;
		this.tel = tel;
		this.fax = fax;
		this.createDate = createDate;
	}



	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
