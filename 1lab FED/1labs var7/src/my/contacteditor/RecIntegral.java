/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.contacteditor;

/**
 *
 * @author student
 */
public class RecIntegral {
     private double lowerLimit;
    private double upperLimit;
    private double range;
    private double result;

    public RecIntegral(double lowerLimit, double upperLimit, double range) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.range = range;
        this.result = 0;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public double getRange() {
        return range;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
