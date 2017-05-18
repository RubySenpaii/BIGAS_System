/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import dao.FarmDAO;
import dao.PlantingReportDAO;
import dao.ProgramProgressDAO;
import java.io.FileNotFoundException;
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
//        String evalValues = "1,4,5,4,4,4,5,4,4,4";
//        Calculator calculator = new Calculator();
//        double val = (double) 5 / 2;
//        System.out.println(val);
//        System.out.println(calculator.getEffectivityResult(calculator.getRespondentResult(evalValues)));
//        System.out.println(calculator.getYear());
        ArrayList<ProgramProgress> progresses = new ProgramProgressDAO().getListOfProgramProgressForDeployedID(5);
        for (int a = 0; a < progresses.size(); a++) {
            System.out.println("progress " + progresses.get(a).getRemarks());
        }
    }
}
