package my.contacteditor;

public class IntegralTask implements Runnable {

    private double lower;
    private double upper;
    private double range;

    private double result;

    public IntegralTask(double lower, double upper, double range) {

        this.lower = lower;
        this.upper = upper;
        this.range = range;
    }

    @Override
    public void run() {

        double start = lower;
        double sumS = 0;

        do {

            double h = Math.min(range, (upper - start));

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

        } while (start < upper);

        result = sumS;
    }

    public double getResult() {
        return result;
    }
}

