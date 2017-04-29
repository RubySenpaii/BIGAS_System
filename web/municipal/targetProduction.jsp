<%-- 
    Document   : targetProduction
    Created on : Jan 1, 2017, 5:30:56 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.GenericObject"%>
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
        <!-- jQuery -->
        <script src="/BIGAS_System/vendors/jquery/dist/jquery.min.js"></script>
        <!-- highcharts.js -->
        <script src="/BIGAS_System/build/js/highcharts/highcharts.js"></script>
        <script src="/BIGAS_System/build/js/highcharts/modules/data.js"></script>
        <script src="/BIGAS_System/build/js/highcharts/modules/exporting.js"></script>

        <script type="text/javascript">
            $(function () {
                $('#container').highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Average Yield Per Municipality'
                    },
                    subtitle: {
                        text: 'Source: WorldClimate.com'
                    },
                    xAxis: {
                        categories: [
                            'Jan',
                            'Feb',
                            'Mar',
                            'Apr',
                            'May',
                            'Jun',
                            'Jul',
                            'Aug',
                            'Sep',
                            'Oct',
                            'Nov',
                            'Dec'
                        ],
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Rainfall (mm)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
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
                    series: [{
                            name: 'Harvested',
                            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

                        }]
                });
            });

            function calculate() {
                var targetProduction = document.getElementById('targetProduction').value;
                for (var a = 0; a < 24; a++) {
                    var percent = document.getElementById('percent' + a).innerHTML;
                    percent = percent.slice(0, percent.length - 1);
                    if (percent > 0) {
                        var target = targetProduction * parseFloat(percent) / 100;
                        document.getElementById('recommendedTarget' + a).value = Math.round(target * 100) / 100;
                    } else {
                        document.getElementById('recommendedTarget' + a).value = 0;
                    }
                }
            }
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
                                <h3>Set Target Production</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <!-- set overall target -->
                                        <div class="row">
                                            <div class="col-md-12 text-center">
                                                <label>Target Production: </label>
                                                <input id="targetProduction" style="width: 500px" type="number" onblur="calculate()">
                                            </div>
                                        </div>
                                        <br/>
                                        <!-- /set overall target -->
                                        <!-- chart -->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                            </div>
                                        </div>
                                        <!-- /chart -->
                                        <!-- list of municipal -->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 20%">Municipal</th>
                                                            <th style="width: 13%">Farm Count</th>
                                                            <th style="width: 13%">Planted Area (Damaged)</th>
                                                            <th style="width: 13%">Total Production</th>
                                                            <th style="width: 13%">Percentage Contributed</th>
                                                            <th style="width: 13%">Average Yield</th>
                                                            <th style="width: 15%">Recommended Target Production</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<GenericObject> municipals = (ArrayList<GenericObject>) session.getAttribute("municipals");
                                                            for (int a = 0; a < municipals.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=municipals.get(a).getAttribute1()%></td>
                                                            <td><%=municipals.get(a).getAttribute2()%></td>
                                                            <td><%=municipals.get(a).getAttribute3()%></td>
                                                            <td><%=municipals.get(a).getAttribute4()%></td>
                                                            <td id='percent<%=a%>'><%=municipals.get(a).getAttribute5()%></td>
                                                            <td><%=municipals.get(a).getAttribute6()%></td>
                                                            <td><input id="recommendedTarget<%=a%>" name="recommendedTarget<%=a%>" type="text"></td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <!-- /list of municipal -->
                                        <div class="ln_solid text-center">
                                            <div class="col-md-12">
                                                <br/>
                                                <a href="" class="btn btn-success" name="" value="">Submit</a>
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