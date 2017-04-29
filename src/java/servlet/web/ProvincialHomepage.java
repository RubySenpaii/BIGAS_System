/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.DamageIncidentDAO;
import dao.DeployedProgramDAO;
import dao.MunicipalityDAO;
import dao.PlantingReportDAO;
import dao.TargetProductionDAO;
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
import object.DamageIncident;
import object.DeployedProgram;
import object.Employee;
import object.Municipality;
import object.TargetProduction;

/**
 *
 * @author RubySenpaii
 */
public class ProvincialHomepage extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        Employee userLogged = (Employee) session.getAttribute("userLogged");
        String action = request.getParameter("action");

        String path = "/WebLogin";
        if (userLogged.getOfficeLevel().equals("PAO")) {
            if (action.equals("goToHomePage")) {
                System.out.println("directing to homepage.jsp...");
                goToHomepage(request, response);
                path = "/provincial/homepage.jsp";
            } else {
                //unknown action
            }
        } else {
            //invalid user
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    private void goToHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int year = new Calculator().getYear();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        TargetProduction targetProduction = new TargetProductionDAO().getTotalTargetProductionForYear(year);
        ArrayList<DeployedProgram> deployedPrograms = new ArrayList<>();
        ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
        double totalProduction = 0;
        for (int a = 0; a < municipalities.size(); a++) {
            try {
                Municipality municipality = new MunicipalityDAO().getMunicipalityProductionForYear(municipalities.get(a).getMunicipalityID(), year);
                totalProduction += municipality.getHarvestedTotal();
                deployedPrograms.addAll(new DeployedProgramDAO().getListOfOngoingProgramsForMunicipality(municipalities.get(a).getMunicipalityID()));
            } catch (IndexOutOfBoundsException x) {
                System.err.println("index out of bounds " + x + " on municipalid " + a);
            }
        }

        session.setAttribute("totalProduction", totalProduction);
        session.setAttribute("deployedPrograms", deployedPrograms);
        session.setAttribute("targetProduction", targetProduction);
    }
}
