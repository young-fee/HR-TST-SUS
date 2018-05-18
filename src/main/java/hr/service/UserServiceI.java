package hr.service;

import hr.model.Sys_permission;
import hr.model.Sys_role;
import hr.model.Sys_user;

import java.util.List;
import java.util.Set;

public interface UserServiceI {
	
	public Sys_user getSys_userById(String id);
	
	public Sys_user AddSys_User(Sys_user user);

	public List<Sys_user> findAllUsers(String keyword);
	
	public int deleteUserById(String id);
	
	public int updateUserById(Sys_user user);
	
	public Sys_user getUserByName(String userName);
	
	public List<Sys_user> getUserByNameList(String userName);
	
	public int delUserRole(String userLoginId,String roleId);
	
	public Set<Sys_role> getRoles(String userName);
	
	public Set<Sys_permission> getPermissions(String userName);

}
