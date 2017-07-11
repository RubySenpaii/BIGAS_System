/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.mobile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.BarangayDAO;
import dao.DamageIncidentDAO;
import dao.DamageReportDAO;
import delete.DamageSolutionDAO;
import dao.DeployedEvaluationDAO;
import dao.DeployedProgramDAO;
import dao.EmployeeDAO;
import dao.FarmDAO;
import dao.FarmerDAO;
import dao.FertilizerDAO;
import dao.FertilizerRecommendationDAO;
import delete.MaterialProvidedDAO;
import dao.MunicipalityDAO;
import dao.NotificationDAO;
import dao.NutrientRecommendationDAO;
import dao.PlantingReportDAO;
import dao.PlotDAO;
import dao.PlotFertilizerDAO;
import dao.ProblemDAO;
import dao.ProgramBeneficiaryDAO;
import dao.ProgramPlanDAO;
import dao.ProgramProcedureDAO;
import dao.ProgramProgressDAO;
import dao.ProgramRequestDAO;
import dao.ProgramSurveyDAO;
import dao.ProgramTargetDAO;
import dao.ProgramTriggerDAO;
import dao.SeedVarietyDAO;
import dao.SoilAnalysisDAO;
import dao.TargetProductionDAO;
import dao.WeeklyPlantingReportDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.Barangay;
import object.DamageIncident;
import object.DamageReport;
import object.DeployedProgram;
import object.Employee;
import object.Farm;
import object.Farmer;
import object.Fertilizer;
import object.FertilizerRecommendation;
import delete.MaterialProvided;
import object.Municipality;
import object.Notification;
import object.NutrientRecommendation;
import object.PlantingReport;
import object.Plot;
import object.PlotFertilizer;
import object.Problem;
import object.ProblemSolution;
import object.ProgramBeneficiary;
import delete.ProgramPersonnel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import object.DeployedEvaluation;
import object.ProgramPlan;
import object.ProgramProcedure;
import object.ProgramProgress;
import object.ProgramRequest;
import object.ProgramSurvey;
import object.ProgramTarget;
import object.ProgramTrigger;
import object.SeedVariety;
import object.SoilAnalysis;
import object.TargetProduction;
import object.WeeklyPlantingReport;

/**
 *
 * @author RubySenpaii
 */
