package scm.model;

import java.io.Serializable;

//销售单明细
public class SellOrderItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String soid;
	private String productCode;
	private String productName;
	private int num;
	private String unitNum;
	private int itemPrice;
	private int sellDetailsTotal;
	
	public SellOrderItem() {}

	public String getSoid() {
		return soid;
	}

	public void setSoid(String soid) {
		this.soid = soid;
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

	public String getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getSellDetailsTotal() {
		return sellDetailsTotal;
	}

	public void setSellDetailsTotal(int sellDetailsTotal) {
		this.sellDetailsTotal = sellDetailsTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SellOrderItem(String soid, String productCode, String productName, int num, String unitNum, int itemPrice,
			int sellDetailsTotal) {
		super();
		this.soid = soid;
		this.productCode = productCode;
		this.productName = productName;
		this.num = num;
		this.unitNum = unitNum;
		this.itemPrice = itemPrice;
		this.sellDetailsTotal = sellDetailsTotal;
	}
	
}
