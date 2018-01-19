package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Dao.UserDao;
import com.DaoImpl.CategoryDaoImpl;
import com.DaoImpl.ProductDaoImpl;
import com.DaoImpl.UserDaoImpl;
import com.model.User;

@Controller
public class indexController
{
	@Autowired 
	 UserDao userDaoImpl;
	
	@Autowired
	ProductDaoImpl productDaoImpl;
	
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	@RequestMapping("/")
	public String index()
	{
		return "index";
	}
	
	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	
	@RequestMapping(value="/goToRegister", method=RequestMethod.GET)
	public ModelAndView goToRegister()
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new User());
		mv.setViewName("register");
		
		return mv;
		
	}
	
	@RequestMapping(value="/saveRegister", method=RequestMethod.POST)
	public ModelAndView saveRegister(@ModelAttribute("user")User user, BindingResult result)
	{
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()) 
		{
			mv.setViewName("register");
		}
		else {
		user.setRole("ROLE_USER"); 
		userDaoImpl.insertUser(user);
		mv.setViewName("index");
		}
		return mv;
	}
	
	@RequestMapping(value="/productCustList")
	public ModelAndView getCustTable(@RequestParam("cid") int cid)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("prodList", productDaoImpl.retrive());
		mv.setViewName("productCustList");
		return mv;
	}
	
	@ModelAttribute
	public void getData(Model m)
	{
		m.addAttribute("catList", categoryDaoImpl.retrive());
	}
	
	@RequestMapping("/reLogin")
	public String relogin()
	{
	return "login";	
	}
	
	@RequestMapping("/userLogged")
	public String userlogged()
	{
	
		return "redirect:/goToLogin";
	}
	
	
}
