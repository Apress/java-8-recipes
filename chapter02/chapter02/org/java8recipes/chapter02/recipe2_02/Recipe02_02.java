package org.java8recipes.chapter02.recipe2_02;

/**
 * Recipe 2-2:  Method References
 * @author Juneau
 */
public class Recipe02_02 {

    public static void main(String[] args) {
        PoolCalculator calculator = new PoolCalculator();
        calculator.setLength(32);
        calculator.setWidth(16);
        calculator.setMinDepth(4);
        calculator.setMaxDepth(8);
        Volume volume = calculator::calculateVolume;
        double poolVolume = volume.calculateVolume();
        System.out.println("Volume of the pool is: " + poolVolume + " cubic feet");
        System.out.println("Gallons in the pool: " + calculator.calculateGallons(volume));
    }
    
    
}
