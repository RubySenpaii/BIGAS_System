/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.BarangayDAO;
import dao.EmployeeDAO;
import dao.FarmDAO;
import extra.GenericObject;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.Barangay;
import object.Employee;
import object.Farm;
import object.Municipality;

/**
 *
 * @author RubySenpaii
 */
@WebServlet(name = "MunicipalFarmAssignment", urlPatterns = {"/MunicipalFarmAssignment"})
public class MunicipalFarmAssignment extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("MAO") && !userLogged.getAuthority().equals("Technician")) {
            if (action.equals("goToTechnicianList")) {
                System.out.println("directing to assignTechnician.jsp...");
                goToTechnicianList(request, response);
                path = "/municipal/technicianList.jsp";
            } else if (action.equals("editTechnicianAssignment")) {
                System.out.println("editing technician assignment...");
                editingTechnicianAssignment(request, response);
                path = "/municipal/technicianAssignment.jsp";
            } else if (action.equals("viewTechnicianAssignment")) {
                System.out.println("view technician assignment...");
                viewTechnicianAssignment(request, response);
                path = "/municipal/technicianAssignment.jsp";
            } else if (action.equals("editTechAssign")) {
                System.out.println("view technician assignment...");
                editTechAssign(request, response);
                path = "/MunicipalFarmAssignment?action=goToTechnicianList";
            } else if (action.equals("sendFarmDetail")) {
                System.out.println("adding new farm...");
                submitFarmDetails(request, response);
                path = "/MunicipalFarmAssignment?action=goToTechnicianList";
            } else {
                //unknown action
            }
        } else {
            //redirect to restricted access
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    private void goToTechnicianList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Municipality municipal = (Municipality) session.getAttribute("municipal");
        System.out.println(municipal.getMunicipalityID() + "");
        ArrayList<Farm> unassignedFarms = new FarmDAO().getListOfUnassignedFarmsInMunicipality(municipal.getMunicipalityID());
        ArrayList<Employee> technicians = new EmployeeDAO().getListOfTechniciansInMunicipal(municipal.getMunicipalityID());
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipal.getMunicipalityID());

        ArrayList<GenericObject> technicianFarms = new ArrayList<>();
        for (int a = 0; a < technicians.size(); a++) {
            GenericObject technicianFarm = new GenericObject();
            technicianFarm.setAttribute1(technicians.get(a).getLastName() + ", " + technicians.get(a).getFirstName());
            ArrayList<Farm> assignedFarms = new FarmDAO().getListOfFarmsAssignedTo(technicians.get(a).getEmployeeID());
            technicianFarm.setAttribute2(assignedFarms.size() + " farm(s)");
            for (int b = 0; b < assignedFarms.size(); b++) {
                if (b == 0) {
                    technicianFarm.setAttribute3(assignedFarms.get(b).getFarmName());
                } else {
                    technicianFarm.setAttribute3(technicianFarm.getAttribute3() + ", " + assignedFarms.get(b).getFarmName());
                }
            }
            technicianFarm.setAttribute4(String.valueOf(technicians.get(a).getEmployeeID()));
            technicianFarms.add(technicianFarm);
        }

        System.out.println("techs: " + technicians.size() + " unassigned: " + unassignedFarms.size());

        session.setAttribute("barangays", barangays);
        session.setAttribute("technicians", technicians);
        session.setAttribute("unassignedFarms", unassignedFarms);
        session.setAttribute("technicianFarms", technicianFarms);
    }

    private void editingTechnicianAssignment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int technicianID = Integer.parseInt(request.getParameter("technicianID"));
        Employee technician = new EmployeeDAO().getEmployeeWithEmployeeID(technicianID);
        ArrayList<Farm> farms = new FarmDAO().getListOfFarmsAssignedTo(technicianID);
        session.setAttribute("mode", "edit");
        session.setAttribute("technicianID", technicianID);
        session.setAttribute("technician", technician);
        session.setAttribute("selectedTechnicianFarms", farms);
    }

    private void viewTechnicianAssignment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int technicianID = Integer.parseInt(request.getParameter("technicianID"));
        Employee technician = new EmployeeDAO().getEmployeeWithEmployeeID(technicianID);
        ArrayList<Farm> farms = new FarmDAO().getListOfFarmsAssignedTo(technicianID);
        session.setAttribute("mode", "view");
        session.setAttribute("technician", technician);
        session.setAttribute("selectedTechnicianFarms", farms);
    }

    private void editTechAssign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        int technicianID = (int) session.getAttribute("technicianID");
        String[] farmInputs = request.getParameterValues("farmID");
        ArrayList<Farm> assignedFarms = new FarmDAO().getListOfFarmsAssignedTo(technicianID);
        for (int a = 0; a < assignedFarms.size(); a++) {
            assignedFarms.get(a).setAssignedTechnician(-1);
            boolean unassigned = new FarmDAO().assignFarmAssign(assignedFarms.get(a));
            System.out.println(assignedFarms.get(a).getFarmID() + " " + unassigned);
        }
        
        for (int a = 0; a < farmInputs.length; a++) {
            Farm farm = new Farm();
            farm.setFarmID(Integer.parseInt(farmInputs[a]));
            farm.setAssignedTechnician(technicianID);
            boolean assigned  = new FarmDAO().assignFarmAssign(farm);
            System.out.println(farm.getFarmID() + " " + assigned);
        }
    }
    
    private void submitFarmDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ArrayList<Farm> farms = new FarmDAO().getListOfFarms();
        
        Farm farm = new Farm();
        farm.setFarmID(farms.size() + 1);
        farm.setBarangayID(new BarangayDAO().getBarangayInfoWithBrgyName(request.getParameter("barangayName")).getBarangayID());
        farm.setFarmName(request.getParameter("farmName"));
        farm.setAddress(request.getParameter("address"));
        farm.setFarmableArea(Double.parseDouble(request.getParameter("farmableArea")));
        farm.setIrrigationMethod(request.getParameter("irrigationMethod"));
        farm.setLandElevation(request.getParameter("landElevation"));
        farm.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        farm.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        farm.setDateVisited("12-31-9999");
        farm.setFlag(1);
        farm.setAssignedTechnician(-1);
        
        boolean farmAdd = new FarmDAO().addFarm(farm);
        System.out.println(farm.getFarmName() + " added: " + farmAdd);
    }
}
