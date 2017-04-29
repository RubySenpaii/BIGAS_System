<%-- 
    Document   : createProgramPlan2
    Created on : Jan 1, 2017, 8:57:57 PM
    Author     : RubySenpaii
--%>

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
                                <h3>Create Program Plan: Step 2</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <form class="ProvincialProgram">
                                            <div class="col-md-4 col-md-offset-3">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>Requirement Name</th>
                                                            <th>Requirement Value</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>Farm(s) Affected</td>
                                                            <td><input type="text" name="farmsAffected" value="0"></td>
                                                        </tr>
                                                        <%
                                                            String problemType = (String) session.getAttribute("problemType");
                                                            if (problemType.equals("Calamity")) {
                                                        %>
                                                        <tr>
                                                            <td>Major Damaged Area</td>
                                                            <td><input type="text" name="majorDamaged" value="0"></td>
                                                        </tr>
                                                        <%
                                                            } else if (problemType.equals("Underproduction")) {
                                                        %>
                                                        <tr>
                                                            <td>Underproduction</td>
                                                            <td><input type="text" name="underproduction" value="0"></td>
                                                        </tr>
                                                        <%
                                                            } else {
                                                        %>
                                                        <tr>
                                                            <td>Minor Damaged Area</td>
                                                            <td><input type="text" name="minorDamaged" value="0"></td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <br/>
                                            <br/>
                                            <div class="row">
                                                <div class="col-md-4 col-sm-4 col-xs-12 col-md-offset-3">
                                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                                    <button type="submit" class="btn btn-success" name="action" value="createProgramStep2">Next</button>
                                                </div>
                                            </div>

                                        </form>
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
