package org.java8recipes.chapter17.recipe17_05;

import java.sql.*;
import java.util.*;

/**
 * Persistence layer.
 * Uses derby.jar and derbytools.jar on the classpath. 
 * @author cdea
 * 
 */
public class DBUtils {
    // connection properties
    final static Properties props = new Properties(); 
    static {
        props.put("user", "scott");
        props.put("password", "tiger");
    }
    private static String framework = "embedded";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    public static void setupDb() {
        loadDriver();
        Connection conn = null;
        ArrayList statements = new ArrayList(); 
        Statement s = null;
        ResultSet rs = null;
        try {
            // database name
            String dbName = "demoDB"; 

            conn = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);

            System.out.println("Creating database " + dbName);
            boolean createTable = false;
            s = conn.createStatement();
            try {
                s.executeQuery("SELECT count(*) FROM rssFeed");
            } catch (Exception e) {
                createTable = true;
            }
            
            if (createTable) {

                // handle transaction
                conn.setAutoCommit(false);

                s = conn.createStatement();
                statements.add(s);

                // Create a contact table...
                s.execute("create table rssFeed(id int, title varchar(255), url varchar(600))");
                System.out.println("Created table rssFeed ");

                conn.commit();
            }
            
            shutdown();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            
            close(rs);

            // Statements and PreparedStatements
            int i = 0;
            while (!statements.isEmpty()) {
                // PreparedStatement extend Statement
                Statement st = (Statement) statements.remove(i);
                close(st);
            }

            close(conn);

        }

    }
    
    
    public static List<RssFeed> loadFeeds() {
        loadDriver();

        Connection conn = null;
        ResultSet rs = null;
        List<RssFeed> feeds = new ArrayList<>();
        try {
            // database name
            String dbName = "demoDB"; 

            conn = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);

            rs = conn.createStatement().executeQuery("select id, title, url from rssFeed");
            while (rs.next()) {
                String title = rs.getString("title");
                String url = rs.getString("url");
                RssFeed rssFeed = new RssFeed(title, url);
                rssFeed.id = rssFeed.link.hashCode();
                feeds.add(rssFeed);
            } 
            shutdown();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            
            close(rs);
            close(conn);

        }
        return feeds;
    }

    private static void shutdown() {
        // standard checking code when shutting down database.
        // code from http://db.apache.org/derby/
        if (framework.equals("embedded")) {
            try {
                // the shutdown=true attribute shuts down Derby
                DriverManager.getConnection("jdbc:derby:;shutdown=true");

            } catch (SQLException se) {
                if (((se.getErrorCode() == 50000)
                        && ("XJ015".equals(se.getSQLState())))) {
                    System.out.println("Derby shut down normally");
                } else {
                    System.err.println("Derby did not shut down normally");
                    se.printStackTrace();
                }
            }
        }
    }
    
    public static int saveRssFeed(RssFeed rssFeed) {
        int pk = rssFeed.link.hashCode();

        loadDriver();

        Connection conn = null;
        ArrayList statements = new ArrayList(); 
        PreparedStatement psInsert = null;
        Statement s = null;
        ResultSet rs = null;
        try {


            // database name
            String dbName = "demoDB"; 

            conn = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);

            rs = conn.createStatement().executeQuery("select count(id) from rssFeed where id = " + rssFeed.link.hashCode());
            
            rs.next();
            int count = rs.getInt(1);
            
            if (count == 0) {

                // handle transaction
                conn.setAutoCommit(false);

                s = conn.createStatement();
                statements.add(s);

                psInsert = conn.prepareStatement("insert into rssFeed values (?, ?, ?)");
                statements.add(psInsert);
                psInsert.setInt(1, pk);
                String escapeTitle = rssFeed.channelTitle.replaceAll("\'", "''");
                psInsert.setString(2, escapeTitle);
                psInsert.setString(3, rssFeed.link);
                psInsert.executeUpdate();
                conn.commit();
                System.out.println("Inserted " + rssFeed.channelTitle + " " + rssFeed.link);
                System.out.println("Committed the transaction");
            }
            shutdown();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            // release all open resources to avoid unnecessary memory usage

            // ResultSet
            close(rs);

            // Statements and PreparedStatements
            int i = 0;
            while (!statements.isEmpty()) {
                // PreparedStatement extend Statement
                Statement st = (Statement) statements.remove(i);
                close(st);
            }

            //Connection
            close(conn);

        }



        return pk;
    }

    private static void close(AutoCloseable closable) {
        try {
            if (closable != null) {
                closable.close();
                closable = null;
            }
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    private static void loadDriver() {
        
            try {
                Class.forName(driver).newInstance();
                System.out.println("Loaded driver");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
}
