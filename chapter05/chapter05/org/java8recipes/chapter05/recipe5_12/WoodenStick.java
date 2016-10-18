
package org.java8recipes.chapter05.recipe5_12;

/**
 * Recipe 5-12
 * 
 * Extending the Functionality of a Class
 * 
 * @author juneau
 */
public class WoodenStick extends HockeyStick {
    
    public static final String material = "WOOD";
    public int lie;
    public int flex;
    
    public WoodenStick(int length, boolean isCurved){
        super(length, isCurved, material);
    }
    
    public WoodenStick(int length, boolean isCurved, int lie, int flex){
        super(length, isCurved, material);
        this.lie = lie;
        this.flex = flex;
    }
}
