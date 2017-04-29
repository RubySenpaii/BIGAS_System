<%-- 
    Document   : damagedApprovedOverview
    Created on : Jan 23, 2017, 7:15:13 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.Formatter"%>
<%@page import="object.DamageIncident"%>
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
                Highcharts.chart('damagedAffected', {
                    chart: {
                        type: 'line'
                    },
                    title: {
                        text: 'Weekly Affected and Damaged'
                    },
                    xAxis: {
                        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                    },
                    yAxis: {
                        title: {
                            text: 'Area (ha)'
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
                            name: 'Area Damaged',
                            data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
                        }, {
                            name: 'Area Affected',
                            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
                        }]
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
                                <h3>Manage Approved Damages</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>



                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <!--div class="row">
                                            <div class="col-md-12">
                                                <div id="damagedAffected" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                            </div>
                                        </div-->

                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 10%">Incident ID</th>
                                                            <th style="width: 10%">Status</th>
                                                            <th style="width: 20%">Farm Name</th>
                                                            <th style="width: 15%">Date Infected</th>
                                                            <th style="width: 12%">Minor Damaged</th>
                                                            <th style="width: 12%">Major Damaged</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            Formatter formatter = new Formatter();
                                                            ArrayList<DamageIncident> incidents = (ArrayList<DamageIncident>) session.getAttribute("damageIncidents");
                                                            for (int a = 0; a < incidents.size(); a++) {
                                                                String dateAffected = new Formatter().valuesToDate(incidents.get(a).getDamagedMonth(), incidents.get(a).getDamagedDay(), incidents.get(a).getDamagedYear());
                                                        %>
                                                        <tr>
                                                            <td><%=incidents.get(a).getDamageIncidentID()%></td>
                                                            <td><%=incidents.get(a).getStatus()%></td>
                                                            <td><%=incidents.get(a).getFarmName()%></td>
                                                            <td><%=dateAffected%></td>
                                                            <td><%=formatter.doubleToString(incidents.get(a).getTotalAreaAffected())%> ha</td>
                                                            <td><%=formatter.doubleToString(incidents.get(a).getTotalAreaDamaged())%> ha</td>
                                                            <td>
                                                                <form action="ProvincialDamages">
                                                                    <input type="hidden" name="incidentID" value="<%=incidents.get(a).getDamageIncidentID()%>">
                                                                    <button class="btn btn-success" name="action" value="viewDamageIncidentDetails">
                                                                        View Details
                                                                    </button>
                                                                </form>
                                                            </td>
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
