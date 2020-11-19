package com.anisimov.denis.ads.HomeWork4;

public class Exponentiation {

    public static void main(String[] args) {
        System.out.println(doExponentiation(2, 3));
        System.out.println(doExponentiation(2, -3));
    }

    public static double doExponentiation(int value, int degree) {
        if (value == 0 ) {
            return 0;
        }
        if (degree >= 0) {
            return exponentiation(value, degree);
        }
        return 1 / exponentiation(value, Math.abs(degree));
    }

    private static double exponentiation(int value, int degree) {
        if (degree == 0) {
            return 1;
        }
        if (degree == 1) {
            return value;
        }
        return value * exponentiation(value, degree - 1);
    }
}
