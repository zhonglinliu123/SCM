package scm.model;

import java.io.Serializable;

//采购单主信息
public class PurchaseOrderMain implements Serializable{
	private static final long serialVersionUID = 1L;
	private String poid;
	private String venderName;
	private String createDate;
	private String account;
	private int tipFee;
	private int productTotal;
	private int poTotal;
	private String payType;
	private int prePayFee;
	private String status;
	
	
	public PurchaseOrderMain(String poid, String venderName, String createDate, String account, int tipFee,
			int productTotal, int poTotal, String payType, int prePayFee, String status) {
		super();
		this.poid = poid;
		this.venderName = venderName;
		this.createDate = createDate;
		this.account = account;
		this.tipFee = tipFee;
		this.productTotal = productTotal;
		this.poTotal = poTotal;
		this.payType = payType;
		this.prePayFee = prePayFee;
		this.status = status;
	}

	public PurchaseOrderMain() {}

	public String getPoid() {
		return poid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPoid(String poid) {
		this.poid = poid;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getTipFee() {
		return tipFee;
	}

	public void setTipFee(int tipFee) {
		this.tipFee = tipFee;
	}

	public int getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(int productTotal) {
		this.productTotal = productTotal;
	}

	public int getPoTotal() {
		return poTotal;
	}

	public void setPoTotal(int poTotal) {
		this.poTotal = poTotal;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public int getPrePayFee() {
		return prePayFee;
	}

	public void setPrePayFee(int prePayFee) {
		this.prePayFee = prePayFee;
	}

	public PurchaseOrderMain(String poid, String venderName, String createDate, String account, int tipFee,
			int productTotal, int poTotal, String payType, int prePayFee) {
		super();
		this.poid = poid;
		this.venderName = venderName;
		this.createDate = createDate;
		this.account = account;
		this.tipFee = tipFee;
		this.productTotal = productTotal;
		this.poTotal = poTotal;
		this.payType = payType;
		this.prePayFee = prePayFee;
	}
	
}
