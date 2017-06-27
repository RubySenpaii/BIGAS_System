/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.MunicipalityDAO;
import dao.NotificationDAO;
import extra.Calculator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.Employee;
import object.Municipality;
import object.Notification;

/**
 *
 * @author RubySenpaii
 */
public abstract class BaseServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            ServletContext context = getServletContext();

            if (request.getParameter("action") != null) {
                String action = request.getParameter("action");
                System.out.println("Attempting to " + action + "...");

                if (action.equals("login")) {
                    servletAction(request, response);
                } else {
                    Employee userLogged = (Employee) session.getAttribute("userLogged");
                    if (userLogged == null) {
                        System.out.println("user is not logged in or has expired");
                        RequestDispatcher rd = context.getRequestDispatcher("/WebLogout");
                        rd.forward(request, response);
                    } else {
                        //updateNotification(request, response);
                        servletAction(request, response);
                    }
                }
            } else {
                System.out.println("Error: No Action Sent! You will now be automatically logged out.");
                session.invalidate();
                RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        }
    }

    /**
     * Update notification will update the notifications that can be seen by the
     * user
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateNotification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        ArrayList<Notification> notifications = new ArrayList<>();
        if (userLogged.getOfficeLevel().equals("PAO")) {
            //notifications = new NotificationDAO().getListOfNotificationsForPAO();
            ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
            for (int a = 0; a < municipalities.size(); a++) {
                ArrayList<Notification> pestDiseasesNotifications = new Calculator().getPestAndDiseaseNotification3(municipalities.get(a).getMunicipalityID());
                notifications.addAll(pestDiseasesNotifications);
            }
        } else if (userLogged.getAuthority().equals("Technician")) {
            //notifications = new NotificationDAO().getListOfNotificationsForTechnician();
        } else {
            //notifications = new NotificationDAO().getListOfNotificationsForMAO();
            ArrayList<Notification> pestDiseasesNotifications = new Calculator().getPestAndDiseaseNotification3(userLogged.getMunicipalityID());
            for (int a = 0; a < pestDiseasesNotifications.size(); a++) {
                notifications.add(0, pestDiseasesNotifications.get(a));
            }
        }
        System.out.println("notifications size: " + notifications.size());
        session.setAttribute("notifications", notifications);
    }

    /**
     * Abstract method will be used in child class for logic. Extension of
     * processRequest method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected abstract void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

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
