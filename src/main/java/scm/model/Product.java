package scm.model;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	private String productCode;
	private String productName;
	private int nowNum;
	private int poNum;
	private int soNum;
	
	public Product() {}

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

	public int getNowNum() {
		return nowNum;
	}

	public void setNowNum(int nowNum) {
		this.nowNum = nowNum;
	}

	public int getPoNum() {
		return poNum;
	}

	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}

	public int getSoNum() {
		return soNum;
	}

	public void setSoNum(int soNum) {
		this.soNum = soNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Product(String productCode, String productName, int nowNum, int poNum, int soNum) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.nowNum = nowNum;
		this.poNum = poNum;
		this.soNum = soNum;
	}
	
}
