package scm.model;

import java.io.Serializable;

// 销售单主信息
public class SellOrderMain implements Serializable{
	private static final long serialVersionUID = 1L;
	private String soid;
	private String customerName;
	private String account;
	private String createTime;
	private float tipFee;
	private float productTotal;
	private float soTotal;
	private String payType;
	private float prePayFee;
	private String status;
	
	public SellOrderMain() {}

	public String getSoid() {
		return soid;
	}

	public void setSoid(String soid) {
		this.soid = soid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public float getTipFee() {
		return tipFee;
	}

	public void setTipFee(float tipFee) {
		this.tipFee = tipFee;
	}

	public float getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(float productTotal) {
		this.productTotal = productTotal;
	}

	public float getSoTotal() {
		return soTotal;
	}

	public void setSoTotal(float soTotal) {
		this.soTotal = soTotal;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public float getPrePayFee() {
		return prePayFee;
	}

	public void setPrePayFee(float prePayFee) {
		this.prePayFee = prePayFee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SellOrderMain(String soid, String customerName, String account, String createTime, float tipFee,
			float productTotal, float soTotal, String payType, float prePayFee, String status) {
		super();
		this.soid = soid;
		this.customerName = customerName;
		this.account = account;
		this.createTime = createTime;
		this.tipFee = tipFee;
		this.productTotal = productTotal;
		this.soTotal = soTotal;
		this.payType = payType;
		this.prePayFee = prePayFee;
		this.status = status;
	}
	
}
