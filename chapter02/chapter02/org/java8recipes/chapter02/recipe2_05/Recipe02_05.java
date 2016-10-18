

package org.java8recipes.chapter02.recipe2_05;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Recipe 2-5:  Obtaining Local Date and Time
 * @author Juneau
 */
public class Recipe02_05 {
    static LocalDate localDate = LocalDate.now();
    static LocalTime localTime = LocalTime.now();
    
    public static void main(String[] args){
        System.out.println("The local date is: " + localDate);
        System.out.println("The local time is: " + localTime);
    }
}
