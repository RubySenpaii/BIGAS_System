/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.FarmDAO;
import dao.FarmerDAO;
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
import object.Farmer;

/**
 *
 * @author RubySenpaii
 */
public class MobileFarmer extends HttpServlet {

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
        System.out.println("farmer input form mobile upload");
        ArrayList<Farmer> farmers = new Gson().fromJson(request.getParameter("farmers"), new TypeToken<List<Farmer>>() {
        }.getType());

        int originalSize = new FarmerDAO().getListOfFarmers().size();
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement addPS = conn.prepareStatement("INSERT INTO " + Farmer.TABLE_NAME + " "
                    + "(" + Farmer.COLUMN_ADDRESS + ", " + Farmer.COLUMN_BIRTHDAY + ", " + Farmer.COLUMN_CONTACTNO + ", " + Farmer.COLUMN_FARMERID + ", "
                    + Farmer.COLUMN_FARMID + ", " + Farmer.COLUMN_FIRSTNAME + ", " + Farmer.COLUMN_FLAG + ", " + Farmer.COLUMN_LASTNAME + ", "
                    + Farmer.COLUMN_MIDDLENAME + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement updatePS = conn.prepareStatement("UPDATE " + Farmer.TABLE_NAME
                    + " SET " + Farmer.COLUMN_ADDRESS + " = ?, " + Farmer.COLUMN_BIRTHDAY + " = ?, " + Farmer.COLUMN_CONTACTNO + " = ?, "
                    + Farmer.COLUMN_FARMERID + " = ?, " + Farmer.COLUMN_FARMID + " = ?, " + Farmer.COLUMN_FIRSTNAME + " = ?, "
                    + Farmer.COLUMN_FLAG + " = ?, " + Farmer.COLUMN_LASTNAME + " = ?, " + Farmer.COLUMN_MIDDLENAME + " = ? "
                    + "WHERE " + Farmer.COLUMN_FARMERID + " = ?");
            for (int a = 0; a < farmers.size(); a++) {
                if (a <= originalSize) {
                    updatePS.setString(1, farmers.get(a).getAddress());
                    updatePS.setString(2, farmers.get(a).getBirthday());
                    updatePS.setString(3, farmers.get(a).getContactNo());
                    updatePS.setInt(4, farmers.get(a).getFarmID());
                    updatePS.setInt(5, farmers.get(a).getFarmID());
                    updatePS.setString(6, farmers.get(a).getFirstName());
                    updatePS.setInt(7, farmers.get(a).getFlag());
                    updatePS.setString(8, farmers.get(a).getLastName());
                    updatePS.setString(9, farmers.get(a).getMiddleName());
                    updatePS.setInt(10, farmers.get(a).getFarmerID());
                    updatePS.addBatch();
                } else {
                    addPS.setString(1, farmers.get(a).getAddress());
                    addPS.setString(2, farmers.get(a).getBirthday());
                    addPS.setString(3, farmers.get(a).getContactNo());
                    addPS.setInt(4, farmers.get(a).getFarmerID());
                    addPS.setInt(5, farmers.get(a).getFarmID());
                    addPS.setString(6, farmers.get(a).getFirstName());
                    addPS.setInt(7, farmers.get(a).getFlag());
                    addPS.setString(8, farmers.get(a).getLastName());
                    addPS.setString(9, farmers.get(a).getMiddleName());
                    addPS.addBatch();
                }
            }

            int[] adds = addPS.executeBatch();
            int[] updates = updatePS.executeBatch();
            count = adds.length + updates.length;
            System.out.println("added farmer rows: " + adds.length);
            System.out.println("updated farmer rows: " + updates.length);
            conn.commit();
            addPS.close();
            updatePS.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MobileFarmer.class.getName()).log(Level.SEVERE, null, x);
        }
        System.out.println("farmer rows affected: " + count);

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
