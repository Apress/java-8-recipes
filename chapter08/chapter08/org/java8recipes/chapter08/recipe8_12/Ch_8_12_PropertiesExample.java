package org.java8recipes.chapter08.recipe8_12;

import java.io.*;
import java.util.Properties;

/**
 * User: freddy
 * Reading and setting file Property
 */

public class Ch_8_12_PropertiesExample {

    private void start() {
        //create property file (at least)
        File file = new File("properties.conf");
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties.conf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean shouldWakeUp = false;
        int startCounter  = 100;
        String shouldWakeUpProperty = properties.getProperty("ShouldWakeup");
        shouldWakeUp = (shouldWakeUpProperty == null) ? false : Boolean.parseBoolean(shouldWakeUpProperty.trim().toLowerCase());

        String startCounterProperty = properties.getProperty("StartCounter");
        try {
          startCounter = Integer.parseInt(startCounterProperty);
        } catch (Exception e) {
            System.out.println("Couldn't read startCounter, defaulting to "+startCounter);
        }
        String dateFormatStringProperty = properties.getProperty("DateFormatString","MMM dd yy");


        System.out.println("Should Wake up? "+shouldWakeUp);
        System.out.println("Start Counter: "+startCounter);
        System.out.println("Date Format String:"+dateFormatStringProperty);

        //setting property
        properties.setProperty("StartCounter","250");
        try {
            properties.store(new FileOutputStream("properties.conf"),"Properties Description");
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.list(System.out);
    }

    public static void main(String[] args) {
        Ch_8_12_PropertiesExample propertiesExample = new Ch_8_12_PropertiesExample();
        propertiesExample.start();
    }


}
