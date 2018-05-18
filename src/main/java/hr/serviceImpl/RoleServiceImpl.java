package hr.serviceImpl;

import hr.dao.Sys_roleMapper;
import hr.dao.Sys_role_permissionMapper;
import hr.dao.Sys_user_roleMapper;
import hr.model.Sys_role;
import hr.service.RoleServiceI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author Young
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleServiceI{

	private Sys_roleMapper roleMapper;
	private Sys_role_permissionMapper rolePermissionMapper;
	private Sys_user_roleMapper userRoleMapper;
	
	public Sys_user_roleMapper getUserRoleMapper() {
		return userRoleMapper;
	}
	@Autowired
	public void setUserRoleMapper(Sys_user_roleMapper userRoleMapper) {
		this.userRoleMapper = userRoleMapper;
	}
	public Sys_role_permissionMapper getRolePermissionMapper() {
		return rolePermissionMapper;
	}
	@Autowired
	public void setRolePermissionMapper(
			Sys_role_permissionMapper rolePermissionMapper) {
		this.rolePermissionMapper = rolePermissionMapper;
	}
	public Sys_roleMapper getRoleMapper() {
		return roleMapper;
	}
	@Autowired
	public void setRoleMapper(Sys_roleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
	
	/**
	 * 用于通过关键字查询角色列表
	 */
	@Override
	public List<Sys_role> showAllRole(String keyword) {
		if(keyword == null){
			keyword = "";
		}
		List<Sys_role> list = roleMapper.showAllRole(keyword);
		return list;
	}
	/**
	 * 用于删除角色
	 */
	@Override
	public int delRole(String roleId) {
		return roleMapper.deleteByPrimaryKey(roleId);
	}
	/**
	 * 用于修改角色
	 */
	@Override
	public int updateRole(Sys_role role) {
		return roleMapper.updateByPrimaryKey(role);
	}
	/**
	 * 用于添加角色
	 */
	@Override
	public int addRole(Sys_role role) {
		return roleMapper.insert(role);
	}
	/**
	 * 用于删除角色所拥有的权限
	 */
	@Override
	public int delRolePermission(String roleId, String permissionId) {
		return rolePermissionMapper.deleteByPrimaryKey(roleId,permissionId);
	}
	/**
	 * 用于添加权限到角色
	 */
	@Override
	public int addPermissionToRole(String roleId, String permissionId) {
		return rolePermissionMapper.insert(roleId, permissionId);
	}
	/**
	 * 用于查出用户对应的角色表
	 */
	@Override
	public List<Sys_role> findUserRole(String userLoginId) {
		return roleMapper.findUserRole(userLoginId);
	}
	/**
	 * 用于查询出该用户未有得其他角色
	 */
	@Override
	public List<Sys_role> getAllRoleWithoutUser(String userLoginId) {
		return roleMapper.getAllRoleWithoutUser(userLoginId);
	}
	/**
	 * 用于添加角色到该用户
	 */
	@Override
	public int addRoleToUser(String userLoginId, String roleId) {
		return userRoleMapper.insert(userLoginId,roleId);
	}

}
