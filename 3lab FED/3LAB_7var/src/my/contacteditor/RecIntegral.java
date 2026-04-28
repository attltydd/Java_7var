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

    public RecIntegral(double lowerLimit, double upperLimit, double range) throws InvalidRangeException {
         
        if (lowerLimit < 0.000001 || lowerLimit > 1000000 ||
        upperLimit < 0.000001 || upperLimit > 1000000 ||
        range < 0.000001 || range > 1000000) {
        
        throw new InvalidRangeException("Значения должны быть от 0.000001 до 1000000");
    }
        
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.range = range;
        this.result = 0;
    }
    public RecIntegral(double lowerLimit, double upperLimit, double range, double result) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.range = range;
        this.result = result;
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

    double CalcIntegral(double UpperLimit, double LowLimit, double Range) {
       double start, h, sumS = 0;
        
        start = LowLimit;
        
        do{
            h = Math.min(Range, (UpperLimit-start));
            sumS += h * (Math.tan(start) + Math.tan(start + h))/2;
            start += h;
        }while((start) < UpperLimit);
        
        return sumS;
    }
}
