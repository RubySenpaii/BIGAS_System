<%-- 
    Document   : report
    Created on : Mar 23, 2017, 12:03:58 AM
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
                                <h3>Municipal Reports</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <!--page content container-->
                        <div class="row">
                            <div class="col-md-12 col-lg-12 col-sm-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <!-- generate report buttons -->
                                        <div class="row">
                                            <div class="col-md-12 col-lg-12">
                                                <form class="MunicipalReport" method="">
                                                    <button type="submit" name="action" value="createPlanting" class="btn btn-primary">Generate Planting</button>

                                                    <button type="submit" name="action" value="createGrowthStage" class="btn btn-success">Generate Growth Stage</button>

                                                    <button type="submit" name="action" value="createDamages"class="btn btn-info">Generate Damage</button>

                                                    <button type="submit" name="action" value="createHarvest"class="btn btn-info">Generate Harvest</button>
                                                </form>
                                            </div>
                                        </div>
                                        <!-- /generate report buttons -->
                                    </div>
                                    <div class="x_content">
                                        <!-- view reported -->
                                        <div class="row">
                                            <div class="col-md-2 col-lg-2 col-sm-2">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>Files</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            String[] files = (String[]) session.getAttribute("fileList");
                                                            for (int a = 0; a < files.length; a++) {
                                                        %>
                                                        <tr>
                                                            <td><button onclick="document.getElementById('pdfContainer').src = '/BIGAS_System/PdfViewer?fileName=<%=files[a]%>'"><%=files[a]%></button></td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="col-md-10 col-lg-10 col-sm-10" style="padding-left: 20px">
                                                <iframe id="pdfContainer" style="height: 800px; width: 1200px"></iframe>
                                            </div>
                                        </div>
                                        <!-- /view reports -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /page content -->
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
        <script src="/BIGAS_System/build/js/pdfobject.js"></script>
    </body>
</html>
