/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.EmployeeDAO;
import dao.MunicipalityDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.Employee;
import object.Municipality;

/**
 *
 * @author RubySenpaii
 */
public class WebLogin extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        String path = "/login.jsp";

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean allowUser = new EmployeeDAO().checkUsername(username, password);
        if (allowUser) {
            System.out.println("logging in as " + username);
            Employee employee = new EmployeeDAO().getEmployeeWithUsername(username);
            session.setAttribute("userLogged", employee);
            updateNotification(request, response);
            
            System.out.println(username + " - " + employee.getOfficeLevel() + " - " + employee.getAuthority());
            if (employee.getOfficeLevel().equals("MAO")) {
                Municipality municipal = new MunicipalityDAO().getMunicipalDetail(employee.getMunicipalityID());
                session.setAttribute("municipal", municipal);
                
                if (employee.getAuthority().equals("Technician")) {
//                    System.out.println("directing technician homepage...");
//                    path = "/technician/homepage.jsp";
//                } else if (employee.getAuthority().equals("Encoder")) {
                    System.out.println("directing encoder homepage...");
                    path = "/EncoderProgram?action=goToListOfOngoingPrograms";
                } else {
                    System.out.println("directing mao homepage...");
                    path = "/MunicipalHomepage?action=goToHomePage";
                }
            } else {
                System.out.println("directing pao homepage...");
                path = "/ProvincialHomepage?action=goToHomePage";
            }
        } else {
            System.out.println(username + " does not exist");
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }
}
