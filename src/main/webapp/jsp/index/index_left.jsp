<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.context.SecurityContext" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <span><img alt="image" class="img-circle" src="${contextPath}/hfr/img/profile_small.jpg" /></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold"><sec:authentication property="name"/></strong></span>

                                    <span class="text-muted text-xs block">
                                      	
                                        <b class="caret"></b>
                                    </span>
                                </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" href="${contextPath}/jsp/userInfo.jsp">当前用户信息</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="logout">安全退出</a>
                        </li>
                    </ul>
                </div>
                <div class="logo-element">H+
                </div>
            </li>

            <!--上下分界线-->

            <li>
                <a  href="#">
                    <i class="fa fa-columns"></i>
                    <span class="nav-label">组织架构</span>
                    <span class="fa arrow"></span>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="showAllEmployeeView"><i class="fa fa-home"></i><span class="nav-label">员工管理</span></a></li>
                        <li><a class="J_menuItem" href="showAllDepartmentView"><i class="fa fa-columns"></i> <span class="nav-label">部门管理</span></a></li>
                    </ul>
                </a>
            </li>

            <li>
                <a  href="#">
                    <i class="fa fa-columns"></i>
                    <span class="nav-label">系统管理</span>
                    <span class="fa arrow"></span>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="showAllPermissionView" data-index="0">权限管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="showAllRoleView">角色管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="showAllUserView">用户管理</a>
                        </li>
                    </ul>
                </a>
            </li>
        </ul>
    </div>
</nav>