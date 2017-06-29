/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.DamageIncidentDAO;
import dao.FarmDAO;
import dao.MunicipalityDAO;
import dao.PlantingReportDAO;
import dao.TargetProductionDAO;
import extra.Calculator;
import extra.Formatter;
import extra.GenericObject;
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
import object.Farm;
import object.Municipality;
import object.TargetProduction;

/**
 *
 * @author RubySenpaii
 */
public class ProvincialTargetProduction extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("PAO")) {
            if (action.equals("goToSetTargetProduction")) {
                System.out.println("directing to targetProduction.jsp...");
                goToSetTargetProduction(request, response);
                path = "/provincial/targetProduction.jsp";
            } else if (action.equals("setTargetProduction")) {
                System.out.println("directing to homepage.jsp...");
                setTargetProduction(request, response);
                path = "/ProvincialHomepage?action=goToHomePage";
            } else {
                //unknown action
            }
        } else {
            //redirect to restricted access
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    private void goToSetTargetProduction(HttpServletRequest request, HttpServletResponse response) {
        Formatter format = new Formatter();
        HttpSession session = request.getSession();

        int year = new Calculator().getYear() - 1;
        ArrayList<GenericObject> objects = new ArrayList<>();
        ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();

        double totalProduction = 0;
        for (int a = 0; a < municipalities.size(); a++) {
            GenericObject object = new GenericObject();
            object.setAttribute1(municipalities.get(a).getMunicipalityName());

            ArrayList<Farm> farmsInMunicipality = new FarmDAO().getListOfFarmsInMunicipality(municipalities.get(a).getMunicipalityName());
            object.setAttribute2(farmsInMunicipality.size() + " farm(s)");

            double municipalHarvested = 0, municipalPlanted = 0, municipalDamaged = 0;
            for (int b = 0; b < farmsInMunicipality.size(); b++) {
                double totalHarvested = new PlantingReportDAO().getTotalHarvestedFromFarmOnYear(farmsInMunicipality.get(b).getFarmID(), year);
                municipalHarvested += totalHarvested;
                double totalPlantedArea = new PlantingReportDAO().getTotalPlantedAreaFromFarmOnYear(farmsInMunicipality.get(b).getFarmID(), year);
                municipalPlanted += totalPlantedArea;
                double totalDamages = new DamageIncidentDAO().getTotalDamagesFromFarmOnYear(farmsInMunicipality.get(b).getFarmID(), year);
                municipalDamaged += totalDamages;
            }
            totalProduction += municipalHarvested;

            object.setAttribute3(format.doubleToString(municipalPlanted) + " ha (" + format.doubleToString(municipalDamaged) + " ha)");
            object.setAttribute4(format.doubleToString(municipalHarvested));
            object.setAttribute5(""); //compute later
            double averageYield = municipalHarvested / (municipalPlanted - municipalDamaged);
            if (municipalPlanted - municipalDamaged == 0) {
                object.setAttribute6("0.00 MT/ha");
            } else {
                object.setAttribute6(format.doubleToString(averageYield) + " MT/ha");
            }
            objects.add(object);
        }

        for (int a = 0; a < objects.size(); a++) {
            String harvest = objects.get(a).getAttribute4();
            String production = harvest.replace(",", "");
            double municipalProduction = Double.parseDouble(production);
            double contributionShare = municipalProduction / totalProduction;
            contributionShare *= 100;
            if (municipalProduction == 0) {
                objects.get(a).setAttribute5("0.00%");
            } else {
                objects.get(a).setAttribute5(format.doubleToString(contributionShare) + "%");
            }
            objects.get(a).setAttribute4(harvest + " MT");
        }

        session.setAttribute("municipals", objects);
    }

    private void setTargetProduction(HttpServletRequest request, HttpServletResponse response) {
        int yearNow = new Calculator().getYear();
        ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
        for (int a = 0; a < municipalities.size(); a++) {
            String recommended = request.getParameter("recommendedTarget" + a);
            TargetProduction targetProduction = new TargetProduction();
            targetProduction.setMunicipalityID(municipalities.get(a).getMunicipalityID());
            targetProduction.setTargetValue(Double.parseDouble(recommended));
            targetProduction.setYear(yearNow);
            System.out.println(new TargetProductionDAO().addTargetProduction(targetProduction));
        }
    }
}
