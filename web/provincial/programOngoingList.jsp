<%-- 
    Document   : programOngoingList
    Created on : Jan 1, 2017, 11:52:02 AM
    Author     : RubySenpaii
--%>

<%@page import="object.DeployedProgram"%>
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

        <title>B.I.G.A.S. System | Provincial - Ongoing Programs </title>

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
                                <h3>Ongoing Program List</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th style="width: 8%">Deployed ID</th>
                                                    <th style="width: 25%">Program Name</th>
                                                    <th style="width: 12%">Program Type</th>
                                                    <th style="width: 15%">Status</th>
                                                    <th style="width: 15%">Date Started</th>
                                                    <th style="width: 15%">Municipal Deployed</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    ArrayList<DeployedProgram> ongoingDeployedPrograms = (ArrayList<DeployedProgram>) session.getAttribute("ongoingPrograms");
                                                    for (int a = 0; a < ongoingDeployedPrograms.size(); a++) {
                                                %>
                                                <tr>
                                                    <td><%=ongoingDeployedPrograms.get(a).getDeployedID()%></td>
                                                    <td><%=ongoingDeployedPrograms.get(a).getProgramName()%></td>
                                                    <td><%=ongoingDeployedPrograms.get(a).getProgramType()%></td>
                                                    <td><%=ongoingDeployedPrograms.get(a).getStatus()%></td>
                                                    <td><%=ongoingDeployedPrograms.get(a).getDateStarted()%></td>
                                                    <td><%=ongoingDeployedPrograms.get(a).getMunicipalName()%></td>
                                                    <td>
                                                        <a href="/BIGAS_System/ProvincialProgram?action=viewDeployedProgramDetails&deployedID=<%=ongoingDeployedPrograms.get(a).getDeployedID()%>" class="btn btn-success">
                                                            View
                                                        </a>
                                                    </td>
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
