package org.example.utils;

public class InstallmentCalculation {

    public double firstYear(long p) {
        float i = 0.04f;
        int n = 5;
        int g = 1;

        double t = Math.pow(1 + (i / 12), 12);
        double r = Math.pow(t, (0));

        double sum = 0;
        for (int j = 1; j < n; j++) {
            sum += Math.pow((1 + g) / t, j);
        }

        double step1 = (1 + sum) * ((t - 1) / ((i / 12) * t));
        double step2 = Math.pow((1 + g) / t, n);
        double step3 = (r - 1) / ((i / 12) * r);

        return p / (step1 + (step2 * step3));
    }

    public double secondYear(Long p) {
        int g = 1;
        return firstYear(p) * (1 + g);
    }

    public double thirdYear(Long p) {
        int g = 1;
        return secondYear(p) * (1 + g);
    }

    public double fourthYear(Long p) {
        int g = 1;
        return thirdYear(p) * (1 + g);
    }

    public double fifthYear(Long p) {
        int g = 1;
        return fourthYear(p) * (1 + g);
    }
}
