package hr.model;

public class Sys_user_roleKey {
    private String userLoginId;

    private String roleId;
    
    private Sys_role role;

    public Sys_role getRole() {
		return role;
	}

	public void setRole(Sys_role role) {
		this.role = role;
	}

	public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId == null ? null : userLoginId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}