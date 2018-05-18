package shiro;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import hr.model.Sys_permission;
import hr.model.Sys_role;
import hr.model.Sys_user;
import hr.service.UserServiceI;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 * @author Young
 *
 */
public class MyRealm extends AuthorizingRealm {
	/**
	 * 使用 AuthenticatingRealm 的子类 AuthorizingRealm， 该类继承了 AuthenticatingRealm，
	 * 所以实际上我们只要继承 AuthorizingRealm，然后实现它的抽象方法就行了。 同时搞定 登录认证 和 权限认证(访问控制)：
	 * doGetAuthorizationInfo 方法，会在权限认证也就是访问控制时，被回调， 而 doGetAuthenticationInfo
	 * 方法会在登录认证时被回调，返回的 AuthenticationInfo类型的对象， 会和用户登录时输入的
	 * 用户名和密码(加密之后的)进行比较，相同则登录成功，反之则登录失败。
	 */

	@Autowired
	private UserServiceI userService;

	/**
	 * 用户授权，根据用户名在数据库中查找其角色
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();// 获取用户名
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//new一个SimpleAuthorizationInfo对象
		  
		Set<Sys_role> roleSet = userService.getRoles(userName);
		Set<Sys_permission> permissionSet = userService.getPermissions(userName);
		//角色名的集合  
	    Set<String> roles = new HashSet<String>();  
	    //权限名的集合  
	    Set<String> permissions = new HashSet<String>();  
	    
	    //迭代遍历角色
		Iterator<Sys_role> it = roleSet.iterator();
		while(it.hasNext()){
			roles.add(it.next().getRoleId());
		}
	    
		 //迭代遍历权限
		Iterator<Sys_permission> it2 = permissionSet.iterator();
		while(it2.hasNext()){
			permissions.add(it2.next().getPermissionId());
		}
	    
		authorizationInfo.addRoles(roles);//添加角色
		authorizationInfo.addStringPermissions(permissions);//添加权限
		
		System.out.println(authorizationInfo.getRoles()+"-------------"+authorizationInfo.getStringPermissions());
		return authorizationInfo;
	}

	/**
	 * 用于登录认证，匹配数据库的信息.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

	
		String userName = (String) token.getPrincipal();// 获取用户名
		
		//前台需要的值
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("userName", userName);
		
		// 根据用户输入的用户名在数据库进行匹配
		Sys_user user = userService.getUserByName(userName);
		//用户名存在则匹配密码
		if (user != null) {
			 //1)principal：认证的实体信息，可以是userName，也可以是数据库表对应的用户的实体对象  
            Object principal = user.getUserName();
            
            //2)credentials：数据库中的密码  
            Object credentials = user.getPassword(); 
            
            //3)realmName：当前realm对象的name，调用父类的getName()方法即可  
            String realmName = getName();  
            
            //4)credentialsSalt盐值  
            ByteSource credentialsSalt = ByteSource.Util.bytes(userName);//使用用户名作为盐值  
            
            //根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
            //5)与数据库中用户名和密码进行比对，密码盐值加密，第4个参数传入realName。
            SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(principal, credentials,credentialsSalt,realmName);
			return authcInfo;
		} else {
			return null;
		}
	}
	
	/**
	 * 自定义清除缓存，在其他代码中调用：先注入reaml，在执行clearCached方法
	 */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}