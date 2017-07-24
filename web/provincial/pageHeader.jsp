<%-- 
    Document   : pageHeader
    Created on : Dec 22, 2016, 10:06:41 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.Formatter"%>
<%@page import="extra.ImportantProblem"%>
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
                    ArrayList<ImportantProblem> notifications = (ArrayList<ImportantProblem>) session.getAttribute("importantProblems");
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
                                    <span><b>[<%=notifications.get(a).getDamageType().toUpperCase()%> ON <%=notifications.get(a).getProblem().getType()%>]</b> System Alert</span>
                                </span>
                                <span class="message">
                                    <p><%=notifications.get(a).getProblem().getProblemName()%></p>
                                    <p>Minor Damaged: <%=notifications.get(a).getTotalMinor()%> (<%=new Formatter().doubleToString(notifications.get(a).getTotalMinor() / notifications.get(a).getPlantableArea() * 100)%>%)</p>
                                    <p>Major Damaged: <%=notifications.get(a).getTotalMajor()%> (<%=new Formatter().doubleToString(notifications.get(a).getTotalMajor() / notifications.get(a).getPlantableArea() * 100)%>%)</p>
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