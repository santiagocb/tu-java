package co.com.randomexec.recclasses;

import java.util.Objects;

public class Rectangle {

    private double sideA, sideB;

    public Rectangle() {
        sideA = 4.0;
        sideB = 3.0;
    }

    public Rectangle(double a, double b) {
        if(a <= 0 || b <= 0) throw new IllegalArgumentException();
        sideA = a;
        sideB = b;
    }

    public Rectangle(double side) {
        if(side <= 0) throw new IllegalArgumentException();
        sideA = side;
        sideB = side;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double area() {
        return sideA * sideB;
    }

    public double perimeter() {
        return 2 * sideA + 2 * sideB;
    }

    public boolean isSquare() {
        return sideA == sideB;
    }

    public void replaceSides() {
        double temp = sideA;
        sideA = sideB;
        sideB = temp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(sideA, rectangle.sideA) == 0 && Double.compare(sideB, rectangle.sideB) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB);
    }
}
