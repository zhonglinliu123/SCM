package scm.model;

import java.io.Serializable;

//供应商
public class Vender implements Serializable{
	private static final long serialVersionUID = 1L;
	private String vendercode;
	private String name;
	private String password;
	private String contactor;
	private String address;
	private String postcode;
	private String tel;
	private String fax;
	private String createdate;
	
	public Vender() {}

	public String getVendercode() {
		return vendercode;
	}

	public void setVendercode(String vendercode) {
		this.vendercode = vendercode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public Vender(String vendercode, String name, String password, String contactor, String address, String postcode,
			String tel, String fax, String createdate) {
		super();
		this.vendercode = vendercode;
		this.name = name;
		this.password = password;
		this.contactor = contactor;
		this.address = address;
		this.postcode = postcode;
		this.tel = tel;
		this.fax = fax;
		this.createdate = createdate;
	}
	
	@Override
	public String toString() {
		return "Vender [vendercode=" + vendercode + ", name=" + name + ", password=" + password + ", contactor=" + contactor
				+  ", address=" + address + ", postcode=" + postcode + ", tel=" + tel + ", fax=" + fax +", createdate=" + createdate + "]";
	}
}
