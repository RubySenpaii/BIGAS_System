/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import db.DBConnectionFactory;
import db.DBConnectionFactoryImp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author RubySenpaii
 */
public class JasperJava {

    public void createBarangayProductionReport() throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\barangayProductionStackedType.jasper";
        File file = new File(source);
        String filepath = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\pdfoutputs";
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("title", "BarangayProductionReportAsOf" + dateNow);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
//        JasperReport jasperPlantingReport = JasperCompileManager.compileReport(source);
        try (Connection conn = myFactory.getConnection()) {
//            JasperReport jasperPlantingReport = JasperCompileManager.compileReport(source);
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + "barangayProductionReportAsOf" + dateNow + ".pdf");
//        JRPdfExporter exporter = new JRPdfExporter();
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(new File(filepath + File.separator + "plantingReports.pdf"))));
//        SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
//        config.setMetadataTitle("PlantingReport");
//        config.setMetadataAuthor("RubySenpaii");
//        exporter.setConfiguration(config);
//        exporter.exportReport();
    }
    
    public void createGrowthStageReport() throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\growthStage.jasper";
        File file = new File(source);
        String filepath = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\pdfoutputs";
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("title", "GrowthStageReportAsOf" + dateNow);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
//        JasperReport jasperPlantingReport = JasperCompileManager.compileReport(source);
        try (Connection conn = myFactory.getConnection()) {
//            JasperReport jasperPlantingReport = JasperCompileManager.compileReport(source);
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + "growthStageReportAsOf" + dateNow + ".pdf");
    }
    
    public void createPestDiseaseReport() throws JRException, FileNotFoundException, SQLException {
        String source = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\reportTemplate\\\\pestDiseaseDamages.jasper";
        File file = new File(source);
        String filepath = "C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\pdfoutputs";
        String dateNow = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        Map parameters = new HashMap();
        parameters.put("title", "PestDiseaseReportAsOf" + dateNow);
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        JasperPrint jasperPrint;
//        JasperReport jasperPlantingReport = JasperCompileManager.compileReport(source);
        try (Connection conn = myFactory.getConnection()) {
//            JasperReport jasperPlantingReport = JasperCompileManager.compileReport(source);
            JasperReport jasperPlantingReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperPlantingReport, parameters, conn);
        }
        JasperExportManager.exportReportToPdfFile(jasperPrint, filepath + File.separator + "pestDiseaseReportAsOf" + dateNow + ".pdf");
    }
}
