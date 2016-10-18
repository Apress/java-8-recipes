
package org.java8recipes.chapter13.recipe13_03;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java8recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 13-3:  Querying the database
 * 
 * @author juneau
 */
public class QueryDatabase {

private static CreateConnection createConn;

    public static void main(String[] args) {

        createConn = new CreateConnection();

        queryDatabase();

    }

    public static void queryDatabase() {
        String qry = "select recipe_number, recipe_name, description from recipes";
        try (Connection conn = createConn.getConnection();
                Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String recipe = rs.getString("RECIPE_NUMBER");
                String name = rs.getString("RECIPE_NAME");
                String desc = rs.getString("DESCRIPTION");

                System.out.println(recipe + "\t" + name + "\t" + desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
