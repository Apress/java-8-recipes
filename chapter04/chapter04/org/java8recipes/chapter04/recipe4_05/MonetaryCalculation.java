package org.java8recipes.chapter04.recipe4_05;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * Recipe 4-5
 * 
 * Calculating Monetary Values
 * 
 * @author juneau
 */
public class MonetaryCalculation {
   
    public static void main(String[] args) {
        // Run the currency calculation examples
        calculateDollars();
    }

    /**
     * Formats a double value into currency format and then returns it as
     * a String
     * @param value
     * @return 
     */
    public static String formatDollars(double value) {
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return dollarFormat.format(value);
    }

    public static void calculateDollars() {
        BigDecimal currencyOne = new BigDecimal("25.65");
        BigDecimal currencyTwo = new BigDecimal("187.32");
        BigDecimal currencyThree = new BigDecimal("4.86");
        BigDecimal result = null;
        String printFormat = null;

        // Add all three values
        result = currencyOne.add(currencyTwo).add(currencyThree);
        // Convert to double and send to formatDollars(), returning a String
        printFormat = formatDollars(result.doubleValue());
        System.out.println(printFormat);

        // Subtract the first currency value from the second
        result = currencyTwo.subtract(currencyOne);
        printFormat = formatDollars(result.doubleValue());
        System.out.println(printFormat);

    }
}
