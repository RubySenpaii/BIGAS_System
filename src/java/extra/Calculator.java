/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import dao.BarangayDAO;
import dao.DamageIncidentDAO;
import dao.DamageReportDAO;
import dao.FarmDAO;
import dao.PlantingReportDAO;
import dao.PlotDAO;
import dao.ProblemDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import object.Barangay;
import object.DamageIncident;
import object.DamageReport;
import object.Farm;
import object.Notification;
import object.PlantingReport;
import object.Plot;
import object.Problem;
import object.DeployedEvaluation;

/**
 *
 * @author RubySenpaii
 */
public class Calculator {

    public String getLastWeekDate(String date) {
        int year = Integer.parseInt(date.substring(6, 10));
        int month = Integer.parseInt(date.substring(0, 2)) - 1;
        int day = Integer.parseInt(date.substring(3, 5));

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Calendar oldTime = Calendar.getInstance();
        oldTime.set(year, month, day);
        Calendar newTime = Calendar.getInstance();
        newTime.set(year, month, day);
        newTime.add(Calendar.DAY_OF_WEEK, -(newTime.get(Calendar.DAY_OF_WEEK) - Calendar.THURSDAY));

        if (newTime.after(oldTime)) {
            newTime.add(Calendar.DAY_OF_MONTH, -7);
        } else if (newTime.equals(oldTime)) {
            newTime.add(Calendar.DAY_OF_MONTH, -7);
        }

        String printDate = sdf.format(newTime.getTime());
        return printDate;
    }

    public String minusDate(String date) {
        int year = Integer.parseInt(date.substring(6, 10));
        int month = Integer.parseInt(date.substring(0, 2)) - 1;
        int day = Integer.parseInt(date.substring(3, 5));
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Calendar newTime = Calendar.getInstance();
        newTime.set(year, month, day);
        newTime.add(Calendar.DAY_OF_MONTH, -1);
        String printDate = sdf.format(newTime.getTime());
        return printDate;
    }

