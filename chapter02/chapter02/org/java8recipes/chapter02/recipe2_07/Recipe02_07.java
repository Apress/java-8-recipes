

package org.java8recipes.chapter02.recipe2_07;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Recipe 2-7:  Date-Time API
 * @author Juneau
 */
public class Recipe02_07 {
    
    public static void main(String[] args){
        LocalDate myDate = LocalDate.now();
        LocalTime myTime = LocalTime.now();

        LocalDate datePlusDays = myDate.plusDays(15);
        System.out.println("Today Plus 15 Days: " + datePlusDays);
        LocalDate datePlusWeeks = myDate.plusWeeks(8);
        System.out.println("Today Plus 8 weeks: " + datePlusWeeks);

        LocalTime timePlusHours = myTime.plusHours(5);
        LocalTime timeMinusMin = myTime.minusMinutes(30);

        System.out.println("Time Plus 5 Hours: " + timePlusHours);
        System.out.println("Time Minus 30 Minutes: " + timeMinusMin);
        
    }
}
