package filter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Service;
/**
 * 如果不使用shiro的logout
 * 自定义logout过滤器
 * @author Young
 *
 */
@Service
public class SystemLogoutFilter extends LogoutFilter{

	 protected boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	        //在这里执行退出系统前需要清空的数据  
	        Subject subject=getSubject(request,response);  
	        String redirectUrl=getRedirectUrl(request,response,subject);  
	        ServletContext context= request.getServletContext();  
	        try {  
	            subject.logout();  
	            context.removeAttribute("error");  
	        }catch (SessionException e){  
	            e.printStackTrace();  
	        }  
	        issueRedirect(request,response,redirectUrl);  
	        return false;  
	    }  
}
