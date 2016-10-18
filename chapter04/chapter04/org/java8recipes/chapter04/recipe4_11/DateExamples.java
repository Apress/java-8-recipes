
package org.java8recipes.chapter04.recipe4_11;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

/**
 * Recipe 4-11
 * 
 * Obtaining and Using the Date and Time Together
 * 
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args){
        obtainDatesWithTime();
    }
    
    public static void obtainDatesWithTime(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("Local Date and Time: " + ldt);
        
        // Obtain the LocalDateTime object of the date 11/11/2000 at 12:00
        LocalDateTime ldt2 = LocalDateTime.of(2000, Month.NOVEMBER, 11, 12, 00);
        System.out.println("Specified Date and Time: " + ldt2);
        
        // Obtain the month from LocalDateTime object
        Month month = ldt.getMonth();
        int monthValue = ldt.getMonthValue();
        System.out.println("Month: " + month);
        System.out.println("Month Value: " + monthValue);
        
        // Obtain day of Month, Week, and Year
        int day = ldt.getDayOfMonth();
        DayOfWeek dayWeek = ldt.getDayOfWeek();
        int dayOfYr = ldt.getDayOfYear();
        System.out.println("Day: " + day);
        System.out.println("Day Of Week: " + dayWeek);
        System.out.println("Day of Year: " + dayOfYr);
        
        // Obtain year
        int year = ldt.getYear();
        System.out.println("Date: " + monthValue + "/" + day + "/" + year);
        
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        int second = ldt.getSecond();
        System.out.println("Current Time: " + hour + ":" + minute + ":" + second);
        
        // Calculation of Months, etc.
        LocalDateTime currMinusMonths = ldt.minusMonths(12);
        LocalDateTime currMinusHours = ldt.minusHours(10);
        LocalDateTime currPlusDays = ldt.plusDays(30);
        System.out.println("Current Date and Time Minus 12 Months: " + currMinusMonths);
        System.out.println("Current Date and Time MInus 10 Hours: " + currMinusHours);
        System.out.println("Current Date and Time Plus 30 Days:" + currPlusDays);
        
        
        
        Date date = new Date();

        System.out.println("Using java.util.Date(): " + date);
        System.out.println("Getting time from java.util.Date(): " + date.getTime());

    }
    public static void compareDates() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Set the date to 01/01/2010:12:00
        cal2.set(2010,0,1,12,0);

        System.out.println(formatDate(cal1) + " before " + formatDate(cal2) + "? " + cal1.before(cal2));
        System.out.println(cal2.compareTo(cal1));

    }
    
    /**
     * Date formatting method that accepts a Calendar object and returns
     * a formatted String.
     * 
     * @param cal
     * @return 
     */
    public static String formatDate(Calendar cal) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("MMM dd yyyy hh:mm:ss aaa");

        return simpleFormatter.format(cal.getTime());

    }
}
