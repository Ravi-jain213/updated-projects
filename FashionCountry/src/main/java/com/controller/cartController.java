package com.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

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

import com.DaoImpl.CartDaoImpl;
import com.DaoImpl.CategoryDaoImpl;
import com.DaoImpl.OrdersDaoImpl;
import com.DaoImpl.ProductDaoImpl;
import com.DaoImpl.SupplierDaoImpl;
import com.DaoImpl.UserDaoImpl;
import com.model.Cart;
import com.model.Category;
import com.model.Orders;
import com.model.Product;
import com.model.Supplier;
import com.model.User;

@Controller 
public class cartController
{
		@Autowired
		SupplierDaoImpl supplierDaoImpl;
		
		@Autowired
		CategoryDaoImpl categoryDaoImpl;
		
		@Autowired
		ProductDaoImpl productDaoImpl;
		
		@Autowired
		CartDaoImpl cartDaoImpl;
		
		@Autowired
		OrdersDaoImpl orderDaoImpl;
		
		@Autowired
		UserDaoImpl userDaoImpl;
		
		@RequestMapping(value="/productDetails/${p.pid}")
		public ModelAndView prodDet(@PathVariable("pid")int pid)
		{
			ModelAndView mv = new ModelAndView();
			Product prod = productDaoImpl.findByPID(pid);
			mv.addObject("prod",prod);
			mv.setViewName("prodDetails");
			return mv;
		}
		
		@RequestMapping(value =  "/addToCart", method = RequestMethod.POST)
		public ModelAndView addtocart(HttpServletRequest request)
		{
			ModelAndView mv = new ModelAndView();
			Principal principal = request.getUserPrincipal();
			String userEmail = principal.getName();
			
			try
			{
				int pid = Integer.parseInt(request.getParameter("pid"));
						Double price = Double.parseDouble(request.getParameter("pPrice"));
						int qty = Integer.parseInt(request.getParameter("pQty"));
						String pname = request.getParameter("prodName");
						String imgName = request.getParameter("imgName");
						Cart cartExist = cartDaoImpl.getCartById(pid, userEmail);
						if(cartExist == null)
						{
							
							Cart cm = new Cart();
							cm.setCartPrice(price);
							cm.setCartProductId(pid);
							cm.setCartStock(qty);
							cm.setCartImage(imgName);
							cm.setCartProductName(pname);
							
							User u = userDaoImpl.findUserByEmail(userEmail);
							cm.setCartUserDetails(u);
							cartDaoImpl.insertCart(cm);
						}
						else if(cartExist != null)
						{
							Cart cm = new Cart();
							cm.setCartId(cartExist.getCartId());
							cm.setCartProductId(pid);
							cm.setCartStock(cartExist.getCartStock()+qty);
							cm.setCartImage(imgName);
							cm.setCartProductName(pname);
							
							User u = userDaoImpl.findUserByEmail(userEmail);
							cm.setCartUserDetails(u);
							cartDaoImpl.updateCart(cm);
						}
						mv.addObject("cartInfo",cartDaoImpl.findByCartID(userEmail));
						mv.setViewName("cart");
						return mv;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				mv.addObject("cartInfo",cartDaoImpl.findByCartID(userEmail));
				mv.setViewName("cart");
				return mv;
			}
		}
		@RequestMapping(value = "/checkout", method = RequestMethod.GET)
		public ModelAndView checkoutProcess(HttpServletRequest req)
		{
			ModelAndView mv = new ModelAndView();
			Principal principal = req.getUserPrincipal();
			String userEmail = principal.getName();
			User user = userDaoImpl.findUserByEmail(userEmail);
			List<Cart> cart = cartDaoImpl.findByCartID(userEmail);
			mv.addObject("user", user);
			mv.addObject("cart", cart);
			return mv;
		}
		@RequestMapping(value = "/Orderprocess", method = RequestMethod.POST )
		public ModelAndView orderProcess(HttpServletRequest req)
		{
			ModelAndView mv = new ModelAndView("ack");
			Orders ord = new Orders();
			Principal principal = req.getUserPrincipal();
			String userEmail = principal.getName();
			Double total = Double.parseDouble(req.getParameter("total"));
			String payment = req.getParameter("payment");
			User users = userDaoImpl.findUserByEmail(userEmail);
			ord.setUser(users);
			ord.setTotal(total);
			ord.setPayment(payment);
			orderDaoImpl.insertOrder(ord);
			mv.addObject("orderDetails", users);
			mv.addObject("ack");
			return mv;
			
		}
		@RequestMapping("/ack")
		public String ack()
		{
			return "ack";
		}
		
		@RequestMapping(value = "/deletePCart/{cartId}")
		public ModelAndView deleteCartItem(@PathVariable("cartId")int cartId, HttpServletRequest req)
		{
			ModelAndView mv = new ModelAndView();
			Principal principal = req.getUserPrincipal();
			String userEmail = principal.getName();
			cartDaoImpl.deleteCart(cartId);
			mv.addObject("cartInfo", cartDaoImpl.findByCartID(userEmail));
			mv.setViewName("cart");
			return mv;
			
			
		}
		
		@RequestMapping(value = "/goToCart", method = RequestMethod.GET)
		public ModelAndView gotocart(HttpServletRequest req)
		{
			ModelAndView mv = new ModelAndView();
			Principal principal = req.getUserPrincipal();
			String userEmail = principal.getName();
			mv.addObject("cartInfo", cartDaoImpl.findByCartID(userEmail));
			mv.setViewName("cart");
			return mv;
		}
}
