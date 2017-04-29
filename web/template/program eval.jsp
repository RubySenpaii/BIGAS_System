<%-- 
    Document   : sample
    Created on : Dec 22, 2016, 9:41:56 PM
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
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <div class="pull-left">
                                            <h3>Program Evaluation Form</h3>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th style="width: 50%">Questionnaire</th>
                                                        <th style="width: 10%; text-align: center">Ineffective</th>
                                                        <th style="width: 10%; text-align: center">Slightly Ineffective</th>
                                                        <th style="width: 10%; text-align: center">Neutral</th>
                                                        <th style="width: 10%; text-align: center">Slightly Effective</th>
                                                        <th style="width: 10%; text-align: center">Effective</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            Is the program effective?
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer" required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer" required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer" required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer" required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer" required>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
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
