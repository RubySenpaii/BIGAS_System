<%-- 
    Document   : programRequestList
    Created on : Mar 15, 2017, 11:31:18 PM
    Author     : RubySenpaii
--%>

<%@page import="object.ProgramPlan"%>
<%@page import="object.ProgramRequest"%>
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
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <div class="pull-left">
                                            <h3>Request List and Status</h3>
                                        </div>
                                        <div class="pull-right">

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>Request ID</th>
                                                    <th>Request Detail</th>
                                                    <th>Requested By</th>
                                                    <th>Date Requested</th>
                                                    <th>Status</th>
                                                    <th>Program Created</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    ArrayList<ProgramRequest> requests = (ArrayList<ProgramRequest>) session.getAttribute("requests");
                                                    for (int a = 0; a < requests.size(); a++) {
                                                %>
                                                <tr>
                                                    <td><%=requests.get(a).getRequestID()%></td>
                                                    <td><%=requests.get(a).getRequestDetail()%></td>
                                                    <td><%=requests.get(a).getRequestBy()%></td>
                                                    <td><%=requests.get(a).getDateRequested()%></td>
                                                    <td><%=requests.get(a).getRequestStatus()%></td>
                                                    <td><%=requests.get(a).getProgramPlanID()%></td>
                                                    <td>
                                                        <%
                                                            if (requests.get(a).getRequestStatus().equals("Requested")) {
                                                        %>
                                                        <button type="button" class="btn btn-success" data-toggle="modal" data-target=".bs-example-modal-sm">Approve</button>

                                                        <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-hidden="true">
                                                            <div class="modal-dialog modal-sm">
                                                                <div class="modal-content">
                                                                    <form id="demo-form" data-parsley-validate class="MunicipalProgram">
                                                                        <div class="modal-header">
                                                                            <h4 class="modal-title" id="myModalLabel2">Program Request</h4>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <label for="programName">Program Plan * :</label>
                                                                            <select id="programName" class="form-control" name="programName" required >
                                                                                <%
                                                                                    ArrayList<ProgramPlan> plans = (ArrayList<ProgramPlan>) session.getAttribute("programPlans");
                                                                                    for (int b = 0; b < plans.size(); b++) {
                                                                                %>
                                                                                <option><%=plans.get(b).getProgramName()%></option>
                                                                                <%
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <input type="hidden" name="requestID" value="<%=requests.get(a).getRequestID()%>">
                                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                                            <button type="submit" class="btn btn-primary" name="action" value="approveRequest">Send Request</button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <a href="/BIGAS_System/MunicipalProgram?action=updateRequest" class="btn btn-warning">Reject</a>
                                                        
                                                        <%
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
