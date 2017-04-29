<%-- 
    Document   : performanceFarm
    Created on : Mar 3, 2017, 11:45:11 PM
    Author     : RubySenpaii
--%>

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
                    url: "MAOPerformanceBarangay",
                    type: 'POST',
                    dataType: 'JSON',
                    success: function (data) {
                        values = data;
                    },
                    async: false
                });
                /*
                 var categoryLinks = {};
                 
                 var categories = [];
                 var brgyValues = [];
                 */
                var pieDetail = [];
                for (var a = 0; a < values[0].barangays.length; a++) {
                    //categories.push(values[0].barangays[a].brgyName);
                    //brgyValues.push(values[0].barangays[a].harvest);
                    //categoryLinks[values[0].barangays[a].brgyName] = 'http://localhost:8084/BIGAS_System/MunicipalPerformance?action=viewFarmPerformance&brgyName=' + values[0].barangays[a].brgyName;

                    var pied = {};
                    pied['name'] = values[0].barangays[a].brgyName;
                    pied['y'] = values[0].barangays[a].harvest;
                    pied['url'] = 'http://localhost:8084/BIGAS_System/MunicipalPerformance?action=viewFarmPerformance&brgyName=' + values[0].barangays[a].brgyName;
                    pieDetail.push(pied);
                }
                var year = values[0].barangays[0].year;
                /*var yearData = {};
                 yearData['name'] = values[0].barangays[0].year;
                 yearData['data'] = brgyValues;
                 var allData = [];
                 allData.push(yearData);*/

//                Highcharts.chart('container', {
//                    chart: {
//                        plotBackgroundColor: null,
//                        plotBorderWidth: null,
//                        plotShadow: false,
//                        type: 'pie'
//                    },
//                    title: {
//                        text: 'Production per Barangay For ' + year
//                    },
//                    tooltip: {
//                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
//                    },
//                    plotOptions: {
//                        pie: {
//                            allowPointSelect: true,
//                            cursor: 'pointer',
//                            dataLabels: {
//                                enabled: true,
//                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
//                                style: {
//                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
//                                }
//                            }, events: {
//                                click: function (e) {
//                                    location.href = e.point.url;
//                                    e.preventDefault();
//                                }
//                            }
//                        }
//                    },
//                    series: [{
//                            name: 'Brands',
//                            colorByPoint: true,
//                            data: pieDetail
//                        }]
//                });

                Highcharts.chart('container', {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Production for San Rafael'
                    },
                    xAxis: {
                        categories: [
                            'Barangay1',
                            'Barangay2',
                            'Barangay3',
                            'Barangay4',
                            'Barangay5',
                            'Barangay6',
                            'Barangay7',
                            'Barangay8',
                            'Barangay9',
                            'Barangay10',
                            'Barangay11',
                            'Barangay12'
                        ],
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Production (MT)'
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
                            name: 'Production',
                            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

                        }, {
                            name: 'Target',
                            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]

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
                                <h3>Overall Production Monitoring</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div style="position: relative">
                                                    Target Value:
                                                    <div class="progress">
                                                        <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" data-transitiongoal="100%"></div>
                                                        <div style="height: 20px; position: absolute; top: 0; right: 50%; color: black">
                                                            <b>1000 MT</b>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div style="position: relative">
                                                    Actual Value:
                                                    <div class="progress">
                                                        <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" data-transitiongoal="80%"></div>
                                                        <div style="height: 20px; position: absolute; top: 0; right: 50%; color: black">
                                                            <b>800 MT</b>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="container" style="width: 100%; height: 700px"></div>
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

