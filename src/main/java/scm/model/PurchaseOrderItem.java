package scm.model;

import java.io.Serializable;

//采购单明细
public class PurchaseOrderItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private String poid;
	private String productCode;
	private String productName;
	private int num;
	private String unitName;
	private int itemPrice;
	private int purchaseDetailsTotal;
	
	public PurchaseOrderItem() {}

	public PurchaseOrderItem(String productCode, String productName, int num, String unitName, int itemPrice,
			int purchaseDetailsTotal) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.num = num;
		this.unitName = unitName;
		this.itemPrice = itemPrice;
		this.purchaseDetailsTotal = purchaseDetailsTotal;
	}

	public String getPoid() {
		return poid;
	}

	public void setPoid(String poid) {
		this.poid = poid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getPurchaseDetailsTotal() {
		return purchaseDetailsTotal;
	}

	public void setPurchaseDetailsTotal(int purchaseDetailsTotal) {
		this.purchaseDetailsTotal = purchaseDetailsTotal;
	}
	
	
}
