package scm.model;

import java.io.Serializable;

public class SomainDTO_c implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	//销售单编号
	private String soid="";
	//客户编号
	private String customerCode="";
	//客户名称
	private String customerName;
	//用户账号
	private String account="";
	//用户名称
	private String userName;
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
	//描述
	private String remark="";
	//状态
	private int status=1;
	
	public SomainDTO_c() {}

	

	public SomainDTO_c(String soid, String customerCode, String account, String createTime, float tipFee,
			float productTotal, float soTotal, String payType, float prePayFee, String remark) {
		super();
		this.soid = soid;
		this.customerCode = customerCode;
		this.account = account;
		this.createTime = createTime;
		this.tipFee = tipFee;
		this.productTotal = productTotal;
		this.soTotal = soTotal;
		this.payType = payType;
		this.prePayFee = prePayFee;
		this.remark = remark;
	}



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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "SomainDTO_c [soid=" + soid + ", customerCode=" + customerCode + ", customerName=" + customerName
				+ ", account=" + account + ", userName=" + userName + ", createTime=" + createTime + ", tipFee="
				+ tipFee + ", productTotal=" + productTotal + ", soTotal=" + soTotal + ", payType=" + payType
				+ ", prePayFee=" + prePayFee + ", remark=" + remark + ", status=" + status + "]";
	}
	
	
	
}
