<%-- 
    Document   : pageHeader
    Created on : Dec 22, 2016, 10:06:41 PM
    Author     : RubySenpaii
--%>

<%@page import="object.Employee"%>
<%@page import="object.Notification"%>
<%@page import="java.util.ArrayList"%>
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

                <%
                    ArrayList<Notification> notifications = (ArrayList<Notification>) session.getAttribute("notifications");
                %>
                <li role="presentation" class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                        <i class="fa fa-envelope-o"></i>
                        <span class="badge bg-green"><%=notifications.size()%></span>
                    </a>
                    <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                        <%
                            for (int a = 0; a < notifications.size(); a++) {
                        %>
                        <li>
                            <a>
                                <span>
                                    <span><b>[<%=notifications.get(a).getType().toUpperCase()%>]</b> Provided by John Smith</span>
                                </span>
                                <span class="message">
                                    <%=notifications.get(a).getMessage()%>
                                </span>
                            </a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>
<!-- /top navigation -->