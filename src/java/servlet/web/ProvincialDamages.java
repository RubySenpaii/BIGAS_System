/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.BarangayDAO;
import dao.DamageIncidentDAO;
import dao.DamageReportDAO;
import dao.DeployedProgramDAO;
import dao.EmployeeDAO;
import dao.MunicipalityDAO;
import dao.PlotDAO;
import dao.ProblemDAO;
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
import object.Barangay;
import object.DamageIncident;
import object.DamageReport;
import object.DeployedProgram;
import object.Employee;
import object.Municipality;
import object.Plot;
import object.Problem;

/**
 *
 * @author RubySenpaii
 */
public class ProvincialDamages extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("PAO")) {
            if (action.equals("goToApprovedDamages")) {
                System.out.println("directing to damageApprovedList.jsp...");
                goToApprovedDamages(request, response);
                path = "/provincial/damageApprovedList.jsp";
            } else if (action.equals("goToMunicipalApproved")) {
                System.out.println("directing to damageMunicipalApprovedList.jsp...");
                goToMunicipalApprovedDamages(request, response);
                path = "/provincial/damageMunicipalApprovedList.jsp";
            } else if (action.equals("goToProgramDamages")) {
                System.out.println("directing to damageProgramList.jsp...");
                goToProgramDamages(request, response);
                path = "/provincial/damageProgramList.jsp";
            } else if (action.equals("goToMunicipalProgramDamages")) {
                System.out.println("directing to damageMunicipalProgramList.jsp...");
                goToMunicipalProgramDamages(request, response);
                path = "/provincial/damageMunicipalProgramList.jsp";
            } else if (action.equals("goToApprovedDamagesOverview")) {
                System.out.println("directing to damageApprovedOverview.jsp...");
                goToApprovedDamagesOverview(request, response);
                path = "/provincial/damageOverview.jsp";
            } else if (action.equals("goToProgramDamagesOverview")) {
                System.out.println("directing to damageProgramOverview.jsp...");
                goToProgramDamagesOverview(request, response);
                path = "/provincial/damageOverview.jsp";
            } else if (action.equals("viewDamageIncidentDetails")) {
                System.out.println("directing to damageDetail.jsp");
                viewDamageIncidentDetails(request, response);
                path = "/provincial/damageDetail.jsp";
            } else if (action.equals("reviewDamages")) {
                System.out.println("directing to damageReview.jsp");
                reviewDamages(request, response);
                path = "/provincial/damageReview.jsp";
            } else if (action.equals("viewIncidentHistory")) {
                System.out.println("directing to damageList.jsp");
                viewIncidentHistory(request, response);
                path = "/provincial/damageList.jsp";
            } else if (action.equals("changeDamageStatus")) {
                System.out.println("changing damage status");
                changeDamageStatus(request, response);
                path = "/MunicipalDamages?action=goToApprovedDamages";
            } else {
                //unknown action
            }
        } else {
            //redirect to restricted access
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    private void goToMunicipalApprovedDamages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ArrayList<DamageIncident> damageIncidents = new DamageIncidentDAO().getApprovedDamageListForProvincial();
        for (int a = 0; a < damageIncidents.size(); a++) {
            Municipality municipality = new MunicipalityDAO().getMunicipalDetail(damageIncidents.get(a).getMunicipalityName());
            double plantableSize = 0;
            ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipality.getMunicipalityID());
            for (int c = 0; c < barangays.size(); c++) {
                ArrayList<Plot> plots = new PlotDAO().getListOfPlotsInBarangay(barangays.get(c).getBarangayID());
                for (int d = 0; d < plots.size(); d++) {
                    plantableSize += plots.get(d).getPlotSize();
                }
            }
            damageIncidents.get(a).setPlantableSize(plantableSize);
        }

        session.setAttribute("damageIncidents", damageIncidents);
    }

    private void goToApprovedDamages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String municipalityName = request.getParameter("municipalityName");
        int problemID = Integer.parseInt(request.getParameter("problemID"));
        Municipality municipality = new MunicipalityDAO().getMunicipalDetail(municipalityName);
        ArrayList<DamageIncident> damageIncidents = new DamageIncidentDAO().getBarangayProblemListWithApprovedDamagesInMunicipalityWithProblem(municipality.getMunicipalityID(), problemID);
        

        for (int a = 0; a < damageIncidents.size(); a++) {
            double plantableSize = 0;
            Barangay barangay = new BarangayDAO().getBarangayInfoWithBrgyName(damageIncidents.get(a).getBarangayName());
            ArrayList<Plot> plots = new PlotDAO().getListOfPlotsInBarangay(barangay.getBarangayID());
            for (int b = 0; b < plots.size(); b++) {
                plantableSize += plots.get(b).getPlotSize();
            }
            damageIncidents.get(a).setPlantableSize(plantableSize);
        }

        session.setAttribute("damageIncidents", damageIncidents);
    }

    private void goToProgramDamages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String municipalityName = request.getParameter("municipalityName");
        int problemID = Integer.parseInt(request.getParameter("problemID"));
        Municipality municipality = new MunicipalityDAO().getMunicipalDetail(municipalityName);
        ArrayList<DamageIncident> damageIncidents = new DamageIncidentDAO().getBarangayProblemListWithProgramDamagesInMunicipality(municipality.getMunicipalityID());
        for (int a = 0; a < damageIncidents.size(); a++) {
            if (damageIncidents.get(a).getProblemReported() != problemID) {
                damageIncidents.remove(damageIncidents.get(a));
                a--;
            }
        }
        

        session.setAttribute("damageIncidents", damageIncidents);
    }

    private void goToMunicipalProgramDamages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<DamageIncident> damageIncidents = new DamageIncidentDAO().getProgramDamageListForProvincial();

        session.setAttribute("damageIncidents", damageIncidents);
    }

    private void goToApprovedDamagesOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        int problemID = Integer.parseInt(request.getParameter("problemID"));
        String barangayName = request.getParameter("barangayName");
        ArrayList<DamageIncident> damageIncidents = new DamageIncidentDAO().getFarmListWithApprovedProblemInBarangay(problemID, barangayName);

        session.setAttribute("damageIncidents", damageIncidents);
    }

    private void goToProgramDamagesOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        int problemID = Integer.parseInt(request.getParameter("problemID"));
        String barangayName = request.getParameter("barangayName");
        ArrayList<DamageIncident> damageIncidents = new DamageIncidentDAO().getFarmListWithProgramProblemInBarangay(problemID, barangayName);

        session.setAttribute("damageIncidents", damageIncidents);
    }

    private void viewDamageIncidentDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int incidentID = Integer.parseInt(request.getParameter("incidentID"));
        DamageIncident damageIncident = new DamageIncidentDAO().getIncidentDetailForFullInfoViewing(incidentID);
        DeployedProgram deployedProgram = new DeployedProgramDAO().getDeployedProgramDetails(damageIncident.getDeployedID());
        ArrayList<DamageReport> damageReports = new DamageReportDAO().getListOfDamageReportWithIncidentID(incidentID);

        session.setAttribute("damageIncident", damageIncident);
        session.setAttribute("deployedProgram", deployedProgram);
        session.setAttribute("damageReports", damageReports);
    }

    private void reviewDamages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int incidentID = Integer.parseInt(request.getParameter("incidentID"));
        DamageIncident damageIncident = new DamageIncidentDAO().getIncidentDetailForFullInfoViewing(incidentID);
        DamageReport damageReport = new DamageReportDAO().getListOfDamageReportWithIncidentID(incidentID).get(0);
        Employee employee = new EmployeeDAO().getEmployeeWithEmployeeID(damageIncident.getReportedBy());

        session.setAttribute("damageIncident", damageIncident);
        session.setAttribute("damageReport", damageReport);
        session.setAttribute("employee", employee.getLastName() + ", " + employee.getFirstName() + " " + employee.getMiddleName());
    }

    private void viewIncidentHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<DamageIncident> incidents = new ArrayList<>();
        ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
        for (int z = 0; z < municipalities.size(); z++) {
            ArrayList<DamageIncident> temporaryIncidents = new DamageIncidentDAO().getListOfIncidentsWithInfoInMunicipal(municipalities.get(z).getMunicipalityID());
            for (int a = 0; a < temporaryIncidents.size(); a++) {
                Employee employee = new EmployeeDAO().getEmployeeWithEmployeeID(temporaryIncidents.get(a).getReportedBy());
                String name = employee.getLastName() + ", " + employee.getFirstName() + " " + employee.getMiddleName();
                temporaryIncidents.get(a).setReportedName(name);
            }
            incidents.addAll(temporaryIncidents);
        }

        session.setAttribute("incidents", incidents);
    }

    private boolean changeDamageStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        int incidentID = Integer.parseInt(request.getParameter("incidentID"));
        DamageIncidentDAO incidentDAO = new DamageIncidentDAO();
        DamageIncident incident = incidentDAO.getDamageIncidentWithIncidentID(incidentID);
        incident.setStatus(request.getParameter("status"));
        return incidentDAO.updateDamageIncident(incident);
    }
}
