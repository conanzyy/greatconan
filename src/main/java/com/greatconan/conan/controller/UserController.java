package com.greatconan.conan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greatconan.conan.model.User;
import com.greatconan.conan.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/loginView")
	public String loginView(){
		return "login";
	}
	
	@RequestMapping("/user/registerView")
	public String registerView(){
		return "register";
	}
	
	@RequestMapping("/user/login")
	public ModelAndView login(User user){
		ModelAndView mav = new ModelAndView();
		User u = userService.loginCheck(user);
		if(null==u){
			mav.setViewName("login");
			mav.addObject("errorMsg","用户名或密码有误！");
			return mav;
		}
		else{
			mav.setViewName("success");
			mav.addObject("user", u);
			return mav;
		}
	}
	
	@RequestMapping(value = "/getResult")
	public String doSearch(HttpServletRequest req,
			HttpServletResponse res, HttpSession sess,String Astr,String Bstr) throws IOException, ServletException {
		if(Astr==null||Astr.trim().equals("")||Bstr==null||Bstr.trim().equals("")){
			res.sendRedirect("front.do");
		}

		return "/jsp/result";
	}
	
	@RequestMapping("/user/register")
	public ModelAndView register(User user){
		ModelAndView mav = new ModelAndView();
		if(userService.register(user)){
			mav.setViewName("register_succ");
			return mav;
		}
		else{
			mav.setViewName("register");
			mav.addObject("errorMsg","用户名已被占用，请更换！！");
			return mav;
		}
	}
}