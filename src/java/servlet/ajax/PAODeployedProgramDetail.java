/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.ajax;

import dao.DamageReportDAO;
import dao.DeployedProgramDAO;
import extra.Calculator;
import extra.Formatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.DamageReport;
import object.DeployedProgram;
import object.Employee;
import object.ProgramBeneficiary;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author RubySenpaii
 */
public class PAODeployedProgramDetail extends HttpServlet {

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

        JSONArray jarrayAreas = new JSONArray();

        Formatter formatter = new Formatter();
        Calculator calculator = new Calculator();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");

        String targetDate = new DeployedProgramDAO().getInitialDateAffectedOfFarmsInDeployedProgram((int) session.getAttribute("deployedID"));
        Date target = formatter.convertToDate(targetDate);
        DeployedProgram deployed = new DeployedProgramDAO().getDeployedProgramDetails((int) session.getAttribute("deployedID"));
        String dateNow;
        Date now;
        int dateCount = 0;
        if (deployed.getStatus().equals("Completed")) {
            dateNow = deployed.getDateCompleted();
            now = formatter.convertToDate(dateNow);
        } else {
            dateNow = sdf.format(Calendar.getInstance().getTime());
            now = formatter.convertToDate(dateNow);
        }
        do {
            ArrayList<ProgramBeneficiary> beneficiaries = (ArrayList<ProgramBeneficiary>) session.getAttribute("beneficiaries");
            double areaAffected = 0, areaDamaged = 0;
            for (int a = 0; a < beneficiaries.size(); a++) {
                try {
                    System.out.println(dateNow + " " + beneficiaries.get(a).getIncidentID());
                    DamageReport report = new DamageReportDAO().getLatestDamageReportWithIncidentIDBefore(beneficiaries.get(a).getIncidentID(), dateNow);
                    areaAffected += report.getAreaAffected();
                    areaDamaged += report.getAreaDamaged();
                } catch (NullPointerException ex) {
                    System.out.println("no area affected or damaged on this date");
                } catch (IndexOutOfBoundsException x) {
                    System.out.println("out of bounds at date " + dateNow);
                }
            }

            String old[] = dateNow.split("-");
            Calendar oldTime = Calendar.getInstance();
            oldTime.set(Integer.parseInt(old[2]), Integer.parseInt(old[0]), Integer.parseInt(old[1]));
            String newt[] = deployed.getDateStarted().split("-");
            Calendar newTime = Calendar.getInstance();
            newTime.set(Integer.parseInt(newt[2]), Integer.parseInt(newt[0]), Integer.parseInt(newt[1]));
            if (oldTime.before(newTime)) {
                dateCount++;
            } 

            try {
                JSONObject area = new JSONObject();
                area.put("date", dateNow);
                area.put("affected", areaAffected);
                area.put("damaged", areaDamaged);
                jarrayAreas.put(area);
            } catch (JSONException ex) {
                System.err.println(ex);
            }

            dateNow = calculator.getLastWeekDate(dateNow);
            now = formatter.convertToDate(dateNow);
        } while (now.after(target));
        System.out.println("datecount " + dateCount);

        JSONObject jsonObjects = new JSONObject();
        try {
            jsonObjects.put("areas", jarrayAreas);
            jsonObjects.put("dateCount", dateCount);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            System.out.println("data written for transfer to mao/programDeployedDetail.jsp");
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
