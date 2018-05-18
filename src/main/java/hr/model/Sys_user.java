package hr.model;

import java.util.List;

public class Sys_user {
    private String userLoginId;

    private String userName;

    private String sex;

    private String password;
    
    private List<Sys_user_roleKey> userRoles;

    public List<Sys_user_roleKey> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Sys_user_roleKey> userRoles) {
		this.userRoles = userRoles;
	}

	public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId == null ? null : userLoginId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}