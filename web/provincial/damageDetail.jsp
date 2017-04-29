<%-- 
    Document   : damageDetail
    Created on : Jan 22, 2017, 3:31:28 PM
    Author     : RubySenpaii
--%>

<%@page import="object.DamageReport"%>
<%@page import="object.DeployedProgram"%>
<%@page import="java.util.ArrayList"%>
<%@page import="extra.Formatter"%>
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
        <!-- jQuery -->
        <script src="/BIGAS_System/vendors/jquery/dist/jquery.min.js"></script>
        <!-- highcharts.js -->
        <script src="/BIGAS_System/build/js/highcharts/highcharts.js"></script>
        <script src="/BIGAS_System/build/js/highcharts/modules/data.js"></script>
        <script src="/BIGAS_System/build/js/highcharts/modules/exporting.js"></script>

        <script>
            $(function () {
                var values;

                $.ajax({
                    url: "MAODamageDetailArea",
                    type: 'POST',
                    dataType: 'JSON',
                    success: function (data) {
                        values = data;
                    },
                    async: false
                });
                
                var dates = [];
                var areaAffected = [];
                var areaDamaged = [];
                for (var a = 0; a < values[0].damageReports.length; a++) {
                    dates.push(values[0].damageReports[a].date);
                    areaAffected.push(values[0].damageReports[a].areaAffected);
                    areaDamaged.push(values[0].damageReports[a].areaDamaged);
                }
                
                Highcharts.chart('container', {
                    chart: {
                        type: 'line'
                    },
                    title: {
                        text: 'Minor Damages vs Major Damages'
                    },
                    xAxis: {
                        categories: dates
                    },
                    yAxis: {
                        title: {
                            text: 'Hectares (ha)'
                        }
                    },
                    plotOptions: {
                        line: {
                            dataLabels: {
                                enabled: true
                            },
                            enableMouseTracking: false
                        }
                    },
                    series: [{
                            name: 'Minor Damages',
                            data: areaAffected
                        }, {
                            name: 'Major Damages',
                            data: areaDamaged
                        }]
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
                                            String dateAffected = new Formatter().valuesToDate(incident.getDamagedMonth(), incident.getDamagedDay(), incident.getDamagedYear());
                                        %>
                                        <h2>Damage Incident #<%=incident.getDamageIncidentID()%> - <%=incident.getStatus()%></h2>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <!-- damage details -->
                                        <div class="row">
                                            <div class="x_panel">
                                                <div clas="x_content">
                                                    <div class="row">
                                                        <br/>
                                                        <div class="col-md-4">
                                                            <p style="font-size: medium"><b>Farm Name: </b><%=incident.getFarmName()%></p>
                                                            <p style="font-size: medium"><b>Seed Planted: </b><%=incident.getSeedVarietyName() + " - " + incident.getSeedsPlanted() + "kg"%></p>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <p style="font-size: medium"><b>Affected Stage: </b><%=incident.getCropStage()%></p>
                                                            <p style="font-size: medium"><b>Minor Damages (Major): </b><%=incident.getTotalAreaAffected() + "ha (" + incident.getTotalAreaDamaged() + "ha)"%></p>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <p style="font-size: medium"><b>Problem Name: </b><%=incident.getProblemName()%></p>
                                                            <p style="font-size: medium"><b>Date Affected: </b><%=dateAffected%></p>
                                                        </div>
                                                    </div>
                                                    <%
                                                        ArrayList<DeployedProgram> deployedPrograms = (ArrayList<DeployedProgram>) session.getAttribute("deployedPrograms");
                                                        for (int a = 0; a < deployedPrograms.size(); a++) {
                                                    %>
                                                    <div class="ln_solid"></div>
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <p style="font-size: medium"><b>Program Deployed: </b><%=deployedPrograms.get(a).getProgramName()%></p>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <p style="font-size: medium"><b>Date Started: </b><%=deployedPrograms.get(a).getDateStarted()%></p>
                                                        </div>
                                                    </div>
                                                    <%
                                                        }
                                                    %>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /damage details -->

                                        <!-- tab panel -->
                                        <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                            <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                                <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Monitoring</a>
                                                </li>
                                                <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab" aria-expanded="false">Images</a>
                                                </li>
                                            </ul>
                                            <div id="myTabContent" class="tab-content">
                                                <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
                                                    <!-- weekly monitoring -->
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div id="container" style="min-width: 310px; height: 550px; margin: 0 auto"></div>
                                                        </div>
                                                    </div>
                                                    <!-- /weekly monitoring -->
                                                </div>
                                                <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
                                                    <!-- damage image -->
                                                    <div class="row">
                                                        <div class="col-md-2">
                                                            <table class="table table-bordered">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Date</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <%
                                                                        ArrayList<DamageReport> reports = (ArrayList<DamageReport>) session.getAttribute("damageReports");
                                                                        for (int a = 0; a < reports.size(); a++) {
                                                                    %>
                                                                    <tr>
                                                                        <td>
                                                                            <a href="javascript:void(0)" onclick="document.getElementById('incidentImage').src='images/damageReport/<%=reports.get(a).getImage()%>.jpg'">
                                                                                <%=reports.get(a).getDateReported()%>
                                                                            </a>
                                                                        </td>
                                                                    </tr>
                                                                    <%
                                                                        }
                                                                    %>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div id="image" class="col-md-10">
                                                            <img id="incidentImage" src="" border="0" height="100%" width="100%">
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
