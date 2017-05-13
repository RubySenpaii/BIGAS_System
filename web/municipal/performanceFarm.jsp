<%-- 
    Document   : performanceMonitoring
    Created on : Mar 3, 2017, 11:45:11 PM
    Author     : RubySenpaii
--%>

<%@page import="object.Farm"%>
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
                    url: "MAOPerformanceFarm",
                    type: 'POST',
                    dataType: 'JSON',
                    success: function (data) {
                        values = data;
                    },
                    async: false
                });
                
                var pieDetail = [];
                /*var categoryLinks = {};
                var categories = [];
                var farmValues = [];*/
                for (var a = 0; a < values[0].farms.length; a++) {
                    //categories.push(values[0].farms[a].farmName);
                    //farmValues.push(values[0].farms[a].harvest);
                    //categoryLinks[values[0].farms[a].farmName] = 'http://localhost:8084/BIGAS_System/MunicipalPerformance?action=viewFarmDetail&farmName=' + values[0].farms[a].farmName;
                    
                    var pied = {};
                    pied['name'] = values[0].farms[a].farmName;
                    pied['y'] = values[0].farms[a].harvest;
                    pied['key'] = values[0].farms[a].farmName;
                    pieDetail.push(pied);
                }
                
                /*Highcharts.chart('container', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: 'Production Total'
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
                
                
                /*var yearData = {};
                yearData['name'] = values[0].farms[0].year;
                yearData['data'] = farmValues;
                var allData = [];
                allData.push(yearData);*/

                Highcharts.chart('container', {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Production for San Rafael'
                    },
                    xAxis: {
                        type: 'category',
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
                                '<td style="padding:0"><b>{point.y:.1f} MT</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        series: {
                          cursor: 'pointer',
                          point: {
                              events: {
                                  click: function() {
                                      location.href = 'http://localhost:8084/BIGAS_System/MunicipalPerformance?action=viewFarmDetail&farmName=' + this.options.key;
                                  }
                              }
                          }
                        },
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0
                        }
                    },
                    series: [{
                            name: 'Production',
                            data: pieDetail
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
                                <h3>Compare Farm Production</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <%
                                            double pixels = ((ArrayList<Farm>) session.getAttribute("farms")).size() * 100;
                                        %>
                                        <div id="container" style="width: 100%; height: 500px"></div>
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

