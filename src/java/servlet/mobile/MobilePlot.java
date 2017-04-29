/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.PlotDAO;
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
import object.Plot;

/**
 *
 * @author RubySenpaii
 */
public class MobilePlot extends HttpServlet {

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
        System.out.println("plot input form mobile upload");
        ArrayList<Plot> plots = new Gson().fromJson(request.getParameter("plots"), new TypeToken<List<Plot>>() {
        }.getType());

        int originalSize = new PlotDAO().getListOfPlots().size();
        int count = 0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement updatePS = conn.prepareStatement("UPDATE " + Plot.TABLE_NAME + 
                    " SET " + Plot.COLUMN_FARMID + " = ?, " + Plot.COLUMN_PLOTID + " = ?, " + Plot.COLUMN_PLOTNUMBER + " = ?, "
                    + Plot.COLUMN_PLOTPLANTED + " = ?, " + Plot.COLUMN_PLOTSIZE + " = ? "
                    + "WHERE " + Plot.COLUMN_PLOTID + " = ?");
            PreparedStatement addPS = conn.prepareStatement("INSERT INTO " + Plot.TABLE_NAME + " "
                    + "(" + Plot.COLUMN_FARMID + ", " + Plot.COLUMN_PLOTID + ", " + Plot.COLUMN_PLOTNUMBER + ", "
                    + Plot.COLUMN_PLOTPLANTED + ", " + Plot.COLUMN_PLOTSIZE + ") "
                    + "VALUES(?, ?, ?, ?, ?)");
            for (int a = 0; a < plots.size(); a++) {
                if (a <= originalSize) {
                    updatePS.setInt(1, plots.get(a).getFarmID());
                    updatePS.setInt(2, plots.get(a).getPlotID());
                    updatePS.setInt(3, plots.get(a).getPlotNumber());
                    updatePS.setInt(4, plots.get(a).getPlotPlanted());
                    updatePS.setDouble(5, plots.get(a).getPlotSize());
                    updatePS.setInt(6, plots.get(a).getPlotID());
                    updatePS.addBatch();
                } else {
                    addPS.setInt(1, plots.get(a).getFarmID());
                    addPS.setInt(2, plots.get(a).getPlotID());
                    addPS.setInt(3, plots.get(a).getPlotNumber());
                    addPS.setInt(4, plots.get(a).getPlotPlanted());
                    addPS.setDouble(5, plots.get(a).getPlotSize());
                    addPS.addBatch();
                }
            }

            int[] adds = addPS.executeBatch();
            int[] updates = updatePS.executeBatch();
            count = adds.length + updates.length;
            System.out.println("added plot rows: " + adds.length);
            System.out.println("updated plot rows: " + updates.length);
            addPS.close();
            updatePS.close();
            conn.close();
        } catch (SQLException x) {
            Logger.getLogger(MobilePlot.class.getName()).log(Level.SEVERE, null, x);
        }
        System.out.println("plot rows affected: " + count);

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
