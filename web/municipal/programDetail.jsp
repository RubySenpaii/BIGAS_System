<%-- 
    Document   : programDetail
    Created on : Feb 7, 2017, 4:08:39 PM
    Author     : RubySenpaii
--%>

<%@page import="object.DeployedProgram"%>
<%@page import="object.ProgramProcedure"%>
<%@page import="object.ProgramPlan"%>
<%@page import="object.ProgramTrigger"%>
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

        <title>B.I.G.A.S. System | Municipal - Program Details </title>

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
                                                    ProgramPlan plan = (ProgramPlan) session.getAttribute("programPlan");
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
                                                            ArrayList<ProgramTrigger> triggers = (ArrayList<ProgramTrigger>) session.getAttribute("triggers");
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
                                                <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                                    <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                                        <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Program Detail</a>
                                                        </li>
                                                        <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Deployed List</a>
                                                        </li>
                                                        <li role="presentation" class=""><a href="#tab_content3" role="tab" id="additional-tab" data-toggle="tab" aria-expanded="false">Program Target</a>
                                                        </li>
                                                    </ul>
                                                    <div id="myTabContent" class="tab-content">
                                                        <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
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
                                                                        ArrayList<ProgramProcedure> procedures = (ArrayList<ProgramProcedure>) session.getAttribute("procedures");
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
                                                        </div>
                                                        <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
                                                            <table class="table table-bordered">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="width: 10%">Deployed ID</th>
                                                                        <th style="width: 15%">Municipality</th>
                                                                        <th style="width: 10%">Date Started</th>
                                                                        <th style="width: 10%">Status</th>
                                                                        <th style="width: 8%">Participants</th>
                                                                        <th style="width: 14%">Seed Provision</th>
                                                                        <th style="width: 14%">Fertilizer Provision</th>
                                                                        <th style="width: 8%">Effectivity Rating</th>
                                                                        <th style="width: 7%"></th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%
                                                                        ArrayList<DeployedProgram> deployedPrograms = (ArrayList<DeployedProgram>) session.getAttribute("deployedPrograms");
                                                                        for (int a = 0; a < deployedPrograms.size(); a++) {
                                                                            String seedProvisisions = "N/A", fertilizerProvisions = "N/A";
                                                                            if (!deployedPrograms.get(a).getVarietyProvided().equals("N/A")) {
                                                                                seedProvisisions = deployedPrograms.get(a).getVarietyProvided() + " " + deployedPrograms.get(a).getVarietyAmount();
                                                                            }
                                                                            if (!deployedPrograms.get(a).getFertilizerProvided().equals("N/A")) {
                                                                                fertilizerProvisions = deployedPrograms.get(a).getFertilizerProvided() + " " + deployedPrograms.get(a).getFertilizerAmount();
                                                                            }
                                                                    %>
                                                                    <tr>
                                                                        <td><%=deployedPrograms.get(a).getDeployedID()%></td>
                                                                        <td><%=deployedPrograms.get(a).getMunicipalName()%></td>
                                                                        <td><%=deployedPrograms.get(a).getDateStarted()%></td>
                                                                        <td><%=deployedPrograms.get(a).getStatus()%></td>
                                                                        <td><%=deployedPrograms.get(a).getFarmCount()%></td>
                                                                        <td><%=seedProvisisions%></td>
                                                                        <td><%=fertilizerProvisions%></td>
                                                                        <td><%=deployedPrograms.get(a).getEffectivityResult()%></td>
                                                                        <td>
                                                                            <a href="/BIGAS_System/MunicipalProgram?action=viewDeployedProgramDetails&deployedID=<%=deployedPrograms.get(a).getDeployedID()%>" class="btn btn-success">
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

                                                        <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="additional-tab">
                                                            <table class="table table-bordered">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="width: 30%">Target Name</th>
                                                                        <th style="width: 10%">Year</th>
                                                                        <th style="width: 20%">Actual Value</th>
                                                                        <th style="width: 20%">Target Value</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr></tr>
                                                                </tbody>
                                                            </table>


                                                            <div class="text-center">
                                                                <a href="" class="btn btn-success">
                                                                    Add New Target
                                                                </a>
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
