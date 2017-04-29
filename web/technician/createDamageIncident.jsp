<%-- 
    Document   : createDamageIncident
    Created on : Dec 24, 2016, 2:59:40 PM
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
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Create Damage Incident</h2>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                                                    <!-- damage incident report -->
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Problem <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                            <input type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Crop Stage Affected <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                            <input type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                                                        </div>
                                                    </div>
                                                    <!-- /damage incident report -->
                                                    <div class="ln_solid"></div>
                                                    <!-- weekly damage reports -->
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Area Damaged <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                            <input type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Area Affected <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                            <input type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">Image <span class="required">*</span>
                                                        </label>
                                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                                            <input type="file" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
                                                        </div>
                                                    </div>
                                                    <!-- /weekly damage reports -->
                                                    <div class="ln_solid"></div>
                                                    <div class="form-group">
                                                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                            <button type="submit" class="btn btn-primary">Cancel</button>
                                                            <button type="submit" class="btn btn-success">Submit</button>
                                                        </div>
                                                    </div>

                                                </form>
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
