/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DamageIncidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.DamageIncident;

/**
 *
 * @author RubySenpaii
 */
public class MobileDamageIncident extends HttpServlet {

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
        int addCount = 0;
        int updateCount = 0;

        System.out.println("damageIncident input form mobile upload");
        ArrayList<DamageIncident> incidents = new Gson().fromJson(request.getParameter("damageIncidents"), new TypeToken<List<DamageIncident>>() {
        }.getType());
        System.out.println(incidents.size());
        for (int a = 0; a < incidents.size(); a++) {
            if (a < new DamageIncidentDAO().getListOfDamages().size()) {
                if (new DamageIncidentDAO().updateDamageIncident(incidents.get(a))) {
                    updateCount++;
                }
            } else if (new DamageIncidentDAO().reportDamageIncident(incidents.get(a))) {
                addCount++;
            } else {
                System.out.println(incidents.get(a).getDamageIncidentID()+ " not added/updated");
            }
        }
        System.out.println("damageIncident input count updated: " + updateCount);
        System.out.println("damageIncident input count added: " + addCount);
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
