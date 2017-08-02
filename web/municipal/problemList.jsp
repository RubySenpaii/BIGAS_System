<%-- 
    Document   : farmList
    Created on : Dec 26, 2016, 10:15:16 PM
    Author     : RubySenpaii
--%>

<%@page import="object.ProgramPlan"%>
<%@page import="object.Problem"%>
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

        <title>B.I.G.A.S. System | Municipal - Problem List</title>

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
                                <h3>Problem List</h3>
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
                                                    <th>Problem ID</th>
                                                    <th>Problem Name</th>
                                                    <th>Type</th>
                                                    <th>Description</th>
                                                    <th>Programs</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    ArrayList<Problem> problems = (ArrayList<Problem>) session.getAttribute("problems");
                                                    ArrayList<ProgramPlan> programPlans = (ArrayList<ProgramPlan>) session.getAttribute("programs");
                                                    for (int a = 1; a < problems.size(); a++) {
                                                %>
                                                <tr>
                                                    <td><%=problems.get(a).getProblemID()%></td>
                                                    <td><%=problems.get(a).getProblemName()%></td>
                                                    <td><%=problems.get(a).getType()%></td>
                                                    <td><%=problems.get(a).getDescription()%></td>
                                                    <td>
                                                        <%
                                                            for (int b = 0; b < programPlans.size(); b++) {
                                                                if (programPlans.get(b).getProblemID() == problems.get(a).getProblemID()) {
                                                        %>
                                                        <%=programPlans.get(b).getProgramName()%><br>
                                                        <%
                                                                }
                                                            }
                                                        %>
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
