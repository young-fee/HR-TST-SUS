package exceptionInfo;
/**
 * 自定义项目的业务异常类：继承 RuntimeException 
 * @author Young
 *
 */
public class BusinessException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public BusinessException(Object Obj) {
        super(Obj.toString());
    }
    
}