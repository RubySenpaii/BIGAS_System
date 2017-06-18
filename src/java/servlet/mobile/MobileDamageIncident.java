/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DamageIncidentDAO;
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

        int originalSize = new DamageIncidentDAO().getListOfDamages().size();
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement addPS = conn.prepareStatement("INSERT INTO " + DamageIncident.TABLE_NAME + " "
                    + "(" + DamageIncident.COLUMN_APPROVEDBY + ", " + DamageIncident.COLUMN_CROPSTAGE + ", " + DamageIncident.COLUMN_DAMAGEDDAY + ", "
                    + DamageIncident.COLUMN_DAMAGEDMONTH + ", " + DamageIncident.COLUMN_DAMAGEDYEAR + ", " + DamageIncident.COLUMN_DAMAGEINCIDENTID + ", "
                    + DamageIncident.COLUMN_ESCALATIONLEVEL + ", " + DamageIncident.COLUMN_PLANTINGREPORTID + ", " + DamageIncident.COLUMN_PROBLEMREPORTED + ", "
                    + DamageIncident.COLUMN_REMARKS + ", " + DamageIncident.COLUMN_REPORTEDBY + ", " + DamageIncident.COLUMN_STATUS + ", "
                    + DamageIncident.COLUMN_DEPLOYEDID + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement updatePS = conn.prepareStatement("UPDATE " + DamageIncident.TABLE_NAME
                    + " SET " + DamageIncident.COLUMN_APPROVEDBY + " = ?, " + DamageIncident.COLUMN_CROPSTAGE + " = ?, " + DamageIncident.COLUMN_DAMAGEDDAY + " = ?, "
                    + DamageIncident.COLUMN_DAMAGEDMONTH + " = ?, " + DamageIncident.COLUMN_DAMAGEDYEAR + " = ?, " + DamageIncident.COLUMN_DAMAGEINCIDENTID + " = ?, "
                    + DamageIncident.COLUMN_ESCALATIONLEVEL + " = ?, " + DamageIncident.COLUMN_PLANTINGREPORTID + " = ?, " + DamageIncident.COLUMN_PROBLEMREPORTED + " = ?, "
                    + DamageIncident.COLUMN_REMARKS + " = ?, " + DamageIncident.COLUMN_REPORTEDBY + " = ?, " + DamageIncident.COLUMN_DEPLOYEDID + " = ?, "
                    + DamageIncident.COLUMN_STATUS + " = ? "
                    + "WHERE " + DamageIncident.COLUMN_DAMAGEINCIDENTID + " = ?");
            for (int a = 0; a < incidents.size(); a++) {
                if (a <= originalSize) {
                    updatePS.setInt(1, incidents.get(a).getApprovedBy());
                    updatePS.setString(2, incidents.get(a).getCropStage());
                    updatePS.setInt(3, incidents.get(a).getDamagedDay());
                    updatePS.setInt(4, incidents.get(a).getDamagedMonth());
                    updatePS.setInt(5, incidents.get(a).getDamagedYear());
                    updatePS.setInt(6, incidents.get(a).getDamageIncidentID());
                    updatePS.setString(7, incidents.get(a).getEscalationLevel());
                    updatePS.setInt(8, incidents.get(a).getPlantingReportID());
                    updatePS.setInt(9, incidents.get(a).getProblemReported());
                    updatePS.setString(10, incidents.get(a).getRemarks());
                    updatePS.setInt(11, incidents.get(a).getReportedBy());
                    updatePS.setInt(12, incidents.get(a).getDeployedID());
                    updatePS.setString(13, incidents.get(a).getStatus());
                    updatePS.setInt(14, incidents.get(a).getDamageIncidentID());
                    updatePS.addBatch();
                } else {
                    addPS.setInt(1, incidents.get(a).getApprovedBy());
                    addPS.setString(2, incidents.get(a).getCropStage());
                    addPS.setInt(3, incidents.get(a).getDamagedDay());
                    addPS.setInt(4, incidents.get(a).getDamagedMonth());
                    addPS.setInt(5, incidents.get(a).getDamagedYear());
                    addPS.setInt(6, incidents.get(a).getDamageIncidentID());
                    addPS.setString(7, incidents.get(a).getEscalationLevel());
                    addPS.setInt(8, incidents.get(a).getPlantingReportID());
                    addPS.setInt(9, incidents.get(a).getProblemReported());
                    addPS.setString(10, incidents.get(a).getRemarks());
                    addPS.setInt(11, incidents.get(a).getReportedBy());
                    addPS.setString(12, incidents.get(a).getStatus());
                    addPS.setInt(13, incidents.get(a).getDeployedID());
                    addPS.addBatch();
                }
            }

            int[] adds = addPS.executeBatch();
            int[] updates = updatePS.executeBatch();
            count = adds.length + updates.length;
            System.out.println("added incident rows: " + adds.length);
            System.out.println("updated incident rows: " + updates.length);
            conn.commit();
            addPS.close();
            updatePS.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MobileFarmer.class.getName()).log(Level.SEVERE, null, x);
        }
        System.out.println("incident rows affected: " + count);

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