    public int getYear() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        if ((now.get(Calendar.MONTH) == 2 && now.get(Calendar.DAY_OF_MONTH) <= 15) || (now.get(Calendar.MONTH) < 2)) {
            year -= 1;
        }
        return year;
    }

    public ArrayList<Notification> getPestAndDiseaseNotification(int municipalityID) {
        ArrayList<Notification> notifications = new ArrayList<>();
        ArrayList<Problem> problems = new ProblemDAO().getListOfProblems();
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipalityID);
        for (int a = 0; a < problems.size(); a++) {
            for (int b = 0; b < barangays.size(); b++) {
                double plantableArea = 0, damagedArea = 0, affectedArea = 0;
                ArrayList<Plot> plotsAvailable = new PlotDAO().getListOfPlotsInBarangay(barangays.get(b).getBarangayID());
                for (int c = 0; c < plotsAvailable.size(); c++) {
                    plantableArea += plotsAvailable.get(c).getPlotSize();
                }
                ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getBarangayProblemListWithApprovedDamagesInMunicipality(municipalityID);
                for (int d = 0; d < incidents.size(); d++) {
                    if (incidents.get(d).getProblemReported() == problems.get(a).getProblemID() && incidents.get(d).getBarangayName().equals(barangays.get(b).getBarangayName())) {
                        damagedArea += incidents.get(d).getTotalAreaDamaged();
                        affectedArea += incidents.get(d).getTotalAreaAffected();
                    }
                }

                if (problems.get(a).getType().equals("Pest") || problems.get(a).getType().equals("Disease")) {
                    if (damagedArea / plantableArea >= 0.03) {
                        Notification notification = new Notification();
                        notification.setType("ALERT");
                        notification.setMessage(problems.get(a).getProblemName() + " has damaged " + new Formatter().doubleToString(damagedArea / plantableArea * 100) + ". Damaged area is " + damagedArea + ".");
                        notification.setEmployeeID(-1);
                        notification.setBarangayID(barangays.get(b).getBarangayID());
                        notifications.add(notification);
                    }

                    if (affectedArea / plantableArea >= 0.045) {
                        Notification notification = new Notification();
                        notification.setType("ALERT");
                        notification.setMessage(problems.get(a).getProblemName() + " has affected " + new Formatter().doubleToString(affectedArea / plantableArea * 100) + "%. Affected area is " + affectedArea + ".");
                        notification.setEmployeeID(-1);
                        notification.setBarangayID(barangays.get(b).getBarangayID());
                        notifications.add(notification);
                    }
                } else if (damagedArea / plantableArea >= 0.4) {
                    Notification notification = new Notification();
                    notification.setType("ALERT");
                    notification.setMessage(problems.get(a).getProblemName() + " has damaged " + new Formatter().doubleToString(damagedArea / plantableArea * 100) + "%. Affected area is " + damagedArea + ".");
                    notification.setEmployeeID(-1);
                    notification.setBarangayID(barangays.get(b).getBarangayID());
                    notifications.add(notification);
                }
            }
        }
        return notifications;
    }

    public ArrayList<Notification> getPestAndDiseaseNotification2(int municipalityID) {
        ArrayList<Notification> notifications = new ArrayList<>();
        ArrayList<Problem> problems = new ProblemDAO().getListOfProblems();
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipalityID);
        ArrayList<DamageIncident> incidents = new DamageIncidentDAO().getListOfPlantingReportsByProblemFarmBarangay(municipalityID);
        if (incidents.size() > 0) {
            for (int a = 0; a < problems.size(); a++) {
                for (int b = 0; b < barangays.size(); b++) {
                    double plantableArea = 0, majorDamaged = 0, minorDamaged = 0;
                    ArrayList<Farm> includedFarms = new ArrayList<>();

                    ArrayList<Farm> farms = new FarmDAO().getListOfFarmsInBarangay(barangays.get(b).getBarangayName());
                    for (int c = 0; c < farms.size(); c++) {
                        ArrayList<Plot> plots = new PlotDAO().getListOfPlotsInFarm(farms.get(c));
                        for (int d = 0; d < plots.size(); d++) {
                            plantableArea += plots.get(d).getPlotSize();
                        }

                        for (int d = 0; d < incidents.size(); d++) {
                            if (incidents.get(d).getBarangayName().equals(barangays.get(b).getBarangayName()) && incidents.get(d).getProblemName().equals(problems.get(a).getProblemName()) && farms.get(c).getFarmName().equals(incidents.get(d).getFarmName())) {
                                majorDamaged += incidents.get(d).getTotalAreaDamaged();
                                minorDamaged += incidents.get(d).getTotalAreaAffected();
                                
                                incidents.remove(d);

                                farms.get(c).setAreaDamaged(majorDamaged);
                                farms.get(c).setAreaAffected(minorDamaged);
                                includedFarms.add(farms.get(c));
                                break;
                            }
                        }
                    }
                    if (!includedFarms.isEmpty()) {
                        if (problems.get(a).getType().equals("Calamity")) {
                            if (majorDamaged / plantableArea >= 0.4) {
                                Notification notification = new Notification();
                                notification.setType("ALERT");
                                notification.setMessage(problems.get(a).getProblemName() + " has damaged " + new Formatter().doubleToString(majorDamaged / plantableArea * 100) + ". Damaged area is " + majorDamaged + ".");
                                notification.setEmployeeID(-1);
                                notification.setBarangayID(barangays.get(b).getBarangayID());
                                notifications.add(notification);
                            }
                        } else if (minorDamaged / plantableArea >= 0.045) {
                            Notification notification = new Notification();
                            notification.setType("ALERT");
                            notification.setMessage(problems.get(a).getProblemName() + " has affected " + new Formatter().doubleToString(minorDamaged / plantableArea * 100) + "%. Affected area is " + minorDamaged + ".");
                            notification.setEmployeeID(-1);
                            notification.setBarangayID(barangays.get(b).getBarangayID());
                            notifications.add(notification);
                        } else if (majorDamaged / plantableArea >= 0.03) {
                            Notification notification = new Notification();
                            notification.setType("ALERT");
                            notification.setMessage(problems.get(a).getProblemName() + " has damaged " + new Formatter().doubleToString(majorDamaged / plantableArea * 100) + ". Damaged area is " + majorDamaged + ".");
                            notification.setEmployeeID(-1);
                            notification.setBarangayID(barangays.get(b).getBarangayID());
                            notifications.add(notification);
                        }

                    }
                }
            }
        }
        return notifications;
    }

    public String getSeason() {
        Calendar cal = Calendar.getInstance();
        if (cal.getTime().getMonth() < 2) {
            return "Wet";
        } else if (cal.getTime().getMonth() == 2 && cal.getTime().getDate() < 16) {
            return "Wet";
        } else if (cal.getTime().getMonth() == 2 && cal.getTime().getDate() >= 16) {
            return "Dry";
        } else if (cal.getTime().getMonth() > 2 && cal.getTime().getMonth() < 8) {
            return "Dry";
        } else if (cal.getTime().getMonth() == 8 && cal.getTime().getDate() < 16) {
            return "Dry";
        } else {
            return "Wet";
        }
    }

    public ArrayList<Notification> getUnderproductionNotification(int municipalityID) {
        ArrayList<Notification> notifications = new ArrayList<>();
        int currentYear = getYear();
        int lastYear = currentYear - 1;

        Formatter format = new Formatter();
        String season = getSeason();
        ArrayList<Barangay> barangays = new BarangayDAO().getListOfBarangaysInMunicipality(municipalityID);
        for (int a = 0; a < barangays.size(); a++) {
            double currentHarvest = 0, lastHarvest = 0;
            ArrayList<Farm> farms = new FarmDAO().getListOfFarmsInBarangay(barangays.get(a).getBarangayName());
            for (int b = 0; b < farms.size(); b++) {
                ArrayList<PlantingReport> currentPlantingReports = new PlantingReportDAO().getListOfPlantingReportsInFarmAndSeasonAndYear(farms.get(b).getFarmID(), season, currentYear);
                for (int c = 0; c < currentPlantingReports.size(); c++) {
                    currentHarvest += currentPlantingReports.get(c).getAmountHarvested();
                }
                ArrayList<PlantingReport> lastPlantingReports = new PlantingReportDAO().getListOfPlantingReportsInFarmAndSeasonAndYear(farms.get(b).getFarmID(), season, lastYear);
                for (int c = 0; c < lastPlantingReports.size(); c++) {
                    lastHarvest += lastPlantingReports.get(c).getAmountHarvested();
                }
            }

            if (lastHarvest / currentHarvest < 0.9) {
                double percentage = lastHarvest / currentHarvest * 100;
                Notification notif = new Notification();
                notif.setType("ALERT");
                notif.setMessage("Underproduction in " + barangays.get(a).getBarangayName() + " by " + format.doubleToString(percentage) + ". Last season harvest: " + lastHarvest + " MT. This season harvest: " + currentHarvest + " MT.");
                notif.setEmployeeID(-1);
                notif.setBarangayID(barangays.get(a).getBarangayID());
                notifications.add(notif);
            }
        }
        return notifications;
    }

    public double getRespondentResult(String evaluation) {
        String[] evaluationValues = evaluation.split(",");
        double result = 0;
        int count = 0;
        for (int a = 0; a < evaluationValues.length; a++) {
            if (Double.parseDouble(evaluationValues[a]) > 0) {
                result += Double.parseDouble(evaluationValues[a]);
                count++;
            }
        }
        if (result != 0) {
            return result / count;
        } else {
            return 0;
        }
    }

    public double getDeployedQuestionnaireResult(ArrayList<DeployedEvaluation> evaluations, int questionNo) {
        double result = 0;
        int count = 0;
        for (int a = 0; a < evaluations.size(); a++) {
            String[] evaluationValues = evaluations.get(a).getEvaluationValues().split(",");
            if (Double.parseDouble(evaluationValues[questionNo]) > 0) {
                result += Double.parseDouble(evaluationValues[questionNo]);
                count++;
            }
        }
        if (result != 0) {
            return result / count;
        } else {
            return 0;
        }
    }

    public String getEffectivityResult(double value) {
        if (value > 1 && value <= 1.8) {
            return "Poor";
        } else if (value > 1.8 && value <= 2.60) {
            return "Needs Improvement";
        } else if (value > 2.6 && value <= 3.4) {
            return "Satisfactory";
        } else if (value > 3.4 && value <= 4.2) {
            return "Very Satisfactory";
        } else if (value > 4.2) {
            return "Outstanding";
        } else {
            return "No Rating Available";
        }
    }
}
