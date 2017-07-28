<%-- 
    Document   : homepage
    Created on : Dec 22, 2016, 9:41:56 PM
    Author     : RubySenpaii
--%>

<%@page import="javafx.scene.control.Alert"%>
<%@page import="extra.Formatter"%>
<%@page import="object.DeployedProgram"%>
<%@page import="object.DamageIncident"%>
<%@page import="java.util.ArrayList"%>
<%@page import="object.TargetProduction"%>
<%@page import="object.Municipality"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>B.I.G.A.S. System | Municipal - Homepage</title>

        <!-- Bootstrap -->
        <link href="/BIGAS_System/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="/BIGAS_System/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- ProgressBar -->
        <link href="/BIGAS_System/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">

        <!-- Custom Theme Style -->
        <link href="/BIGAS_System/build/css/custom.min.css" rel="stylesheet">
        <!-- jQuery -->
        <script src="/BIGAS_System/vendors/jquery/dist/jquery.min.js"></script>
        <!-- highcharts.js -->
        <script src="/BIGAS_System/build/js/highcharts/highcharts.js"></script>
        <script src="/BIGAS_System/build/js/highcharts/modules/drilldown.js"></script>
        <script src="/BIGAS_System/build/js/highcharts/modules/data.js"></script>
        <script src="/BIGAS_System/build/js/highcharts/modules/exporting.js"></script>

        <script type="text/javascript">
            $(function () {
                var values;

                $.ajax({
                    url: "MAODashboard",
                    type: 'POST',
                    dataType: 'JSON',
                    success: function (data) {
                        values = data;
                    },
                    async: false
                });


                var dryHarvested = 0, wetHarvested = 0;
                var dryGoodHarvest = 0, dryHybridHarvest = 0, dryCertifiedHarvest = 0, dryRegisteredHarvest = 0;
                var wetGoodHarvest = 0, wetHybridHarvest = 0, wetCertifiedHarvest = 0, wetRegisteredHarvest = 0;
                var dryGoodData = [], dryHybridData = [], dryCertifiedData = [], dryRegisteredData = [];
                var wetGoodData = [], wetHybridData = [], wetCertifiedData = [], wetRegisteredData = [];
                for (var a = 0; a < values[0].cropProduction.length; a++) {
                    if (values[0].cropProduction[a].season === "Dry") {
                        var acquisition = {};
                        if (values[0].cropProduction[a].type === "Good") {
                            acquisition["name"] = values[0].cropProduction[a].acquisition;
                            acquisition["y"] = values[0].cropProduction[a].harvest;
                            dryGoodData.push(acquisition);
                            dryGoodHarvest += values[0].cropProduction[a].harvest;
                        } else if (values[0].cropProduction[a].type === "Hybrid") {
                            acquisition["name"] = values[0].cropProduction[a].acquisition;
                            acquisition["y"] = values[0].cropProduction[a].harvest;
                            dryHybridData.push(acquisition);
                            dryHybridHarvest += values[0].cropProduction[a].harvest;
                        } else if (values[0].cropProduction[a].type === "Certified") {
                            acquisition["name"] = values[0].cropProduction[a].acquisition;
                            acquisition["y"] = values[0].cropProduction[a].harvest;
                            dryCertifiedData.push(acquisition);
                            dryCertifiedHarvest += values[0].cropProduction[a].harvest;
                        } else {
                            acquisition["name"] = values[0].cropProduction[a].acquisition;
                            acquisition["y"] = values[0].cropProduction[a].harvest;
                            dryRegisteredData.push(acquisition);
                            dryRegisteredHarvest += values[0].cropProduction[a].harvest;
                        }
                        dryHarvested += values[0].cropProduction[a].harvest;
                    } else {
                        var acquisition = {};
                        if (values[0].cropProduction[a].type === "Good") {
                            acquisition["name"] = values[0].cropProduction[a].acquisition;
                            acquisition["y"] = values[0].cropProduction[a].harvest;
                            wetGoodData.push(acquisition);
                            wetGoodHarvest += values[0].cropProduction[a].harvest;
                        } else if (values[0].cropProduction[a].type === "Hybrid") {
                            acquisition["name"] = values[0].cropProduction[a].acquisition;
                            acquisition["y"] = values[0].cropProduction[a].harvest;
                            wetHybridData.push(acquisition);
                            wetHybridHarvest += values[0].cropProduction[a].harvest;
                        } else if (values[0].cropProduction[a].type === "Certified") {
                            acquisition["name"] = values[0].cropProduction[a].acquisition;
                            acquisition["y"] = values[0].cropProduction[a].harvest;
                            wetCertifiedData.push(acquisition);
                            wetCertifiedHarvest += values[0].cropProduction[a].harvest;
                        } else {
                            acquisition["name"] = values[0].cropProduction[a].acquisition;
                            acquisition["y"] = values[0].cropProduction[a].harvest;
                            wetRegisteredData.push(acquisition);
                            wetRegisteredHarvest += values[0].cropProduction[a].harvest;
                        }
                        wetHarvested += values[0].cropProduction[a].harvest;
                    }
                }

                //start of dry season data
                var drySeasonData = [];
                var drySeason = {};
                drySeason["name"] = "Dry";
                drySeason["id"] = "Dry";

                var dryGood = {};
                dryGood["name"] = "Good";
                dryGood["drilldown"] = "DryGood";
                dryGood["y"] = dryGoodHarvest;
                drySeasonData.push(dryGood);

                var dryHybrid = {};
                dryHybrid["name"] = "Hybrid";
                dryHybrid["drilldown"] = "DryHybrid";
                dryHybrid["y"] = dryHybridHarvest;
                drySeasonData.push(dryHybrid);

                var dryCertified = {};
                dryCertified["name"] = "Certified";
                dryCertified["drilldown"] = "DryCertified";
                dryCertified["y"] = dryCertifiedHarvest;
                drySeasonData.push(dryCertified);

                var dryRegistered = {};
                dryRegistered["name"] = "Registered";
                dryRegistered["drilldown"] = "DryRegistered";
                dryRegistered["y"] = dryRegisteredHarvest;
                drySeasonData.push(dryRegistered);

                drySeason["data"] = drySeasonData;

                var dryGoodDrilldown = {};
                dryGoodDrilldown["name"] = "Good";
                dryGoodDrilldown["id"] = "DryGood";
                dryGoodDrilldown["data"] = dryGoodData;

                var dryHybridDrilldown = {};
                dryHybridDrilldown["name"] = "Hybrid";
                dryHybridDrilldown["id"] = "DryHybrid";
                dryHybridDrilldown["data"] = dryHybridData;

                var dryCertifiedDrilldown = {};
                dryCertifiedDrilldown["name"] = "Certified";
                dryCertifiedDrilldown["id"] = "DryCertified";
                dryCertifiedDrilldown["data"] = dryCertifiedData;

                var dryRegisteredDrilldown = {};
                dryRegisteredDrilldown["name"] = "Registered";
                dryRegisteredDrilldown["id"] = "DryRegistered";
                dryRegisteredDrilldown["data"] = dryRegisteredData;
                //end of dry season data

                //start of wet season data
                var wetSeasonData = [];
                var wetSeason = {};
                wetSeason["name"] = "Wet";
                wetSeason["id"] = "Wet";

                var wetGood = {};
                wetGood["name"] = "Good";
                wetGood["drilldown"] = "WetGood";
                wetGood["y"] = wetGoodHarvest;
                wetSeasonData.push(wetGood);

                var wetHybrid = {};
                wetHybrid["name"] = "Hybrid";
                wetHybrid["drilldown"] = "WetHybrid";
                wetHybrid["y"] = wetHybridHarvest;
                wetSeasonData.push(wetHybrid);

                var wetCertified = {};
                wetCertified["name"] = "Certified";
                wetCertified["drilldown"] = "WetCertified";
                wetCertified["y"] = wetCertifiedHarvest;
                wetSeasonData.push(wetCertified);

                var wetRegistered = {};
                wetRegistered["name"] = "Registered";
                wetRegistered["drilldown"] = "WetRegistered";
                wetRegistered["y"] = wetRegisteredHarvest;
                wetSeasonData.push(wetRegistered);

                wetSeason["data"] = wetSeasonData;

                var wetGoodDrilldown = {};
                wetGoodDrilldown["name"] = "Good";
                wetGoodDrilldown["id"] = "WetGood";
                wetGoodDrilldown["data"] = wetGoodData;

                var wetHybridDrilldown = {};
                wetHybridDrilldown["name"] = "Hybrid";
                wetHybridDrilldown["id"] = "WetHybrid";
                wetHybridDrilldown["data"] = wetHybridData;

                var wetCertifiedDrilldown = {};
                wetCertifiedDrilldown["name"] = "Certified";
                wetCertifiedDrilldown["id"] = "WetCertified";
                wetCertifiedDrilldown["data"] = wetCertifiedData;

                var wetRegisteredDrilldown = {};
                wetRegisteredDrilldown["name"] = "Registered";
                wetRegisteredDrilldown["id"] = "WetRegistered";
                wetRegisteredDrilldown["data"] = wetRegisteredData;
                //end of wet season data

                var season = {};
                season["name"] = "Season";

                var seasonData = [];
                var season1 = {};
                season1["name"] = "Dry";
                season1["drilldown"] = "Dry";
                season1["y"] = dryHarvested;
                seasonData.push(season1);

                var season2 = {};
                season2["name"] = "Wet";
                season2["drilldown"] = "Wet";
                season2["y"] = wetHarvested;
                seasonData.push(season2);

                season["data"] = seasonData;

                Highcharts.chart('cropProductionStatus', {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Total Harvest Production For March 16, 2017 - March 15, 2018'
                    },
                    subtitle: {
                        text: values[0].municipal
                    },
                    xAxis: {
                        type: 'category'
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Metric Tons (MT)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:.1f} MT</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0
                        }
                    },
                    series: [season],
                    drilldown: {
                        series: [drySeason, dryGoodDrilldown, dryHybridDrilldown, dryCertifiedDrilldown, dryRegisteredDrilldown, wetSeason, wetGoodDrilldown, wetHybridDrilldown, wetCertifiedDrilldown, wetRegisteredDrilldown]
                    }
                });
            });
        </script>
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
                                    Municipality municipality = (Municipality) session.getAttribute("municipality");
                                %>
                                <h3>Home Page - <%=municipality.getMunicipalityName()%></h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <!-- target vs actual -->
                                        <div class="row">
                                            <%
                                                Formatter formatter = new Formatter();
                                                TargetProduction targetProduction = (TargetProduction) session.getAttribute("targetProduction");
                                                double target = 100;
                                                double actual = 100;
                                                if (targetProduction.getTargetValue() > municipality.getHarvestedTotal()) {
                                                    actual = municipality.getHarvestedTotal() / targetProduction.getTargetValue() * 100;
                                                } else {
                                                    target = targetProduction.getTargetValue() / municipality.getHarvestedTotal() * 100;
                                                }
                                            %>
                                            <div class="col-md-12">
                                                <div style="position: relative">
                                                    Target Value:
                                                    <div class="progress">
                                                        <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" data-transitiongoal="<%=target + "%"%>"></div>
                                                        <div style="height: 20px; position: absolute; top: 0; right: 50%; color: black">
                                                            <b><%=formatter.doubleToString(targetProduction.getTargetValue())%> MT</b>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div style="position: relative">
                                                    Actual Value:
                                                    <div class="progress">
                                                        <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" data-transitiongoal="<%=actual + "%"%>"></div>
                                                        <div style="height: 20px; position: absolute; top: 0; right: 50%; color: black">
                                                            <b><%=formatter.doubleToString(municipality.getHarvestedTotal())%> MT</b>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /target vs actual -->
                                        <!-- barangay status -->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h4>Current Status</h4>
                                                <div id="cropProductionStatus" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                            </div>
                                        </div>
                                        <!-- /barangay status -->
                                        <br/>
                                        <!-- additional info -->
                                        <div class="row">
                                            <!-- new damages reported -->
                                            <div class="col-md-7 col-sm-7 col-xs-12">
                                                <h4>New Damages Reported</h4>
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 19%">Problem Name</th>
                                                            <th style="width: 19%">Farm Name</th>
                                                            <th style="width: 13%">Date Affected</th>
                                                            <th style="width: 17%">Major Damaged Area</th>
                                                            <th style="width: 17%">Minor Damaged Area</th>
                                                            <th style="width: 15%"></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<DamageIncident> newIncidents = (ArrayList<DamageIncident>) session.getAttribute("newIncidents");
                                                            for (int a = 0; a < newIncidents.size(); a++) {
                                                                String date = formatter.valuesToDate(newIncidents.get(a).getDamagedMonth(), newIncidents.get(a).getDamagedDay(), newIncidents.get(a).getDamagedYear());
                                                        %>
                                                        <tr>
                                                            <td><%=newIncidents.get(a).getProblemName()%></td>
                                                            <td><%=newIncidents.get(a).getFarmName()%></td>
                                                            <td><%=date%></td>
                                                            <td style="text-align: right"><%=formatter.doubleToString(newIncidents.get(a).getTotalAreaAffected())%> ha</td>
                                                            <td style="text-align: right"><%=formatter.doubleToString(newIncidents.get(a).getTotalAreaDamaged())%> ha</td>
                                                            <td>
                                                                <a href="/BIGAS_System/MunicipalDamages?action=reviewDamages&incidentID=<%=newIncidents.get(a).getDamageIncidentID()%>" class="btn btn-success">
                                                                    View Problem
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /new damages reported -->

                                            <!-- ongoing programs -->
                                            <div class="col-md-5 col-sm-5 col-xs-12">
                                                <h4>List of Ongoing Programs</h4>
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 35%">Program Name</th>
                                                            <th style="width: 25%">Date Started</th>
                                                            <th style="width: 25%">Status</th>
                                                            <th style="width: 15%"></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<DeployedProgram> deployedPrograms = (ArrayList<DeployedProgram>) session.getAttribute("deployedPrograms");
                                                            for (int a = 0; a < deployedPrograms.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=deployedPrograms.get(a).getProgramName()%></td>
                                                            <td><%=deployedPrograms.get(a).getDateStarted()%></td>
                                                            <td><%=deployedPrograms.get(a).getStatus()%></td>
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
                                            <!-- /ongoing programs -->
                                        </div>
                                        <!-- /additional info -->
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

        <!-- Bootstrap -->
        <script src="/BIGAS_System/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="/BIGAS_System/vendors/fastclick/lib/fastclick.js"></script>
        <!-- NProgress -->
        <script src="/BIGAS_System/vendors/nprogress/nprogress.js"></script>
        <!-- progress bar -->
        <script src="/BIGAS_System/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>

        <!-- Custom Theme Scripts -->
        <script src="/BIGAS_System/build/js/custom.min.js"></script>
    </body>
</html>
