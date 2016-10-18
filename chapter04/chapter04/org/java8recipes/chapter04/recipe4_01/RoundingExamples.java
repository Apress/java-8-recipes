
package org.java8recipes.chapter04.recipe4_01;

/**
 * Recipe 4-1
 * 
 * Rounding Float and Double Values to Integers
 * @author juneau
 */
public class RoundingExamples {
    
    public static void main(String[] args){
        // Round a float value to an Integer
        System.out.println(roundFloatToInt(new Float("8.837")));
        System.out.println(roundDoubleToLong(new Double("9.9")));
    }
    
    /**
     * Rounds a floating-point number to an Integer and returns the result
     * @param myFloat
     * @return 
     */
    public static int roundFloatToInt(float myFloat){
        return Math.round(myFloat);
    }

    /**
     * Rounds a Double value to an Integer and returns the result
     * @param myDouble
     * @return 
     */
    public static long roundDoubleToLong(double myDouble){
        return Math.round(myDouble);
    }
    
}
