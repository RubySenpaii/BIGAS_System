<%-- 
    Document   : pageSidebar
    Created on : Dec 22, 2016, 10:09:08 PM
    Author     : RubySenpaii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <a href="/BIGAS_System/MunicipalHomepage?action=goToHomePage" class="site_title"><span>&nbsp;&nbsp;B.I.G.A.S. System</span></a>
        </div>
        <div class="clearfix"></div>


        <br />

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                    <li><a href="/BIGAS_System/MunicipalHomepage?action=goToHomePage"><i class="fa fa-home"></i>Home Page </a></li>
                    <li><a href="/BIGAS_System/MunicipalPerformance?action=performanceSummary"><i class="fa fa-tachometer"></i>Current Week Summary</a></li>
                        <!--ul class="nav child_menu">
                            <li><a href="/BIGAS_System/MunicipalPerformance?action=monitorAreaPlanted">Farm Area</a></li>
                            <li><a href="/BIGAS_System/MunicipalPerformance?action=monitorCropGrowth">Crop Growth</a></li>
                            <!--li><a href="/BIGAS_System/MunicipalPerformance?action=monitorCropHarvest">Crop Harvest</a></li>
                        </ul>
                    </li-->
                    <li><a><i class="fa fa-bug"></i>Damage Incident <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="/BIGAS_System/MunicipalDamages?action=goToApprovedDamages">Approved Damages</a></li>
                            <li><a href="/BIGAS_System/MunicipalDamages?action=goToProgramDamages">Incidents With Program</a></li>
                            <li><a href="/BIGAS_System/MunicipalDamages?action=viewIncidentHistory">Incidents History</a></li>
                        </ul>
                    </li>
                    <li><a href="/BIGAS_System/MunicipalProgram?action=viewProblemRecommendation"><i class="fa fa-home"></i>Critical Damages </a></li>
                    <li><a href="/BIGAS_System/MunicipalDamages?action=viewProblems"><i class="fa fa-home"></i>Problem List </a></li>
                    <li><a href="/BIGAS_System/MunicipalPerformance?action=viewBarangayPerformance"><i class="fa fa-line-chart"></i>Barangay Monitoring </a></li>
                    <li><a><i class="fa fa-laptop"></i>Program <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="/BIGAS_System/MunicipalProgram?action=goToListOfOngoingPrograms">Ongoing Deployed Program</a></li>
                            <li><a href="/BIGAS_System/MunicipalProgram?action=viewRequestList">Program Requests</a></li>
                            <li><a href="/BIGAS_System/MunicipalProgram?action=viewProgramList">Program List</a></li>
                        </ul>
                    </li>
                    <li><a href="/BIGAS_System/MunicipalReport?action=viewReports"><i class="fa fa-home"></i>Report </a></li>
                </ul>
            </div>
            <div class="menu_section">
                <h3>Additional</h3>
                <ul class="nav side-menu">
                    <li><a href="/BIGAS_System/MunicipalFarmAssignment?action=goToTechnicianList"><i class="fa fa-thumb-tack"></i>Technician List </a></li>
                </ul>
            </div>

        </div>
        <!-- /sidebar menu -->

        <!-- /menu footer buttons -->
        <div class="sidebar-footer hidden-small">
            <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            </a>
        </div>
        <!-- /menu footer buttons -->
    </div>
</div>

