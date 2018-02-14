package com.Dao;

import java.util.List;

import com.model.Supplier;

public interface SupplierDao
{
	public void insertSupplier(Supplier supplier);
	public List<Supplier> retrive();
	public Supplier findBySuppId(int sid);
	
	
}
