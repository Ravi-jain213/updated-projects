package com.Dao;

import java.util.List;

import com.model.*;

public interface ProductDao
{
	public void insertProduct(Product product);
	public List<Product> retrive();
	public Product findByPID(int pid);
}
