<%-- 
    Document   : report
    Created on : Mar 23, 2017, 12:03:58 AM
    Author     : RubySenpaii
--%>

<%@page import="extra.Formatter"%>
<%@page import="extra.GenericObject"%>
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
                            <div class="col-md-12 col-lg-12 col-sm-12" role="tabpanel" data-example-id="toggleable-tabs">
                                <!-- tab labels-->
                                <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                    <li role="presentation" class="active">
                                        <a href="#tab_content1" id="irrigated-tab" role="tab" data-toggle="tab" aria-expanded="true">Planted</a>
                                    </li>
                                    <li role="presentation" class>
                                        <a href="#tab_content2" id="rainfed-tab" role="tab" data-toggle="tab" aria-expanded="false">Harvested</a>
                                    </li>
                                    <li role="presentation" class>
                                        <a href="#tab_content3" id="total-tab" role="tab" data-toggle="tab" aria-expanded="false">Damaged</a>
                                    </li>
                                </ul>
                                <!--end: tab labels-->

                                <!--tab contents-->
                                <%
                                    Formatter format = new Formatter();
                                %>
                                <div id="myTabContent" class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="irrigated-tab">
                                        <!--irrigated-->
                                        <div class="report-table">
                                            <table class="table table-bordered"">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th style="text-align: center" colspan="22">Irrigated</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="22">Rainfed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="22">Total</th>
                                                        <th colspan="2"></th>
                                                    </tr>
                                                    <tr>
                                                        <th></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center; min-width: 150px">Barangay</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        ArrayList<GenericObject> planted = (ArrayList<GenericObject>) session.getAttribute("planted");
                                                        for (int a = 0; a < planted.size(); a++) {
                                                    %>
                                                    <tr>
                                                        <td><%=planted.get(a).getAttribute1()%></td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute2()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute3()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute4()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute5()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute6()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute7()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute8()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute9()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute10()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute11()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute12()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute13()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute14()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute15()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute16()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute17()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute18()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute19()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute20()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute21()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute22()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute23()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute24()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute25()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute26()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute27()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute28()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute29()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute30()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute31()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute32()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute33()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute34()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute35()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute36()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute37()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute38()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute39()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute40()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute41()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute42()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute43()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute44()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute45()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute46()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute47()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute48()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute49()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute50()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute51()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute52()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute53()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute54()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute55()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute56()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute57()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute58()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute59()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute60()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute61()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute62()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute63()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute64()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute65()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute66()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute67()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute68()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute69()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute70()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute71()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute72()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(planted.get(a).getAttribute73()))%> ha</td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!--end: irrigated-->
                                    </div>

                                    <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="rainfed-tab">
                                        <!--rainfed-->
                                        <div class="report-table">
                                            <table class="table table-bordered"">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th style="text-align: center" colspan="22">Irrigated</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="22">Rainfed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="22">Total</th>
                                                        <th colspan="2"></th>
                                                    </tr>
                                                    <tr>
                                                        <th></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center; min-width: 150px">Municipal</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        ArrayList<GenericObject> harvested = (ArrayList<GenericObject>) session.getAttribute("harvested");
                                                        for (int a = 0; a < harvested.size(); a++) {
                                                    %>
                                                    <tr>
                                                        <td><%=harvested.get(a).getAttribute1()%> </td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute2()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute3()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute4()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute5()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute6()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute7()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute8()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute9()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute10()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute11()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute12()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute13()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute14()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute15()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute16()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute17()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute18()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute19()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute20()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute21()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute22()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute23()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute24()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute25()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute26()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute27()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute28()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute29()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute30()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute31()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute32()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute33()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute34()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute35()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute36()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute37()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute38()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute39()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute40()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute41()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute42()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute43()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute44()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute45()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute46()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute47()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute48()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute49()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute50()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute51()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute52()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute53()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute54()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute55()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute56()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute57()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute58()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute59()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute60()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute61()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute62()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute63()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute64()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute65()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute66()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute67()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute68()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute69()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute70()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute71()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute72()))%> MT</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(harvested.get(a).getAttribute73()))%> MT</td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!--end: rainfed-->
                                    </div>

                                    <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="total-tab">
                                        <!--total-->
                                        <div class="report-table">
                                            <table class="table table-bordered"">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th style="text-align: center" colspan="22">Irrigated</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="22">Rainfed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="22">Total</th>
                                                        <th colspan="2"></th>
                                                    </tr>
                                                    <tr>
                                                        <th></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                        <th style="text-align: center" colspan="6">Hybrid</th>
                                                        <th style="text-align: center" colspan="5">Registered Seed</th>
                                                        <th style="text-align: center" colspan="6">Certified Seed</th>
                                                        <th style="text-align: center" colspan="5">Good Seed</th>
                                                        <th colspan="2"></th>
                                                    </tr>
                                                    <tr>
                                                        <th style="text-align: center; min-width: 150px">Barangay</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>

                                                        <th style="text-align: center; min-width: 100px">HYTA Program</th>
                                                        <th style="text-align: center; min-width: 100px">HYTA Demo</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchase</th>
                                                        <th style="text-align: center; min-width: 100px">MTD/PTD/Cluster</th>
                                                        <th style="text-align: center; min-width: 100px">PLGU/MLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">CSB</th>
                                                        <th style="text-align: center; min-width: 100px">SG</th>
                                                        <th style="text-align: center; min-width: 100px">MTD</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">Rehab</th>
                                                        <th style="text-align: center; min-width: 100px">3rd Crop</th>
                                                        <th style="text-align: center; min-width: 100px">MLGU/ PLGU</th>
                                                        <th style="text-align: center; min-width: 100px">Direct Purchased</th>
                                                        <th style="text-align: center; min-width: 100px">Others</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from CSB)</th>
                                                        <th style="text-align: center; min-width: 100px">HQS (from untagged)</th>
                                                        <th style="text-align: center; min-width: 100px">FHSS</th>
                                                        <th style="text-align: center; min-width: 100px">GSR</th>
                                                        <th style="text-align: center; min-width: 100px">Total</th>

                                                        <th style="text-align: center; min-width: 100px">UPLAND</th>
                                                        <th style="text-align: center; min-width: 100px">GRAND TOTAL</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        ArrayList<GenericObject> damaged = (ArrayList<GenericObject>) session.getAttribute("damaged");
                                                        for (int a = 0; a < damaged.size(); a++) {
                                                    %>
                                                    <tr>
                                                        <td><%=damaged.get(a).getAttribute1()%> </td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute2()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute3()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute4()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute5()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute6()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute7()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute8()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute9()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute10()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute11()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute12()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute13()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute14()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute15()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute16()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute17()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute18()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute19()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute20()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute21()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute22()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute23()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute24()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute25()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute26()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute27()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute28()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute29()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute30()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute31()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute32()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute33()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute34()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute35()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute36()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute37()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute38()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute39()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute40()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute41()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute42()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute43()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute44()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute45()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute46()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute47()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute48()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute49()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute50()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute51()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute52()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute53()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute54()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute55()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute56()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute57()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute58()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute59()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute60()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute61()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute62()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute63()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute64()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute65()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute66()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute67()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute68()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute69()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute70()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute71()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute72()))%> ha</td>
                                                        <td><%=format.doubleToString(Double.parseDouble(damaged.get(a).getAttribute73()))%> ha</td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                            <!--end: total-->
                                        </div>
                                    </div>
                                </div>
                                <!--end: page content container-->
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
    </body>
</html>
