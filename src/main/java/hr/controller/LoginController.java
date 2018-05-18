package hr.controller;

import hr.service.UserServiceI;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 登录认证
 * @author Young
 *
 */
@Controller
public class LoginController {

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	/**
	 * 登陆控制
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,String userName,String password) {
		
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
	    try{
	    	//调用subject.login(token)进行登录，会自动委托给securityManager,调用之前  
            subject.login(token);//会跳到我们自定义的realm中 
            return "/jsp/index/index";
	    }catch(Exception e){
			e.printStackTrace();
			System.out.println("用户名或密码错误");
			//request.setAttribute("error", "用户名或密码错误");
			return "/jsp/login/loginError";
	    }
	}
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "/jsp/login/WHPU_login";
	}
}
