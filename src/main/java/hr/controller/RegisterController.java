package hr.controller;

import hr.model.Sys_user;
import hr.service.UserServiceI;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用于注册普通用户，以便登录
 * @author Young
 *
 */
@Controller
public class RegisterController {
	
	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
/**
 * 使用md5盐值加密用于注册用户信息，盐即为用户Id;用户id与用户名相同
 * @param user
 * @return
 */
	@RequestMapping(value = "registerUser",method = RequestMethod.POST)
	public String registerUser(Sys_user user){
		
		//加密密码
		String password = new SimpleHash("MD5", user.getPassword(), user.getUserName(), 1024).toString();
		user.setPassword(password);
		userService.AddSys_User(user);
		return "/jsp/user/registerSuccess";
	}
	
	/**
	 * 用户ajax检验用户名是否已经存在
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
	@ResponseBody
	public int checkUserName(String userName){
		//查询用户名是否重复
		//o即表示已经存在该用户名，不能注册，1表示该用户名不存在，可以注册
		List<Sys_user> list = userService.getUserByNameList(userName);
		if(list!=null && !list.isEmpty()){
			return 0;
		}else{
			return 1;
		}
	}
}
