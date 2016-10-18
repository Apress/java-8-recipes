
package org.java8recipes.chapter05.recipe5_05;

import java.util.List;

/**
 * Recipe 5-5
 * 
 * Creating Reusable Objects
 * 
 * @author juneau
 */
public interface TeamType {
    
    void setPlayers(List<Player> players);
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
