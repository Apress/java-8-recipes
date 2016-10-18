
package org.java8recipes.chapter05.recipe5_13;

/**
 * Recipe 5-13
 * 
 * Defining a Template for Classes to Extend
 * 
 * @author juneau
 */
public class TeamSchedule extends Schedule {
    
    public TeamSchedule(String teamName){
        super(teamName);
    }
    
    

    @Override
    void calculateDaysPlayed(int month) {
        
        // Perform implementation here
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
