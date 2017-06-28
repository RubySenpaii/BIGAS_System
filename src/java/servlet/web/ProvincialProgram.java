/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.BarangayDAO;
import dao.DamageIncidentDAO;
import delete.DamageSolutionDAO;
import dao.DeployedEvaluationDAO;
import dao.DeployedProgramDAO;
import dao.FarmDAO;
import dao.FertilizerDAO;
import dao.MunicipalityDAO;
import dao.PlantingReportDAO;
import dao.PlotDAO;
import dao.ProblemDAO;
import dao.ProgramBeneficiaryDAO;
import dao.ProgramPlanDAO;
import dao.ProgramProcedureDAO;
import dao.ProgramProgressDAO;
import dao.ProgramRequestDAO;
import dao.ProgramTriggerDAO;
import dao.SeedVarietyDAO;
import extra.Calculator;
import extra.ImportantProblem;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import object.Barangay;
import object.DamageIncident;
import object.DeployedEvaluation;
import object.DeployedProgram;
import object.Employee;
import object.Farm;
import object.Fertilizer;
import object.Municipality;
import object.PlantingReport;
import object.Plot;
import object.Problem;
import object.ProgramBeneficiary;
import object.ProgramPlan;
import object.ProgramProcedure;
import object.ProgramProgress;
import object.ProgramRequest;
import object.ProgramTrigger;
import object.SeedVariety;

/**
 *
 * @author RubySenpaii
 */
