<%-- 
    Document   : pageHeader
    Created on : Dec 22, 2016, 10:06:41 PM
    Author     : RubySenpaii
--%>

<%@page import="object.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- top navigation -->
<div class="top_nav">
    <div class="nav_menu">
        <nav class="" role="navigation">
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <%
                            Employee employee = (Employee) session.getAttribute("userLogged");
                        %>
                        <%=employee.getLastName() + ", " + employee.getFirstName()%>
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="/BIGAS_System/WebLogout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>
<!-- /top navigation -->