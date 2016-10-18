
package org.java8recipes.chapter05.recipe5_08;

import java.util.List;
import org.java8recipes.chapter05.recipe5_04.Player;

/**
 * Recipe 5-8
 * 
 * @author juneau
 */
public interface TeamType {
    
    void setPlayers(List<Player> players);
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
