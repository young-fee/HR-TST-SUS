package hr.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.dao.Sys_permissionMapper;
import hr.model.Sys_permission;
import hr.service.PermissionServiceI;

/**
 * 
 * @author Young
 *
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionServiceI {
	
	private Sys_permissionMapper permissionMapper;

	public Sys_permissionMapper getPermissionMapper() {
		return permissionMapper;
	}

	@Autowired
	public void setPermissionMapper(Sys_permissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}
	/**
	 * 用于通过关键字查询权限列表
	 */
	@Override
	public List<Sys_permission> showAllPermission(String keyword) {
		if(keyword == null){
			keyword = "";
		}
		return permissionMapper.showAllPermission(keyword);
	}
	/**
	 * 用于添加权限
	 */
	@Override
	public int addPermission(Sys_permission permission) {
		return permissionMapper.insertSelective(permission);

	}
	/**
	 * 用于删除权限
	 */
	@Override
	public int delPermission(String permissionId) {
		return permissionMapper.deleteByPrimaryKey(permissionId);
	}
	/**
	 * 用于修改权限
	 */
	@Override
	public int updatePermissionById(Sys_permission permission) {
		return permissionMapper.updateByPrimaryKey(permission);
	}
	/**
	 * 用于通过角色ID查找该角色的权限
	 */
	@Override
	public List<Sys_permission> findRolePermission(String roleId) {
		return permissionMapper.findRolePermission(roleId);
	}
	/**
	 * 用于查询出该角色未分配的其他权限
	 */
	@Override
	public List<Sys_permission> getAllPermissionWithoutRole(String roleId) {
		return permissionMapper.getAllPermissionWithoutRole(roleId);
	}
}
