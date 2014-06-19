package com.avihs.movie.web.user.login.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avihs.movie.business.user.model.User;
import com.avihs.movie.business.user.service.UserService;
import com.avihs.movie.web.user.login.form.UserLoginForm;
import com.avihs.movie.web.util.Constants;

@Controller
@RequestMapping("userLogin")
public class UserLoginController {

	private static final String LOGIN_PAGE = "userLogin";

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String load(Model model) {
		model.addAttribute("userLoginForm", new UserLoginForm());
		return LOGIN_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@Valid UserLoginForm userLoginForm,
			BindingResult bindingResult, Model model, HttpSession session) {
		User user = userService.getUser(userLoginForm.getUserId(),
				userLoginForm.getPwd());
		if (user != null) {
			session.setAttribute(Constants.LOGGED_IN_USER, user);
			return "redirect:" + Constants.HOME_PAGE;
		} else {
			model.addAttribute("msg", "Invalid UserId/Password");
			return LOGIN_PAGE;
		}
	}

	@RequestMapping(method = RequestMethod.GET, params = "logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute(Constants.LOGGED_IN_USER);
		session.setAttribute(Constants.LOGGED_IN_USER, null);
		session.invalidate();
		return "redirect:" + LOGIN_PAGE;
	}
}
