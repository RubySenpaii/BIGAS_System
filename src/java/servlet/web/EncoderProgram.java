/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.DamageIncidentDAO;
import dao.DeployedEvaluationDAO;
import dao.DeployedProgramDAO;
import dao.ProgramBeneficiaryDAO;
import dao.ProgramPlanDAO;
import dao.ProgramProcedureDAO;
import dao.ProgramProgressDAO;
import dao.ProgramSurveyDAO;
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
import object.DeployedEvaluation;
import object.DeployedProgram;
import object.Employee;
import object.ProgramBeneficiary;
import object.ProgramPlan;
import object.ProgramProcedure;
import object.ProgramProgress;
import object.ProgramSurvey;

/**
 *
 * @author RubySenpaii
 */
public class EncoderProgram extends HttpServlet {

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
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("MAO") && !userLogged.getAuthority().equals("Technician")) {
            if (action.equals("goToListOfOngoingPrograms")) {
                System.out.println("directing to programOngoingList.jsp");
                goToOngoingPrograms(request, response);
                path = "/encoder/programOngoingList.jsp";
            } else if (action.equals("viewDeployedProgramDetails")) {
                System.out.println("directing to programDeployedDetail.jsp");
                goToProgramDeployedDetail(request, response);
                path = "/encoder/programDeployedDetail.jsp";
            } else if (action.equals("goToEvaluation")) {
                System.out.println("directing to programEvaluation.jsp...");
                goToEvaluation(request, response);
                path = "/encoder/programEvaluationForm.jsp";
            } else if (action.equals("submitEvaluation")) {
                System.out.println("directing to somewhere");
                submitEvaluation(request, response);
                path = "/EncoderProgram?action=viewDeployedProgramDetails&deployedID=" + (int) session.getAttribute("deployedID");
            } else {
                //unknown action
            }
        } else {
            //redirect to restricted access
        }
        
        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    private void goToOngoingPrograms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<DeployedProgram> ongoingDeployedPrograms = new DeployedProgramDAO().getListOfCompletedProgramsForMunicipality(userLogged.getMunicipalityID());

        session.setAttribute("ongoingPrograms", ongoingDeployedPrograms);
    }

    private void goToProgramDeployedDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int deployedID = Integer.parseInt(request.getParameter("deployedID"));
        DeployedProgram deployedProgram = new DeployedProgramDAO().getDeployedProgramDetails(deployedID);
        ProgramPlan programPlan = new ProgramPlanDAO().getProgramPlanDetail(deployedProgram.getProgramPlanID());
        deployedProgram.setProgramName(programPlan.getProgramName());
        ArrayList<ProgramProgress> deployedProgress = new ArrayList<>();
        ArrayList<ProgramProcedure> procedures = new ProgramProcedureDAO().getListOfProgramProceduresForProgramID(deployedProgram.getProgramPlanID());
        ArrayList<ProgramProgress> progress = new ProgramProgressDAO().getListOfProgramProgressForDeployedID(deployedID);
        for (int a = 0; a < procedures.size(); a++) {
            if (progress.size() == 0) {
                ProgramProgress prog = new ProgramProgress();
                prog.setActivity(procedures.get(a).getActivity());
                prog.setDateCompleted("N/A");
                prog.setDeployedID(deployedID);
                prog.setImage("N/A");
                prog.setPhase(procedures.get(a).getPhase());
                prog.setProcedureNumber(procedures.get(a).getProcedureNo());
                prog.setUpdatedByName("N/A");
                prog.setRemarks("N/A");
                deployedProgress.add(prog);
            } else {
                for (int b = 0; b < progress.size(); b++) {
                    if (progress.get(b).getProcedureNumber() == procedures.get(a).getProcedureNo()) {
                        ProgramProgress prog = new ProgramProgress();
                        prog.setActivity(procedures.get(a).getActivity());
                        prog.setDateCompleted(progress.get(b).getDateCompleted());
                        prog.setDeployedID(deployedID);
                        prog.setImage(progress.get(b).getImage());
                        prog.setPhase(procedures.get(a).getPhase());
                        prog.setProcedureNumber(procedures.get(a).getProcedureNo());
                        prog.setUpdatedByName(progress.get(b).getUpdatedByName());
                        prog.setRemarks(progress.get(b).getRemarks());
                        deployedProgress.add(prog);
                        break;
                    } else if (b == progress.size() - 1) {
                        ProgramProgress prog = new ProgramProgress();
                        prog.setActivity(procedures.get(a).getActivity());
                        prog.setDateCompleted("N/A");
                        prog.setDeployedID(deployedID);
                        prog.setImage("N/A");
                        prog.setPhase(procedures.get(a).getPhase());
                        prog.setProcedureNumber(procedures.get(a).getProcedureNo());
                        prog.setUpdatedByName("N/A");
                        prog.setRemarks("N/A");
                        deployedProgress.add(prog);
                    }
                }
            }
        }

        ArrayList<ProgramBeneficiary> beneficiaries = new ProgramBeneficiaryDAO().getListOfProgramBeneficiariesForDeployedID(deployedID);
        ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getFarmListWithDeployedID(deployedID);
        for (int a = 0; a < incidents.size(); a++) {
            for (int b = 0; b < beneficiaries.size(); b++) {
                if (incidents.get(a).getFarmName().equals(beneficiaries.get(b).getFarmName())) {
                    beneficiaries.get(b).setAreaAffected(incidents.get(a).getTotalAreaAffected());
                    beneficiaries.get(b).setAreaDamaged(incidents.get(a).getTotalAreaDamaged());
                    beneficiaries.get(b).setIncidentID(incidents.get(a).getDamageIncidentID());
                }
            }
        }

        ArrayList<DeployedEvaluation> deployedEvaluations = new DeployedEvaluationDAO().getListOfProgramEvaluationsOnDeployedID(deployedID);
        ArrayList<ProgramSurvey> survey = new ArrayList<>();
        if (programPlan.getSurveyForm() != -1) {
            survey = new ProgramSurveyDAO().getProgramSurveyQuestionsBySurveyID(programPlan.getSurveyForm());
        }

        session.setAttribute("deployedID", deployedID);
        session.setAttribute("deployedProgram", deployedProgram);
        session.setAttribute("deployedProgress", deployedProgress);
        session.setAttribute("beneficiaries", beneficiaries);
        session.setAttribute("deployedEvaluations", deployedEvaluations);
        session.setAttribute("survey", survey);
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
        int a = 1;
        do {
            String value = request.getParameter("quesAnswer" + a);
            result += value + ",";
            a++;
        } while (request.getParameter("quesAnswer" + a) != null);
        result = result.substring(0, result.length() - 1);

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
