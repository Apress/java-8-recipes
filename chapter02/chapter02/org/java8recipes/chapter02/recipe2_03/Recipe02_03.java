
package org.java8recipes.chapter02.recipe2_03;

/**
 * Recipe 2-3:  Default Methods
 * @author Juneau
 */
public class Recipe02_03 {
    public static void main(String[] args){
        RectangularPoolCalculator rpc = new RectangularPoolCalculator();
        Double[] measurements = new Double[2];
        measurements[0] = 32.0;
        measurements[1] = 16.0;
        Double[] depths = new Double[2];
        depths[0] = 3.0;
        depths[1] = 8.0;
        
        double volume = rpc.calculateVolume(depths, measurements);
        System.out.println("Calculated volume is: " + volume);
        
        RoundPoolCalculator round = new RoundPoolCalculator();
        Double[] measurementsRound = new Double[1];
        measurementsRound[0] = 4.5;
        Double[] depthsRound = new Double[1];
        depthsRound[0] = 4.0;
        double roundVol= round.calculateVolume(depthsRound, measurementsRound);
        System.out.println("Round volume is: " + roundVol);
    }
}
