<%-- 
    Document   : recommendProgram
    Created on : Jan 1, 2017, 12:27:04 PM
    Author     : RubySenpaii
--%>

<%@page import="object.Municipality"%>
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

        <title>B.I.G.A.S. System | Municipal - Programs Recommended </title>

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
                                <%
                                    Formatter formatter = new Formatter();
                                    ImportantProblem importantProblem = (ImportantProblem) session.getAttribute("importantProblem");
                                %>
                                <h3>Programs Recommended - Barangay <%=importantProblem.getBarangayName()%></h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="x_panel">
                                                <div class="x_content">
                                                    <div class="col-md-12">
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
                                                                <%=importantProblem.getFarmAffected()%> farms (<%=importantProblem.getPlantableArea()%> ha)
                                                            </p>
                                                            <p style="font-size: medium">
                                                                <strong>Total Minor Damaged (Major) :</strong>
                                                                <%=formatter.doubleToString(importantProblem.getTotalMinor()) + " ha (" + formatter.doubleToString(importantProblem.getTotalMajor()) + " ha)"%>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <br>
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 20%">Program Name</th>
                                                            <th style="width: 10%">Program Type</th>
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
                                                            <td><%=requirements%></td>
                                                            <td><%=programPlans.get(a).getEffectivityStatus()%></td>
                                                            <td>
                                                                <a href="/BIGAS_System/MunicipalProgram?action=goToProgramDeployment&programID=<%=programPlans.get(a).getProgramPlanID()%>" class="btn btn-success">Deploy Program</a>
                                                            </td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>

                                                <div class="col-md-8 col-md-offset-4">
                                                    <button type="button" class="btn btn-success" data-toggle="modal" data-target=".bs-example-modal-lg">Request Program</button>

                                            <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <form id="demo-form" data-parsley-validate class="MunicipalProgram">
                                                            <div class="modal-header">
                                                                <h4 class="modal-title" id="myModalLabel2">Program Request</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <label for="requestName">Request Name * :</label>
                                                                <input type="text" id="requestName" class="form-control" name="requestName" value="Request program for <%=importantProblem.getProblem().getProblemName()%>" required />
                                                                <label for="municipal">Municipality * :</label>
                                                                <select id="municipal" class="form-control" name="municipal" required disabled>
                                                                    <option><%=((Municipality) session.getAttribute("municipal")).getMunicipalityName()%></option>
                                                                </select>
                                                                <label for="problemName">Problem Name * :</label>
                                                                <input type="text" id="problemName" class="form-control" name="problemName" value="<%=importantProblem.getProblem().getProblemName()%>" required />
                                                                <label for="farmsAffected">Farms Affected * :</label>
                                                                <input type="number" id="farmsAffected" class="form-control" name="farmsAffected" value="<%=formatter.doubleToString(importantProblem.getFarmCount()/ importantProblem.getFarmCount()* 100)%>" required />
                                                                <label for="requestDetail">Damages * :</label>
                                                                <div class="form-control">
                                                                    <select class="col-md-4" disabled name="damageType">
                                                                        <option><%=importantProblem.getDamageType()%></option>
                                                                    </select>
                                                                    <%
                                                                        String value = "";
                                                                        if (importantProblem.getDamageType().contains("Minor")) {
                                                                            value = formatter.doubleToString(importantProblem.getTotalMinor() / importantProblem.getPlantableArea() * 100);
                                                                        } else {
                                                                            value = formatter.doubleToString(importantProblem.getTotalMajor()/ importantProblem.getPlantableArea() * 100);
                                                                        }
                                                                    %>
                                                                    <input type="text" class="col-md-8" name="damageValue" value="<%=value%>">
                                                                </div>
                                                                <label for="requestDetail">Description * :</label>
                                                                <textarea id="requestDetail" class="form-control" name="requestDetail" required rows="7"></textarea>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                                <button type="submit" class="btn btn-primary" name="action" value="sendRequest">Send Request</button>
                                                            </div>
                                                        </form>
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
