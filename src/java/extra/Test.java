/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import dao.FarmDAO;
import dao.PlantingReportDAO;
import dao.ProgramProgressDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import object.PlantingReport;
import object.ProgramProgress;

/**
 *
 * @author RubySenpaii
 */
public class Test {

    public static void main(String[] args) {
//        try {
            //        String evalValues = "1,4,5,4,4,4,5,4,4,4";
//        Calculator calculator = new Calculator();
//        double val = (double) 5 / 2;
//        System.out.println(val);
//        System.out.println(calculator.getEffectivityResult(calculator.getRespondentResult(evalValues)));
//        System.out.println(calculator.getYear());
//        ArrayList<ProgramProgress> progresses = new ProgramProgressDAO().getListOfProgramProgressForDeployedID(5);
//        for (int a = 0; a < progresses.size(); a++) {
//            System.out.println("progress " + progresses.get(a).getRemarks());
//        }
//        File file = new File("C:\\\\Users\\\\RubySenpaii\\\\Desktop\\\\NetBeansProjects\\\\BIGAS System\\\\web\\\\pdf");
//        String fileNames[] = file.list();
//        for (int a = 0; a < fileNames.length; a++) {
//            System.out.println("filenames[]: " + fileNames[a]);
//        }

            DecimalFormat df = new DecimalFormat("#,###.##");
            System.out.println(df.format(2238472834.52525));
//            new JasperJava().createMunicipalWeeklyDamagesReport("Jan", "Jemi", "Paombong");
//        } catch (JRException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
