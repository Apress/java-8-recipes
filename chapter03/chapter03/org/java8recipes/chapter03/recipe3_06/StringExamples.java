
package org.java8recipes.chapter03.recipe3_06;

/**
 * Recipe 3-6
 * 
 * Converting Strings to Numeric Values
 * 
 * @author juneau
 */
public class StringExamples {
    
    
    
    public static void main(String[] args){

       stringsToNumbers();
       stringsToNumbersParseInt();

    }
   
    /**
     * Solution #1 using Integer.valueOf()
     */
    public static void stringsToNumbers(){
        String one = "1";
        String two = "2";
        
        int result = Integer.valueOf(one) + Integer.valueOf(two);
        
        System.out.println(result);
        
    }
    
    /**
     * Solution #2 using Integer.parseInt()
     */
    public static void stringsToNumbersParseInt(){
        String one = "1";
        String two = "2";

        int result = Integer.parseInt(one) + Integer.parseInt(two);

        System.out.println(result);
    }
    
   
}
