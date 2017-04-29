/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.ajax;

import dao.BarangayDAO;
import dao.DamageIncidentDAO;
import dao.PlantingReportDAO;
import dao.PlotDAO;
import dao.WeeklyPlantingReportDAO;
import extra.Calculator;
import extra.Formatter;
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
import object.PlantingReport;
import object.Plot;
import object.WeeklyPlantingReport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RubySenpaii
 */
public class MAODashboard extends HttpServlet {

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

        JSONObject jsonObjects = new JSONObject();
        try {
            jsonObjects.put("cropProduction", getProduction(userLogged.getMunicipalityID()));

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            System.out.println("data written for transfer to mao/homepage.jsp");
            response.getWriter().write("[" + jsonObjects.toString() + "]");
        } catch (JSONException ex) {
            Logger.getLogger(MAODashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private JSONArray getProduction(int municipalityID) {
        JSONArray jarrayProduction = new JSONArray();
        int year = new Calculator().getYear();
        
        ArrayList<PlantingReport> harvestAmount = new PlantingReportDAO().getTotalHarvestForMunicipalAndYearBySeasonSeedTypeAndAcquisition(municipalityID, year);
        for (int a = 0; a < harvestAmount.size(); a++) {
            try {
                JSONObject production = new JSONObject();
                production.put("season", harvestAmount.get(a).getSeason());
                production.put("type", harvestAmount.get(a).getSeedType());
                production.put("acquisition", harvestAmount.get(a).getSeedAcquisition());
                production.put("harvest", harvestAmount.get(a).getAmountHarvested());
                jarrayProduction.put(production);
            } catch (JSONException ex) {
                System.err.println(ex);
            }
        }
        return jarrayProduction;
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
