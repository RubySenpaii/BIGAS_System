<%-- 
    Document   : problemList
    Created on : Mar 14, 2017, 2:57:27 PM
    Author     : RubySenpaii
--%>

<%@page import="object.Farm"%>
<%@page import="extra.Formatter"%>
<%@page import="extra.ImportantProblem"%>
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

        <title>B.I.G.A.S. System | Municipal - Problem List </title>

        <!-- Bootstrap -->
        <link href="/BIGAS_System/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="/BIGAS_System/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

        <!-- Datatables -->
        <link href="/BIGAS_System/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="/BIGAS_System/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="/BIGAS_System/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="/BIGAS_System/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="/BIGAS_System/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
        
        <!-- Custom Theme Style -->
        <link href="/BIGAS_System/build/css/custom.min.css" rel="stylesheet">
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
                                <h3>Problem List</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <table id="datatable" class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>Problem Name</th>
                                                    <th>Barangay</th>
                                                    <th>Farm(s) Affected</th>
                                                    <th>Values</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    Formatter formatter = new Formatter();
                                                    ArrayList<ImportantProblem> importantProblems = (ArrayList<ImportantProblem>) session.getAttribute("importantProblems");
                                                    for (int a = 0; a < importantProblems.size(); a++) {
                                                        
                                                %>
                                                <tr>
                                                    <td><%=importantProblems.get(a).getProblem().getProblemName()%></td>
                                                    <td><%=importantProblems.get(a).getBarangayName()%></td>
                                                    <td><%=importantProblems.get(a).getFarmAffected()%> farm(s)</td>
                                                    <td>
                                                        <%
                                                            String column = "";
                                                            double percentValue = 0;
                                                            if (importantProblems.get(a).getProblem().getType().equals("Calamity")) {
                                                                column = "Major Damages: " + formatter.doubleToString(importantProblems.get(a).getTotalMajor()) + " ha / " + formatter.doubleToString(importantProblems.get(a).getPlantableArea()) + " ha";
                                                                percentValue = importantProblems.get(a).getTotalMajor() / importantProblems.get(a).getPlantableArea();
                                                            } else if (importantProblems.get(a).getProblem().getType().equals("Underproduction")) {
                                                                
                                                            } else {
                                                                if (importantProblems.get(a).getTotalMajor() / importantProblems.get(a).getPlantableArea() >= 0.03) {
                                                                    column = "Major Damages: " + formatter.doubleToString(importantProblems.get(a).getTotalMajor()) + " ha / " + formatter.doubleToString(importantProblems.get(a).getPlantableArea()) + " ha";
                                                                    percentValue = importantProblems.get(a).getTotalMajor() / importantProblems.get(a).getPlantableArea();
                                                                } else {
                                                                    column = "Minor Damages: " + formatter.doubleToString(importantProblems.get(a).getTotalMinor()) + " ha / " + formatter.doubleToString(importantProblems.get(a).getPlantableArea()) + " ha";
                                                                    percentValue = importantProblems.get(a).getTotalMinor() / importantProblems.get(a).getPlantableArea();
                                                                }
                                                            }
                                                            percentValue *= 100;
                                                        %>
                                                        <%=column%>
                                                        <div class="progress progress_sm">
                                                            <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="<%=percentValue%>"></div>
                                                        </div>
                                                        <small><%=formatter.doubleToString(percentValue)%> %</small>
                                                    </td>
                                                    <td>
                                                        <a href="/BIGAS_System/MunicipalProgram?action=viewRecommendedPrograms&index=<%=a%>" class="btn btn-warning">
                                                            Deploy Program
                                                        </a>
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
                <!-- /page content -->
                <jsp:include page="pageFooter.jsp"/>
            </div>
        </div>

        <!-- jQuery -->
        <script src="/BIGAS_System/vendors/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="/BIGAS_System/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="/BIGAS_System/vendors/fastclick/lib/fastclick.js"></script>
        <!-- NProgress -->
        <script src="/BIGAS_System/vendors/nprogress/nprogress.js"></script>
        <!-- bootstrap-progressbar -->
        <script src="/BIGAS_System/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
        <!-- Datatables -->
        <script src="/BIGAS_System/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
        <script src="/BIGAS_System/vendors/datatables.net-scroller/js/datatables.scroller.min.js"></script>
        <script src="/BIGAS_System/vendors/jszip/dist/jszip.min.js"></script>
        <script src="/BIGAS_System/vendors/pdfmake/build/pdfmake.min.js"></script>
        <script src="/BIGAS_System/vendors/pdfmake/build/vfs_fonts.js"></script>
        
        <!-- Custom Theme Scripts -->
        <script src="/BIGAS_System/build/js/custom.min.js"></script>
        
        
        <!-- Datatables -->
        <script>
            $(document).ready(function () {
                var handleDataTableButtons = function () {
                    if ($("#datatable-buttons").length) {
                        $("#datatable-buttons").DataTable({
                            dom: "Bfrtip",
                            buttons: [
                                {
                                    extend: "copy",
                                    className: "btn-sm"
                                },
                                {
                                    extend: "csv",
                                    className: "btn-sm"
                                },
                                {
                                    extend: "excel",
                                    className: "btn-sm"
                                },
                                {
                                    extend: "pdfHtml5",
                                    className: "btn-sm"
                                },
                                {
                                    extend: "print",
                                    className: "btn-sm"
                                },
                            ],
                            responsive: true
                        });
                    }
                };

                TableManageButtons = function () {
                    "use strict";
                    return {
                        init: function () {
                            handleDataTableButtons();
                        }
                    };
                }();

                $('#datatable').dataTable();
                $('#datatable-keytable').DataTable({
                    keys: true
                });

                $('#datatable-responsive').DataTable();

                $('#datatable-scroller').DataTable({
                    ajax: "js/datatables/json/scroller-demo.json",
                    deferRender: true,
                    scrollY: 380,
                    scrollCollapse: true,
                    scroller: true
                });

                var table = $('#datatable-fixed-header').DataTable({
                    fixedHeader: true
                });

                TableManageButtons.init();
            });
        </script>
        <!-- /Datatables -->
    </body>
</html>
