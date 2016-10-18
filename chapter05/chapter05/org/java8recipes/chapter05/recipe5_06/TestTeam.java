

package org.java8recipes.chapter05.recipe5_06;

/**
 * Recipe 5-6
 * 
 * Defining an Interface for a Class
 * 
 * @author Juneau
 */
public class TestTeam {
    
    public static void main(String[] args){
        TeamType team1 = new Team();
        // Since an object of TeamType has been created, we are only able
        // to call upon the methods that were made available via the
        // TeamType interface
        team1.getFullName();
    }
}
