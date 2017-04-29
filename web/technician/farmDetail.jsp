<%-- 
    Document   : farmDetail
    Created on : Dec 23, 2016, 8:24:37 PM
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
                                <h3>Farm Details</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <!-- plot lists -->
                                            <div class="col-md-9 col-sm-9 col-xs-12">

                                                <div class="col-md-12">

                                                </div>
                                                <div class="clearfix"></div>
                                                <!-- plot sample -->
                                                <div class="col-md-4 col-sm-4 col-xs-6 profile_details">
                                                    <div class="well profile_view">
                                                        <div class="col-sm-12">
                                                            <h3 class="brief"><a href=""><b>Plot Number</b></a></h3>
                                                            <div class="left col-xs-7">
                                                                <p><strong>Seed Planted: </strong> Tubigan 13</p>
                                                                <p><strong>Date Planted: </strong> December 12, 2016 </p>
                                                                <p><strong>Crop Stage: </strong> Newly Planted </p>
                                                            </div>
                                                            <div class="right col-xs-5 text-center">
                                                                <img src="images/user.png" alt="" class="img-circle img-responsive">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-4 col-sm-4 col-xs-6 profile_details">
                                                    <div class="well profile_view">
                                                        <div class="col-sm-12">
                                                            <h3 class="brief"><a href=""><b>Plot Number</b></a></h3>
                                                            <div class="left col-xs-7">
                                                                <p><strong>Seed Planted: </strong> Tubigan 13</p>
                                                                <p><strong>Date Planted: </strong> December 12, 2016 </p>
                                                                <p><strong>Crop Stage: </strong> Newly Planted </p>
                                                            </div>
                                                            <div class="right col-xs-5 text-center">
                                                                <img src="/images/user.png" alt="" class="img-circle img-responsive">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-md-4 col-sm-4 col-xs-6 profile_details">
                                                    <div class="well profile_view">
                                                        <div class="col-sm-12">
                                                            <h3 class="brief"><a href=""><b>Plot Number</b></a></h3>
                                                            <div class="left col-xs-7">
                                                                <p><strong>Seed Planted: </strong> Tubigan 13</p>
                                                                <p><strong>Date Planted: </strong> December 12, 2016 </p>
                                                                <p><strong>Crop Stage: </strong> Newly Planted </p>
                                                            </div>
                                                            <div class="right col-xs-5 text-center">
                                                                <img src="images/user.png" alt="" class="img-circle img-responsive">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /plot sample -->

                                            </div>
                                            <!-- /plot lists -->

                                            <!-- farm details -->
                                            <div class="col-md-3 col-sm-3 col-xs-12">
                                                <section class="panel">

                                                    <div class="x_title">
                                                        <h2>Farm Description</h2>
                                                        <div class="clearfix"></div>
                                                    </div>
                                                    <div class="panel-body">
                                                        <div class="project_detail">
                                                            <p class="title">Address</p>
                                                            <p>Deveint Inc</p>
                                                            <p class="title">Farm Characteristic</p>
                                                            <p>Deveint Inc</p>
                                                            <p class="title">Contact Person</p>
                                                            <p>Deveint Inc</p>
                                                        </div>

                                                        <br />

                                                        <div class="text-center mtop20">
                                                            <a href="#" class="btn btn-sm btn-primary">Change Contact Person</a>
                                                            <a href="#" class="btn btn-sm btn-warning">Edit Plot</a>
                                                        </div>
                                                    </div>

                                                </section>
                                            </div>
                                            <!-- /farm details -->
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
