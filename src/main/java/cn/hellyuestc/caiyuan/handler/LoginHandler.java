package cn.hellyuestc.caiyuan.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hellyuestc.caiyuan.entity.User;
import cn.hellyuestc.caiyuan.service.LoginService;

@Controller
public class LoginHandler {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String goLogin() {
		return "login";
	}
	
	@RequestMapping(value="/validateLogin", method=RequestMethod.POST)
	public String validateLogin(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {
		User currentUser = null;
		if (null != (currentUser = loginService.validateLoginByPhone(account, password))) {
			session.setAttribute("currentUser", currentUser);
			return "redirect:/";
		} else if (null != (currentUser = loginService.validateLoginByEmail(account, password))) {
			session.setAttribute("currentUser", currentUser);
			return "redirect:/";
		} else if (null != (currentUser = loginService.validateLoginByName(account, password))) {
			session.setAttribute("currentUser", currentUser);
			return "redirect:/";
		} else {
			return "redirect:/login";
		}
	}

}
