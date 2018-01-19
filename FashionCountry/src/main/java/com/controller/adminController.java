package com.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.DaoImpl.CategoryDaoImpl;
import com.DaoImpl.ProductDaoImpl;
import com.DaoImpl.SupplierDaoImpl;
import com.model.Category;
import com.model.Product;
import com.model.Supplier;

@Controller
@RequestMapping("/admin")
public class adminController
{

	@Autowired
	SupplierDaoImpl supplierDaoImpl;
	
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	@Autowired
	ProductDaoImpl productDaoImpl;
	
	@RequestMapping("/adding")
	public String adding()
	{
		return "adding";
	}
	
	@RequestMapping(value="/saveSupp", method=RequestMethod.POST)
	@Transactional
	public ModelAndView saveSuppData(@RequestParam("sid")int sid, @RequestParam("sname")String sname)
	{
		ModelAndView mv = new ModelAndView();
		Supplier ss = new Supplier();
		ss.setSid(sid);
		ss.setSupplierName(sname);
		supplierDaoImpl.insertSupplier(ss);
		mv.setViewName("model");
		return mv;
		
	}
	
	@RequestMapping(value="/saveCat", method=RequestMethod.POST)
	@Transactional
	public ModelAndView saveCatData(@RequestParam("cid")int cid, @RequestParam("cname")String cname)
	{
		ModelAndView mv = new ModelAndView();
		Category cc = new Category();
		cc.setCid(cid);
		cc.setCname(cname);
		categoryDaoImpl.insertCategory(cc);
		mv.setViewName("modal");
		return mv;
	}
	
	@RequestMapping(value="/saveProduct", method= RequestMethod.POST)
	public String saveProd(HttpServletRequest request, @RequestParam("file")MultipartFile file)
	{
Product prod = new Product();
prod.setPname(request.getParameter("pName"));
prod.setPrice(Double.parseDouble(request.getParameter("pPrice")));
prod.setDescription(request.getParameter("pDescription"));
prod.setStock(Integer.parseInt(request.getParameter("pStock")));
prod.setCategory(categoryDaoImpl.findByCatId(Integer.parseInt(request.getParameter("pCategory"))));
prod.setSupplier(supplierDaoImpl.findBySuppId(Integer.parseInt(request.getParameter("pSupplier"))));

String filepath = request.getSession().getServletContext().getRealPath("/"); 
String filename = file.getOriginalFilename();
prod.setImgName(filename);
productDaoImpl.insertProduct(prod);

System.out.println("File path"+ filepath);

try
{
byte imagebyte[] = file.getBytes();
BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath+"/resources/"+filename));

fos.write(imagebyte);
fos.close();
}
catch(IOException e)
{
	e.printStackTrace();
}
return "model";
	}
	
@ModelAttribute
public void loadingDataInPage(Model m)
{
m.addAttribute("satList", supplierDaoImpl.retrive());
m.addAttribute("catList", categoryDaoImpl.retrive());
m.addAttribute("prodList", productDaoImpl.retrive());
}
	@RequestMapping("/productList")
	public ModelAndView prodlist()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("prodList", productDaoImpl.retrive());	
		mv.setViewName("productAdminList");
		return mv;
	}

	@RequestMapping("/supplierList")
	public ModelAndView supplist()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("satList", supplierDaoImpl.retrive());
		mv.setViewName("supplierAdminList");
		return mv;
	}
	
	@RequestMapping("/categoryList")
	public ModelAndView catlist()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("catList", categoryDaoImpl.retrive());
		mv.setViewName("categoryAdminList");
		return mv;
	}
	
	@RequestMapping("/deleteProd/{pid}")
	public String deleteProduct(@PathVariable("pid")int pid)
	{
		productDaoImpl.deleteprod(pid);
		return "redirect:/productList?del";
	}
	
	@RequestMapping("/updateProd")
	public ModelAndView updateproduct(@RequestParam("pid") int pid)
	{
		
		ModelAndView mv = new ModelAndView();
		Product p = productDaoImpl.findByPID(pid);
		mv.addObject("prod", p);
		mv.addObject("cList", categoryDaoImpl.retrive());
		mv.addObject("sList",supplierDaoImpl.retrive());
		mv.setViewName("updateProduct");
		return mv;
	}
	
	
	@RequestMapping(value="/productUpdate", method= RequestMethod.POST)
	public String updateProd(HttpServletRequest request, @RequestParam("file")MultipartFile file)
	{
		String pid = request.getParameter("pid");
Product prod = new Product();
prod.setPid(Integer.parseInt(pid));

prod.setPname(request.getParameter("pName"));
prod.setPrice(Double.parseDouble(request.getParameter("pPrice")));
prod.setDescription(request.getParameter("pDescription"));
prod.setStock(Integer.parseInt(request.getParameter("pStock")));

String cat = request.getParameter("pCategory");
String sat = request.getParameter("pSupplier");

prod.setCategory(categoryDaoImpl.findByCatId(Integer.parseInt(cat)));
prod.setSupplier(supplierDaoImpl.findBySuppId(Integer.parseInt(sat)));

String filepath = request.getSession().getServletContext().getRealPath("/"); 
String filename = file.getOriginalFilename();
prod.setImgName(filename);
productDaoImpl.update(prod);

System.out.println("File path"+ filepath);

try
{
byte imagebyte[] = file.getBytes();
BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath+"/resources/"+filename));

fos.write(imagebyte);
fos.close();
}
catch(IOException e)
{
	e.printStackTrace();
}
return "redirect:/productList?update";
	}
	
}












 



