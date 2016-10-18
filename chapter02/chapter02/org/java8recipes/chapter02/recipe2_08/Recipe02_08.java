

package org.java8recipes.chapter02.recipe2_08;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe 2-8:  Iterating over a Collection
 * @author Juneau
 */
public class Recipe02_08 {
    public static void main(String[] args){
        List<String> myList = new ArrayList();

        // Populate the list
        for(int x = 0; x <= 10; x++){
            myList.add("Test " + x);
        }

        // Print each element within the list
        myList.stream().forEach((value)->{
            System.out.println(value);
        });
    }
}
