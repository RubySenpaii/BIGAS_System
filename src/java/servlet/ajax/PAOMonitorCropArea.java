/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.ajax;

import dao.BarangayDAO;
import dao.DamageIncidentDAO;
import dao.MunicipalityDAO;
import dao.PlantingReportDAO;
import dao.PlotDAO;
import extra.Calculator;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.Barangay;
import object.DamageIncident;
import object.Employee;
import object.Municipality;
import object.PlantingReport;
import object.Plot;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RubySenpaii
 */
public class PAOMonitorCropArea extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get additional info for populating charts
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        String currentDate = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        String lastDate = new Calculator().getLastWeekDate(currentDate);
        System.out.println(currentDate + " | " + lastDate);

        JSONObject jsonObjects = new JSONObject();
        try {
            jsonObjects.put("lastWeek", getLastWeek(lastDate));
            jsonObjects.put("thisWeek", getThisWeek(currentDate));

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            System.out.println("data written for transfer to pao/monitorCropArea.jsp");
            response.getWriter().write("[" + jsonObjects.toString() + "]");
        } catch (JSONException ex) {
            Logger.getLogger(PAOMonitorCropArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JSONArray getLastWeek(String date) {
        JSONArray jarrayArea = new JSONArray();
        double planted = 0, unplanted = 0, major = 0, minor = 0;
        ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
        for (int z = 0; z < municipalities.size(); z++) {
            ArrayList<Barangay> barangays = getBarangayAreaOnDate(municipalities.get(z).getMunicipalityID(), date);
            for (int a = 0; a < barangays.size(); a++) {
                planted += barangays.get(a).getPlantedArea();
                unplanted += barangays.get(a).getUnplantedArea();
                minor += barangays.get(a).getMinorDamagedArea();
                major += barangays.get(a).getMajorDamagedArea();
            }
        }
        try {
            JSONObject production = new JSONObject();
            production.put("planted", planted);
            production.put("unplanted", unplanted);
            production.put("minor", minor);
            production.put("major", major);
            jarrayArea.put(production);
        } catch (JSONException ex) {
            System.err.println(ex);
        }
        return jarrayArea;
    }

    private JSONArray getThisWeek(String date) {
        JSONArray jarrayArea = new JSONArray();
        double planted = 0, unplanted = 0, major = 0, minor = 0;
        ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
        for (int z = 0; z < municipalities.size(); z++) {
            ArrayList<Barangay> barangays = getBarangayArea(municipalities.get(z).getMunicipalityID(), date);
            for (int a = 0; a < barangays.size(); a++) {
                planted += barangays.get(a).getPlantedArea();
                unplanted += barangays.get(a).getUnplantedArea();
                minor += barangays.get(a).getMinorDamagedArea();
                major += barangays.get(a).getMajorDamagedArea();
            }
        }
        try {
            JSONObject production = new JSONObject();
            production.put("planted", planted);
            production.put("unplanted", unplanted);
            production.put("minor", minor);
            production.put("major", major);
            jarrayArea.put(production);
        } catch (JSONException ex) {
            System.err.println(ex);
        }
        return jarrayArea;
    }

    private ArrayList<Barangay> getBarangayArea(int municipalityID, String date) {
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipalityID);
        for (int a = 0; a < barangays.size(); a++) {
            double plantableArea = 0, plantedArea = 0, unplantedArea = 0, majorDamaged = 0, minorDamaged = 0;

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
                                majorDamaged += incidents.get(d).getTotalAreaDamaged();
                                tempDamages += incidents.get(d).getTotalAreaDamaged();
                                minorDamaged += incidents.get(d).getTotalAreaAffected();
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
            barangays.get(a).setMajorDamagedArea(majorDamaged);
            barangays.get(a).setMinorDamagedArea(minorDamaged);
            barangays.get(a).setPlantableArea(plantableArea);
        }
        return barangays;
    }

    private ArrayList<Barangay> getBarangayAreaOnDate(int municipalityID, String date) {
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipalityID);
        for (int a = 0; a < barangays.size(); a++) {
            double plantableArea = 0, plantedArea = 0, unplantedArea = 0, majorDamged = 0, minorDamaged = 0;

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
                                majorDamged += incidents.get(d).getTotalAreaDamaged();
                                tempDamages += incidents.get(d).getTotalAreaDamaged();
                                minorDamaged += incidents.get(d).getTotalAreaAffected();
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
            barangays.get(a).setMajorDamagedArea(majorDamged);
            barangays.get(a).setMinorDamagedArea(minorDamaged);
            barangays.get(a).setPlantableArea(plantableArea);
        }
        return barangays;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
