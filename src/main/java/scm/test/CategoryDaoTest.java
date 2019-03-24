package scm.test;

import java.util.ArrayList;

import scm.dao.CategoryDao_c;
import scm.model.Category_c;

public class CategoryDaoTest {

	public static void main(String[] args) {
		CategoryDao_c categoryDao = new CategoryDao_c();
		Category_c category = new Category_c();
		category.setCategoryID(1);
		category.setName("水果");
		ArrayList<Category_c> categorylist = null;
		try {
			categorylist = categoryDao.listCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Category_c s:categorylist) {
			System.out.println(s.getName());
		}
	}
}
