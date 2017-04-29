<%-- 
    Document   : damageReview
    Created on : Jan 22, 2017, 2:22:52 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.Formatter"%>
<%@page import="object.DamageReport"%>
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
                                <h3>Review Damage Reported</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <%

                                            DamageIncident incident = (DamageIncident) session.getAttribute("damageIncident");
                                        %>
                                        <h2>Damage Incident #<%=incident.getDamageIncidentID()%></h2>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <!-- damage details -->
                                        <div class="row">
                                            <div class="x_panel">
                                                <div clas="x_content">
                                                    <%
                                                        DamageReport report = (DamageReport) session.getAttribute("damageReport");
                                                        Formatter format = new Formatter();
                                                        String dateAffected = format.valuesToDate(incident.getDamagedMonth(), incident.getDamagedDay(), incident.getDamagedYear());
                                                    %>
                                                    <div class="col-md-3">
                                                        <p style="font-size: medium"><b>Farm Name: </b><%=incident.getFarmName()%></p>
                                                        <p style="font-size: medium"><b>Seeds Planted: </b><%=incident.getSeedsPlanted()%> kg</p>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <p style="font-size: medium"><b>Crop Stage: </b><%=incident.getCropStage()%></p>
                                                        <p style="font-size: medium"><b>Minor Damages (Major): </b><%=report.getAreaAffected()%>ha (<%=report.getAreaDamaged()%>ha)</p>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <p style="font-size: medium"><b>Problem Name: </b><%=incident.getProblemName()%></p>
                                                        <p style="font-size: medium"><b>Date Affected: </b><%=dateAffected%></p>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <p style="font-size: medium"><b>Reported By: </b><%=(String) session.getAttribute("employee")%></p>
                                                        <p style="font-size: medium"><b>Date Reported: </b><%=report.getDateReported()%></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /damage details -->

                                        <!-- damage image -->
                                        <div class="row">
                                            <div id="image" class="col-md-10 col-md-offset-1">
                                                <img src="/BIGAS_System/images/damageReport/<%=report.getImage()%>.jpg" border="0" height="100%" width="100%">
                                            </div>
                                        </div>
                                        <!-- /damage image -->

                                        <div class="ln_solid"></div>
                                        <div class="text-center">
                                            <a href="/BIGAS_System/MunicipalDamages?action=changeDamageStatus&status=Approved&incidentID=<%=incident.getDamageIncidentID()%>" class="btn btn-success">Approve</a>
                                            <!--a href="/BIGAS_System/MunicipalDamages?action=changeDamageStatus&status=Rejected&incidentID=<%=incident.getDamageIncidentID()%>" class="btn btn-danger">Reject</a-->
                                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target=".bs-example-modal-sm">Reject</button>

                                            <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-hidden="true">
                                                <div class="modal-dialog modal-sm">
                                                    <div class="modal-content">
                                                        <form id="demo-form" data-parsley-validate class="MunicipalProgram">
                                                            <div class="modal-header">
                                                                <h4 class="modal-title" id="myModalLabel2">Rejection Details</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <label for="requestDetail">Reason For Rejection * :</label>
                                                                <input type="text" id="requestDetail" class="form-control" name="rejectDetail" required />
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                                <button type="submit" class="btn btn-primary" name="action" value="sendRequest">Send Comments</button>
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
