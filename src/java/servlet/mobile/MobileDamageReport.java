/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DamageIncidentDAO;
import dao.DamageReportDAO;
import db.DBConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.DamageIncident;
import object.DamageReport;

/**
 *
 * @author RubySenpaii
 */
public class MobileDamageReport extends HttpServlet {

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
        ArrayList<DamageReport> damageReports = new Gson().fromJson(request.getParameter("damageReports"), new TypeToken<List<DamageReport>>() {
        }.getType());
        System.out.println(damageReports.size() + " DamageReport inputs from mobile upload");

        int originalSize = new DamageReportDAO().getListOfDamageReports().size();
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement addPS = conn.prepareStatement("INSERT INTO " + DamageReport.TABLE_NAME + " "
                    + "(" + DamageReport.COLUMN_AREAAFFECTED + ", " + DamageReport.COLUMN_AREADAMAGED + ", " + DamageReport.COLUMN_DAMAGEINCIDENTID + ", "
                    + DamageReport.COLUMN_DATEREPORTED + ", " + DamageReport.COLUMN_IMAGE + ") "
                    + "VALUES(?, ?, ?, ?, ?)");
            PreparedStatement updatePS = conn.prepareStatement("");
            for (int a = 0; a < damageReports.size(); a++) {
                if (a < originalSize) {
                    //cannot update
                } else {
                    addPS.setDouble(1, damageReports.get(a).getAreaAffected());
                    addPS.setDouble(2, damageReports.get(a).getAreaDamaged());
                    addPS.setInt(3, damageReports.get(a).getDamageIncidentID());
                    addPS.setString(4, damageReports.get(a).getDateReported());
                    addPS.setString(5, damageReports.get(a).getImage());
                    addPS.addBatch();
                }
            }

            int[] adds = addPS.executeBatch();
            int[] updates = updatePS.executeBatch();
            count = adds.length + updates.length;
            System.out.println("added damage report rows: " + adds.length);
            System.out.println("updated damage report rows: " + updates.length);
            conn.commit();
            addPS.close();
            updatePS.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MobileFarmer.class.getName()).log(Level.SEVERE, null, x);
        }
        System.out.println("damage report rows affected: " + count);

        response.getWriter().write(new Gson().toJson("done download"));
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
