
package org.java8recipes.chapter04.recipe4_14;

import java.time.LocalDate;
import java.time.Month;

/**
 * Recipe 4-14
 * 
 * Compare Two Dates
 * 
 * @author Juneau
 */
public class DateComparisons {

    public static void compareDates(LocalDate ldt1,
            LocalDate ldt2) {
        int comparison = ldt1.compareTo(ldt2);
        if (comparison > 0) {
            System.out.println(ldt1 + " is larger than " + ldt2);
        } else if (comparison < 0) {
            System.out.println(ldt1 + " is smaller than " + ldt2);
        } else {
            System.out.println(ldt1 + " is equal to " + ldt2);
        }

    }

    

    public static void main(String[] args) {
        LocalDate anniversary = LocalDate.of(2000, Month.NOVEMBER, 11);
        LocalDate today = LocalDate.now();
        compareDates(anniversary, today);
  
    }
}
