<%-- 
    Document   : programDeployment
    Created on : Jan 26, 2017, 5:38:20 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.ImportantProblem"%>
<%@page import="object.Farm"%>
<%@page import="extra.Formatter"%>
<%@page import="object.DamageIncident"%>
<%@page import="object.Fertilizer"%>
<%@page import="object.SeedVariety"%>
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
                                <h3>Program Deployment</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">

                            <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" class="ProvincialProgram">
                                <div class="col-md-7 col-sm-7 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2>Program Provisions and Start Date</h2>
                                            <div class="clearfix"></div>
                                        </div>

                                        <div class="x_content">
                                            <!--div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="municipality">Municipality <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <input type="text" id="municipality" required="required" class="form-control col-md-7 col-xs-12">
                                                </div>
                                            </div-->
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="seed-variety">Seed Variety <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <input list="variety" name="seedVariety" required="required" class="form-control col-md-7 col-xs-12">
                                                    <datalist id="variety">
                                                        <%
                                                            ImportantProblem importantProblem = (ImportantProblem) session.getAttribute("importantProblem");
                                                            ArrayList<SeedVariety> varieties = (ArrayList<SeedVariety>) session.getAttribute("varieties");
                                                            for (int a = 0; a < varieties.size(); a++) {
                                                        %>
                                                        <option value="<%=varieties.get(a).getVarietyName()%>">
                                                            <%
                                                                }
                                                            %>
                                                    </datalist>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="variety-amount">Variety Amount <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <input type="text" id="last-name" name="varietyAmount" required="required" class="form-control col-md-7 col-xs-12">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="fertilizer" class="control-label col-md-3 col-sm-3 col-xs-12">Fertilizer <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <input list="fertilizer" class="form-control col-md-7 col-xs-12" type="text" name="fertilizer" required="required">
                                                    <datalist id="fertilizer">
                                                        <%
                                                            ArrayList<Fertilizer> fertilizers = (ArrayList<Fertilizer>) session.getAttribute("fertilizers");
                                                            for (int a = 0; a < fertilizers.size(); a++) {
                                                        %>
                                                        <option value="<%=fertilizers.get(a).getFertilizerName()%>">
                                                            <%
                                                                }
                                                            %>
                                                    </datalist>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="fertilizer-amount">Fertilizer Amount <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <input type="text" id="last-name" name="fertilizerAmount" required="required" class="form-control col-md-7 col-xs-12">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Start Date <span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <input id="startDate" class="date-picker form-control col-md-7 col-xs-12" name="startDate" required="required" type="text">
                                                </div>
                                            </div>
                                            <div class="ln_solid"></div>
                                            <div class="form-group">
                                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                                    <button type="submit" class="btn btn-success" name="action" value="deployProgram">Submit</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-5 col-sm-5 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2>Problem Details</h2>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">
                                            <div class="col-md-12">
                                                <p style="font-size: medium">
                                                    <b>Problem Name:</b> <%=importantProblem.getProblem().getProblemName()%>
                                                </p>
                                                <p style="font-size: medium">
                                                    <b>Description:</b> <%=importantProblem.getProblem().getDescription()%>
                                                </p>
                                                <br/>
                                            </div>
                                            <table class="table table-bordered col-md-12">
                                                <thead>
                                                    <tr>
                                                        <th style="width: 5%"></th>
                                                        <th style="width: 45%">Farm Name</th>
                                                        <th style="width: 25%">Minor Damaged</th>
                                                        <th style="width: 25%">Major Damaged</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        double totalAffected = 0, totalDamaged = 0;
                                                        ArrayList<DamageIncident> farms = (ArrayList<DamageIncident>) session.getAttribute("farms");
                                                        for (int a = 0; a < farms.size(); a++) {
                                                            totalAffected += farms.get(a).getTotalAreaAffected();
                                                            totalDamaged += farms.get(a).getTotalAreaDamaged();
                                                    %>
                                                    <tr>
                                                        <td><input type="checkbox" name="farmName" value="<%=farms.get(a).getFarmName()%>"></td>
                                                        <td><%=farms.get(a).getFarmName()%></td>
                                                        <td><%=new Formatter().doubleToString(farms.get(a).getTotalAreaAffected())%> ha</td>
                                                        <td><%=new Formatter().doubleToString(farms.get(a).getTotalAreaDamaged())%> ha</td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                                <tfoot>
                                                    <tr>
                                                        <th colspan="2">Total: </th>
                                                        <th><%=new Formatter().doubleToString(totalAffected)%> ha</th>
                                                        <th><%=new Formatter().doubleToString(totalDamaged)%> ha</th>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form>
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
        <!-- bootstrap-daterangepicker -->
        <script src="/BIGAS_System/vendors/moment/moment.js"></script>
        <script src="/BIGAS_System/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

        <!-- Custom Theme Scripts -->
        <script src="/BIGAS_System/build/js/custom.min.js"></script>

        <!-- bootstrap-daterangepicker -->
        <script>
            $(document).ready(function () {
                $('#startDate').daterangepicker({
                    singleDatePicker: true,
                    calender_style: "picker_4"
                }, function (start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                });
            });
        </script>
        <!-- /bootstrap-daterangepicker -->
    </body>
</html>
