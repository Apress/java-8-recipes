

package org.java8recipes.chapter02.recipe2_06;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Recipe 2-6:  Date-Time API
 * @author Juneau
 */
public class Recipe02_06 {
    
    
    public static void main (String[] args){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate yearStart = LocalDate.parse("01/01/2014", formatter);
        System.out.println("Beginning of year: " + yearStart);
    }
            
}
