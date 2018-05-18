package exceptionInfo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 自定义异常处理类：实现 HandlerExceptionResolver  这个类是用来处理异常发生时做出的逻辑处理，和spring框架集成：
 * @author Young
 *
 */
public class MySimpleMappingExceptionResolver implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object object, Exception exception) {
        // 判断是否ajax请求
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果不是ajax，JSP格式返回
            // 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            if (exception instanceof BusinessException) {
                map.put("errorMsg", exception.getMessage());
                return new ModelAndView("/jsp/error", map);
            } 
            else if(exception instanceof UnauthorizedException){
            	map.put("errorMsg", "没有权限！");
            	 return new ModelAndView("/jsp/403", map);
            }            
            else {
                map.put("errorMsg", exception.getMessage());
                return new ModelAndView("/jsp/error", map);
            }
        } else {//如果是ajax请求
            response.setContentType("application/json;charset=UTF-8");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			// 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
			if (exception instanceof BusinessException) {
			    map.put("errorMsg", exception.getMessage());
			} else {
			    map.put("errorMsg", "系统异常！");
			}
        }
        return null;
    }

}