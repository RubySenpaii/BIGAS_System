<%-- 
    Document   : monitorHarvest
    Created on : Feb 3, 2017, 3:26:07 PM
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

        <title>B.I.G.A.S. System | Provincial - Harvest Monitoring </title>

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

        <!--script>
            $(function () {
                Highcharts.chart('container', {
                    data: {
                        table: 'harvestdata'
                    },
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Data extracted from a HTML table in the page'
                    },
                    yAxis: {
                        allowDecimals: true,
                        title: {
                            text: 'Units'
                        }
                    },
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.series.name + '</b><br/>' +
                                    this.point.y + ' ' + this.point.name;
                        }
                    }
                });
            });
        </script-->
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
                                <h3>Harvest Monitoring</h3>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <!-- planted area vs unplanted area vs damaged area >
                                        <div class="row">
                                            <div class="col-md-12">
                                                <h4>Planted Area vs Unplanted Area</h4>
                                                <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                            </div>
                                        </div>
                                        <!-- /planted area vs unplanted area vs damaged area -->

                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-bordered" id="harvestdata">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 27%">Barangay Name</th>
                                                            <th style="width: 13%">Number of Farms</th>
                                                            <th style="width: 15%">Planted Area</th>
                                                            <th style="width: 15%">Damages</th>
                                                            <th style="width: 15%">Harvested</th>
                                                            <th style="width: 15%">Average Yield</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            Formatter formatter = new Formatter();
                                                            ArrayList<Barangay> barangays = (ArrayList<Barangay>) session.getAttribute("barangays");
                                                            for (int a = 0; a < barangays.size(); a++) {
                                                                String yield = "0";
                                                                if (barangays.get(a).getHarvest() > 0) {
                                                                    yield = formatter.doubleToString(barangays.get(a).getHarvest() / barangays.get(a).getPlantedArea());
                                                                }
                                                        %>
                                                        <tr>
                                                            <td><%=barangays.get(a).getBarangayName()%></td>
                                                            <td><%=barangays.get(a).getFarmCount()%></td>
                                                            <td><%=formatter.doubleToString(barangays.get(a).getPlantedArea()) + " ha"%></td>
                                                            <td><%=formatter.doubleToString(barangays.get(a).getDamagedArea()) + " ha"%></td>
                                                            <td><%=formatter.doubleToString(barangays.get(a).getHarvest()) + " MT"%></td>
                                                            <td><%=yield + "%"%></td>
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
