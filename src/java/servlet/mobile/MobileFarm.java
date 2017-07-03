/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.FarmDAO;
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
import object.Farm;

/**
 *
 * @author RubySenpaii
 */
public class MobileFarm extends HttpServlet {

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
        ArrayList<Farm> farms = new Gson().fromJson(request.getParameter("farms"), new TypeToken<List<Farm>>() {
        }.getType());
        System.out.println(farms.size() + "farm inputs from mobile upload");
        
        int originalSize = new FarmDAO().getListOfFarms().size();
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement updatePS = conn.prepareStatement("UPDATE " + Farm.TABLE_NAME
                    + " SET " + Farm.COLUMN_ADDRESS + " = ?, " + Farm.COLUMN_ASSIGNEDTECHNICIAN + " = ?, " + Farm.COLUMN_BARANGAYID + " = ?, "
                    + Farm.COLUMN_DATEVISITED + " = ?, " + Farm.COLUMN_FARMABLEAREA + " = ?, " + Farm.COLUMN_FARMID + " = ?, "
                    + Farm.COLUMN_FARMNAME + " = ?, " + Farm.COLUMN_FLAG + " = ?, " + Farm.COLUMN_IRRIGATIONMETHOD + " = ?, "
                    + Farm.COLUMN_LANDELEVATION + " = ?, " + Farm.COLUMN_LATITUDE + " = ?, " + Farm.COLUMN_LONGITUDE + " = ? "
                    + "WHERE " + Farm.COLUMN_FARMID + " = ?");
            PreparedStatement addPS = conn.prepareStatement("INSERT INTO " + Farm.TABLE_NAME + " "
                    + "(" + Farm.COLUMN_ADDRESS + ", " + Farm.COLUMN_BARANGAYID + ", " + Farm.COLUMN_DATEVISITED + ", " + Farm.COLUMN_FARMABLEAREA + ", "
                    + Farm.COLUMN_FARMID + ", " + Farm.COLUMN_FARMNAME + ", " + Farm.COLUMN_FLAG + ", " + Farm.COLUMN_IRRIGATIONMETHOD + ", "
                    + Farm.COLUMN_LANDELEVATION + ", " + Farm.COLUMN_LATITUDE + ", " + Farm.COLUMN_LONGITUDE + ", " + Farm.COLUMN_ASSIGNEDTECHNICIAN + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (int a = 0; a < farms.size(); a++) {
                if (a < originalSize) {
                    updatePS.setString(1, farms.get(a).getAddress());
                    updatePS.setInt(2, farms.get(a).getAssignedTechnician());
                    updatePS.setInt(3, farms.get(a).getBarangayID());
                    updatePS.setString(4, farms.get(a).getDateVisited());
                    updatePS.setDouble(5, farms.get(a).getFarmableArea());
                    updatePS.setInt(6, farms.get(a).getFarmID());
                    updatePS.setString(7, farms.get(a).getFarmName());
                    updatePS.setInt(8, farms.get(a).getFlag());
                    updatePS.setString(9, farms.get(a).getIrrigationMethod());
                    updatePS.setString(10, farms.get(a).getLandElevation());
                    updatePS.setDouble(11, farms.get(a).getLatitude());
                    updatePS.setDouble(12, farms.get(a).getLongitude());
                    updatePS.setInt(13, farms.get(a).getFarmID());
                    updatePS.addBatch();
                } else {
                    addPS.setString(1, farms.get(a).getAddress());
                    addPS.setInt(2, farms.get(a).getBarangayID());
                    addPS.setString(3, farms.get(a).getDateVisited());
                    addPS.setDouble(4, farms.get(a).getFarmableArea());
                    addPS.setInt(5, farms.get(a).getFarmID());
                    addPS.setString(6, farms.get(a).getFarmName());
                    addPS.setInt(7, farms.get(a).getFlag());
                    addPS.setString(8, farms.get(a).getIrrigationMethod());
                    addPS.setString(9, farms.get(a).getLandElevation());
                    addPS.setDouble(10, farms.get(a).getLatitude());
                    addPS.setDouble(11, farms.get(a).getLongitude());
                    addPS.setInt(12, farms.get(a).getAssignedTechnician());
                    addPS.addBatch();
                }
            }

            int[] adds = addPS.executeBatch();
            int[] updates = updatePS.executeBatch();
            count = adds.length + updates.length;
            System.out.println("added farm rows: " + adds.length);
            System.out.println("updated farm rows: " + updates.length);
            conn.commit();
            addPS.close();
            updatePS.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MobileFarm.class.getName()).log(Level.SEVERE, null, x);
        }
        System.out.println("farm rows affected: " + count);
        
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
