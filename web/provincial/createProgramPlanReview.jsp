<%-- 
    Document   : createProgramPlanReview
    Created on : Mar 14, 2017, 2:58:12 AM
    Author     : RubySenpaii
--%>

<%@page import="object.ProgramProcedure"%>
<%@page import="object.ProgramTrigger"%>
<%@page import="java.util.ArrayList"%>
<%@page import="object.ProgramPlan"%>
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
                                <h3>Program Detail</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <br/>
                                            <div class="col-md-9">
                                                <%
                                                    ProgramPlan plan = (ProgramPlan) session.getAttribute("newProgramPlan");
                                                %>
                                                <p style="font-size: medium"><b>Program Name: </b> <%=plan.getProgramName()%></p>
                                                <br/>
                                                <p style="font-size: medium"><b>Program Type </b> <%=plan.getType()%></p>
                                                <br/>
                                                <p style="font-size: medium"><b>Description: </b> <%=plan.getDescription()%></p>
                                            </div>
                                            <div class="col-md-3">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 60%">Trigger Name</th>
                                                            <th style="width: 40%">Trigger Value</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<ProgramTrigger> triggers = (ArrayList<ProgramTrigger>) session.getAttribute("newTriggers");
                                                            for (int a = 0; a < triggers.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=triggers.get(a).getTriggerName()%></td>
                                                            <td><%=triggers.get(a).getTriggerValue()%></td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                        <div class="ln_solid"></div>

                                        <div class="row">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 15%">Procedure No</th>
                                                            <th style="width: 15%">Phase</th>
                                                            <th>Activity</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<ProgramProcedure> procedures = (ArrayList<ProgramProcedure>) session.getAttribute("newProcedures");
                                                            for (int a = 0; a < procedures.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=procedures.get(a).getProcedureNo()%></td>
                                                            <td><%=procedures.get(a).getPhase()%></td>
                                                            <td><%=procedures.get(a).getActivity()%></td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>

                                                <div class="text-center">
                                                    <a href="/BIGAS_System/ProvincialProgram?action=submitProgramDetail" class="btn btn-success">Submit</a>
                                                </div>
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
