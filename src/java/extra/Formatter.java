/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author RubySenpaii
 */
public class Formatter {

    public String doubleToString(double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (value > 0 && value < 1) {
            return "0" + df.format(value);
        } else if (value != 0) {
            return df.format(value);
        } else {
            return "0.00";
        }
    }

    public String valuesToDate(int month, int day, int year) {
        String monthVal = String.valueOf(month), dayVal = String.valueOf(day), yearVal = String.valueOf(year);
        if (month < 10) {
            monthVal = "0" + month;
        }
        if (day < 10) {
            dayVal = "0" + day;
        }
        return monthVal + "-" + dayVal + "-" + yearVal;
    }
    
    public Date convertToDate(String date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Integer.parseInt(date.substring(0, 2)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(3, 5)));
        cal.set(Calendar.YEAR, Integer.parseInt(date.substring(6)));
        return cal.getTime();
    }
}
