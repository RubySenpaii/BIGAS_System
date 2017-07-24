/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.BarangayDAO;
import dao.DamageIncidentDAO;
import dao.DeployedProgramDAO;
import dao.FarmDAO;
import dao.FarmerDAO;
import dao.MunicipalityDAO;
import dao.PlantingReportDAO;
import dao.PlotDAO;
import dao.TargetProductionDAO;
import dao.WeeklyPlantingReportDAO;
import extra.Calculator;
import extra.Formatter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.Barangay;
import object.DamageIncident;
import object.DeployedProgram;
import object.Employee;
import object.Farm;
import object.Farmer;
import object.Municipality;
import object.PlantingReport;
import object.Plot;
import object.TargetProduction;
import object.WeeklyPlantingReport;

/**
 *
 * @author RubySenpaii
 */
public class MunicipalPerformance extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("MAO") && !userLogged.getAuthority().equals("Technician")) {
            if (action.equals("monitorAreaPlanted")) {
                System.out.println("directing to monitorAreaPlanted.jsp...");
                viewCropAreaMonitoring(request, response);
                path = "/municipal/monitorAreaPlanted.jsp";
            } else if (action.equals("monitorCropGrowth")) {
                System.out.println("directing to monitorCropGrowth.jsp...");
                viewCropGrowthMonitoring(request, response);
                path = "/municipal/monitorCropGrowth.jsp";
            } else if (action.equals("monitorCropHarvest")) {
                System.out.println("directing to monitorHarvest.jsp...");
                viewCropHarvestMonitoring(request, response);
                path = "/municipal/monitorHarvest.jsp";
            } else if (action.equals("viewBarangayPerformance")) {
                System.out.println("directing to performanceBarangay.jsp...");
                viewBarangayPerformance(request, response);
                path = "/municipal/performanceBarangay.jsp";
            } else if (action.equals("viewFarmPerformance")) {
                System.out.println("directing to performanceFarm.jsp...");
                viewFarmPerformance(request, response);
                path = "/municipal/performanceFarm.jsp";
            } else if (action.equals("viewFarmDetail")) {
                System.out.println("directing to farmDetail.jsp...");
                viewFarmDetails(request, response);
                path = "/municipal/farmDetail.jsp";
            } else {
                //unknown action
            }
        } else {
            //redirect to restricted access
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    private void viewCropAreaMonitoring(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        ArrayList<Barangay> currentWeekBarangay = new BarangayDAO().getBarangayAreaMonitoring(userLogged.getMunicipalityID(), date);

        session.setAttribute("currentBarangays", currentWeekBarangay);
    }

    private void viewCropGrowthMonitoring(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(userLogged.getMunicipalityID());
        Municipality municipal = new MunicipalityDAO().getMunicipalDetail(userLogged.getMunicipalityID());
        ArrayList<Barangay> barangayCropStage = new BarangayDAO().getBarangayCropGrowthMonitoring(municipal.getMunicipalityName(), date);
        for (int a = 0; a < barangays.size(); a++) {
            for (int b = 0; b < barangayCropStage.size(); b++) {
                if (barangays.get(a).getBarangayName().equals(barangayCropStage.get(b).getBarangayName())) {
                    if (barangayCropStage.get(b).getCropStage().equals("Newly Planted")) {
                        barangays.get(a).setNewlyPlantedArea(barangayCropStage.get(b).getArea());
                    } else if (barangayCropStage.get(b).getCropStage().equals("Tillering")) {
                        barangays.get(a).setTilleringArea(barangayCropStage.get(b).getArea());
                    } else if (barangayCropStage.get(b).getCropStage().equals("Reproductive")) {
                        barangays.get(a).setReproductiveArea(barangayCropStage.get(b).getArea());
                    } else {
                        barangays.get(a).setHarvestingArea(barangayCropStage.get(b).getArea());
                    }
                }
            }
        }

        session.setAttribute("barangays", barangays);
    }

    private void viewCropHarvestMonitoring(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(userLogged.getMunicipalityID());
        for (int a = 0; a < barangays.size(); a++) {
            double plantedArea = 0, harvested = 0, majorDamaged = 0, minorDamaged = 0;
            ArrayList<PlantingReport> plantingReports = new PlantingReportDAO().getListOfOngoingPlantingReportsInBarangay(barangays.get(a).getBarangayID());
            for (int b = 0; b < plantingReports.size(); b++) {
                double plotSize = new PlotDAO().getPlotDetails(plantingReports.get(b).getPlotID()).getPlotSize();
                harvested += plantingReports.get(b).getAmountHarvested();

                double tempDamages = 0, tempMinor = 0;
                ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getDamageIncidentForPlantingReportBefore(plantingReports.get(b).getPlantingReportID(), date);
                for (int c = 0; c < incidents.size(); c++) {
                    majorDamaged += incidents.get(c).getTotalAreaDamaged();
                    tempDamages += incidents.get(c).getTotalAreaDamaged();
                    minorDamaged += incidents.get(c).getTotalAreaAffected();
                    tempMinor += incidents.get(c).getTotalAreaAffected();
                }

                plantedArea += (plotSize - tempDamages - tempMinor);
            }
            ArrayList<Farm> farms = new FarmDAO().getListOfFarmsInBarangay(barangays.get(a).getBarangayName());

            barangays.get(a).setPlantedArea(plantedArea);
            barangays.get(a).setHarvest(harvested);
            barangays.get(a).setMajorDamagedArea(majorDamaged);
            barangays.get(a).setFarmCount(farms.size());
        }

        session.setAttribute("barangays", barangays);
    }

    private void viewBarangayPerformance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        int year = new Calculator().getYear();
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangayProductionInMunicipality(userLogged.getMunicipalityID(), year);
        TargetProduction targetProduction = new TargetProductionDAO().getTargetProductionForMunicipalOnYear(userLogged.getMunicipalityID(), year);
        Municipality municipality = new MunicipalityDAO().getMunicipalityProductionForYear(userLogged.getMunicipalityID(), year);

        session.setAttribute("year", year);
        session.setAttribute("barangays", barangays);
        session.setAttribute("municipality", municipality);
        session.setAttribute("targetProduction", targetProduction);
    }

    private void viewFarmPerformance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String barangayName = request.getParameter("brgyName");
        int year = new Calculator().getYear();
        ArrayList<Farm> farms = new FarmDAO().getListOfFarmsProductionInBarangay(barangayName, year);

        session.setAttribute("year", year);
        session.setAttribute("barangaySelected", barangayName);
        session.setAttribute("farms", farms);
    }

    private void viewFarmDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String farmName = request.getParameter("farmName");
        Farm farm = new FarmDAO().getFarmDetailOnFarmName(farmName);
        Farmer farmer = new FarmerDAO().getFarmerDetaillsOnFarm(farm.getFarmID());
        ArrayList<Plot> plots = new PlotDAO().getListOfActivePlotsInFarm(farm);
        ArrayList<PlantingReport> plantingReports = new PlantingReportDAO().getListOfPlantingReportsInFarm(farm.getFarmID());
        for (int a = 0; a < plantingReports.size(); a++) {
            String date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
            try {
                WeeklyPlantingReport weeklyPlantingReport = new WeeklyPlantingReportDAO().getLatestWeeklyPlantingReportForPlantingReportID(plantingReports.get(a).getPlantingReportID());
                if (plantingReports.get(a).getAmountHarvested() > 0) {
                    plantingReports.get(a).setStage("Harvested");
                } else {
                    plantingReports.get(a).setStage(weeklyPlantingReport.getCropStage());
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Index out of bounds at line 205 " + ex);
                System.out.println("no weekly planting reports at plantingreportid " + plantingReports.get(a).getPlantingReportID());
                plantingReports.get(a).setStage("Unknown");
            }

            double damagedArea = 0;
            ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getDamageIncidentForPlantingReportBefore(plantingReports.get(a).getPlantingReportID(), date);
            for (int d = 0; d < incidents.size(); d++) {
                damagedArea += incidents.get(d).getTotalAreaDamaged();
            }

            plantingReports.get(a).setAreaDamaged(damagedArea);
        }

        ArrayList<DeployedProgram> deployed = new DeployedProgramDAO().getListOfDeployedProgramsForFarm(farm.getFarmID());

        session.setAttribute("farm", farm);
        session.setAttribute("farmer", farmer);
        session.setAttribute("plots", plots);
        session.setAttribute("plantingReports", plantingReports);
        session.setAttribute("deployed", deployed);
    }

    private ArrayList<Barangay> getBarangayArea(int municipalityID, String date) {
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipalityID);
        for (int a = 0; a < barangays.size(); a++) {
            double plantableArea = 0, plantedArea = 0, unplantedArea = 0, majorDamagedArea = 0, minorDamagedArea = 0;

            ArrayList<PlantingReport> plantingReports = new PlantingReportDAO().getListOfOngoingPlantingReportsInBarangayBefore(barangays.get(a).getBarangayID(), date);
            ArrayList<Plot> plotsAvailable = new PlotDAO().getListOfPlotsInBarangay(barangays.get(a).getBarangayID());
            for (int b = 0; b < plotsAvailable.size(); b++) {
                plantableArea += plotsAvailable.get(b).getPlotSize();
                if (plantingReports.isEmpty()) {
                    unplantedArea += plotsAvailable.get(b).getPlotSize();
                } else {
                    for (int c = 0; c < plantingReports.size(); c++) {
                        //check if plot is planted or not
                        if (plotsAvailable.get(b).getPlotID() == plantingReports.get(c).getPlotID()) {
                            //plot is planted
                            int plantingReportID = plantingReports.get(c).getPlantingReportID();

                            //check if plot has damages
                            double tempDamages = 0, tempMinor = 0;
                            ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getDamageIncidentForPlantingReportBefore(plantingReportID, date);
                            for (int d = 0; d < incidents.size(); d++) {
                                majorDamagedArea += incidents.get(d).getTotalAreaDamaged();
                                minorDamagedArea += incidents.get(d).getTotalAreaAffected();
                                tempDamages += incidents.get(d).getTotalAreaDamaged();
                                tempMinor += incidents.get(d).getTotalAreaAffected();
                            }

                            //get planted area
                            plantedArea += (plotsAvailable.get(b).getPlotSize() - tempDamages - tempMinor);
                            break;
                        } else if (c == plantingReports.size() - 1) {
                            //plot is unplanted
                            unplantedArea += plotsAvailable.get(b).getPlotSize();
                        }
                    }
                }
            }

            barangays.get(a).setPlantedArea(plantedArea);
            barangays.get(a).setUnplantedArea(unplantedArea);
            barangays.get(a).setMajorDamagedArea(majorDamagedArea);
            barangays.get(a).setMinorDamagedArea(minorDamagedArea);
            barangays.get(a).setPlantableArea(plantableArea);
        }
        return barangays;
    }
}
