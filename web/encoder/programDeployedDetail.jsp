<%-- 
    Document   : programDeployedDetail
    Created on : Feb 7, 2017, 8:10:00 PM
    Author     : RubySenpaii
--%>

<%@page import="object.ProgramSurvey"%>
<%@page import="extra.Calculator"%>
<%@page import="object.DeployedEvaluation"%>
<%@page import="object.DeployedProgram"%>
<%@page import="extra.Formatter"%>
<%@page import="object.ProgramBeneficiary"%>
<%@page import="object.ProgramProgress"%>
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

        <title>B.I.G.A.S. System | Municipal - Deployed Program Details </title>

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
//            $(function () {
//                var values;
//
//                $.ajax({
//                    url: "MAODeployedProgramDetail",
//                    type: 'POST',
//                    dataType: 'JSON',
//                    success: function (data) {
//                        values = data;
//                    },
//                    async: false
//                });
//
//                var dates = [];
//                var affected = [];
//                var damaged = [];
//
//                for (var a = values[0].areas.length - 1; a >= 0; a--) {
//                    dates.push(values[0].areas[a].date);
//                    affected.push(values[0].areas[a].affected);
//                    damaged.push(values[0].areas[a].damaged);
//                }
//
//                $('#areaMonitor').highcharts({
//                    chart: {
//                        type: 'line'
//                    },
//                    title: {
//                        text: 'Area Monitoring For ' + dates[0] + ' to ' + dates[dates.length - 1]
//                    },
//                    xAxis: {
//                        categories: dates
//                    },
//                    yAxis: {
//                        title: {
//                            text: 'Hectares (ha)'
//                        }
//                    },
//                    plotOptions: {
//                        line: {
//                            dataLabels: {
//                                enabled: true
//                            },
//                            enableMouseTracking: true
//                        }
//                    },
//                    series: [{
//                            name: 'Minor Damages',
//                            data: affected,
//                            zoneAxis: 'x',
//                            zones: [{
//                                    value: values[0].dateCount + 1
//                                }, {
//                                    color: "#00f000"
//                                }]
//                        }, {
//                            name: 'Major Damages',
//                            data: damaged,
//                            zoneAxis: 'x',
//                            zones: [{
//                                    value: values[0].dateCount + 1
//                                }, {
//                                    color: "#ff0000"
//                                }]
//                        }]
//                });
//            });
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
                                <h3>Deployed Program Detail</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <%
                                            DeployedProgram deployedProgram = (DeployedProgram) session.getAttribute("deployedProgram");
                                            String details = "Deployed ID " + deployedProgram.getDeployedID() + " - " + deployedProgram.getProgramName();
                                        %>
                                        <h2><%=details%></h2>

                                        <div class="pull-right">
                                            <%
                                                if (deployedProgram.getStatus().equals("Completed")) {
                                            %>
                                            <a href="/BIGAS_System/EncoderProgram?action=goToEvaluation&deployedID=<%=deployedProgram.getDeployedID()%>" class="btn btn-success">
                                                Submit Evaluation
                                            </a>
                                            <%
                                                }
                                            %>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <!-- status >
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div id="areaMonitor" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                                            </div>
                                        </div>
                                        <!-- /status -->

                                        <div class="row">
                                            <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                                <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                                    <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Program Progress</a>
                                                    </li>
                                                    <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Participants</a>
                                                    </li>
                                                    <li role="presentation" class=""><a href="#tab_content3" role="tab" id="evaluation-tab" data-toggle="tab" aria-expanded="false">Program Evaluation</a>
                                                    </li>
                                                </ul>
                                                <div id="myTabContent" class="tab-content">
                                                    <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                                <tr>
                                                                    <th style="width: 14%">Procedure No</th>
                                                                    <th style="width: 16%">Phase</th>
                                                                    <th>Activity</th>
                                                                    <th style="width: 12%">Completed</th>
                                                                    <th style="width: 12%">Updated By</th>
                                                                    <th style="width: 15%">Remarks</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%
                                                                    Formatter formatter = new Formatter();
                                                                    ArrayList<ProgramProgress> progress = (ArrayList<ProgramProgress>) session.getAttribute("deployedProgress");
                                                                    for (int a = 0; a < progress.size(); a++) {
                                                                %>
                                                                <tr>
                                                                    <td><%=progress.get(a).getProcedureNumber()%></td>
                                                                    <td><%=progress.get(a).getPhase()%></td>
                                                                    <td><%=progress.get(a).getActivity()%></td>
                                                                    <td><%=progress.get(a).getDateCompleted()%></td>
                                                                    <td><%=progress.get(a).getUpdatedByName()%></td>
                                                                    <td><%=progress.get(a).getRemarks()%></td>
                                                                </tr>
                                                                <%
                                                                    }
                                                                %>
                                                            </tbody>
                                                        </table>

                                                        <%
                                                            if (deployedProgram.getStatus().equals("Ongoing")) {
                                                        %>
                                                        <div class="center">
                                                            <a href="/BIGAS_System/MunicipalProgram?action=incompleteProgram&deployedID=<%=deployedProgram.getDeployedID()%>" class="btn btn-warning">Incomplete</a>
                                                        </div>
                                                        <%
                                                            }
                                                        %>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                                <tr>
                                                                    <th style="width: 20%">Farm Name</th>
                                                                    <th style="width: 15%">Barangay</th>
                                                                    <th style="width: 14%">Current Minor Damaged</th>
                                                                    <th style="width: 14%">Major Damaged</th>
                                                                    <th style="width: 15%">Amount of Seeds Received</th>
                                                                    <th style="width: 15%">Amount of Fertilizer Received</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%
                                                                    ArrayList<ProgramBeneficiary> beneficiaries = (ArrayList<ProgramBeneficiary>) session.getAttribute("beneficiaries");
                                                                    for (int a = 0; a < beneficiaries.size(); a++) {
                                                                %>
                                                                <tr>
                                                                    <td><%=beneficiaries.get(a).getFarmName()%></td>
                                                                    <td><%=beneficiaries.get(a).getBarangay()%></td>
                                                                    <td><%=formatter.doubleToString(beneficiaries.get(a).getAreaAffected())%> ha</td>
                                                                    <td><%=formatter.doubleToString(beneficiaries.get(a).getAreaDamaged())%> ha</td>
                                                                    <td><%=formatter.doubleToString(beneficiaries.get(a).getVarietyReceived())%> kg</td>
                                                                    <td><%=formatter.doubleToString(beneficiaries.get(a).getFertilizerReceived())%> kg</td>
                                                                </tr>
                                                                <%
                                                                    }
                                                                %>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="evaluation-tab">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                                <tr>
                                                                    <th style='width: 15%'>Evaluator's Name</th>
                                                                        <%
                                                                            ArrayList<ProgramSurvey> survey = (ArrayList<ProgramSurvey>) session.getAttribute("survey");
                                                                            for (int a = 0; a < survey.size(); a++) {
                                                                        %>
                                                                    <th style='width: 7%'>Question <%=a + 1%></th>
                                                                        <%
                                                                            }
                                                                        %>
                                                                    <th style="width: 15%">Remarks</th>
                                                                    <th style='width: 15%'>Rating</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%
                                                                    ArrayList<DeployedEvaluation> evaluations = (ArrayList<DeployedEvaluation>) session.getAttribute("deployedEvaluations");
                                                                    double averageRating = 0;
                                                                    Calculator calc = new Calculator();
                                                                    for (int a = 0; a < evaluations.size(); a++) {
                                                                        double result = calc.getRespondentResult(evaluations.get(a).getEvaluationValues());
                                                                        averageRating += result;
                                                                        String rating = calc.getEffectivityResult(result);
                                                                %>
                                                                <tr>
                                                                    <td><%=evaluations.get(a).getRespondentName()%></td>
                                                                    <%
                                                                        String[] answers = evaluations.get(a).getEvaluationValues().split(",");
                                                                        for (int b = 0; b < answers.length; b++) {
                                                                    %>
                                                                    <td><%=answers[b]%></td>
                                                                    <%
                                                                        }
                                                                    %>
                                                                    <td><%=evaluations.get(a).getFeedback()%></td>
                                                                    <td><%=rating%> (<%=formatter.doubleToString(result)%>)</td>
                                                                </tr>
                                                                <%
                                                                    }
                                                                    averageRating /= evaluations.size();
                                                                    String avgRating = calc.getEffectivityResult(averageRating);
                                                                %>
                                                            </tbody>
                                                            <tfoot>
                                                                <tr>
                                                                    <th colspan="<%=survey.size() + 2%>">Average Rating</th>
                                                                    <th><%=avgRating%> (<%=formatter.doubleToString(averageRating)%>)</th>
                                                                </tr>
                                                            </tfoot>
                                                        </table>
                                                    </div>
                                                </div>
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
