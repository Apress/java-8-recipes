
package org.java8recipes.chapter13.recipe13_01;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Recipe 13-1:  Example for creating a datasource
 * 
 * @author juneau
 */
public class DataSourceCreator {

    public void createDataSource() {
        org.java8recipes.chapter13.recipe13_01.FakeDataSourceDriver ds =
                new org.java8recipes.chapter13.recipe13_01.FakeDataSourceDriver();
        ds.setServerName("my-server");
        ds.setDatabaseName("JavaRecipes");
        ds.setDescription("Database connection for Java 7 Recipes");

    }

    public void registerDS() {
        try {
            Context ctx = new InitialContext();
            DataSource ds =
                    (DataSource) ctx.lookup("jdbc/java7recipesDB");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
}
