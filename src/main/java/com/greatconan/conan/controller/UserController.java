package com.greatconan.conan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greatconan.conan.model.User;
import com.greatconan.conan.service.UserService;
import com.greatconan.conan.util.BeanUtil;


@Controller
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/loginView")
	public String loginView(){
		return "login";
	}
	
	@RequestMapping(value = "/main/{req}.do")
	public ModelAndView registerView(@PathVariable("req") String reqstr,HttpServletRequest req,
			HttpServletResponse res){
		logger.info("---start----/main-"+reqstr+"-in");
		
		String code=req.getParameter("code");
		List actualResult=new ArrayList();
//		actualResult.add("21");
		boolean stringflag=BeanUtil.compare("upload.ftl", actualResult);
		logger.info(stringflag);
		ModelAndView mav = new ModelAndView();
		User user =new User();
		user.setUserName("张三");
		mav.setViewName("login");
		mav.addObject("Msg","qwrert");
		mav.addObject("code",code);
		mav.addObject("test_user",user);
		logger.info("---end----/main-"+reqstr+"-in");
		return mav;
	}
	
	@RequestMapping("/user/login")
	public ModelAndView login(User user){
		ModelAndView mav = new ModelAndView();
		user=new User();
		user.setUserName("zyy");
		user.setPassword("12323");
		User u = userService.loginCheck(user);
//		logger.info(u.getPassword());
		if(null==u){
			mav.setViewName("login");
			mav.addObject("Msg","用户名或密码有误！");
			return mav;
		}
		else{
//			mav.setViewName("success");
			mav.setViewName("login");
			mav.addObject("user", u);
			return mav;
		}
	}
	
	@RequestMapping("/user/a")
	public ModelAndView a(User user){
		ModelAndView mav = new ModelAndView();
//			mav.setViewName("success");
		user=new User();
		user.setUserName("zyy");
		user.setPassword("12323");
		user.setId(1);
		userService.test(user);
			mav.setViewName("login");
			mav.addObject("Msg", "----aa");
			return mav;
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