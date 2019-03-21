package scm.model;

import java.io.Serializable;

/**
 * 
 * 销售单，somain表
 * 
 */
public class Somain_c implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//销售单编号
	private String soid="";
	//客户编号
	private String customerCode="";
	//用户账号
	private String account="";
	private String userName;
	private String customerName;
	//创建时间
	private String createTime="";
	//附加费用
	private float tipFee;
	//产品总价
	private float productTotal;
	
	//总价格
	private float soTotal;
	
	//付款类型
	private String payType="";
	//预付金额
	private float prePayFee;
	private int status;
	private String remark="";
	private String stockTime="";
	private String stockUser="";
	private String payTime="";
	private String payUser="";
	private String prePayTime="";
	private String prePayUser="";
	private String endTime="";
	private String endUser="";
	
	public Somain_c() {}

	

	


	public String getSoid() {
		return soid;
	}

	public void setSoid(String soid) {
		this.soid = soid;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getStockTime() {
		return stockTime;
	}



	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}



	public String getStockUser() {
		return stockUser;
	}



	public void setStockUser(String stockUser) {
		this.stockUser = stockUser;
	}



	public String getPayTime() {
		return payTime;
	}



	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}



	public String getPayUser() {
		return payUser;
	}



	public void setPayUser(String payUser) {
		this.payUser = payUser;
	}



	public String getPrePayUser() {
		return prePayUser;
	}



	public void setPrePayUser(String prePayUser) {
		this.prePayUser = prePayUser;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public String getEndUser() {
		return endUser;
	}



	public void setEndUser(String endUser) {
		this.endUser = endUser;
	}



	public String getPrePayTime() {
		return prePayTime;
	}



	public void setPrePayTime(String prePayTime) {
		this.prePayTime = prePayTime;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
}
