<%-- 
    Document   : createProgramPlanSurvey
    Created on : Jul 6, 2017, 2:31:43 PM
    Author     : RubySenpaii
--%>

<%@page import="object.ProgramSurvey"%>
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
                                <h3>Create Program Plan: Step 4</h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_content">
                                        <form class="ProvincialProgram form-horizontal form-label-left">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">Form Name: </label>
                                                    <div class="col-md-7">
                                                        <input class="form-control" type="text" list="surveys" onchange="input_table(this.value)">
                                                        <datalist id="surveys">
                                                            <%
                                                                ArrayList<ProgramSurvey> surveys = (ArrayList<ProgramSurvey>) session.getAttribute("surveys");
                                                                for (int a = 0; a < surveys.size(); a++) {
                                                            %>
                                                            <option><%=surveys.get(a).getType()%></option>
                                                            <%
                                                                }
                                                            %>
                                                        </datalist>
                                                    </div>
                                                </div>
                                            </div>
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th style="width: 2%"><input type="checkbox" class="check_all" onclick="select_all()"></th>
                                                        <th style="width: 10%">Question No</th>
                                                        <th>Question</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td><input type="checkbox" class="case"></td>
                                                        <td><span id='snum'>1</span></td>
                                                        <td>
                                                            <textarea style="width: 100%; resize: none" rows="1" maxlength="600" name="questions"></textarea>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                                <button type="button" onclick="delete_row()">- Delete</button>
                                                <button type="button" onclick="add_row()">+ Add More</button>
                                            </table>
                                            <br/>
                                            <br/>
                                            <div class="row">
                                                <div class="col-md-4 col-sm-4 col-xs-12 col-md-offset-3">
                                                    <button type="submit" class="btn btn-primary">Cancel</button>
                                                    <button type="submit" class="btn btn-success" name="action" value="createProgramStep4">Submit</button>
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
                data += "<td><textarea style='width: 100% ; resize: none' rows='1' maxlength='600' name='questions'></textarea></td></tr>";
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

            var json_values;
            function input_table(type) {
                $.ajax({
                    url: "PAOCreateSurvey",
                    type: 'POST',
                    data: jQuery.param({type: type}),
                    dataType: 'JSON',
                    success: function (json_data) {
                        json_values = json_data;
                        var isSuccessful = json_values[0].success;
                        if (isSuccessful) {
                            var table_data = "<thead><tr><th style='width: 2%'><input type='checkbox' class='check_all' onclick='select_all()'></th><th style='width: 10%'>Question No</th><th>Question</th></tr></thead><tbody>";
                            var surveys = json_values[0].surveys;
                            for (var a = 0; a < surveys.length; a++) {
                                var survey_count = a + 1;
                                var tempData = "";
                                tempData += "<tr><td><input type='checkbox' class='case'/></td><td><span id='snum" + a + "'>" + survey_count + "</span></td>";
                                tempData += "<td><textarea style='width: 100% ; resize: none' rows='1' maxlength='600' name='questions'>" + surveys[a].question + "</textarea></td></tr>"
                                table_data += tempData;
                            }
                            table_data += "</tbody>";
                            $('table').html(table_data);
                        } else {
                            var tempData = "";
                            tempData += "<thead><tr><th style='width: 2%'><input type='checkbox' class='check_all' onclick='select_all()'></th><th style='width: 10%'>Question No</th><th>Question</th></tr></thead>";
                            tempData += "<tbody><tr><td><input type='checkbox' class='case'></td><td><span id='snum'>1</span></td><td><textarea style='width: 100%; resize: none' rows='1' maxlength='600' name='questions'></textarea></td></tr></tbody>"
                            $('table').html(tempData);
                        }
                    },
                    async: false
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
