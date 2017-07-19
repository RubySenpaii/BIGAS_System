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
import dao.FarmDAO;
import dao.MunicipalityDAO;
import dao.PlotDAO;
import dao.ProblemDAO;
import dao.ProgramBeneficiaryDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.Barangay;
import object.DamageIncident;
import object.DamageReport;
import object.DeployedProgram;
import object.Employee;
import object.Farm;
import object.Municipality;
import object.Plot;
import object.Problem;
import object.ProgramBeneficiary;

/**
 *
 * @author RubySenpaii
 */
public class MunicipalDamages extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("MAO") && !userLogged.getAuthority().equals("Technician")) {
            if (action.equals("goToApprovedDamages")) {
                System.out.println("directing to damageApprovedList.jsp...");
                goToApprovedDamages(request, response);
                path = "/municipal/damageApprovedList.jsp";
            } else if (action.equals("goToProgramDamages")) {
                System.out.println("directing to damageProgramList.jsp...");
                goToProgramDamages(request, response);
                path = "/municipal/damageProgramList.jsp";
            } else if (action.equals("goToApprovedDamagesOverview")) {
                System.out.println("directing to damageApprovedOverview.jsp...");
                goToApprovedDamagesOverview(request, response);
                path = "/municipal/damageOverview.jsp";
            } else if (action.equals("goToProgramDamagesOverview")) {
                System.out.println("directing to damageProgramOverview.jsp...");
                goToProgramDamagesOverview(request, response);
                path = "/municipal/damageOverview.jsp";
            } else if (action.equals("viewDamageIncidentDetails")) {
                System.out.println("directing to damageDetail.jsp");
                viewDamageIncidentDetails(request, response);
                path = "/municipal/damageDetail.jsp";
            } else if (action.equals("reviewDamages")) {
                System.out.println("directing to damageReview.jsp");
                reviewDamages(request, response);
                path = "/municipal/damageReview.jsp";
            } else if (action.equals("viewIncidentHistory")) {
                System.out.println("directing to damageList.jsp");
                viewIncidentHistory(request, response);
                path = "/municipal/damageList.jsp";
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

    private void goToApprovedDamages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<DamageIncident> damageIncidents = new DamageIncidentDAO().getBarangayProblemListWithApprovedDamagesInMunicipality(userLogged.getMunicipalityID());
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

        ArrayList<DamageIncident> damageIncidents = new DamageIncidentDAO().getBarangayProblemListWithProgramDamagesInMunicipality(userLogged.getMunicipalityID());

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
        if (request.getParameter("action").toLowerCase().contains("program")) {
            int deployedID = Integer.parseInt(request.getParameter("deployedID"));
            ArrayList<ProgramBeneficiary> beneficiaries = new ProgramBeneficiaryDAO().getListOfProgramBeneficiariesForDeployedID(deployedID);
            for (int a = 0; a < damageIncidents.size(); a++) {
                for (int b = 0; b < beneficiaries.size(); b++) {
                    System.out.println("incident farm: " + damageIncidents.get(a).getFarmName() + a);
                    System.out.println("beneficiary: " + beneficiaries.get(b).getFarmName());
                    if (damageIncidents.get(a).getFarmName().equals(beneficiaries.get(b).getFarmName())) {
                        break;
                    } else if (b + 1 == beneficiaries.size()) {
                        damageIncidents.remove(a);
                    }
                }
            }
        }

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

        ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getListOfIncidentsWithInfoInMunicipal(userLogged.getMunicipalityID());
        for (int a = 0; a < incidents.size(); a++) {
            Employee employee = new EmployeeDAO().getEmployeeWithEmployeeID(incidents.get(a).getReportedBy());
            String name = employee.getLastName() + ", " + employee.getFirstName() + " " + employee.getMiddleName();
            incidents.get(a).setReportedName(name);
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
        if (request.getParameter("status").equals("Rejected")) {
            incident.setRemarks(request.getParameter("remarks"));
        }
        return incidentDAO.updateDamageIncident(incident);
    }
}