public class ProvincialProgram extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("PAO")) {
            if (action.equals("viewRecommendedPrograms")) {
                System.out.println("directing to programRecommendation.jsp...");
                viewRecommendedPrograms(request, response);
                path = "/provincial/programRecommendation.jsp";
            } else if (action.equals("goToProgramDeployment")) {
                System.out.println("directing to programDeployment.jsp...");
                goToProgramDeployment(request, response);
                path = "/provincial/programDeployment.jsp";
            } else if (action.equals("goToListOfOngoingPrograms")) {
                System.out.println("directing to programOngoingList.jsp");
                goToOngoingPrograms(request, response);
                path = "/provincial/programOngoingList.jsp";
            } else if (action.equals("viewDeployedProgramDetails")) {
                System.out.println("directing to programDeployedDetail.jsp");
                goToProgramDeployedDetail(request, response);
                path = "/provincial/programDeployedDetail.jsp";
            } else if (action.equals("viewProgramList")) {
                System.out.println("directing to programList.jsp");
                viewProgramList(request, response);
                path = "/provincial/programList.jsp";
            } else if (action.equals("viewProgramDetail")) {
                System.out.println("directing to programDetail.jsp");
                viewProgramDetail(request, response);
                path = "/provincial/programDetail.jsp";
            } else if (action.equals("deployProgram")) {
                System.out.println("deploy programs");
                boolean success = deployProgram(request, response);
                if (success) {
                    path = "/MunicipalProgram?action=goToListOfOngoingPrograms";
                } else {
                    path = "/MunicipalHomepage?action=goToHomePage";
                }
            } else if (action.equals("viewProblemRecommendation")) {
                System.out.println("directing to problemList.jsp");
                viewProblemRecommendation(request, response);
                path = "/provincial/problemList.jsp";
            } else if (action.equals("updateProgress")) {
                System.out.println("directing to programDeployedDetail.jsp");
                int deployedID = updateProgress(request, response);
                path = "/MunicipalProgram?action=viewDeployedProgramDetails&deployedID=" + deployedID;
            } else if (action.equals("viewRequestList")) {
                System.out.println("directing to programRequestList.jsp");
                viewRequestList(request, response);
                path = "/provincial/programRequestList.jsp";
            } else if (action.equals("approveRequest")) {
                System.out.println("directing to programRequestList.jsp");
                approveRequestList(request, response);
                path = "/ProvincialProgram?action=viewRequestList";
            } else if (action.equals("rejectRequest")) {
                System.out.println("directing to programRequestList.jsp");
                rejectRequestList(request, response);
                path = "/ProvincialProgram?action=viewRequestList";
            } else if (action.equals("createProgram")) {
                System.out.println("directing to createProgramPlan1.jsp");
                createProgram(request, response);
                path = "/provincial/createProgramPlan1.jsp";
            } else if (action.equals("createProgramStep1")) {
                System.out.println("directing to createProgramPlan2.jsp");
                createProgramStep1(request, response);
                path = "/provincial/createProgramPlan2.jsp";
            } else if (action.equals("createProgramStep2")) {
                System.out.println("directing to createProgramPlan3.jsp");
                createProgramStep2(request, response);
                path = "/provincial/createProgramPlan3.jsp";
            } else if (action.equals("createProgramStep3")) {
                System.out.println("directing to createProgramPlanReview.jsp");
                createProgramStep3(request, response);
                path = "/provincial/createProgramPlanReview.jsp";
            } else if (action.equals("submitProgramDetail")) {
                System.out.println("directing to programList.jsp");
                submitReviewedProgramDetail(request, response);
                path = "/ProvincialProgram?action=viewProgramList";
            } else {
                //unknown action
            }
        } else {
            //redirect to restricted access
        }

        RequestDispatcher rd = context.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    private void viewRecommendedPrograms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        Calculator calculator = new Calculator();

        ArrayList<ImportantProblem> importantProblems = (ArrayList<ImportantProblem>) session.getAttribute("importantProblems");
        ImportantProblem importantProblem = importantProblems.get(Integer.parseInt(request.getParameter("index")));

        ArrayList<ProgramPlan> remove = new ArrayList<>();
        ArrayList<ProgramPlan> programPlans = new ProgramPlanDAO().getListOfProgramPlansForProblem(importantProblem.getProblem().getProblemID());
        for (int a = 0; a < programPlans.size(); a++) {
            ArrayList<DeployedProgram> deployeds = new DeployedProgramDAO().getListOfDeployedProgramsForProgramPlan(programPlans.get(a).getProgramPlanID());
            double evaluationResult = 0;
            for (int b = 0; b < deployeds.size(); b++) {
                ArrayList<DeployedEvaluation> evaluations = new DeployedEvaluationDAO().getListOfProgramEvaluationsOnDeployedID(deployeds.get(b).getDeployedID());
                for (int c = 0; c < evaluations.size(); c++) {
                    double farmerEvaluation = calculator.getRespondentResult(evaluations.get(c).getEvaluationValues());
                    evaluationResult += farmerEvaluation;
                }
            }
            String effectivity = calculator.getEffectivityResult(evaluationResult);
            System.out.println(effectivity + evaluationResult);
            programPlans.get(a).setEffectivityStatus(effectivity);

            ArrayList<ProgramTrigger> programTriggers = new ProgramTriggerDAO().getListOfProgramTriggersForProgramID(programPlans.get(a).getProgramPlanID());
            String triggerName = "";

            for (int b = 0; b < programTriggers.size(); b++) {
                if (programTriggers.get(b).getTriggerName().equals("Farm Affected")) {
                    if (programTriggers.get(b).getTriggerValue() > importantProblem.getFarms().size()) {
                        remove.add(programPlans.get(a));
                        break;
                    } else {
                        programPlans.get(a).setProgramTriggerFarmCount(programTriggers.get(b).getTriggerValue());
                        if (triggerName.length() > 0) {
                            triggerName += " | ";
                        }
                        triggerName += "Affected Farm Count: " + programTriggers.get(b).getTriggerValue();
                    }
                } else if (programTriggers.get(b).getTriggerName().equals("Area Affected")) {
                    if (programTriggers.get(b).getTriggerValue() > importantProblem.getTotalMinor()) {
                        remove.add(programPlans.get(a));
                        break;
                    } else {
                        programPlans.get(a).setProgramTriggerFarmArea(programTriggers.get(b).getTriggerValue());
                        if (triggerName.length() > 0) {
                            triggerName += " | ";
                        }
                        triggerName += "Affected Farm Area: " + programTriggers.get(b).getTriggerValue();
                    }
                } else {
                    System.out.println("Unknown Trigger Name: " + programTriggers.get(b).getTriggerName());
                }
            }

            programPlans.get(a).setProgramTrigger(triggerName);
        }
        for (int a = 0; a < remove.size(); a++) {
            programPlans.remove(remove.get(a));
        }

        session.setAttribute("importantProblem", importantProblem);
        session.setAttribute("programPlans", programPlans);
    }

    private void goToProgramDeployment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<SeedVariety> varieties = new SeedVarietyDAO().getListOfSeedVarieties();
        ArrayList<Fertilizer> fertilizers = new FertilizerDAO().getListOfFertilizers();

        int programPlanID = Integer.parseInt(request.getParameter("programID"));

        ImportantProblem importantProblem = (ImportantProblem) session.getAttribute("importantProblem");
        ArrayList<DamageIncident> farms = new DamageIncidentDAO().getFarmListWithApprovedProblemInBarangay(importantProblem.getProblem().getProblemID(), importantProblem.getBarangayName());

        session.setAttribute("varieties", varieties);
        session.setAttribute("fertilizers", fertilizers);
        session.setAttribute("programPlanID", programPlanID);
        session.setAttribute("farms", farms);
    }

    private void goToOngoingPrograms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<DeployedProgram> ongoingDeployedPrograms = new ArrayList<>();
        ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
        for (int a = 0; a < municipalities.size(); a++) {
            ArrayList<DeployedProgram> deployedPrograms = new DeployedProgramDAO().getListOfOngoingProgramsForMunicipality(municipalities.get(a).getMunicipalityID());
            ongoingDeployedPrograms.addAll(deployedPrograms);
        }

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

        session.setAttribute("deployedID", deployedID);
        session.setAttribute("deployedProgram", deployedProgram);
        session.setAttribute("deployedProgress", deployedProgress);
        session.setAttribute("beneficiaries", beneficiaries);
    }

    private void viewProgramList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Calculator calculator = new Calculator();
        ArrayList<ProgramPlan> programPlans = new ProgramPlanDAO().getListOfProgramPlans();
        for (int a = 0; a < programPlans.size(); a++) {
            System.out.println(programPlans.get(a).getProgramPlanID());
            ArrayList<DeployedProgram> deployeds = new DeployedProgramDAO().getListOfDeployedProgramsForProgramPlanID(programPlans.get(a).getProgramPlanID());
            System.out.println("deployed count " + deployeds.size());
            double evaluationResult = 0;
            int deployedCount = 0;
            for (int b = 0; b < deployeds.size(); b++) {
                ArrayList<DeployedEvaluation> evaluations = new DeployedEvaluationDAO().getListOfProgramEvaluationsOnDeployedID(deployeds.get(b).getDeployedID());
                System.out.println("eval count " + evaluations.size());
                for (int c = 0; c < evaluations.size(); c++) {
                    double farmerEvaluation = calculator.getRespondentResult(evaluations.get(c).getEvaluationValues());
                    evaluationResult += farmerEvaluation;
                    deployedCount++;
                }
            }
            double programResult = evaluationResult / deployedCount;
            String effectivity = calculator.getEffectivityResult(programResult);
            System.out.println(effectivity + evaluationResult);
            programPlans.get(a).setEffectivityStatus(effectivity);
        }

        session.setAttribute("programPlans", programPlans);
    }

    private void viewProgramDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        int programPlanID = Integer.parseInt(request.getParameter("programPlanID"));
        ProgramPlan programPlan = new ProgramPlanDAO().getProgramPlanDetail(programPlanID);
        ArrayList<ProgramTrigger> triggers = new ProgramTriggerDAO().getListOfProgramTriggersForProgramID(programPlanID);
        ArrayList<ProgramProcedure> procedures = new ProgramProcedureDAO().getListOfProgramProceduresForProgramID(programPlanID);
        ArrayList<DeployedProgram> deployedPrograms = new DeployedProgramDAO().getListOfDeployedProgramsForProgramPlan(programPlanID);

        session.setAttribute("programPlan", programPlan);
        session.setAttribute("triggers", triggers);
        session.setAttribute("procedures", procedures);
        session.setAttribute("deployedPrograms", deployedPrograms);
    }

    private boolean deployProgram(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ImportantProblem importantProblem = (ImportantProblem) session.getAttribute("importantProblem");
        Municipality municipality = new MunicipalityDAO().getMunicipalDetail(importantProblem.getMunicipalityName());
        int programPlanID = (int) session.getAttribute("programPlanID");
        String seedVariety = request.getParameter("seedVariety");
        double seedAmount = Double.parseDouble(request.getParameter("varietyAmount"));
        String fertilizer = request.getParameter("fertilizer");
        double fertilizerAmount = Double.parseDouble(request.getParameter("fertilizerAmount"));
        String dateStarted = request.getParameter("startDate");
        dateStarted = dateStarted.replace('/', '-');

        int deployedID = new DeployedProgramDAO().getListOfDeployedPrograms().size() + 1;
        DeployedProgram deployed = new DeployedProgram();
        deployed.setDeployedID(deployedID);
        deployed.setProgramPlanID(programPlanID);
        deployed.setAssignedMunicipality(municipality.getMunicipalityID());
        deployed.setVarietyProvided(seedVariety);
        deployed.setVarietyAmount(seedAmount);
        deployed.setFertilizerProvided(fertilizer);
        deployed.setFertilizerAmount(fertilizerAmount);
        deployed.setDateStarted(dateStarted);
        deployed.setDateCompleted("12-31-9999");
        deployed.setStatus("Deployed");

        boolean deployedSuccess = new DeployedProgramDAO().addDeployedProgram(deployed);

        int beneficiaryCount = 0;
        String[] farmNames = request.getParameterValues("farmName");
        for (int a = 0; a < farmNames.length; a++) {
            Farm farm = new FarmDAO().getFarmDetailOnFarmName(farmNames[a]);
            ProgramBeneficiary beneficiary = new ProgramBeneficiary();
            beneficiary.setDeployedID(deployedID);
            beneficiary.setFarmID(farm.getFarmID());
            beneficiary.setVarietyReceived(0);
            beneficiary.setFertilizerReceived(0);
            beneficiary.setDateReceived("12-31-9999");
            if (new ProgramBeneficiaryDAO().addProgramBeneficiary(beneficiary)) {
                beneficiaryCount++;
            }
        }

        if (beneficiaryCount == farmNames.length && deployedSuccess) {
            ArrayList<DamageIncident> incidents = (ArrayList<DamageIncident>) session.getAttribute("incidents");
            for (int a = 0; a < incidents.size(); a++) {
                for (int b = 0; b < farmNames.length; b++) {
                    if (incidents.get(a).getFarmName().equals(farmNames[b])) {
                        DamageIncidentDAO incidentDAO = new DamageIncidentDAO();
                        DamageIncident incident = incidentDAO.getDamageIncidentWithIncidentID(incidents.get(a).getDamageIncidentID());
                        incident.setStatus("Program");
                        incident.setDeployedID(deployedID);
                        incidentDAO.updateDamageIncident(incident);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void viewProblemRecommendation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<ImportantProblem> importantProblems = getListOfPestDiseaseProblem2(request, response);

        session.setAttribute("importantProblems", importantProblems);
    }

    private int updateProgress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        int procedureNo = Integer.parseInt(request.getParameter("procedureNo"));
        int deployedID = (int) session.getAttribute("deployedID");
        ProgramProgress progress = new ProgramProgress();
        progress.setDeployedID(deployedID);
        progress.setUpdatedBy(userLogged.getEmployeeID());
        progress.setProcedureNumber(procedureNo);
        progress.setDateCompleted(dateNow);
        progress.setImage("nothing");
        boolean updateProgress = new ProgramProgressDAO().addProgramProgress(progress);
        System.out.println("update status: " + updateProgress);

        return deployedID;
    }

    private void viewRequestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<ProgramRequest> requests = new ProgramRequestDAO().getListOfProgramRequests();
        ArrayList<ProgramPlan> programPlans = new ProgramPlanDAO().getListOfProgramPlans();

        session.setAttribute("requests", requests);
        session.setAttribute("programPlans", programPlans);
    }

    private void approveRequestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        int requestID = Integer.parseInt(request.getParameter("requestID"));
        String programName = request.getParameter("programName");
        ProgramPlan programPlan = new ProgramPlanDAO().getProgramPlanDetail(programName);
        ArrayList<ProgramRequest> requests = (ArrayList<ProgramRequest>) session.getAttribute("requests");
        ProgramRequest progRequest = new ProgramRequest();
        for (int a = 0; a < requests.size(); a++) {
            if (requests.get(a).getRequestID() == requestID) {
                System.out.println(requests.get(a).getMunicipalityID());
                progRequest = requests.get(a);
            }
        }

        progRequest.setRequestStatus("Approved");
        progRequest.setProgramPlanID(programPlan.getProgramPlanID());

        boolean update = new ProgramRequestDAO().updateProgramRequest(progRequest);
        System.out.println("update status: " + update);
    }

    private void rejectRequestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        int requestID = Integer.parseInt(request.getParameter("requestID"));
        ArrayList<ProgramRequest> requests = (ArrayList<ProgramRequest>) session.getAttribute("requests");
        ProgramRequest progRequest = new ProgramRequest();
        for (int a = 0; a < requests.size(); a++) {
            if (requests.get(a).getRequestID() == requestID) {
                progRequest = requests.get(a);
            }
        }

        progRequest.setRequestStatus("Rejected");

        boolean update = new ProgramRequestDAO().updateProgramRequest(progRequest);
        System.out.println("update status: " + update);
    }

    private void createProgram(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ArrayList<Problem> problems = new ProblemDAO().getListOfProblems();

        session.setAttribute("problems", problems);
    }

    private void createProgramStep1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String programName = request.getParameter("programName");
        String programType = request.getParameter("programType");
        String description = request.getParameter("description");
        String problem = request.getParameter("problem");

        Problem problemInfo = new ProblemDAO().getProblemWithName(problem);
        int programID = new ProgramPlanDAO().getListOfProgramPlans().size() + 1;

        ProgramPlan programPlan = new ProgramPlan();
        programPlan.setProgramPlanID(programID);
        programPlan.setProgramName(programName);
        programPlan.setType(programType);
        programPlan.setDescription(description);
        programPlan.setProblemID(problemInfo.getProblemID());
        programPlan.setFlag(1);

        session.setAttribute("newProgramPlan", programPlan);
        session.setAttribute("problemType", problemInfo.getType());
    }

    private void createProgramStep2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String problemType = (String) session.getAttribute("problemType");
        ProgramPlan programPlan = (ProgramPlan) session.getAttribute("newProgramPlan");
        ArrayList<ProgramTrigger> triggers = new ArrayList<>();
        if (problemType.equals("Pest") || problemType.equals("Disease")) {
            int farmsAffected = Integer.parseInt(request.getParameter("farmsAffected"));
            double minorDamaged = Double.parseDouble(request.getParameter("minorDamaged"));

            if (farmsAffected > 0) {
                ProgramTrigger trigger = new ProgramTrigger();
                trigger.setTriggerName("Farm Affected");
                trigger.setTriggerValue(farmsAffected);
                trigger.setProgramID(programPlan.getProgramPlanID());
                triggers.add(trigger);
            }

            if (minorDamaged > 0) {
                ProgramTrigger trigger = new ProgramTrigger();
                trigger.setTriggerName("Area Affected");
                trigger.setTriggerValue(minorDamaged);
                trigger.setProgramID(programPlan.getProgramPlanID());
                triggers.add(trigger);
            }
        } else if (problemType.equals("Calamity")) {
            int farmsAffected = Integer.parseInt(request.getParameter("farmsAffected"));
            double majorDamaged = Double.parseDouble(request.getParameter("majorDamaged"));

            if (farmsAffected > 0) {
                ProgramTrigger trigger = new ProgramTrigger();
                trigger.setTriggerName("Farm Affected");
                trigger.setTriggerValue(farmsAffected);
                trigger.setProgramID(programPlan.getProgramPlanID());
                triggers.add(trigger);
            }

            if (majorDamaged > 0) {
                ProgramTrigger trigger = new ProgramTrigger();
                trigger.setTriggerName("Area Damaged");
                trigger.setTriggerValue(majorDamaged);
                trigger.setProgramID(programPlan.getProgramPlanID());
                triggers.add(trigger);
            }
        } else {
            int farmsAffected = Integer.parseInt(request.getParameter("farmsAffected"));
            double underproduction = Double.parseDouble(request.getParameter("underproduction"));

            if (farmsAffected > 0) {
                ProgramTrigger trigger = new ProgramTrigger();
                trigger.setTriggerName("Farm Affected");
                trigger.setTriggerValue(farmsAffected);
                trigger.setProgramID(programPlan.getProgramPlanID());
                triggers.add(trigger);
            }

            if (underproduction > 0) {
                ProgramTrigger trigger = new ProgramTrigger();
                trigger.setTriggerName("Underproduction");
                trigger.setTriggerValue(underproduction);
                trigger.setProgramID(programPlan.getProgramPlanID());
                triggers.add(trigger);
            }
        }

        session.setAttribute("newTriggers", triggers);
    }

    private void createProgramStep3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ProgramPlan programPlan = (ProgramPlan) session.getAttribute("newProgramPlan");
        String[] phases = request.getParameterValues("phases");
        String[] activities = request.getParameterValues("activities");

        ArrayList<ProgramProcedure> procedures = new ArrayList<>();
        for (int a = 0; a < phases.length; a++) {
            ProgramProcedure procedure = new ProgramProcedure();
            procedure.setProgramPlanID(programPlan.getProgramPlanID());
            procedure.setProcedureNo(a + 1);
            procedure.setPhase(phases[a]);
            procedure.setActivity(activities[a]);
            procedures.add(procedure);
        }

        session.setAttribute("newProcedures", procedures);
    }

    private void submitReviewedProgramDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ProgramPlan programPlan = (ProgramPlan) session.getAttribute("newProgramPlan");
        ArrayList<ProgramTrigger> triggers = (ArrayList<ProgramTrigger>) session.getAttribute("newTriggers");
        ArrayList<ProgramProcedure> procedures = (ArrayList<ProgramProcedure>) session.getAttribute("newProcedures");

        boolean addPlan = new ProgramPlanDAO().addProgramPlan(programPlan);
        System.out.println("add plan success: " + addPlan);
        if (addPlan) {
            for (int a = 0; a < triggers.size(); a++) {
                boolean addTriggers = new ProgramTriggerDAO().addProgramTrigger(triggers.get(a));
                System.out.println("trigger " + a + " added: " + addTriggers);
            }

            for (int a = 0; a < procedures.size(); a++) {
                boolean addProcedure = new ProgramProcedureDAO().addProgramProcedure(procedures.get(a));
                System.out.println("procedure " + a + " added: " + addProcedure);
            }
        }
    }

    private ArrayList<ImportantProblem> getListOfPestDiseaseProblem2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<ImportantProblem> importantProblems = new ArrayList<>();
        ArrayList<Municipality> municipals = new MunicipalityDAO().getMunicipalNotification();
        for (int a = 0; a < municipals.size(); a++) {
            Problem problem = new ProblemDAO().getProblemWithName(municipals.get(a).getProblemName());
            double plantableArea = municipals.get(a).getArea();
            double majorDamaged = municipals.get(a).getMajorDamagedArea();
            double minorDamaged = municipals.get(a).getMinorDamagedArea();

            if (problem.getType().equals("Calamity")) {
                double calamityMajor = majorDamaged / plantableArea;
                if (calamityMajor >= 0.4) {
                    ImportantProblem importantProblem = new ImportantProblem();
                    importantProblem.setMunicipalityName(municipals.get(a).getMunicipalityName());
                    importantProblem.setBarangayName(municipals.get(a).getBarangayName());
                    importantProblem.setProblem(problem);
                    importantProblem.setTotalMajor(majorDamaged);
                    importantProblem.setTotalMinor(minorDamaged);
                    importantProblem.setPlantableArea(plantableArea);
                    importantProblem.setFarmAffected(municipals.get(a).getFarmAffected());
                    importantProblem.setFarmCount(municipals.get(a).getFarmCount());
                    importantProblem.setDamageType("Major Damages");
                    importantProblems.add(importantProblem);
                }
            } else if (majorDamaged / plantableArea >= 0.03) {
                ImportantProblem importantProblem = new ImportantProblem();
                importantProblem.setMunicipalityName(municipals.get(a).getMunicipalityName());
                importantProblem.setBarangayName(municipals.get(a).getBarangayName());
                importantProblem.setProblem(problem);
                importantProblem.setTotalMajor(majorDamaged);
                importantProblem.setTotalMinor(minorDamaged);
                importantProblem.setPlantableArea(plantableArea);
                importantProblem.setFarmAffected(municipals.get(a).getFarmAffected());
                importantProblem.setFarmCount(municipals.get(a).getFarmCount());
                importantProblem.setDamageType("Major Damages");
                importantProblems.add(importantProblem);
            } else if (minorDamaged / plantableArea >= 0.045) {
                ImportantProblem importantProblem = new ImportantProblem();
                importantProblem.setMunicipalityName(municipals.get(a).getMunicipalityName());
                importantProblem.setBarangayName(municipals.get(a).getBarangayName());
                importantProblem.setProblem(problem);
                importantProblem.setTotalMajor(majorDamaged);
                importantProblem.setTotalMinor(minorDamaged);
                importantProblem.setPlantableArea(plantableArea);
                importantProblem.setFarmAffected(municipals.get(a).getFarmAffected());
                importantProblem.setFarmCount(municipals.get(a).getFarmCount());
                importantProblem.setDamageType("Minor Damages");
                importantProblems.add(importantProblem);
            }
        }
        System.out.println("problem size " + importantProblems.size());
        return importantProblems;
    }

    private ArrayList<ImportantProblem> getListOfPestDiseaseProblem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<ImportantProblem> importantProblems = new ArrayList<>();
        ArrayList<Problem> problems = new ProblemDAO().getListOfProblems();
        ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
        for (int z = 0; z < municipalities.size(); z++) {
            ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipalities.get(z).getMunicipalityID());
            for (int a = 0; a < barangays.size(); a++) {
                ArrayList<PlantingReport> plantingReports = new PlantingReportDAO().getListOfOngoingPlantingReportsInBarangay(barangays.get(a).getBarangayID());
                ArrayList<Farm> farms = new FarmDAO().getListOfFarmsInBarangay(barangays.get(a).getBarangayName());

                for (int b = 0; b < problems.size(); b++) {
                    double plantableArea = 0, plantedArea = 0, minorDamaged = 0, majorDamaged = 0;
                    ArrayList<Farm> includedFarms = new ArrayList<>();
                    for (int c = 0; c < farms.size(); c++) {
                        ArrayList<Plot> plots = new PlotDAO().getListOfPlotsInFarm(farms.get(c));
                        for (int d = 0; d < plots.size(); d++) {
                            plantableArea += plots.get(d).getPlotSize();
                            for (int e = 0; e < plantingReports.size(); e++) {
                                if (plantingReports.get(e).getPlotID() == plots.get(d).getPlotID()) {
                                    plantedArea += plots.get(d).getPlotSize();
                                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                                    String now = sdf.format(Calendar.getInstance().getTime());
                                    ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getApprovedDamageIncidentForPlantingReportBefore(plantingReports.get(e).getPlantingReportID(), now);
                                    for (int f = 0; f < incidents.size(); f++) {
                                        if (incidents.get(f).getProblemReported() == problems.get(b).getProblemID()) {

                                            minorDamaged += incidents.get(f).getTotalAreaAffected();
                                            majorDamaged += incidents.get(f).getTotalAreaDamaged();

                                            Farm farm = farms.get(c);
                                            farm.setAreaAffected(incidents.get(f).getTotalAreaAffected());
                                            farm.setAreaDamaged(incidents.get(f).getTotalAreaDamaged());
                                            includedFarms.add(farm);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (!includedFarms.isEmpty()) {
                        if (problems.get(b).getType().equals("Calamity")) {
                            if (majorDamaged / plantableArea >= 0.4) {
                                ImportantProblem importantProblem = new ImportantProblem();
                                importantProblem.setBarangayName(barangays.get(a).getBarangayName());
                                importantProblem.setFarms(includedFarms);
                                importantProblem.setProblem(problems.get(b));
                                importantProblem.setTotalMajor(majorDamaged);
                                importantProblem.setTotalMinor(0);
                                importantProblem.setPlantableArea(plantableArea);
                                importantProblem.setMunicipalityName(municipalities.get(z).getMunicipalityName());
                                importantProblems.add(importantProblem);
                            }
                        } else if (minorDamaged / plantableArea >= 0.045) {
                            ImportantProblem importantProblem = new ImportantProblem();
                            importantProblem.setBarangayName(barangays.get(a).getBarangayName());
                            importantProblem.setFarms(includedFarms);
                            importantProblem.setProblem(problems.get(b));
                            importantProblem.setTotalMajor(0);
                            importantProblem.setTotalMinor(minorDamaged);
                            importantProblem.setPlantableArea(plantableArea);
                            importantProblem.setMunicipalityName(municipalities.get(z).getMunicipalityName());
                            importantProblems.add(importantProblem);
                        } else if (majorDamaged / plantedArea >= 0.03) {
                            ImportantProblem importantProblem = new ImportantProblem();
                            importantProblem.setBarangayName(barangays.get(a).getBarangayName());
                            importantProblem.setFarms(includedFarms);
                            importantProblem.setProblem(problems.get(b));
                            importantProblem.setTotalMajor(majorDamaged);
                            importantProblem.setTotalMinor(0);
                            importantProblem.setPlantableArea(plantableArea);
                            importantProblem.setMunicipalityName(municipalities.get(z).getMunicipalityName());
                            importantProblems.add(importantProblem);
                        }
                    }
                }
            }
        }
        return importantProblems;
    }
}
