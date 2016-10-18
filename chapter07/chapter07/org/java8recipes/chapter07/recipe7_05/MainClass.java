package org.java8recipes.chapter07.recipe7_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recipe 7-5: Safely Enabling Types or Methods
 *             to Operate on Objects of Various Types
 * 
 * @author Juneau
 */
public class MainClass {

    static List<Player> team;

    private static void loadTeam() {
        System.out.println("Loading team...");

        // Use of the diamond operator
        team = new ArrayList<>();
        Player player1 = new Player();
        player1.setFirstName("Josh");
        player1.setLastName("Juneau");
        player1.setGoals(5);
        Player player2 = new Player();
        player2.setFirstName("Duke");
        player2.setLastName("Java");
        player2.setGoals(15);
        Player player3 = new Player();
        player3.setFirstName("Jonathan");
        player3.setLastName("Gennick");
        player3.setGoals(1);
        Player player4 = new Player();
        player4.setFirstName("Bob");
        player4.setLastName("Smith");
        player4.setGoals(18);
        Player player5 = new Player();
        player5.setFirstName("Steve");
        player5.setLastName("Adams");
        player5.setGoals(7);

        team.add(player1);
        team.add(player2);
        team.add(player3);
        team.add(player4);
        team.add(player5);

    }

    public static void main(String[] args) {
        loadTeam();

        // Create a list without specifying a type
        List objectList = new ArrayList();
        Object obj1 = "none";
        objectList.add(obj1);

        // Create a List that can be of type that is any superclass of Player
        List<? super Player> myTeam = objectList;
        for (Object p : myTeam) {
            System.out.println("Printing the objects...");
            System.out.println(p.toString());
        }

        Map<String, String> strMap = new HashMap<>();
        strMap.put("first", "Josh");
        strMap.put("last", "Juneau");
        System.out.println(strMap.values());
    }
}
