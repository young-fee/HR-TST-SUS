package hr.serviceImpl;

import hr.dao.Sys_permissionMapper;
import hr.dao.Sys_roleMapper;
import hr.dao.Sys_role_permissionMapper;
import hr.dao.Sys_userMapper;
import hr.dao.Sys_user_roleMapper;
import hr.model.Sys_permission;
import hr.model.Sys_role;
import hr.model.Sys_user;
import hr.service.UserServiceI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private Sys_userMapper userMapper;
	private Sys_roleMapper roleMapper;
	private Sys_permissionMapper permissionMapper;
	public Sys_permissionMapper getPermissionMapper() {
		return permissionMapper;
	}
	@Autowired
	public void setPermissionMapper(Sys_permissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}
	public Sys_roleMapper getRoleMapper() {
		return roleMapper;
	}
	@Autowired
	public void setRoleMapper(Sys_roleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	private Sys_user_roleMapper userRoleMapper;
	private Sys_role_permissionMapper rolePermissionMapper;


	public Sys_role_permissionMapper getRolePermissionMapper() {
		return rolePermissionMapper;
	}
	@Autowired
	public void setRolePermissionMapper(
			Sys_role_permissionMapper rolePermissionMapper) {
		this.rolePermissionMapper = rolePermissionMapper;
	}

	public Sys_user_roleMapper getUserRoleMapper() {
		return userRoleMapper;
	}
	
	@Autowired
	public void setUserRoleMapper(Sys_user_roleMapper userRoleMapper) {
		this.userRoleMapper = userRoleMapper;
	}

	public Sys_userMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(Sys_userMapper userMapper) {
		this.userMapper = userMapper;
	}
	/**
	 * 用于通过主键查询用户
	 */
	@Override
	public Sys_user getSys_userById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}
	/**
	 * 用于添加一个用户
	 */
	@Override
	public Sys_user AddSys_User(Sys_user user) {
		userMapper.insert(user);
		return user;
	}
	/**
	 * 用于通过关键字查询相应用户
	 */
	@Override
	public List<Sys_user> findAllUsers(String keyword) {
		if (keyword == null) {
			keyword = "";
		}
		return userMapper.findAllUsers(keyword);
	}
	/**
	 * 用于删除用户
	 */
	@Override
	public int deleteUserById(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 用于修改用户
	 */
	@Override
	public int updateUserById(Sys_user user) {
		return userMapper.updateByPrimaryKey(user);
	}
	/**
	 * 用于登录认证时，通过用户输入的用户名查询是否存在用户
	 */
	@Override
	public Sys_user getUserByName(String userName) {
		Sys_user user = userMapper.getUserByName(userName);
		return user;
	}
	/**
	 * 用于删除用户所有的角色
	 */
	@Override
	public int delUserRole(String userLoginId, String roleId) {
		return userRoleMapper.deleteByPrimaryKey(userLoginId, roleId);
	}
	/**
	 * 用于授权时通过用户名查询该用户所有的角色
	 */
	@Override
	public Set<Sys_role> getRoles(String userName) {
		Set<Sys_role> set = new HashSet<Sys_role>();
		set = roleMapper.getRoles(userName);
		return set;
	}
	/**
	 * 用于授权时通过用户名查询该用户所有的权限
	 */
	@Override
	public Set<Sys_permission> getPermissions(String userName) {
		Set<Sys_permission> set = new HashSet<Sys_permission>();
		set = permissionMapper.getPermissions(userName);
		return set;
	}
	/**
	 * 查询用户名是否已经存在
	 */
	@Override
	public List<Sys_user> getUserByNameList(String userName) {
		return userMapper.getUserByNameList(userName);
	}

}
