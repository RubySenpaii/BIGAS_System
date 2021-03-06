<%-- 
    Document   : damageProgramList
    Created on : Feb 20, 2017, 7:02:03 PM
    Author     : RubySenpaii
--%>

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

        <title>B.I.G.A.S. System | Provincial - Damage Program List </title>

        <!-- Bootstrap -->
        <link href="/BIGAS_System/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="/BIGAS_System/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

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
                                <h3>Manage Damages With Program</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 20%">Problem Name</th>
                                                            <th style="width: 15%">Barangay</th>
                                                            <th style="width: 12%">Farm(s) Affected</th>
                                                            <th style="width: 12%">Minor Damaged</th>
                                                            <th style="width: 12%">Major Damaged</th>
                                                            <th style="width: 12%">Escalation Level</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<DamageIncident> incidents = (ArrayList<DamageIncident>) session.getAttribute("damageIncidents");
                                                            for (int a = 0; a < incidents.size(); a++) {
                                                        %>
                                                        <tr>
                                                            <td><%=incidents.get(a).getProblemName()%></td>
                                                            <td><%=incidents.get(a).getBarangayName()%></td>
                                                            <td><%=incidents.get(a).getFarmsAffected()%></td>
                                                            <td><%=incidents.get(a).getTotalAreaAffected()%></td>
                                                            <td><%=incidents.get(a).getTotalAreaDamaged()%></td>
                                                            <td><%=incidents.get(a).getEscalationLevel()%></td>
                                                            <td>
                                                                <a href="/BIGAS_System/ProvincialDamages?action=goToProgramDamagesOverview&problemID=<%=incidents.get(a).getProblemReported()%>&barangayName=<%=incidents.get(a).getBarangayName()%>" class="btn btn-success">
                                                                    View Detail
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

        <!-- Custom Theme Scripts -->
        <script src="/BIGAS_System/build/js/custom.min.js"></script>
    </body>
</html>
