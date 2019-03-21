package scm.model;

import java.io.Serializable;

/**
 * 
 * 产品分类表对应的类
 * 
 */
public class Category_c implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int categoryID;//分类序列号
	private String name;//名称
	private String remark;//描述
	
	public Category_c() {}
	
	
	//更新
	public Category_c(int categoryID, String name, String remark) {
		this.categoryID = categoryID;
		this.name = name;
		this.remark = remark;
	}


	public Category_c(String name, String remark) {
		this.name = name;
		this.remark = remark;
	}


	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
