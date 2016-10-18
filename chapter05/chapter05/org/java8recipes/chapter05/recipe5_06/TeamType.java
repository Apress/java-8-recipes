
package org.java8recipes.chapter05.recipe5_06;

import java.util.List;

/**
 * Recipe 5-6
 * 
 * Defining an Interface for a Class
 * 
 * @author juneau
 */
public interface TeamType {
    
    void setPlayers(List<Player> players);
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
