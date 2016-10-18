
package org.java8recipes.chapter05.recipe5_03;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe 5-3 Solution #2
 * 
 * Creating a Class That Can Only Have One Instance
 * 
 * @author juneau
 */
public enum StatisticsSingleton {
    INSTANCE;
    
    private final List teams = new ArrayList();
    
    /**
     * @return the teams
     */
    public List getTeams() {
        return teams;
    }

    /**
     * @param teams the teams to set
     */
    public void setTeams(List teams) {
        teams = teams;
    }
    
}
