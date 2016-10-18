package org.java8recipes.chapter02.recipe2_02;

/**
 * Recipe 2-2:  Method References
 * @author Juneau
 */
public class PoolCalculator implements Volume {
    private double width;
    private double length;
    private double minDepth;
    private double maxDepth;
    
    public PoolCalculator(){}
    
    public PoolCalculator(double width,
                          double length,
                          double minDepth,
                          double maxDepth){
        this.width = width;
        this.length = length;
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(double length) {
        this.length = length;
    }

  
    /**
     * Calculate the volume
     * @return 
     */ 
    public double calculateVolume(){
        double avgDepth = (minDepth+maxDepth)/2;
        return avgDepth * length * width;
    }
    
    /**
     * Returns the number of gallons for a given Volume
     * @param vol
     * @return 
     */
    public double calculateGallons(Volume vol){
        return 7.48 * vol.calculateVolume();
    }

    /**
     * @return the minDepth
     */
    public double getMinDepth() {
        return minDepth;
    }

    /**
     * @param minDepth the minDepth to set
     */
    public void setMinDepth(double minDepth) {
        this.minDepth = minDepth;
    }

    /**
     * @return the maxDepth
     */
    public double getMaxDepth() {
        return maxDepth;
    }

    /**
     * @param maxDepth the maxDepth to set
     */
    public void setMaxDepth(double maxDepth) {
        this.maxDepth = maxDepth;
    }
    
    
}
