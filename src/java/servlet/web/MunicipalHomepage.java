/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.DamageIncidentDAO;
import dao.DeployedProgramDAO;
import dao.MunicipalityDAO;
import dao.TargetProductionDAO;
import extra.Calculator;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.DamageIncident;
import object.DeployedProgram;
import object.Employee;
import object.Municipality;
import object.TargetProduction;

/**
 *
 * @author RubySenpaii
 */
public class MunicipalHomepage extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("MAO") && !userLogged.getAuthority().equals("Technician")) {
            if (action.equals("goToHomePage")) {
                System.out.println("directing to homepage.jsp...");
                goToHomepage(request, response);
                path = "/municipal/homepage.jsp";
            } else {
                //unknown action
            }
        } else {
            //redirect to restricted access
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    private void goToHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int year = new Calculator().getYear();
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        TargetProduction targetProduction = new TargetProductionDAO().getTargetProductionForMunicipalOnYear(userLogged.getMunicipalityID(), year);
        ArrayList<DamageIncident> newIncidents = new DamageIncidentDAO().getListOfDamagesByStatus("Reported");
        ArrayList<DeployedProgram> deployedPrograms = new DeployedProgramDAO().getListOfOngoingProgramsForMunicipality(userLogged.getMunicipalityID());
        Municipality municipality = new MunicipalityDAO().getMunicipalityProductionForYear(userLogged.getMunicipalityID(), year);

        session.setAttribute("municipality", municipality);
        session.setAttribute("deployedPrograms", deployedPrograms);
        session.setAttribute("newIncidents", newIncidents);
        session.setAttribute("targetProduction", targetProduction);
    }
}
