<%-- 
    Document   : monitorCropGrowth
    Created on : Feb 1, 2017, 5:40:40 PM
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

        <title>B.I.G.A.S. System | Municipal - Crop Growth Monitoring </title>


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
                    url: "MAOMonitorCropGrowth",
                    type: 'POST',
                    dataType: 'JSON',
                    success: function (data) {
                        values = data;
                    },
                    async: false
                });
                
                var barangay = [];
                var newlyPlanted = [];
                var tillering = [];
                var reproductive = [];
                var harvested = [];
                for (var a = 0; a < values[0].barangays.length; a++) {
                    barangay.push(values[0].barangays[a].brgyName);
                    newlyPlanted.push(values[0].barangays[a].newlyPlanted);
                    tillering.push(values[0].barangays[a].tillering);
                    reproductive.push(values[0].barangays[a].reproductive);
                    harvested.push(values[0].barangays[a].harvested);
                }
                
                Highcharts.chart('cropGrowthMonitoring', {
                    chart: {
                        type: 'bar'
                    },
                    title: {
                        text: 'Crop Stages'
                    },
                    xAxis: {
                        categories: barangay                       
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Hectares Planted'
                        }
                    },
                    legend: {
                        reversed: true
                    },
                    plotOptions: {
                        series: {
                            stacking: 'normal'
                        }
                    },
                    series: [{
                            name: 'Harvested',
                            data: harvested,
                            color: '#ef8f8f'
                        }, {
                            name: 'Reproductive',
                            data: reproductive,
                            color: '#8fd7ef'
                        }, {
                            name: 'Tillering',
                            data: tillering,
                            color: '#93ef8f'
                        }, {
                            name: 'Newly Planted',
                            data: newlyPlanted,
                            color: '#efbe8f'
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
                                <h3>Monitor Crop Growth</h3>
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
                                                    double pixels = ((ArrayList<Barangay>) session.getAttribute("barangays")).size() * 40;
                                                %>
                                                <div id="cropGrowthMonitoring" style="width: 100%; height: <%=pixels%>px"></div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>Barangay</th>
                                                            <th style="width: 18%">Newly Planted Area</th>
                                                            <th style="width: 18%">Tillering Area</th>
                                                            <th style="width: 18%">Reproductive Area</th>
                                                            <th style="width: 18%">Harvesting Area</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<Barangay> barangays = (ArrayList<Barangay>) session.getAttribute("barangays");
                                                            for (int a = 0; a < barangays.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=barangays.get(a).getBarangayName()%></td>
                                                            <td><%=new Formatter().doubleToString(barangays.get(a).getNewlyPlantedArea()) + " ha"%></td>
                                                            <td><%=new Formatter().doubleToString(barangays.get(a).getTilleringArea()) + " ha"%></td>
                                                            <td><%=new Formatter().doubleToString(barangays.get(a).getReproductiveArea()) + " ha"%></td>
                                                            <td><%=new Formatter().doubleToString(barangays.get(a).getHarvestingArea()) + " ha"%></td>
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
