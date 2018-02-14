package com.Dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.model.Category;

public interface CategoryDao
{
	public void insertCategory(Category category);
	public Category findByCatId(int cid);
	public List<Category> retrive();
}
