<%-- 
    Document   : recommendProgram
    Created on : Jan 1, 2017, 12:27:04 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.ImportantProblem"%>
<%@page import="extra.Formatter"%>
<%@page import="object.ProgramPlan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="object.DamageIncident"%>
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
                                <h3>Programs Recommended</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="x_panel">
                                                <div class="col-md-12">
                                                    <%
                                                        Formatter formatter = new Formatter();
                                                        ImportantProblem importantProblem = (ImportantProblem) session.getAttribute("importantProblem");
                                                    %>
                                                    <div class="col-md-6">
                                                        <p style="font-size: medium">
                                                            <strong>Problem Name:</strong>
                                                            <%=importantProblem.getProblem().getProblemName()%>
                                                        </p>
                                                        <p style="font-size: medium">
                                                            <strong>Description:</strong>
                                                            <%=importantProblem.getProblem().getDescription()%>
                                                        </p>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <p style="font-size: medium">
                                                            <strong>Total Farms Affected:</strong>
                                                            <%=importantProblem.getFarms().size()%>
                                                        </p>
                                                        <p style="font-size: medium">
                                                            <strong>Total Minor Damaged (Major) :</strong>
                                                            <%=formatter.doubleToString(importantProblem.getTotalMinor()) + " ha (" + formatter.doubleToString(importantProblem.getTotalMajor()) + " ha)"%>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 20%">Program Name</th>
                                                            <th style="width: 10%">Program Type</th>
                                                            <!--th style="width: 20%">Recommended Min. Farms Affected</th>
                                                            <th style="width: 20%">Recommended Min. Area Affected</th-->
                                                            <th style="width: 40%">Program Requirements</th>
                                                            <th style="width: 15%">Success Rate</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<ProgramPlan> programPlans = (ArrayList<ProgramPlan>) session.getAttribute("programPlans");
                                                            for (int a = 0; a < programPlans.size(); a++) {
                                                                String requirements = programPlans.get(a).getProgramTrigger();
                                                                if (requirements == null) {
                                                                    requirements = "no requirements";
                                                                }
                                                        %>
                                                        <tr>
                                                            <td><%=programPlans.get(a).getProgramName()%></td>
                                                            <td><%=programPlans.get(a).getType()%></td>
                                                            <!--td><%=programPlans.get(a).getProgramTriggerFarmCount()%></td>
                                                            <td><%=programPlans.get(a).getProgramTriggerFarmArea()%></td-->
                                                            <td><%=requirements%></td>
                                                            <td><%=programPlans.get(a).getEffectivityStatus()%></td>
                                                            <td>
                                                                <a href="/BIGAS_System/ProvincialProgram?action=goToProgramDeployment&programID=<%=programPlans.get(a).getProgramPlanID()%>" class="btn btn-success">Deploy Program</a>
                                                            </td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>

                                                <div class="col-md-12 col-md-offset-4">
                                                    <a href="" class="btn btn-success">Create Program</a>
                                                </div>
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
