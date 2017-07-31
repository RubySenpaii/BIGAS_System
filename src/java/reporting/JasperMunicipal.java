/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporting;

import db.DBConnectionFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import object.Municipality;
import object.ProgramPlan;

/**
 *
 * @author RubySenpaii
 */
public class JasperMunicipal {

    private final String filepath = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\pdfoutputs\\\\municipal";

    public void createMunicipalWeeklyPlantingReport(String preparedBy, String approvedBy, String municipal, String dateRange) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Documents\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\municipal\\\\weeklyPlantingReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        parameters.put("municipality_name", municipal);
        parameters.put("dateRange", dateRange);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = municipal + "WeeklyPlantingReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }

    public void createMunicipalWeeklyDamagesReport(String preparedBy, String approvedBy, String municipal, String dateRange) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Documents\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\municipal\\\\weeklyDamageReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        parameters.put("municipalityName", municipal);
        parameters.put("dateRange", dateRange);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = municipal + "WeeklyDamageReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }

    public void createMunicipalWeeklyHarvestReport(String preparedBy, String approvedBy, String municipal, String dateRange) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Documents\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\municipal\\\\weeklyHarvestingReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        parameters.put("municipality_name", municipal);
        parameters.put("dateRange", dateRange);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = municipal + "WeeklyHarvestReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }

    public void createMunicipalWeeklyCropGrowthReport(String preparedBy, String approvedBy, String municipal, String dateRange) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Documents\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\municipal\\\\weeklyGrowthStageReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        parameters.put("municipal", municipal);
        parameters.put("dateRange", dateRange);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = municipal + "WeeklyCropGrowthReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }

    public void createMunicipalProgramReport(String preparedBy, String approvedBy, Municipality municipal, ProgramPlan programPlan) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Documents\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\municipal\\\\programReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        parameters.put("programPlanID", programPlan.getProgramPlanID());
        parameters.put("programPlan", programPlan.getProgramName());
        parameters.put("municipalityID", municipal.getMunicipalityID());
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = municipal.getMunicipalityName() + programPlan.getProgramName() + "ProgramReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }

    public void createMunicipalProgramReportv2(String preparedBy, String approvedBy, Municipality municipal, ArrayList<ProgramPlan> programPlans) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Documents\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\municipal\\\\programReportv2.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        parameters.put("programPlanID", programPlans);
        JasperPrint jasperPrint;

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(programPlans, false);
        JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
        jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, dataSource);
        String filename = municipal.getMunicipalityName() + "ProgramReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }
}
