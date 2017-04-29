<%-- 
    Document   : technicianAssignment
    Created on : Jan 22, 2017, 1:18:47 PM
    Author     : RubySenpaii
--%>

<%@page import="object.Farm"%>
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
                                <h3>Technician Assignment</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-8 col-md-offset-2">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 5%"></th>
                                                            <th style="width: 15%">Farm Name</th>
                                                            <th>Farm Address</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<Farm> selectedTechnicianFarms = (ArrayList<Farm>) session.getAttribute("selectedTechnicianFarms");
                                                            for (int a = 0; a < selectedTechnicianFarms.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><input type="checkbox" checked name="farmID" value="<%=selectedTechnicianFarms.get(a).getFarmID()%>"</td>
                                                            <td><%=selectedTechnicianFarms.get(a).getFarmName()%></td>
                                                            <td><%=selectedTechnicianFarms.get(a).getAddress()%></td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                        <%
                                                            ArrayList<Farm> unassignedFarms = (ArrayList<Farm>) session.getAttribute("unassignedFarms");
                                                            for (int a = 0; a < unassignedFarms.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><input type="checkbox" name="farmID" value="<%=unassignedFarms.get(a).getFarmID()%>"></td>
                                                            <td><%=unassignedFarms.get(a).getFarmName()%></td>
                                                            <td><%=unassignedFarms.get(a).getAddress()%></td>
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
