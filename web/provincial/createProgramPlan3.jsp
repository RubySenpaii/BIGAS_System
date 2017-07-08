<%-- 
    Document   : createProgramPlan3
    Created on : Jan 1, 2017, 9:03:00 PM
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

        <title>B.I.G.A.S. System | Provincial - Create Program Plan </title>

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
                                <h3>Create Program Plan: Step 3</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <form class="ProvincialProgram">
                                            <div class="col-md-10 col-md-offset-1">
                                                <table class="table table-bordered" >
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 2%"><input type="checkbox" class="check_all" onclick="select_all()"></th>
                                                            <th style="width: 2%">#</th>
                                                            <th style="width: 36%">Phase</th>
                                                            <th style="width: 60%">Activity</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td><input type="checkbox" class="case"></td>
                                                            <td><span id='snum'>1</span></td>
                                                            <td>
                                                                <select style="width: 100%; height: 100%" name="phases">
                                                                    <option>Pre-Implementation</option>
                                                                </select>
                                                            </td>
                                                            <td>
                                                                <textarea style="width: 100%; resize: none" rows="1" maxlength="600" name="activities"></textarea>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                    <button type="button" onclick="delete_row()">- Delete</button>
                                                    <button type="button" onclick="add_row()">+ Add More</button>
                                                </table>
                                            </div>
                                            <br/>
                                            <br/>
                                            <div class="row">
                                                <div class="col-md-4 col-sm-4 col-xs-12 col-md-offset-3">
                                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                                    <button type="submit" class="btn btn-success" name="action" value="createProgramStep3">Submit</button>
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

        <script>
            function delete_row() {
                $('.case:checkbox:checked').parents("tr").remove();
                $('.check_all').prop("checked", false);
                check();
            }
            var i = 2;
            function add_row() {
                count = $('table tr').length;
                var data = "<tr><td><input type='checkbox' class='case'/></td><td><span id='snum" + i + "'>" + count + "</span></td>";
                data += "<td><select style='width: 100%; height: 100%' name='phases'><option>Pre-Implementation</option><option>Implementation</option><option>Post-Implementation</option></select></td><td><textarea style='width: 100% ; resize: none' rows='1' maxlength='600' name='activities'></textarea></td></tr>";
                $('table').append(data);
                i++;
            }

            function select_all() {
                $('input[class=case]:checkbox').each(function () {
                    if ($('input[class=check_all]:checkbox:checked').length === 0) {
                        $(this).prop("checked", false);
                    } else {
                        $(this).prop("checked", true);
                    }
                });
            }

            function check() {
                obj = $('table tr').find('span');
                $.each(obj, function (key, value) {
                    id = value.id;
                    $('#' + id).html(key + 1);
                });
            }
        </script>

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
