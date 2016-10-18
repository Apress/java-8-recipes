
package org.java8recipes.chapter05.recipe5_03;

import java.util.List;

/**
 * Recipe 5-3
 * 
 * Test class for the enum singleton
 * 
 * @author juneau
 */
public class TestSingleton {
    public static void main(String[] args){
        StatisticsSingleton stats = StatisticsSingleton.INSTANCE;
        
        System.out.println("Adding objects to the list using stats object");
                
        List mylist = stats.getTeams();
        mylist.add("One");
        mylist.add("Two");
        
        System.out.println("Reading objects from the list using stats2 object");
        StatisticsSingleton stats2 = StatisticsSingleton.INSTANCE;
        List mylist2 = stats2.getTeams();
        for(Object name : mylist2){
            System.out.println(name.toString());
        }
            
    }
}
