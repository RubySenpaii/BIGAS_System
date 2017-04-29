<%-- 
    Document   : programEvaluationForm
    Created on : Mar 15, 2017, 1:06:06 PM
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
                                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left MunicipalEvaluation">
                                            <div class="form-group">
                                                <label class="form-label-left">Respondent Name</label>
                                                <input class="form-control" name="respondentName" required>
                                            </div>
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th style="width: 40%">Questionnaire</th>
                                                        <th style="width: 10%; text-align: center">Not Applicable</th>
                                                        <th style="width: 10%; text-align: center">Strongly Disagree</th>
                                                        <th style="width: 10%; text-align: center">Slightly Disagree</th>
                                                        <th style="width: 10%; text-align: center">Neutral</th>
                                                        <th style="width: 10%; text-align: center">Slightly Agree</th>
                                                        <th style="width: 10%; text-align: center">Strongly Agree</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            Materials provided aids in solving and preventing the problem.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer1" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer1" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer1" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer1" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer1" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer1" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Objectives of the program was clearly defined.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer2" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer2" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer2" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer2" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer2" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer2" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Activities are done were relevant to the farm.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer3" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer3" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer3" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer3" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer3" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer3" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            The program was easy to understand and apply.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer4" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer4" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer4" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer4" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer4" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer4" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Programs implemented improves farmer's knowledge and skills in farming.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer5" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer5" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer5" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer5" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer5" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer5" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Materials are received in good condition and in a timely manner.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer6" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer6" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer6" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer6" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer6" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer6" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Time allotted was sufficient.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer7" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer7" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer7" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer7" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer7" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer7" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            The program met farmer's expectations.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer8" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer8" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer8" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer8" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer8" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer8" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            The program is recommendable to other farmers.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer9" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer9" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer9" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer9" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer9" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer9" value='5' required>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            The program helped farmers identify the problem.
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer10" value='0' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer10" value='1' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer10" value='2' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer10" value='3' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer10" value='4' required>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <input type="radio" name="quesAnswer10" value='5' required>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            
                                            <div class="form-group">
                                                <label class="form-label-left">Summary</label>
                                                <textarea class="form-control"></textarea>
                                            </div>
                                            <div class="ln_solid"></div>
                                            <div class="form-group">
                                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                    <button type="reset" class="btn btn-primary">Reset</button>
                                                    <button type="submit" class="btn btn-success" name='action' value='submitEvaluation'>Submit</button>
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