public class MobileDatabaseSync extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataReceived = request.getParameter("dataToBeRetrieved");

        System.out.println("receiving..." + dataReceived);
        if (dataReceived.equals("barangays")) {
            System.out.println("send barangay data to mobile");
            ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangays();
            response.getWriter().write(new Gson().toJson(barangays));
        } else if (dataReceived.equals("damageIncident")) {
            System.out.println("send incident data to mobile");
            ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getListOfDamages();
            response.getWriter().write(new Gson().toJson(incidents));
            System.out.println("incident size: " + incidents.size());
        } else if (dataReceived.equals("damageReport")) {
            System.out.println("send damageReport data to mobile");
            ArrayList<DamageReport> damageReports = new DamageReportDAO().getListOfDamageReports();
            response.getWriter().write(new Gson().toJson(damageReports));
        } else if (dataReceived.equals("deployedProgram")) {
            System.out.println("send deployedPrograms data to mobile");
            ArrayList<DeployedProgram> deployedPrograms = new DeployedProgramDAO().getListOfDeployedPrograms();
            response.getWriter().write(new Gson().toJson(deployedPrograms));
        } else if (dataReceived.equals("deployedEvaluation")) {
            System.out.println("send deployedEvaluation data to mobile");
            ArrayList<DeployedEvaluation> evaluations = new DeployedEvaluationDAO().getListOfProgramEvaluations();
            response.getWriter().write(new Gson().toJson(evaluations));
        } else if (dataReceived.equals("employees")) {
            System.out.println("send employee data to mobile");
            ArrayList<Employee> employees = new EmployeeDAO().getListOfEmployees();
            response.getWriter().write(new Gson().toJson(employees));
        } else if (dataReceived.equals("farms")) {
            System.out.println("send farm data to mobile");
            ArrayList<Farm> farms = new FarmDAO().getListOfFarms();
            response.getWriter().write(new Gson().toJson(farms));
        } else if (dataReceived.equals("farmers")) {
            System.out.println("send farmer data to mobile");
            ArrayList<Farmer> farmers = new FarmerDAO().getListOfFarmers();
            response.getWriter().write(new Gson().toJson(farmers));
        } else if (dataReceived.equals("fertilizers")) {
            System.out.println("send fertilizers data to mobile");
            ArrayList<Fertilizer> fertilizers = new FertilizerDAO().getListOfFertilizers();
            response.getWriter().write(new Gson().toJson(fertilizers));
        } else if (dataReceived.equals("fertilizerRecommendation")) {
            System.out.println("send fertilizerRecommendation data to mobile");
            ArrayList<FertilizerRecommendation> fertilizerRecommendations = new FertilizerRecommendationDAO().getListOfFertilizerRecommendations();
            response.getWriter().write(new Gson().toJson(fertilizerRecommendations));
        } else if (dataReceived.equals("materialProvided")) {
            System.out.println("send materialProvided data to mobile");
            ArrayList<MaterialProvided> materialsProvided = new MaterialProvidedDAO().getListOfMaterialsProvided();
            response.getWriter().write(new Gson().toJson(materialsProvided));
        } else if (dataReceived.equals("municipalities")) {
            System.out.println("send municipality data to mobile");
            ArrayList<Municipality> municipalities = new MunicipalityDAO().getListOfMunicipalities();
            response.getWriter().write(new Gson().toJson(municipalities));
        } else if (dataReceived.equals("notification")) {
            System.out.println("send notification data to mobile");
            ArrayList<Notification> notifications = new NotificationDAO().getListOfNotifications();
            response.getWriter().write(new Gson().toJson(notifications));
        } else if (dataReceived.equals("nutrientRecommendation")) {
            System.out.println("send nutrientRecommendation data to mobile");
            ArrayList<NutrientRecommendation> nutrientRecommendations = new NutrientRecommendationDAO().getListOfNutrientRecommendations();
            response.getWriter().write(new Gson().toJson(nutrientRecommendations));
        } else if (dataReceived.equals("plantingReport")) {
            System.out.println("send plantingReport data to mobile");
            ArrayList<PlantingReport> plantingReports = new PlantingReportDAO().getListOfPlantingReports();
            response.getWriter().write(new Gson().toJson(plantingReports));
        } else if (dataReceived.equals("plot")) {
            System.out.println("send plot data to mobile");
            ArrayList<Plot> plots = new PlotDAO().getListOfPlots();
            response.getWriter().write(new Gson().toJson(plots));
        } else if (dataReceived.equals("plotFertilizer")) {
            System.out.println("send plotFertilizer data to mobile");
            ArrayList<PlotFertilizer> plotFertilizers = new PlotFertilizerDAO().getListOfPlotFertilizers();
            response.getWriter().write(new Gson().toJson(plotFertilizers));
        } else if (dataReceived.equals("problems")) {
            System.out.println("send problem data to mobile");
            ArrayList<Problem> problems = new ProblemDAO().getListOfProblems();
            response.getWriter().write(new Gson().toJson(problems));
        } else if (dataReceived.equals("programBeneficiary")) {
            System.out.println("send programBeneficiary data to mobile");
            ArrayList<ProgramBeneficiary> beneficiaries = new ProgramBeneficiaryDAO().getListOfProgramBeneficiaries();
            response.getWriter().write(new Gson().toJson(beneficiaries));
        } else if (dataReceived.equals("programPlan")) {
            System.out.println("send programPlan data to mobile");
            ArrayList<ProgramPlan> programPlans = new ProgramPlanDAO().getListOfProgramPlans();
            response.getWriter().write(new Gson().toJson(programPlans));
        } else if (dataReceived.equals("programProcedure")) {
            System.out.println("send programProcedure data to mobile");
            ArrayList<ProgramProcedure> programProcedures = new ProgramProcedureDAO().getListOfProgramProcedures();
            response.getWriter().write(new Gson().toJson(programProcedures));
        } else if (dataReceived.equals("programProgress")) {
            System.out.println("send programProgress data to mobile");
            ArrayList<ProgramProgress> programProgresses = new ProgramProgressDAO().getListOfProgramProgresses();
            response.getWriter().write(new Gson().toJson(programProgresses));
        } else if (dataReceived.equals("programRequest")) {
            System.out.println("send programRequest data to mobile");
            ArrayList<ProgramRequest> programRequests = new ProgramRequestDAO().getListOfProgramRequests();
            response.getWriter().write(new Gson().toJson(programRequests));
        } else if (dataReceived.equals("programTarget")) {
            System.out.println("send programTarget data to mobile");
            ArrayList<ProgramTarget> programTargets = new ProgramTargetDAO().getListOfProgramTargets();
            response.getWriter().write(new Gson().toJson(programTargets));
        } else if (dataReceived.equals("programTrigger")) {
            System.out.println("send programTrigger data to mobile");
            ArrayList<ProgramTrigger> programTriggers = new ProgramTriggerDAO().getListOfProgramTriggers();
            response.getWriter().write(new Gson().toJson(programTriggers));
        } else if (dataReceived.equals("seedVarieties")) {
            System.out.println("send seedVariety data to mobile");
            ArrayList<SeedVariety> seedVarieties = new SeedVarietyDAO().getListOfSeedVarieties();
            response.getWriter().write(new Gson().toJson(seedVarieties));
        } else if (dataReceived.equals("soilAnalysis")) {
            System.out.println("send soilAnalysis data to mobile");
            ArrayList<SoilAnalysis> soilAnalyses = new SoilAnalysisDAO().getListOfSoilAnalyses();
            response.getWriter().write(new Gson().toJson(soilAnalyses));
        } else if (dataReceived.equals("targetProduction")) {
            System.out.println("send targetProduction data to mobile");
            ArrayList<TargetProduction> targetProductions = new TargetProductionDAO().getListOfTargetProductions();
            response.getWriter().write(new Gson().toJson(targetProductions));
        } else if (dataReceived.equals("weeklyPlantingReport")) {
            System.out.println("send weeklyPlantingReport data to mobile");
            ArrayList<WeeklyPlantingReport> weeklyPlantingReports = new WeeklyPlantingReportDAO().getListOfWeeklyPlantingReports();
            response.getWriter().write(new Gson().toJson(weeklyPlantingReports));
        } else if (dataReceived.equals("programSurvey")) {
            System.out.println("send programSurvey data to mobile");
            ArrayList<ProgramSurvey> surveys = new ProgramSurveyDAO().getListOfProgramSurveys();
            response.getWriter().write(new Gson().toJson(surveys));
        } else if (dataReceived.equals("getData")) {
//            retrieveMunicipalityDataFromMobile(request, response);
//            retrieveBarangayDataFromMobile(request, response);
//            retrieveFarmDataFromMobile(request, response);
//            retrieveFarmerDataFromMobile(request, response);
//            retrievePlotDataFromMobile(request, response);
//            retrievePlotFertilizerDataFromMobile(request, response);
//            retrievePlantingReportDataFromMobile(request, response);
//            retrieveWeeklyPlantingReportDataFromMobile(request, response);
//            retrieveProblemDataFromMobile(request, response);
//            retrieveDamageIncidentDataFromMobile(request, response);
//            retrieveDamageReportDataFromMobile(request, response);
//            retrieveDeployedEvaluationDataFromMobile(request, response);
            
            response.getWriter().write("done download");
        } else {
            System.out.println("unknown data to be retrieved: " + dataReceived);
        }
        
        System.out.println(new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + " MobileDatabaseSync");
    }

    private void retrieveBarangayDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("barangay input form mobile upload");
        ArrayList<Barangay> barangays = new Gson().fromJson(request.getParameter("barangays"), new TypeToken<List<Barangay>>() {
        }.getType());
        for (int a = 0; a < barangays.size(); a++) {
            if (a < new BarangayDAO().getListOfBarangays().size()) {
                if (new BarangayDAO().updateBarangays(barangays.get(a))) {
                    updateCount++;
                }
            } else if (new BarangayDAO().addBarangay(barangays.get(a))) {
                addCount++;
            } else {
                System.out.println(barangays.get(a).getBarangayID()+ " not added/updated");
            }
        }
        System.out.println("barangay input count updated: " + updateCount);
        System.out.println("barangay input count added: " + addCount);
    }

    private void retrieveDamageIncidentDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("damageIncident input form mobile upload");
        ArrayList<DamageIncident> incidents = new Gson().fromJson(request.getParameter("damageIncidents"), new TypeToken<List<DamageIncident>>() {
        }.getType());
        System.out.println(incidents.size());
        for (int a = 0; a < incidents.size(); a++) {
            if (a < new DamageIncidentDAO().getListOfDamages().size()) {
                if (new DamageIncidentDAO().updateDamageIncident(incidents.get(a))) {
                    updateCount++;
                }
            } else if (new DamageIncidentDAO().reportDamageIncident(incidents.get(a))) {
                addCount++;
            } else {
                System.out.println(incidents.get(a).getDamageIncidentID()+ " not added/updated");
            }
        }
        System.out.println("damageIncident input count updated: " + updateCount);
        System.out.println("damageIncident input count added: " + addCount);
    }

    private void retrieveDamageReportDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("damageReport input form mobile upload");
        ArrayList<DamageReport> damageReports = new Gson().fromJson(request.getParameter("damageReports"), new TypeToken<List<DamageReport>>() {
        }.getType());
        System.out.println(damageReports.size());
        for (int a = 0; a < damageReports.size(); a++) {
            if (a < new DamageReportDAO().getListOfDamageReports().size()) {
                if (new DamageReportDAO().updateDamageReport(damageReports.get(a))) {
                    updateCount++;
                }
            } else if (new DamageReportDAO().reportDamageReport(damageReports.get(a))) {
                addCount++;
            } else {
                System.out.println(damageReports.get(a).getDamageIncidentID() + " on " + damageReports.get(a).getDateReported() + " not added/updated");
            }
        }
        System.out.println("damage report input count updated: " + updateCount);
        System.out.println("damage report input count added: " + addCount);
    }

    private void retrieveDeployedProgramDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("deployedProgram input form mobile upload");
        ArrayList<DeployedProgram> deployedPrograms = new Gson().fromJson(request.getParameter("deployedProgram"), new TypeToken<List<DeployedProgram>>() {
        }.getType());
        for (int a = 0; a < deployedPrograms.size(); a++) {
            if (a < new DeployedProgramDAO().getListOfDeployedPrograms().size()) {
                if (new DeployedProgramDAO().updateDeployedProgram(deployedPrograms.get(a))) {
                    updateCount++;
                }
            } else if (new DeployedProgramDAO().addDeployedProgram(deployedPrograms.get(a))) {
                addCount++;
            } else {
                System.out.println(deployedPrograms.get(a).getDeployedID()+ " not added/updated");
            }
        }
        System.out.println("deployedPrograms input count updated: " + updateCount);
        System.out.println("deployedPrograms input count added: " + addCount);
    }
    
    private void retrieveDeployedEvaluationDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("deployedEvaluation input form mobile upload");
        ArrayList<DeployedEvaluation> deployedEvaluations = new Gson().fromJson(request.getParameter("deployedEvaluations"), new TypeToken<List<DeployedEvaluation>>() {
        }.getType());
        for (int a = 0; a < deployedEvaluations.size(); a++) {
            if (a < new DeployedEvaluationDAO().getListOfProgramEvaluations().size()) {
                //do nothing
            } else if (new DeployedEvaluationDAO().addDeployedEvaluation(deployedEvaluations.get(a))) {
                addCount++;
            } else {
                System.out.println(deployedEvaluations.get(a).getDeployedID()+ " not added/updated");
            }
        }
        System.out.println("deployed eval input count updated: " + updateCount);
        System.out.println("deployed eval input count added: " + addCount);
    }

    private void retrieveEmployeeDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("employee input form mobile upload");
        ArrayList<Employee> employees = new Gson().fromJson(request.getParameter("employees"), new TypeToken<List<Employee>>() {
        }.getType());
        for (int a = 0; a < employees.size(); a++) {
            if (a < new EmployeeDAO().getListOfEmployees().size()) {
                if (new EmployeeDAO().updateEmployee(employees.get(a))) {
                    updateCount++;
                }
            } else if (new EmployeeDAO().registerEmployee(employees.get(a))) {
                addCount++;
            } else {
                System.out.println(employees.get(a).getEmployeeID() + " not added/updated");
            }
        }
        System.out.println("employee input count updated: " + updateCount);
        System.out.println("employee input count added: " + addCount);
    }

    private void retrieveFarmDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("farm input form mobile upload");
        ArrayList<Farm> farms = new Gson().fromJson(request.getParameter("farms"), new TypeToken<List<Farm>>() {
        }.getType());
        for (int a = 0; a < farms.size(); a++) {
            if (a < new FarmDAO().getListOfFarms().size()) {
                if (new FarmDAO().updateFarm(farms.get(a))) {
                    updateCount++;
                }
            } else if (new FarmDAO().addFarm(farms.get(a))) {
                addCount++;
            } else {
                System.out.println(farms.get(a).getFarmID() + " not added/updated");
            }
        }
        System.out.println("farm input count updated: " + updateCount);
        System.out.println("farm input count added: " + addCount);
    }

    private void retrieveFarmerDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;
        
        System.out.println("farmer input form mobile upload");
        ArrayList<Farmer> farmers = new Gson().fromJson(request.getParameter("farmers"), new TypeToken<List<Farmer>>() {
        }.getType());
        for (int a = 0; a < farmers.size(); a++) {
            if (a < new FarmerDAO().getListOfFarmers().size()) {
                if (new FarmerDAO().updateFarmer(farmers.get(a))) {
                    updateCount++;
                }
            } else if (new FarmerDAO().registerFarmer(farmers.get(a))) {
                addCount++;
            } else {
                System.out.println(farmers.get(a).getFarmerID() + " not added/updated");
            }
        }
        System.out.println("farmer input count updated: " + updateCount);
        System.out.println("farmer input count added: " + addCount);
    }

    private void retrieveFertilizerDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("fertilizer input form mobile upload");
        ArrayList<Fertilizer> fertilizers = new Gson().fromJson(request.getParameter("fertilizers"), new TypeToken<List<Fertilizer>>() {
        }.getType());
        for (int a = 0; a < fertilizers.size(); a++) {
            if (a < new FertilizerDAO().getListOfFertilizers().size()) {
                //do nothing
            } else if (new FertilizerDAO().addFertilizer(fertilizers.get(a))) {
                addCount++;
            } else {
                System.out.println(fertilizers.get(a).getFertilizerName()+ " not added/updated");
            }
        }
        System.out.println("fertilizer input count updated: " + updateCount);
        System.out.println("fertilizer input count added: " + addCount);
    }

    private void retrieveFertilizerRecommendationDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("fertilizerRecommendation input form mobile upload");
        ArrayList<FertilizerRecommendation> fertilizerRecommendations = new Gson().fromJson(request.getParameter("fertilizerRecommendations"), new TypeToken<List<FertilizerRecommendation>>() {
        }.getType());
        for (int a = 0; a < fertilizerRecommendations.size(); a++) {
            if (a < new FertilizerRecommendationDAO().getListOfFertilizerRecommendations().size()) {
                //do nothing
            } else if (new FertilizerRecommendationDAO().addFertilizerRecommendation(fertilizerRecommendations.get(a))) {
                addCount++;
            } else {
                System.out.println(fertilizerRecommendations.get(a).getSoilAnalysisNumber()+ " not added/updated");
            }
        }
        System.out.println("fertilizerRecommendation input count updated: " + updateCount);
        System.out.println("fertilizerRecommendation input count added: " + addCount);
    }

    private void retrieveMunicipalityDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("municipality input form mobile upload");
        ArrayList<Municipality> municipalities = new Gson().fromJson(request.getParameter("municipalities"), new TypeToken<List<Municipality>>() {
        }.getType());
        for (int a = 0; a < municipalities.size(); a++) {
            if (a < new MunicipalityDAO().getListOfMunicipalities().size()) {
                if (new MunicipalityDAO().updateMunicipality(municipalities.get(a))) {
                    updateCount++;
                }
            } else if (new MunicipalityDAO().addMunicipality(municipalities.get(a))) {
                addCount++;
            } else {
                System.out.println(municipalities.get(a).getMunicipalityID()+ " not added/updated");
            }
        }
        System.out.println("plot input count updated: " + updateCount);
        System.out.println("plot input count added: " + addCount);
    }

    private void retrieveNutrientRecommendationDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("nutrientRecommendation input form mobile upload");
        ArrayList<NutrientRecommendation> nutrientRecommendations = new Gson().fromJson(request.getParameter("nutrientRecommendations"), new TypeToken<List<NutrientRecommendation>>() {
        }.getType());
        for (int a = 0; a < nutrientRecommendations.size(); a++) {
            if (a < new NutrientRecommendationDAO().getListOfNutrientRecommendations().size()) {
                //do nothing
            } else if (new NutrientRecommendationDAO().addNutrientRecommendation(nutrientRecommendations.get(a))) {
                addCount++;
            } else {
                System.out.println(nutrientRecommendations.get(a).getSoilAnalysisNumber()+ " not added/updated");
            }
        }
        System.out.println("nutrientRecommendation input count updated: " + updateCount);
        System.out.println("nutrientRecommendation input count added: " + addCount);
    }

    private void retrievePlantingReportDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("plantingReport input form mobile upload");
        ArrayList<PlantingReport> plantingReports = new Gson().fromJson(request.getParameter("plantingReports"), new TypeToken<List<PlantingReport>>() {
        }.getType());
        
        ArrayList<PlantingReport> reports = new PlantingReportDAO().getListOfPlantingReports();
        for (int a = reports.size(); a < reports.size(); a++){
            if (new PlantingReportDAO().addPlantingReport(plantingReports.get(a))) {
                System.out.println("added");
            }
        }
        
        for (int a = 0; a < plantingReports.size(); a++) {
            if (a < new PlantingReportDAO().getListOfPlantingReports().size()) {
                if (new PlantingReportDAO().updatePlantingReport(plantingReports.get(a))) {
                    updateCount++;
                }
            } else if (new PlantingReportDAO().addPlantingReport(plantingReports.get(a))) {
                addCount++;
            } else {
                System.out.println(plantingReports.get(a).getPlotID() + " not added/updated");
            }
        }
        System.out.println("plantingReport input count updated: " + updateCount);
        System.out.println("plantingReport input count added: " + addCount);
    }

    private void retrievePlotDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("plot input form mobile upload");
        ArrayList<Plot> plots = new Gson().fromJson(request.getParameter("plots"), new TypeToken<List<Plot>>() {
        }.getType());
        for (int a = 0; a < plots.size(); a++) {
            if (a < new PlotDAO().getListOfPlots().size()) {
                if (new PlotDAO().updatePlot(plots.get(a))) {
                    updateCount++;
                }
            } else if (new PlotDAO().addPlot(plots.get(a))) {
                addCount++;
            } else {
                System.out.println(plots.get(a).getPlotID() + " not added/updated");
            }
        }
        System.out.println("plot input count updated: " + updateCount);
        System.out.println("plot input count added: " + addCount);
    }

    private void retrievePlotFertilizerDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("plotFertilizers input form mobile upload");
        ArrayList<PlotFertilizer> plotFertilizers = new Gson().fromJson(request.getParameter("plotFertilizers"), new TypeToken<List<PlotFertilizer>>() {
        }.getType());
        for (int a = 0; a < plotFertilizers.size(); a++) {
            if (a < new PlotFertilizerDAO().getListOfPlotFertilizers().size()) {
                if (new PlotFertilizerDAO().addPlotFertilizer(plotFertilizers.get(a))) {
                    updateCount++;
                }
            } else if (new PlotFertilizerDAO().addPlotFertilizer(plotFertilizers.get(a))) {
                addCount++;
            } else {
                System.out.println(plotFertilizers.get(a).getPlotID() + " not added/updated");
            }
        }
        System.out.println("plotFertilizers input count updated: " + updateCount);
        System.out.println("plotFertilizers input count added: " + addCount);
    }

    private void retrieveProblemDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("problem input form mobile upload");
        ArrayList<Problem> problems = new Gson().fromJson(request.getParameter("problems"), new TypeToken<List<Problem>>() {
        }.getType());
        for (int a = 0; a < problems.size(); a++) {
            if (a < new ProblemDAO().getListOfProblems().size()) {
                //do nothing
            } else if (new ProblemDAO().reportProblem(problems.get(a))) {
                addCount++;
            } else {
                System.out.println(problems.get(a).getProblemID()+ " not added/updated");
            }
        }
        System.out.println("problem input count updated: " + updateCount);
        System.out.println("problem input count added: " + addCount);
    }

    private void retrieveProgramBeneficiaryDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("programBeneficiary input form mobile upload");
        ArrayList<ProgramBeneficiary> programBeneficiaries = new Gson().fromJson(request.getParameter("programBeneficiaries"), new TypeToken<List<ProgramBeneficiary>>() {
        }.getType());
        for (int a = 0; a < programBeneficiaries.size(); a++) {
            if (a < new ProgramBeneficiaryDAO().getListOfProgramBeneficiaries().size()) {
                if (new ProgramBeneficiaryDAO().updateProgramBeneficiary(programBeneficiaries.get(a))) {
                    updateCount++;
                }
            } else if (new ProgramBeneficiaryDAO().addProgramBeneficiary(programBeneficiaries.get(a))) {
                addCount++;
            } else {
                System.out.println(programBeneficiaries.get(a).getDeployedID() + " " + programBeneficiaries.get(a).getFarmID()+ " not added/updated");
            }
        }
        System.out.println("programBeneficiary input count updated: " + updateCount);
        System.out.println("programBeneficiary input count added: " + addCount);
    }

    private void retrieveProgramProgressDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("programProgress input form mobile upload");
        ArrayList<ProgramProgress> programProgress = new Gson().fromJson(request.getParameter("programProgress"), new TypeToken<List<ProgramProgress>>() {
        }.getType());
        for (int a = 0; a < programProgress.size(); a++) {
            if (a < new ProgramProgressDAO().getListOfProgramProgresses().size()) {
                if (new ProgramProgressDAO().updateProgramProgress(programProgress.get(a))) {
                    updateCount++;
                }
            } else if (new ProgramProgressDAO().addProgramProgress(programProgress.get(a))) {
                addCount++;
            } else {
                System.out.println(programProgress.get(a).getProcedureNumber()+ " not added/updated");
            }
        }
        System.out.println("programProgress input count updated: " + updateCount);
        System.out.println("programProgress input count added: " + addCount);
    }

    private void retrieveProgramTargetDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("programTargets input form mobile upload");
        ArrayList<ProgramTarget> programTargets = new Gson().fromJson(request.getParameter("programTargets"), new TypeToken<List<ProgramTarget>>() {
        }.getType());
        for (int a = 0; a < programTargets.size(); a++) {
            if (a < new ProgramTargetDAO().getListOfProgramTargets().size()) {
                if (new ProgramTargetDAO().updateProgramTarget(programTargets.get(a))) {
                    updateCount++;
                }
            } else if (new ProgramTargetDAO().addProgramTarget(programTargets.get(a))) {
                addCount++;
            } else {
                System.out.println(programTargets.get(a).getProgramPlanID()+ " not added/updated");
            }
        }
        System.out.println("programTargets input count updated: " + updateCount);
        System.out.println("programTargets input count added: " + addCount);
    }

    private void retrieveProgramRequestDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("programRequests input form mobile upload");
        ArrayList<ProgramRequest> programRequests = new Gson().fromJson(request.getParameter("programRequests"), new TypeToken<List<ProgramRequest>>() {
        }.getType());
        for (int a = 0; a < programRequests.size(); a++) {
            if (a < new ProgramRequestDAO().getListOfProgramRequests().size()) {
                if (new ProgramRequestDAO().updateProgramRequest(programRequests.get(a))) {
                    updateCount++;
                }
            } else if (new ProgramRequestDAO().addProgramRequest(programRequests.get(a))) {
                addCount++;
            } else {
                System.out.println(programRequests.get(a).getRequestID()+ " not added/updated");
            }
        }
        System.out.println("programRequests input count updated: " + updateCount);
        System.out.println("programRequests input count added: " + addCount);
    }
    
    private void retrieveWeeklyPlantingReportDataFromMobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addCount = 0;
        int updateCount = 0;

        System.out.println("weeklyPlantingReport input form mobile upload");
        ArrayList<WeeklyPlantingReport> weeklyPlantingReports = new Gson().fromJson(request.getParameter("weeklyPlantingReports"), new TypeToken<List<WeeklyPlantingReport>>() {
        }.getType());
        for (int a = 0; a < weeklyPlantingReports.size(); a++) {
            if (a < new WeeklyPlantingReportDAO().getListOfWeeklyPlantingReports().size()) {
                //do nothing
            } else if (new WeeklyPlantingReportDAO().addWeeklyPlantingReport(weeklyPlantingReports.get(a))) {
                addCount++;
            } else {
                System.out.println(weeklyPlantingReports.get(a).getPlantingReportID()+ " not added/updated");
            }
        }
        System.out.println("weeklyPlantingReport input count updated: " + updateCount);
        System.out.println("weeklyPlantingReport input count added: " + addCount);
    }

    private void receivedPlantingReportImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filepath = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\images\\\\plantingReport";
        ArrayList<String> imagePath = new ArrayList<>();
        Iterable<Part> parts = request.getParts();
        OutputStream out = null;
        InputStream fileContent = null;
        for (Part part: parts) {
            try {
                out = new FileOutputStream(new File(filepath + File.separator + part.getSubmittedFileName()));
                fileContent = part.getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];
                
                while ((read = fileContent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                imagePath.add("images\\plantingReport" + part.getSubmittedFileName());
            } catch (FileNotFoundException x) {
                System.err.println("file not found");
                System.err.println(x);
            } finally {
                if (out != null) {
                    out.close();
                }
                if (fileContent != null) {
                    fileContent.close();
                }
            }
        }
    }
    
    private void receivedDamageReportImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filepath = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\images\\\\damageReport";
        ArrayList<String> imagePath = new ArrayList<>();
        Iterable<Part> parts = request.getParts();
        OutputStream out = null;
        InputStream fileContent = null;
        for (Part part: parts) {
            try {
                out = new FileOutputStream(new File(filepath + File.separator + part.getSubmittedFileName()));
                fileContent = part.getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];
                
                while ((read = fileContent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                imagePath.add("images\\plantingReport" + part.getSubmittedFileName());
            } catch (FileNotFoundException x) {
                System.err.println("file not found");
                System.err.println(x);
            } finally {
                if (out != null) {
                    out.close();
                }
                if (fileContent != null) {
                    fileContent.close();
                }
            }
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
