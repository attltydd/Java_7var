/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.contacteditor;
import java.io.Serializable;
/**
 *
 * @author student
 */
public class RecIntegral implements Serializable{
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
        if (lowerLimit > upperLimit) {
        throw new InvalidRangeException("Нижний предел не может быть больше верхнего!");
    }

    if (range > (upperLimit - lowerLimit)) {
        throw new InvalidRangeException("Шаг (range) не может быть больше длины интервала!");
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

    double CalcIntegral(double LowerLimit, double UpperLimit, double Range) {
    double start = LowerLimit;
    double sumS = 0;

    do {
        double h = Math.min(Range, (UpperLimit - start));
        double x1 = start;
        double x2 = start + h;

        
        if (x1 <= 0 || x2 <= 0) {
            throw new IllegalArgumentException("x должен быть > 0");
        }

        
        if ((x1 < 1 && x2 > 1)) {
            double mid = 1.0;

            double f1 = 1.0 / Math.log(x1);
            double fm = 1.0 / Math.log(mid - 1e-10); 

            double h1 = mid - x1;
            sumS += h1 * (f1 + fm) / 2.0;

            double fm2 = 1.0 / Math.log(mid + 1e-10); 
            double f2 = 1.0 / Math.log(x2);

            double h2 = x2 - mid;
            sumS += h2 * (fm2 + f2) / 2.0;

        } else {
            double f1 = 1.0 / Math.log(x1);
            double f2 = 1.0 / Math.log(x2);

            sumS += h * (f1 + f2) / 2.0;
        }

        start += h;

    } while (start < UpperLimit);

    return sumS;
}
}
