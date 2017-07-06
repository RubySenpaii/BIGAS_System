<%-- 
    Document   : approveDamages
    Created on : Dec 28, 2016, 9:30:44 PM
    Author     : RubySenpaii
--%>

<%@page import="object.Barangay"%>
<%@page import="extra.Formatter"%>
<%@page import="object.Problem"%>
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

        <title>B.I.G.A.S. System | Provincial - Approved Damage List </title>

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
                                <h3>Manage Approved Damages</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table id="datatable" class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 18%">Problem Name</th>
                                                            <th style="width: 15%">Barangay</th>
                                                            <th style="width: 10%">Date Affected</th>
                                                            <th style="width: 10%">Farm(s) Affected</th>
                                                            <th style="width: 10%">Minor Damages</th>
                                                            <th style="width: 10%">Major Damages</th>
                                                            <th style="width: 10%">Total Area</th>
                                                            <th style="width: 10%">Escalation Level</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            Formatter formatter = new Formatter();
                                                            ArrayList<DamageIncident> incidents = (ArrayList<DamageIncident>) session.getAttribute("damageIncidents");
                                                            for (int a = 0; a < incidents.size(); a++) {
                                                                String date = new Formatter().valuesToDate(incidents.get(a).getDamagedMonth(), incidents.get(a).getDamagedDay(), incidents.get(a).getDamagedYear());
                                                        %>
                                                        <tr>
                                                            <td><%=incidents.get(a).getProblemName()%></td>
                                                            <td><%=incidents.get(a).getBarangayName()%></td>
                                                            <td><%=date%></td>
                                                            <td><%=incidents.get(a).getFarmsAffected()%> farm(s)</td>
                                                            <td><%=formatter.doubleToString(incidents.get(a).getTotalAreaAffected())%> ha</td>
                                                            <td><%=formatter.doubleToString(incidents.get(a).getTotalAreaDamaged())%> ha</td>
                                                            <td><%=formatter.doubleToString(incidents.get(a).getPlantableSize())%> ha</td>
                                                            <td><%=incidents.get(a).getEscalationLevel()%></td>
                                                            <td>
                                                                <a href="/BIGAS_System/ProvincialDamages?action=goToApprovedDamagesOverview&problemID=<%=incidents.get(a).getProblemReported()%>&barangayName=<%=incidents.get(a).getBarangayName()%>" class="btn btn-success">
                                                                    View Detail
                                                                </a>
                                                                <!--a href="/BIGAS_System/MunicipalProgram?action=viewRecommendedPrograms&problemID=<%=incidents.get(a).getProblemReported()%>&barangayName=<%=incidents.get(a).getBarangayName()%>" class="btn btn-warning">
                                                                    Deploy Program
                                                                </a-->
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

        <!-- jQuery -->
        <script src="/BIGAS_System/vendors/jquery/dist/jquery.min.js"></script>

        <!-- Bootstrap -->
        <script src="/BIGAS_System/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="/BIGAS_System/vendors/fastclick/lib/fastclick.js"></script>
        <!-- NProgress -->
        <script src="/BIGAS_System/vendors/nprogress/nprogress.js"></script>
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
