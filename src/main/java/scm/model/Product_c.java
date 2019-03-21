package scm.model;

import java.io.Serializable;

/**
 * 
 * 产品表product
 * 
 */

public class Product_c implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//产品编号
	private String productCode;
	//类别序列号
	private int categoryID;
	
	//类别名称，product中没有此字段
	private String categoryName="";
	
	//产品名称
	private String productName;
	//数量单位
	private String unitName;
	//销售价
	private float price;
	//添加日期
	private String createDate;
	//产品描述
	private String remark="";
	//采购在途数
	private int poNum;
	//销售待发数
	private int soNum;
	
	public Product_c() {}
	

	public Product_c(String productCode, int categoryID, String productName, String unitName, float price,
			String createDate, String remark) {
		this.productCode = productCode;
		this.categoryID = categoryID;
		this.productName = productName;
		this.unitName = unitName;
		this.price = price;
		this.createDate = createDate;
		this.remark = remark;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	
}
