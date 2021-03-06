/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.web;

import dao.BarangayDAO;
import dao.DamageIncidentDAO;
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
import dao.ProgramSurveyDAO;
import dao.ProgramTriggerDAO;
import dao.SeedVarietyDAO;
import extra.Calculator;
import extra.Formatter;
import extra.ImportantProblem;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
import object.ProgramSurvey;
import object.ProgramTrigger;
import object.SeedVariety;

/**
 *
 * @author RubySenpaii
 */
public class MunicipalProgram extends BaseServlet {

    @Override
    protected void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        String action = request.getParameter("action");
        String path = "/homepage.jsp";
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        if (userLogged.getOfficeLevel().equals("MAO") && !userLogged.getAuthority().equals("Technician")) {
            if (action.equals("viewRecommendedPrograms")) {
                System.out.println("directing to programRecommendation.jsp...");
                viewRecommendedPrograms(request, response);
                path = "/municipal/programRecommendation.jsp";
            } else if (action.equals("goToProgramDeployment")) {
                System.out.println("directing to programDeployment.jsp...");
                goToProgramDeployment(request, response);
                path = "/municipal/programDeployment.jsp";
            } else if (action.equals("goToListOfOngoingPrograms")) {
                System.out.println("directing to programOngoingList.jsp");
                goToOngoingPrograms(request, response);
                path = "/municipal/programOngoingList.jsp";
            } else if (action.equals("viewDeployedProgramDetails")) {
                System.out.println("directing to programDeployedDetail.jsp");
                goToProgramDeployedDetail(request, response);
                path = "/municipal/programDeployedDetail.jsp";
            } else if (action.equals("viewProgramList")) {
                System.out.println("directing to programList.jsp");
                viewProgramList(request, response);
                path = "/municipal/programList.jsp";
            } else if (action.equals("viewProgramDetail")) {
                System.out.println("directing to programDetail.jsp");
                viewProgramDetail(request, response);
                path = "/municipal/programDetail.jsp";
            } else if (action.equals("deployProgram")) {
                System.out.println("deploy programs");
                boolean success = deployProgram(request, response);
                if (success) {
                    path = "/MunicipalProgram?action=goToListOfOngoingPrograms";
                } else {
                    path = "/MunicipalHomepage?action=goToHomePage";
                }
            } else if (action.equals("viewProblemRecommendation")) {
                System.out.println("directing to criticalDamages.jsp");
                viewProblemRecommendation(request, response);
                path = "/municipal/criticalDamages.jsp";
            } else if (action.equals("updateProgress")) {
                System.out.println("directing to programDeployedDetail.jsp");
                int deployedID = updateProgress(request, response);
                ArrayList<ProgramProgress> progress = new ProgramProgressDAO().getListOfProgramProgressForDeployedID(deployedID);
                DeployedProgram deployedProgram = new DeployedProgramDAO().getDeployedProgramDetails(deployedID);
                ArrayList<ProgramProcedure> procedures = new ProgramProcedureDAO().getListOfProgramProceduresForProgramID(deployedProgram.getProgramPlanID());
                System.out.println("prog " + progress.size() + " proc " + procedures.size());
                if (progress.size() == procedures.size()) {
                    deployedProgram.setStatus("Completed");
                    deployedProgram.setDateCompleted(new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime()));
                    boolean success = new DeployedProgramDAO().updateDeployedProgram(deployedProgram);
                    System.out.println(deployedID + " completed: " + success);
                }

                path = "/MunicipalProgram?action=viewDeployedProgramDetails&deployedID=" + deployedID;
            } else if (action.equals("viewRequestList")) {
                System.out.println("directing to programRequestList.jsp");
                viewRequestList(request, response);
                path = "/municipal/programRequestList.jsp";
            } else if (action.equals("sendRequest")) {
                System.out.println("directing to programRequestList.jsp");
                sendRequestList(request, response);
                path = "/MunicipalProgram?action=viewRequestList";
            } else if (action.equals("incompleteProgram")) {
                System.out.println("directing to programDeployedDetail.jsp");
                int deployedID = incompleteProgram(request, response);
                path = "/MunicipalProgram?action=viewDeployedProgramDetails&deployedID=" + deployedID;
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
        Formatter formatter = new Formatter();

        ArrayList<ImportantProblem> importantProblems = (ArrayList<ImportantProblem>) session.getAttribute("importantProblems");
        ImportantProblem importantProblem = importantProblems.get(Integer.parseInt(request.getParameter("index")));

        ArrayList<ProgramPlan> remove = new ArrayList<>();
        ArrayList<ProgramPlan> programPlans = new ProgramPlanDAO().getListOfProgramPlansForProblem(importantProblem.getProblem().getProblemID());
        for (int a = 0; a < programPlans.size(); a++) {
            double programResult = calculator.getProgramRating(programPlans.get(a).getProgramPlanID());
            String effectivity = calculator.getEffectivityResult(programResult);
            programPlans.get(a).setEffectivityRating(programResult);
            programPlans.get(a).setEffectivityStatus(effectivity);

            ArrayList<ProgramTrigger> programTriggers = new ProgramTriggerDAO().getListOfProgramTriggersForProgramID(programPlans.get(a).getProgramPlanID());
            String triggerName = "";
            System.out.println(programPlans.get(a).getProgramName());
            if (programTriggers.isEmpty()) {
                remove.add(programPlans.get(a));
            } else {
                for (int b = 0; b < programTriggers.size(); b++) {
                    System.out.println("trigger val " + programTriggers.get(b).getTriggerValue());
                    if (programTriggers.get(b).getTriggerName().equals("Farm Affected")) {
                        double farmPercentage = (double) importantProblem.getFarmAffected() / importantProblem.getFarmCount() * 100;
                        System.out.println("farm percentage " + farmPercentage);
                        if (programTriggers.get(b).getTriggerValue() > farmPercentage) {
                            remove.add(programPlans.get(a));
                            break;
                        } else {
                            int requirement = (int) Math.ceil(programTriggers.get(b).getTriggerValue() / 100 * importantProblem.getFarmCount());
                            programPlans.get(a).setProgramTriggerFarmCount(requirement);
                            if (triggerName.length() > 0) {
                                triggerName += "<br>";
                            }
                            triggerName += "Affected Farm Count: " + formatter.doubleToString(programTriggers.get(b).getTriggerValue()) + "% of " + importantProblem.getFarmCount()
                                    + " farm(s) needed <b>(" + requirement + "farm(s))</b>";
                        }
                    } else if (programTriggers.get(b).getTriggerName().equals("Minor Damages")) {
                        double minorPercentage = (double) importantProblem.getTotalMinor() / importantProblem.getPlantableArea() * 100;
                        System.out.println("minor percentage " + minorPercentage);
                        if (programTriggers.get(b).getTriggerValue() > minorPercentage) {
                            remove.add(programPlans.get(a));
                            break;
                        } else {
                            double requirement = programTriggers.get(b).getTriggerValue() / 100 * importantProblem.getPlantableArea();
                            programPlans.get(a).setProgramTriggerFarmArea(requirement);
                            if (triggerName.length() > 0) {
                                triggerName += "<br>";
                            }
                            triggerName += "Minor Damaged Area: " + formatter.doubleToString(programTriggers.get(b).getTriggerValue()) + "% of " + importantProblem.getPlantableArea()
                                    + " ha needed <b>(" + requirement + " ha)</b>";
                        }
                    } else if (programTriggers.get(b).getTriggerName().equals("Major Damages")) {
                        double majorPercentage = (double) importantProblem.getTotalMajor() / importantProblem.getPlantableArea() * 100;
                        System.out.println("major percentage " + majorPercentage);
                        if (programTriggers.get(b).getTriggerValue() > majorPercentage) {
                            remove.add(programPlans.get(a));
                            break;
                        } else {
                            double requirement = programTriggers.get(b).getTriggerValue() / 100 * importantProblem.getPlantableArea();
                            programPlans.get(a).setProgramTriggerFarmArea(requirement);
                            if (triggerName.length() > 0) {
                                triggerName += "<br>";
                            }
                            triggerName += "Major Damaged Area: " + formatter.doubleToString(programTriggers.get(b).getTriggerValue()) + "% of " + importantProblem.getPlantableArea()
                                    + " ha needed <b>(" + requirement + " ha)</b>";
                        }
                    } else {
                        System.out.println("Unknown Trigger Name: " + programTriggers.get(b).getTriggerName());
                    }
                }

                programPlans.get(a).setProgramTrigger(triggerName);
            }
        }
        for (int a = 0; a < remove.size(); a++) {
            programPlans.remove(remove.get(a));
        }

        Collections.sort(programPlans, (ProgramPlan p1, ProgramPlan p2) -> Double.compare(p2.getEffectivityRating(), p1.getEffectivityRating()));

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
        ArrayList<ProgramProcedure> procedures = new ProgramProcedureDAO().getListOfProgramProceduresForProgramID(programPlanID);
        ProgramPlan programPlan = new ProgramPlanDAO().getProgramPlanDetail(programPlanID);

        session.setAttribute("procedures", procedures);
        session.setAttribute("varieties", varieties);
        session.setAttribute("fertilizers", fertilizers);
        session.setAttribute("programPlan", programPlan);
        session.setAttribute("programPlanID", programPlanID);
        session.setAttribute("farms", farms);
    }

