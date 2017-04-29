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
import object.DamageReport;
import object.Employee;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RubySenpaii
 */
public class MAODamageDetailArea extends HttpServlet {

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
        
        ArrayList<DamageReport> reports = (ArrayList<DamageReport>) session.getAttribute("damageReports");
        JSONArray jarrayDamageReports = new JSONArray();
        
        for (int a = 0; a < reports.size(); a++) {
            try {
                JSONObject jarrayDamageReport = new JSONObject();
                jarrayDamageReport.put("date", reports.get(a).getDateReported());
                jarrayDamageReport.put("areaAffected", reports.get(a).getAreaAffected());
                jarrayDamageReport.put("areaDamaged", reports.get(a).getAreaDamaged());
                jarrayDamageReports.put(jarrayDamageReport);
            } catch (JSONException ex) {
                Logger.getLogger(MAODamageDetailArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        JSONObject jsonObjects = new JSONObject();
        try {
            jsonObjects.put("damageReports", jarrayDamageReports);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            System.out.println("data written for transfer to mao/damageDetail.jsp");
            response.getWriter().write("[" + jsonObjects.toString() + "]");
        } catch (JSONException ex) {
            Logger.getLogger(MAODashboard.class.getName()).log(Level.SEVERE, null, ex);
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
