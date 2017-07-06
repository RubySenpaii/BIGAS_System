<%-- 
    Document   : reviewSubmittedDamage
    Created on : Mar 3, 2017, 9:36:52 PM
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

        <title>B.I.G.A.S. System | Municipal - Review Damage Incident Report </title>

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
                                <h3>Incident ID: </h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <div class="row">
                                            <br/>
                                            <div class="col-md-4">
                                                <p style="font-size: medium"><b>Farm Name: </b> </p>
                                                <br/>
                                                <p style="font-size: medium"><b>Seed Planted: </b> </p>
                                            </div>
                                            <div class="col-md-4">
                                                <p style="font-size: medium"><b>Affected Stage: </b> </p>
                                                <br/>
                                                <p style="font-size: medium"><b>Minor Damages (Major): </b> </p>
                                            </div>
                                            <div class="col-md-4">
                                                <p style="font-size: medium"><b>Problem Name: </b> </p>
                                                <br/>
                                                <p style="font-size: medium"><b>Date Affected </b> </p>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <img src="" border="0" height="100%" width="100%">
                                            </div>
                                        </div>
                                        
                                        <div class="ln_solid"></div>
                                        
                                        <div class="text-center">
                                            <button class="btn btn-success">Approve</button>
                                            <button class="btn btn-danger">Reject</button>
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