    private void goToOngoingPrograms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<DeployedProgram> ongoingDeployedPrograms = new DeployedProgramDAO().getListOfOngoingProgramsForMunicipality(userLogged.getMunicipalityID());

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

    private void viewProgramList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Calculator calculator = new Calculator();

        ArrayList<ProgramPlan> programPlans = new ProgramPlanDAO().getListOfProgramPlans();

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
        deployed.setAssignedMunicipality(userLogged.getMunicipalityID());
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
            ArrayList<DamageIncident> incidents = (ArrayList<DamageIncident>) session.getAttribute("farms");
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

        ArrayList<ImportantProblem> importantProblems = getListOfPestDiseaseProblem3(request, response);

        session.setAttribute("importantProblems", importantProblems);
    }

    private int updateProgress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        String remarks = request.getParameter("remarks");
        int procedureNo = Integer.parseInt(request.getParameter("procedureNo"));
        int deployedID = (int) session.getAttribute("deployedID");
        ProgramProgress progress = new ProgramProgress();
        progress.setDeployedID(deployedID);
        progress.setUpdatedBy(userLogged.getEmployeeID());
        progress.setProcedureNumber(procedureNo);
        progress.setDateCompleted(dateNow);
        progress.setRemarks(remarks);
        progress.setImage("nothing");
        boolean updateProgress = new ProgramProgressDAO().addProgramProgress(progress);
        System.out.println("update status: " + updateProgress);

        return deployedID;
    }

    private void viewRequestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        Municipality municipal = new MunicipalityDAO().getMunicipalDetail(userLogged.getMunicipalityID());
        ArrayList<ProgramRequest> requests = new ProgramRequestDAO().getListOfProgramRequestsInMunicipality(userLogged.getMunicipalityID());

        session.setAttribute("municipal", municipal);
        session.setAttribute("requests", requests);
    }

    private void sendRequestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");
        ProgramRequestDAO prdao = new ProgramRequestDAO();
        String name = userLogged.getLastName() + ", " + userLogged.getFirstName();

        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        String completeDetails = "";
        String requestName = request.getParameter("requestName");
        completeDetails += "Title: " + requestName + "<br>";

        String problem = request.getParameter("problemName");
        completeDetails += "Problem: " + problem + "<br>";

        int farmsAffected = Integer.parseInt(request.getParameter("farmsAffected"));
        completeDetails += "Farm(s) Affected: " + farmsAffected + " farm(s)<br>";

        String damageType = request.getParameter("damageType");
        completeDetails += "Damage Type: " + damageType + "<br>";

        double damageValue = Double.parseDouble(request.getParameter("damageValue"));
        completeDetails += "Amount Damaged: " + damageValue + " ha<br>";

        String requestDetail = request.getParameter("requestDetail");
        completeDetails += "Description: " + requestDetail;

        ProgramRequest progRequest = new ProgramRequest();
        progRequest.setDateRequested(dateNow);
        progRequest.setMunicipalityID(userLogged.getMunicipalityID());
        progRequest.setProgramPlanID(-1);
        progRequest.setRequestBy(name);
        progRequest.setRequestDetail(completeDetails);
        progRequest.setRequestID(prdao.getListOfProgramRequests().size() + 1);
        progRequest.setRequestStatus("Requested");
        boolean success = prdao.addProgramRequest(progRequest);
        System.out.println("program request submitted: " + success);
    }

    private int incompleteProgram(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeployedProgram deployedProgram = new DeployedProgramDAO().getDeployedProgramDetails(Integer.parseInt(request.getParameter("deployedID")));
        deployedProgram.setStatus("Incomplete");
        deployedProgram.setDateCompleted(new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime()));
        boolean updateSuccess = new DeployedProgramDAO().updateDeployedProgram(deployedProgram);
        System.out.println("deployed id " + deployedProgram.getDeployedID() + " change to incomplete: " + updateSuccess);
        return deployedProgram.getDeployedID();
    }

    private ArrayList<ImportantProblem> getListOfPestDiseaseProblem3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<ImportantProblem> importantProblems = new ArrayList<>();
        ArrayList<Barangay> barangays = new BarangayDAO().getMunicipalNotification(userLogged.getMunicipalityID());
        for (int a = 0; a < barangays.size(); a++) {

            System.out.print(barangays.get(a).getBarangayName() + ":");
            System.out.print("problem: " + barangays.get(a).getProblemName());
            System.out.print("totalarea: " + barangays.get(a).getArea());
            System.out.print("major: " + barangays.get(a).getMajorDamagedArea());
            System.out.print("minor: " + barangays.get(a).getMinorDamagedArea());
            if (barangays.get(a).getProblemName() != null) {
                Problem problem = new ProblemDAO().getProblemWithName(barangays.get(a).getProblemName());
                double plantableArea = barangays.get(a).getArea();
                double majorDamaged = barangays.get(a).getMajorDamagedArea();
                double minorDamaged = barangays.get(a).getMinorDamagedArea();

                if (problem.getType().equals("Calamity")) {
                    double calamityMajor = majorDamaged / plantableArea;
                    if (calamityMajor >= 0.4) {
                        ImportantProblem importantProblem = new ImportantProblem();
                        importantProblem.setBarangayName(barangays.get(a).getBarangayName());
                        importantProblem.setProblem(problem);
                        importantProblem.setTotalMajor(majorDamaged);
                        importantProblem.setTotalMinor(minorDamaged);
                        importantProblem.setPlantableArea(plantableArea);
                        importantProblem.setFarmAffected(barangays.get(a).getFarmAffected());
                        importantProblem.setFarmCount(barangays.get(a).getFarmCount());
                        importantProblem.setDamageType("Major Damages");
                        importantProblems.add(importantProblem);
                    }
                } else if (majorDamaged / plantableArea >= 0.03) {
                    ImportantProblem importantProblem = new ImportantProblem();
                    importantProblem.setBarangayName(barangays.get(a).getBarangayName());
                    importantProblem.setProblem(problem);
                    importantProblem.setTotalMajor(majorDamaged);
                    importantProblem.setTotalMinor(minorDamaged);
                    importantProblem.setPlantableArea(plantableArea);
                    importantProblem.setFarmAffected(barangays.get(a).getFarmAffected());
                    importantProblem.setFarmCount(barangays.get(a).getFarmCount());
                    importantProblem.setDamageType("Major Damages");
                    importantProblems.add(importantProblem);
                } else if (minorDamaged / plantableArea >= 0.045) {
                    ImportantProblem importantProblem = new ImportantProblem();
                    importantProblem.setBarangayName(barangays.get(a).getBarangayName());
                    importantProblem.setProblem(problem);
                    importantProblem.setTotalMajor(majorDamaged);
                    importantProblem.setTotalMinor(minorDamaged);
                    importantProblem.setPlantableArea(plantableArea);
                    importantProblem.setFarmAffected(barangays.get(a).getFarmAffected());
                    importantProblem.setFarmCount(barangays.get(a).getFarmCount());
                    importantProblem.setDamageType("Minor Damages");
                    importantProblems.add(importantProblem);
                }
            }
        }
        System.out.println("problem size " + importantProblems.size());
        return importantProblems;
    }

    private ArrayList<ImportantProblem> getListOfPestDiseaseProblem2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee userLogged = (Employee) session.getAttribute("userLogged");

        ArrayList<ImportantProblem> importantProblems = new ArrayList<>();
        ArrayList<Problem> problems = new ProblemDAO().getListOfProblems();
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(userLogged.getMunicipalityID());
        ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getListOfPlantingReportsByProblemFarmBarangay(userLogged.getMunicipalityID());
        for (int a = 0; a < problems.size(); a++) {
            for (int b = 0; b < barangays.size(); b++) {
                double plantableArea = 0, majorDamaged = 0, minorDamaged = 0;
                ArrayList<Farm> includedFarms = new ArrayList<>();

                ArrayList<Farm> farms = new FarmDAO().getListOfFarmsInBarangay(barangays.get(b).getBarangayName());
                int farmCount = farms.size();
                for (int c = 0; c < farms.size(); c++) {
                    ArrayList<Plot> plots = new PlotDAO().getListOfPlotsInFarm(farms.get(c));
                    for (int d = 0; d < plots.size(); d++) {
                        plantableArea += plots.get(d).getPlotSize();
                    }

                    for (int d = 0; d < incidents.size(); d++) {
                        if (incidents.get(d).getBarangayName().equals(barangays.get(b).getBarangayName()) && incidents.get(d).getProblemName().equals(problems.get(a).getProblemName()) && farms.get(c).getFarmName().equals(incidents.get(d).getFarmName())) {
                            majorDamaged += incidents.get(d).getTotalAreaDamaged();
                            minorDamaged += incidents.get(d).getTotalAreaAffected();

                            farms.get(c).setAreaDamaged(majorDamaged);
                            farms.get(c).setAreaAffected(minorDamaged);
                            includedFarms.add(farms.get(c));
                        }
                    }
                }

                if (!includedFarms.isEmpty()) {
                    if (problems.get(a).getType().equals("Calamity")) {
                        double calamityMajor = majorDamaged / plantableArea;
                        if (calamityMajor >= 0.4) {
                            System.out.println("problem " + problems.get(a).getProblemName() + " barangay " + barangays.get(b).getBarangayName() + " major " + majorDamaged + " minor " + minorDamaged + " plantable " + plantableArea);
                            ImportantProblem importantProblem = new ImportantProblem();
                            importantProblem.setBarangayName(barangays.get(b).getBarangayName());
                            importantProblem.setFarms(includedFarms);
                            importantProblem.setProblem(problems.get(b));
                            importantProblem.setTotalMajor(majorDamaged);
                            importantProblem.setTotalMinor(minorDamaged);
                            importantProblem.setPlantableArea(plantableArea);
                            importantProblem.setFarmCount(farmCount);
                            importantProblems.add(importantProblem);
                        }
                    } else if (majorDamaged / plantableArea >= 0.03) {
                        System.out.println("problem " + problems.get(a).getProblemName() + " barangay " + barangays.get(b).getBarangayName() + " major " + majorDamaged + " minor " + minorDamaged + " plantable " + plantableArea);
                        ImportantProblem importantProblem = new ImportantProblem();
                        importantProblem.setBarangayName(barangays.get(b).getBarangayName());
                        importantProblem.setFarms(includedFarms);
                        importantProblem.setProblem(problems.get(a));
                        importantProblem.setTotalMajor(majorDamaged);
                        importantProblem.setTotalMinor(minorDamaged);
                        importantProblem.setPlantableArea(plantableArea);
                        importantProblem.setFarmCount(farmCount);
                        importantProblems.add(importantProblem);
                    } else if (minorDamaged / plantableArea >= 0.045) {
                        System.out.println("problem " + problems.get(a).getProblemName() + " barangay " + barangays.get(b).getBarangayName() + " major " + majorDamaged + " minor " + minorDamaged + " plantable " + plantableArea);
                        ImportantProblem importantProblem = new ImportantProblem();
                        importantProblem.setBarangayName(barangays.get(b).getBarangayName());
                        importantProblem.setFarms(includedFarms);
                        importantProblem.setProblem(problems.get(a));
                        importantProblem.setTotalMajor(majorDamaged);
                        importantProblem.setTotalMinor(minorDamaged);
                        importantProblem.setPlantableArea(plantableArea);
                        importantProblem.setFarmCount(farmCount);
                        importantProblems.add(importantProblem);
                    }
                }
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
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(userLogged.getMunicipalityID());
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
                                        System.out.println(problems.get(b).getProblemName() + " " + majorDamaged + " " + minorDamaged);

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
                        importantProblems.add(importantProblem);
                    } else if (majorDamaged / plantedArea >= 0.03) {
                        ImportantProblem importantProblem = new ImportantProblem();
                        importantProblem.setBarangayName(barangays.get(a).getBarangayName());
                        importantProblem.setFarms(includedFarms);
                        importantProblem.setProblem(problems.get(b));
                        importantProblem.setTotalMajor(majorDamaged);
                        importantProblem.setTotalMinor(0);
                        importantProblem.setPlantableArea(plantableArea);
                        importantProblems.add(importantProblem);
                    }
                }
            }
        }
        System.out.println("problem size " + importantProblems.size());
        return importantProblems;
    }
}
