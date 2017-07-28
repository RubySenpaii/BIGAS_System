<%-- 
    Document   : performanceSummary
    Created on : Jul 28, 2017, 5:20:00 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.Formatter"%>
<%@page import="object.Barangay"%>
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

        <title>B.I.G.A.S. System | Performance Summary </title>

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
        <script src="/BIGAS_System/build/js/highcharts/modules/drilldown.js"></script>
        <script src="/BIGAS_System/build/js/highcharts/modules/exporting.js"></script>

        <script>
            $(function () {
                var values;
                $.ajax({
                    url: "MAOPerformanceSummary",
                    type: 'POST',
                    dataType: 'JSON',
                    success: function (data) {
                        values = data;
                    },
                    async: false
                });

                var undamagedVal = [];
                var unplantedVal = [];
                var damagedVal = [];

                var undamaged = {};
                undamaged["name"] = "Undamaged Area";
                undamaged["color"] = "#7aff85";

                var unplanted = {};
                unplanted["name"] = "Unplanted Area";
                
                var damaged = {};
                damaged["name"] = "Damaged Area";
                damaged["color"] = "#ed5757";
                
                var drilldown = [];
                for (var a = 0; a < values[0].barangays.length; a++) {
                    var undamagedData = {};
                    undamagedData["name"] = values[0].barangays[a].barangayName;
                    undamagedData["y"] = values[0].barangays[a].undamagedArea;
                    undamagedData["drilldown"] = values[0].barangays[a].barangayName + "Undamaged";
                    undamagedData["color"] = "#7aff85";
                    undamagedVal.push(undamagedData);

                    var unplantedData = {};
                    unplantedData["name"] = values[0].barangays[a].barangayName;
                    unplantedData["y"] = values[0].barangays[a].unplanted;
                    unplantedVal.push(unplantedData);
                    
                    var damagedData = {};
                    damagedData["name"] = values[0].barangays[a].barangayName;
                    damagedData["y"] = values[0].barangays[a].totalDamage;
                    damagedData["color"] = "#ed5757";
                    damagedData["drilldown"] = values[0].barangays[a].barangayName + "Damaged";
                    damagedVal.push(damagedData);
                    
                    var drilldownUndamaged = {};
                    drilldownUndamaged["id"] = values[0].barangays[a].barangayName + "Undamaged";
                    drilldownUndamaged["name"] = values[0].barangays[a].barangayName + " Crop Stages";
                    
                    var drilldownUndamagedData = [];
                    
                    var drilldownUndamagedDataNewlyPlanted = {};
                    drilldownUndamagedDataNewlyPlanted["name"] = "Newly Planted";
                    drilldownUndamagedDataNewlyPlanted["y"] = values[0].barangays[a].newlyPlanted;
                    drilldownUndamagedDataNewlyPlanted["color"] = "#7aff85";
                    drilldownUndamagedData.push(drilldownUndamagedDataNewlyPlanted);
                    
                    var drilldownUndamagedDataTillering = {};
                    drilldownUndamagedDataTillering["name"] = "Tillering";
                    drilldownUndamagedDataTillering["y"] = values[0].barangays[a].tillering;
                    drilldownUndamagedDataNewlyPlanted["color"] = "#40ef4c";
                    drilldownUndamagedData.push(drilldownUndamagedDataTillering);
                    
                    var drilldownUndamagedDataReproductive = {};
                    drilldownUndamagedDataReproductive["name"] = "Reproductive";
                    drilldownUndamagedDataReproductive["y"] = values[0].barangays[a].reproductive;
                    drilldownUndamagedDataNewlyPlanted["color"] = "#3fcc6b";
                    drilldownUndamagedData.push(drilldownUndamagedDataReproductive);
                    
                    var drilldownUndamagedDataHarvested = {};
                    drilldownUndamagedDataHarvested["name"] = "Harvesting";
                    drilldownUndamagedDataHarvested["y"] = values[0].barangays[a].harvesting;
                    drilldownUndamagedDataNewlyPlanted["color"] = "#e29e16";
                    drilldownUndamagedData.push(drilldownUndamagedDataHarvested);
                    
                    drilldownUndamaged["data"] = drilldownUndamagedData;
                    
                    drilldown.push(drilldownUndamaged);
                    
                    var drilldownDamage = {};
                    drilldownDamage["id"] = values[0].barangays[a].barangayName + "Damaged";
                    drilldownDamage["name"] = values[0].barangays[a].barangayName + " Damage Types";
                    
                    var drilldownDamagedData = [];
                    
                    var drilldownDamageMinor = {};
                    drilldownDamageMinor["name"] = "Minor Damages";
                    drilldownDamageMinor["y"] = values[0].barangays[a].minor;
                    drilldownDamageMinor["color"] = "#ffac3f";
                    drilldownDamagedData.push(drilldownDamageMinor);
                    
                    var drilldownDamageMajor = {};
                    drilldownDamageMajor["name"] = "Major Damages";
                    drilldownDamageMajor["y"] = values[0].barangays[a].major;
                    drilldownDamageMajor["color"] = "#ed5757";
                    drilldownDamagedData.push(drilldownDamageMajor);
                    
                    drilldownDamage["data"] = drilldownDamagedData;
                    
                    drilldown.push(drilldownDamage);
                }
                
                undamaged["data"] = undamagedVal;
                unplanted["data"] = unplantedVal;
                damaged["data"] = damagedVal;


                Highcharts.chart('performanceSummary', {
                    chart: {
                        type: 'bar'
                    },
                    title: {
                        text: 'Stacked bar chart'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    yAxis: {
                        title: {
                            text: 'Farm Area'
                        }
                    },
                    legend: {
                        enabled: true,
                        reversed: true
                    },
                    tooltip: {
                        pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.2f}%)<br/>',
                        shared: false
                    },
                    plotOptions: {
                        series: {
                            stacking: 'normal'
                        }
                    },
                    series: [unplanted, damaged, undamaged],
                    drilldown: {
                        series: drilldown
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
                                <h3>Monitor This Week's Performance</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <%
                                                    //double pixels = ((ArrayList<Barangay>) session.getAttribute("barangays")).size() * 40;
                                                %>
                                                <div id="performanceSummary" style="width: 100%; height: 600px"></div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th colspan="5" style="text-align: center">Planted Area</th>
                                                            <th colspan="3" style="text-align: center">Damaged Area</th>
                                                            <th style="text-align: center">(Planted - Damaged)</th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                        <tr>
                                                            <th style="width: 12%; text-align: left;">Barangay Name</th>
                                                            <th style="width: 8%; text-align: center;">Newly Planted</th>
                                                            <th style="width: 8%; text-align: center;">Tillering</th>
                                                            <th style="width: 8%; text-align: center;">Reproductive</th>
                                                            <th style="width: 8%; text-align: center;">Harvesting</th>
                                                            <th style="width: 6%; text-align: center;">Total</th>
                                                            <th style="width: 8%; text-align: center;">Minor</th>
                                                            <th style="width: 8%; text-align: center;">Major</th>
                                                            <th style="width: 6%; text-align: center;">Total</th>
                                                            <th style="width: 10%; text-align: center;">Undamaged Area</th>
                                                            <th style="width: 8%; text-align: center;">Unplanted Area</th>
                                                            <th style="width: 8%; text-align: center;">Plantable Area</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            Formatter format = new Formatter();
                                                            ArrayList<Barangay> barangays = (ArrayList<Barangay>) session.getAttribute("barangays");
                                                            for (int a = 0; a < barangays.size(); a++) {
                                                                double totalDamages = barangays.get(a).getMinorDamagedArea() + barangays.get(a).getMajorDamagedArea();
                                                                double plantedArea = barangays.get(a).getPlantedArea() - totalDamages;
                                                        %>
                                                        <tr>
                                                            <td><%=barangays.get(a).getBarangayName()%></td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getNewlyPlantedArea())%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getTilleringArea())%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getReproductiveArea())%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getHarvestingArea())%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getPlantedArea())%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getMinorDamagedArea())%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getMajorDamagedArea())%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(totalDamages)%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(plantedArea)%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getUnplantedArea())%> ha</td>
                                                            <td style="text-align: right"><%=format.doubleToString(barangays.get(a).getArea())%> ha</td>
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
