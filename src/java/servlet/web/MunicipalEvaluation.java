/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.DeployedEvaluationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.DeployedEvaluation;
import object.Employee;

/**
 *
 * @author RubySenpaii
 */
public class MunicipalEvaluation extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("MAO") && !userLogged.getAuthority().equals("Technician")) {
            if (action.equals("goToEvaluation")) {
                System.out.println("directing to programEvaluation.jsp...");
                goToEvaluation(request, response);
                path = "/municipal/programEvaluationForm.jsp";
            } else if (action.equals("submitEvaluation")) {
                System.out.println("directing to somewhere");
                submitEvaluation(request, response);
                path = "/MunicipalProgram?action=viewDeployedProgramDetails&deployedID=" + (int) session.getAttribute("deployedID");
            } else {
                //unknown action
            }
        } else {
            //redirect to restricted access
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }
    
    private void goToEvaluation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int deployedID = Integer.parseInt(request.getParameter("deployedID"));
        session.setAttribute("deployedID", deployedID);
    }
    
    private void submitEvaluation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int deployedID = (int) session.getAttribute("deployedID");
        
        String result = "";
        for (int a = 1; a <= 10; a++) {
            String value = request.getParameter("quesAnswer" + a);
            result += value;
            if (a < 10) {
                result += ",";
            }
        }
        
        String feedback = request.getParameter("feedback");
        if (feedback.equals("") || feedback.isEmpty()) {
            feedback = " ";
        }
        
        String respondent = request.getParameter("respondentName");
        DeployedEvaluation deployedEval = new DeployedEvaluation();
        deployedEval.setDeployedID(deployedID);
        deployedEval.setEvaluationValues(result);
        deployedEval.setRespondentName(respondent);
        deployedEval.setFeedback(feedback);
        if (new DeployedEvaluationDAO().addDeployedEvaluation(deployedEval)) {
            System.out.println("evaluation submitted success");
        }
    }
}
