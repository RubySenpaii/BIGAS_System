<%-- 
    Document   : damageList
    Created on : Feb 20, 2017, 7:02:22 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.Formatter"%>
<%@page import="object.DamageIncident"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Gentallela Alela! | </title>

        <!-- Bootstrap -->
        <link href="/BIGAS_System/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="/BIGAS_System/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <!-- Custom Theme Style -->
        <link href="/BIGAS_System/build/css/custom.min.css" rel="stylesheet">
    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <jsp:include page="pageSidebar.jsp"/>
                <jsp:include page="pageHeader.jsp"/>

                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="">
                        <div class="page-title">
                            <div class="title_left">
                                <h3>Incident List</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 7%">Incident ID</th>
                                                            <th style="width: 17%">Problem Name</th>
                                                            <th style="width: 13%">Status</th>
                                                            <th style="width: 14%">Farm Name</th>
                                                            <th style="width: 11%">Minor Damaged</th>
                                                            <th style="width: 11%">Major Damaged</th>
                                                            <th style="width: 9%">Escalation Level</th>
                                                            <th style="width: 9%">Reported By</th>
                                                            <th style="width: 7%"></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            Formatter formatter = new Formatter();
                                                            ArrayList<DamageIncident> incidents = (ArrayList<DamageIncident>) session.getAttribute("incidents");
                                                            for (int a = 0; a < incidents.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=incidents.get(a).getDamageIncidentID()%></td>
                                                            <td><%=incidents.get(a).getProblemName()%></td>
                                                            <td><%=incidents.get(a).getStatus()%></td>
                                                            <td><%=incidents.get(a).getFarmName()%></td>
                                                            <td><%=formatter.doubleToString(incidents.get(a).getTotalAreaAffected())%> ha</td>
                                                            <td><%=formatter.doubleToString(incidents.get(a).getTotalAreaDamaged())%> ha</td>
                                                            <td><%=incidents.get(a).getEscalationLevel()%></td>
                                                            <td><%=incidents.get(a).getReportedName()%></td>
                                                            <td></td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /page content -->
                <jsp:include page="pageFooter.jsp"/>
            </div>
        </div>

        <!-- jQuery -->
        <script src="/BIGAS_System/vendors/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="/BIGAS_System/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="/BIGAS_System/vendors/fastclick/lib/fastclick.js"></script>
        <!-- NProgress -->
        <script src="/BIGAS_System/vendors/nprogress/nprogress.js"></script>

        <!-- Custom Theme Scripts -->
        <script src="/BIGAS_System/build/js/custom.min.js"></script>
    </body>
</html>
