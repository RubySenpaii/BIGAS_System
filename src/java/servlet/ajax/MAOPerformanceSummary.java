/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.Barangay;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RubySenpaii
 */
public class MAOPerformanceSummary extends HttpServlet {

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
        
        JSONArray jarrrayBarangays = new JSONArray();
        ArrayList<Barangay> barangays = (ArrayList<Barangay>) session.getAttribute("barangays");
        for (int a = 0; a < barangays.size(); a++) {
            try {
                JSONObject barangay = new JSONObject();
                barangay.put("barangayName", barangays.get(a).getBarangayName());
                barangay.put("newlyPlanted", barangays.get(a).getNewlyPlantedArea());
                barangay.put("tillering", barangays.get(a).getTilleringArea());
                barangay.put("reproductive", barangays.get(a).getReproductiveArea());
                barangay.put("harvesting", barangays.get(a).getHarvestingArea());
                barangay.put("totalPlanted", barangays.get(a).getPlantedArea());
                barangay.put("minor", barangays.get(a).getMinorDamagedArea());
                barangay.put("major", barangays.get(a).getMajorDamagedArea());
                barangay.put("totalDamage", barangays.get(a).getMajorDamagedArea() + barangays.get(a).getMinorDamagedArea());
                barangay.put("undamagedArea", barangays.get(a).getPlantedArea() - barangays.get(a).getMinorDamagedArea() - barangays.get(a).getMajorDamagedArea());
                barangay.put("unplanted", barangays.get(a).getUnplantedArea());
                barangay.put("plantable", barangays.get(a).getArea());
                jarrrayBarangays.put(barangay);
            } catch (JSONException ex) {
                System.err.println(ex);
            }
        }
        JSONObject jsonObjects = new JSONObject();
        try {
            jsonObjects.put("barangays", jarrrayBarangays);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            System.out.println("data written for transfer to mao/performanceSummary.jsp");
            response.getWriter().write("[" + jsonObjects.toString() + "]");
        } catch (JSONException ex) {
            Logger.getLogger(MAOPerformanceSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
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
