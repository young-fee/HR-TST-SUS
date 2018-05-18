package hr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author Young
 *
 */
@Controller
public class BaseController {

	@RequestMapping("hr")
	public String hr(){
		
		return "jsp/index/index";
	}

	@RequestMapping("welcome")	
	public String welcome(){
		
		return "jsp/welcome/welcome";
	}
}
