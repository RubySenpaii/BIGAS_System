<%-- 
    Document   : assignTechnician
    Created on : Dec 28, 2016, 10:04:20 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.GenericObject"%>
<%@page import="object.Employee"%>
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
                                <h3>Technician List</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-2 col-sm-2 col-xs-12 col-md-offset-5">
                                                <div class="tile-stats">
                                                    <div class="count"><%=((ArrayList<GenericObject>) session.getAttribute("unassignedFarms")).size()%></div>
                                                    <h3>Unassigned Farms</h3>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <!-- technician list -->
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 20%">Technician</th>
                                                            <th style="width: 15%">No of Farms Assigned</th>
                                                            <th style="width: 55%">Assigned Farms</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<GenericObject> technicianFarms = (ArrayList<GenericObject>) session.getAttribute("technicianFarms");
                                                            for (int a = 0; a < technicianFarms.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=technicianFarms.get(a).getAttribute1()%></td>
                                                            <td><%=technicianFarms.get(a).getAttribute2()%></td>
                                                            <td><%=technicianFarms.get(a).getAttribute3()%></td>
                                                            <td>
                                                                <form action="MunicipalFarmAssignment">
                                                                    <input type="hidden" name="technicianID" value="<%=technicianFarms.get(a).getAttribute4()%>">
                                                                    <button class="btn btn-success" type="submit" name="action" value="view">
                                                                        <i class="fa fa-eye"></i>
                                                                    </button>
                                                                    <button class="btn btn-warning" type="submit" name="action" value="editTechnicianAssignment">
                                                                        <i class="fa fa-edit"></i>
                                                                    </button>
                                                                    <button class="btn btn-danger" type="submit" name="action" value="flag">
                                                                        <i class="fa fa-flag"></i>
                                                                    </button>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /technician list-->
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
