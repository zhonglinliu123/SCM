package scm.model;

import java.io.Serializable;
/**
 * 
 * 增加明细，soitem表
 * 
 */
public class Soitem_c implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	//产品编号
	private String productCode;
	
	private String productName;
	//销售单编号
	private String soid;
	//产品数量
	private int productNumber;
	//数量单位
	private String unitName;
	//产品单价
	private float unitPrice;
	//销售细明总价
	private float itemPrice;
	
	public Soitem_c() {}

	public Soitem_c(String soid, String productCode, int productNumber, float unitPrice, String unitName,
			float itemPrice) {
		super();
		this.soid = soid;
		this.productCode = productCode;
		this.productNumber = productNumber;
		this.unitPrice = unitPrice;
		this.unitName = unitName;
		this.itemPrice = itemPrice;
	}

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

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
}
