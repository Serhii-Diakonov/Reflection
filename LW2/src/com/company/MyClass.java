package com.company;

public class MyClass implements Summable{
    private double a1;
    private double a2;

    MyClass(double a, double b) {
        a1 = a;
        a2 = b;
    }

    MyClass() {
        a1 = 0;
        a2 = 0;
    }

    public double sum() {
        return a1 + a2;
    }

    public double sum(double a, double b) {
        return a + b;
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
