
package org.java8recipes.chapter03.recipe3_05;

/**
 * Recipe 3-5
 * 
 * Concatenating Strings
 * 
 * @author juneau
 */
public class StringExamples {
    
    
    
    public static void main(String[] args){

       concatExample();
       concatOperatorExample();
       stringBufferExample();
       
    }
   
    
    public static void concatExample(){
        String one = "Hello";
        String two = "Java8";
        String result = one.concat(" ".concat(two));
        
        System.out.println(result);
    }
    
    public static void concatOperatorExample(){
        String one = "Hello";
        String two = "Java8";
        String result = one + " " + two;
        
        System.out.println(result);
    }
    
    public static void stringBufferExample(){
        String one = "Hello";
        String two = "Java8";
        StringBuffer buffer = new StringBuffer();
        buffer.append(one).append(" ").append(two);

        String result = buffer.toString();

        System.out.println(result);
    }
}