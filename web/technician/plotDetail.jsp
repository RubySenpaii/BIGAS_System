<%-- 
    Document   : plotDetail
    Created on : Dec 23, 2016, 10:02:59 PM
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

                        <div class="clearfix"></div>

                        <!-- plot details -->
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h4>Plot Detail</h4>
                                    </div>
                                    <div class="x_content">
                                        <div class="col-md-4">
                                            <p><strong>Seed Variety: </strong> Tubigan 13</p>
                                            <p><strong>Crop Stage: </strong> Newly Planted</p>
                                            <p><strong>Date Planted: </strong> December 12, 2016</p>
                                        </div>
                                        <div class="col-md-4">
                                            <p><strong>Amount Harvested </strong> 30 MT</p>
                                            <p><strong>Date Harvested </strong> December 20, 2016</p>
                                        </div>
                                        <div class="col-md-2 col-md-offset-2">
                                            <button>Create Planting Report</button>
                                            <button>Create Harvesting Report</button>
                                            <button>Create Damage Report</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /plot details -->

                        <div class="row">
                            <!-- weekly date reports -->
                            <div class="col-md-2">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h5>Weekly Planting Reports</h5>
                                    </div>
                                    <div class="x_content">
                                        <table class="table table-bordered">
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!-- /weekly date reports -->
                            
                            <!-- weekly reports detail -->
                            <div class="col-md-4">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h5>Weekly Planting Report Details</h5>
                                    </div>
                                    <div class="x_content">
                                        
                                    </div>
                                </div>
                            </div>
                            <!-- /weekly reports detail -->
                            
                            <!-- damage incidents -->
                            <div class="col-md-2">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h5>Incidents Reported</h5>
                                    </div>
                                    <div class="x_content">
                                        
                                    </div>
                                </div>
                            </div>
                            <!-- /damage incidents -->
                            
                            <!-- incident detail -->
                            <div class="col-md-4">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h5>Incident Detail</h5>
                                    </div>
                                    <div class="x_content">
                                        
                                    </div>
                                </div>
                            </div>
                            <!-- /incident detail -->
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
