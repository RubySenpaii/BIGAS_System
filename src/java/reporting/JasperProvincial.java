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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import object.Municipality;
import object.ProgramPlan;

/**
 *
 * @author RubySenpaii
 */
public class JasperProvincial {
    private String filepath = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\pdfoutputs\\\\provincial";
    
    public void createProvincialWeeklyPlantingReport(String preparedBy, String approvedBy) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\provincial\\\\weeklyPlantingReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = "BulacanWeeklyPlantingReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }
    
    public void createProvincialWeeklyDamagesReport(String preparedBy, String approvedBy) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\provincial\\\\weeklyDamageReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", "01-10-2017");
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = "BulacanWeeklyDamageReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }
    
    public void createProvincialWeeklyHarvestReport(String preparedBy, String approvedBy) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\provincial\\\\weeklyHarvestingReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = "BulacanWeeklyHarvestReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }
    
    public void createProvincialWeeklyCropGrowthReport(String preparedBy, String approvedBy) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\provincial\\\\weeklyGrowthStageReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = "BulacanWeeklyCropGrowthReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }
    
    public void createProvincialProgramReport(String preparedBy, String approvedBy, ProgramPlan programPlan) throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\provincial\\\\programReport.jasper";
        File file = new File(source);
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("report_date", dateNow);
        parameters.put("preparedBy", preparedBy);
        parameters.put("approvedBy", approvedBy);
        parameters.put("programPlanID", programPlan.getProgramPlanID());
        parameters.put("programPlan", programPlan.getProgramName());
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
        try (Connection conn = myFactory.getConnection()) {
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        String filename = "Bulacan" + programPlan.getProgramName() + "ProgramReport" + dateNow;
        filename = filename.replaceAll(" ", "");
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + filename + ".pdf");
    }
}
