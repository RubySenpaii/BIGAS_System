<%-- 
    Document   : performanceMunicipal
    Created on : Mar 15, 2017, 1:12:57 PM
    Author     : RubySenpaii
--%>

<%@page import="object.Municipality"%>
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
                    url: "PAOPerformanceMunicipality",
                    type: 'POST',
                    dataType: 'JSON',
                    success: function (data) {
                        values = data;
                    },
                    async: false
                });

//                var categoryLinks = {};
//
//                var categories = [];
//                var brgyValues = [];
                var pieDetail = [];
                for (var a = 0; a < values[0].municipals.length; a++) {
//                    categories.push(values[0].municipals[a].municipalName);
//                    brgyValues.push(values[0].municipals[a].harvest);
//                    categoryLinks[values[0].municipals[a].municipalName] = 'http://localhost:8084/BIGAS_System/ProvincialPerformance?action=viewBarangayPerformance&municipalID=' + values[0].municipals[a].municipalID;
//                    
                    var pied = {};
                    pied['name'] = values[0].municipals[a].municipalName;
                    pied['y'] = values[0].municipals[a].harvest;
                    pied['url'] = 'http://localhost:8084/BIGAS_System/ProvincialPerformance?action=viewBarangayPerformance&municipalID=' + values[0].municipals[a].municipalID;
                    pieDetail.push(pied);
                }
                var year = values[0].municipals[0].year;
//                var yearData = {};
//                yearData['name'] = values[0].municipals[0].year;
//                yearData['data'] = brgyValues;
//                var allData = [];
//                allData.push(yearData);
                
                Highcharts.chart('container', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: 'Production per Municipal For ' + year
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                }
                            }, events: {
                                click: function (e) {
                                    location.href = e.point.url;
                                    e.preventDefault();
                                }
                            }
                        }
                    },
                    series: [{
                            name: 'Brands',
                            colorByPoint: true,
                            data: pieDetail
                        }]
                });

//                Highcharts.chart('container12', {
//                    chart: {
//                        type: 'bar'
//                    },
//                    title: {
//                        text: 'Historic World Population by Region'
//                    },
//                    subtitle: {
//                        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
//                    },
//                    xAxis: {
//                        categories: categories,
//                        title: {
//                            text: null
//                        },
//                        labels: {
//                            formatter: function () {
//                                return '<a href="' + categoryLinks[this.value] + '">' +
//                                        this.value + '</a>';
//                            }
//                        }
//                    },
//                    yAxis: {
//                        min: 0,
//                        title: {
//                            text: 'Population (millions)',
//                            align: 'high'
//                        },
//                        labels: {
//                            overflow: 'justify'
//                        }
//                    },
//                    tooltip: {
//                        valueSuffix: ' MT'
//                    },
//                    plotOptions: {
//                        bar: {
//                            dataLabels: {
//                                enabled: true
//                            }
//                        }
//                    },
//                    legend: {
//                        layout: 'vertical',
//                        align: 'right',
//                        verticalAlign: 'top',
//                        x: -40,
//                        y: 80,
//                        floating: false,
//                        borderWidth: 1,
//                        backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
//                        shadow: true
//                    },
//                    credits: {
//                        enabled: false
//                    },
//                    series: allData
//                });
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
                                <h3>Overall Production Municipal</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div id="container" style="width: 100%; height: 800px"></div>
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

