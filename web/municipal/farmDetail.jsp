<%-- 
    Document   : farmDetail
    Created on : Dec 28, 2016, 9:52:31 PM
    Author     : RubySenpaii
--%>

<%@page import="object.DeployedProgram"%>
<%@page import="extra.Formatter"%>
<%@page import="object.PlantingReport"%>
<%@page import="object.Plot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="object.Farmer"%>
<%@page import="object.Farm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>B.I.G.A.S. System | Municipal - Farm Detail </title>

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
                                <h3>Farm Details</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <br/>
                                            <%
                                                Farm farm = (Farm) session.getAttribute("farm");
                                                Farmer farmer = (Farmer) session.getAttribute("farmer");
                                            %>
                                            <div class="col-md-4">
                                                <p style="font-size: medium"><b>Farm Name: </b><%=farm.getFarmName()%></p>
                                                <br/>
                                                <p style="font-size: medium"><b>Contact Person: </b><%=farmer.getLastName() + ", " + farmer.getFirstName() + " " + farmer.getMiddleName()%></p>
                                            </div>
                                            <div class="col-md-4">
                                                <p style="font-size: medium"><b>Irrigation Method: </b><%=farm.getIrrigationMethod()%></p>
                                                <br/>
                                                <p style="font-size: medium"><b>Land Elevation: </b><%=farm.getLandElevation()%></p>
                                            </div>
                                            <div class="col-md-4">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>Plot Number</th>
                                                            <th>Plot Size</th>
                                                            <th>Planted</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<Plot> plots = (ArrayList<Plot>) session.getAttribute("plots");
                                                            for (int a = 0; a < plots.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=plots.get(a).getPlotNumber()%></td>
                                                            <td><%=plots.get(a).getPlotSize()%> ha</td>
                                                            <td>
                                                                <%
                                                                    String planted = "N";
                                                                    if (plots.get(a).getPlotPlanted() == 1) {
                                                                        planted = "Y";
                                                                    }
                                                                %>
                                                                <%=planted%>
                                                            </td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                        <!-- tab panel -->
                                        <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                            <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                                <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Planting</a>
                                                </li>
                                                <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Programs</a>
                                                </li>
                                            </ul>
                                            <div id="myTabContent" class="tab-content">
                                                <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
                                                    <!-- weekly monitoring -->
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <table class="table table-bordered">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Planting Report ID</th>
                                                                        <th>Seed Variety</th>
                                                                        <th>Date Planted</th>
                                                                        <th>Amount Planted</th>
                                                                        <th>Amount Harvested</th>
                                                                        <th>Stage</th>
                                                                        <th>Area Damaged</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%
                                                                        Formatter formatter = new Formatter();
                                                                        ArrayList<PlantingReport> plantingReports = (ArrayList<PlantingReport>) session.getAttribute("plantingReports");
                                                                        for (int a = 0; a < plantingReports.size(); a++) {
                                                                            String plantedDate = new Formatter().valuesToDate(plantingReports.get(a).getPlantedMonth(), plantingReports.get(a).getPlantedDay(), plantingReports.get(a).getPlantedYear());
                                                                    %>
                                                                    <tr>
                                                                        <td><%=plantingReports.get(a).getPlantingReportID()%></td>
                                                                        <td><%=plantingReports.get(a).getSeedVariety()%></td>
                                                                        <td><%=plantedDate%></td>
                                                                        <td><%=formatter.doubleToString(plantingReports.get(a).getAmountPlanted())%> kg</td>
                                                                        <td><%=formatter.doubleToString(plantingReports.get(a).getAmountHarvested())%> MT</td>
                                                                        <td><%=plantingReports.get(a).getStage()%></td>
                                                                        <td><%=formatter.doubleToString(plantingReports.get(a).getAreaDamaged())%> ha</td>
                                                                    </tr>
                                                                    <%
                                                                        }
                                                                    %>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <!-- /weekly monitoring -->
                                                </div>
                                                <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
                                                    <!-- damage image -->
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <table class="table table-bordered">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Deployed ID</th>
                                                                        <th>Program Name</th>
                                                                        <th>Program Type</th>
                                                                        <th>Status</th>
                                                                        <th>Provisions Received</th>
                                                                        <th>Date Completed</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%
                                                                        ArrayList<DeployedProgram> deployeds = (ArrayList<DeployedProgram>) session.getAttribute("deployed");
                                                                        for (int a = 0; a < deployeds.size(); a++) {
                                                                            String provisions = "";
                                                                            if (deployeds.get(a).getFertilizerReceived() > 0) {
                                                                                provisions += deployeds.get(a).getFertilizerProvided() + ": " + deployeds.get(a).getFertilizerReceived() + "kg";
                                                                            }
                                                                            if (provisions.length() > 1) {
                                                                                provisions += " | ";
                                                                            }
                                                                            if (deployeds.get(a).getVarietyReceived()> 0) {
                                                                                provisions += deployeds.get(a).getVarietyProvided()+ ": " + deployeds.get(a).getVarietyReceived() + "kg";
                                                                            }
                                                                    %>
                                                                    <tr>
                                                                        <td><%=deployeds.get(a).getDeployedID()%></td>
                                                                        <td><%=deployeds.get(a).getProgramName()%></td>
                                                                        <td><%=deployeds.get(a).getProgramType()%></td>
                                                                        <td><%=deployeds.get(a).getStatus()%></td>
                                                                        <td><%=provisions%></td>
                                                                        <td><%=deployeds.get(a).getDateCompleted()%></td>
                                                                    </tr>
                                                                    <%
                                                                    }
                                                                    %>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <!-- /damage image -->
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /tab panel-->
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
