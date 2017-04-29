<%-- 
    Document   : technicianActivity
    Created on : Jan 1, 2017, 10:42:34 AM
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
        <!-- Progress Bar -->
        <link href="/BIGAS_System/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">

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
                                <h3>Technician Activity</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="col-md-3 col-sm-3 col-xs-12 profile_left">
                                            <h3>Technician Name</h3>
                                            <ul class="list-unstyled user_data">
                                                <li><i class="fa fa-map-marker user-profile-icon"></i> San Francisco, California, USA
                                                </li>

                                                <li>
                                                    <i class="fa fa-briefcase user-profile-icon"></i> Software Engineer
                                                </li>

                                                <li class="m-top-xs">
                                                    <i class="fa fa-external-link user-profile-icon"></i>
                                                    <a href="http://www.kimlabs.com/profile/" target="_blank">www.kimlabs.com</a>
                                                </li>
                                            </ul>
                                            <a class="btn btn-success"><i class="fa fa-edit m-right-xs"></i>Edit Profile</a>
                                            <br />
                                            <ul class="list-unstyled user_data">
                                                <li>
                                                    <p>Web Applications</p>
                                                    <div class="progress progress_sm">
                                                        <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="50"></div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                        
                                        <div class="col-md-9 col-sm-9 col-xs-12">
                                            <h3>Recent Reports</h3>
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>Report ID</th>
                                                        <th>Report Type</th>
                                                        <th>Farm Name</th>
                                                        <th>Date Reported</th>
                                                    </tr>
                                                </thead>
                                            </table>
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
        <!-- bootstrap-progressbar -->
        <script src="/BIGAS_System/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>

        <!-- Custom Theme Scripts -->
        <script src="/BIGAS_System/build/js/custom.min.js"></script>
    </body>
</html>
