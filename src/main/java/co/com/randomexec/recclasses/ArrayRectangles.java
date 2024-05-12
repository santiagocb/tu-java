package co.com.randomexec.recclasses;

import java.util.Arrays;

public class ArrayRectangles {

    Rectangle[] rectangleArray;

    public ArrayRectangles(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        rectangleArray = new Rectangle[size];
    }

    public ArrayRectangles(Rectangle... rectangles) {
        if (rectangles == null || rectangles.length == 0) throw new IllegalArgumentException();
        rectangleArray = rectangles;
    }

    public boolean addRectangle(Rectangle rectangle) {
        if (rectangle == null) throw new IllegalArgumentException();

        boolean wasSuccess = false;
        for (int i = 0; i < rectangleArray.length; i++) {
            if(rectangleArray[i] == null) {
                rectangleArray[i] = rectangle;
                wasSuccess = true;
                break;
            }
        }
        return wasSuccess;
    }

    public int size() {
        int counter = 0;
        for (Rectangle rectangle : rectangleArray) {
            if (rectangle != null) counter++;
        }
        return counter;
    }

    public int indexMaxArea() {
        int maxIndex = 0;
        double maxArea = Double.MIN_VALUE;
        for (int i = 0; i < rectangleArray.length; i++) {
            if(rectangleArray[i].area() > maxArea) {
                maxArea = rectangleArray[i].area();
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int indexMinPerimeter() {
        int minIndex = 0;
        double minPerimeter = Double.MAX_VALUE;
        for (int i = 0; i < rectangleArray.length; i++) {
            if(rectangleArray[i].perimeter() < minPerimeter) {
                minPerimeter = rectangleArray[i].perimeter();
                minIndex = i;
            }
        }
        return minIndex;
    }

    public int numberSquares() {
        return (int) Arrays.stream(rectangleArray).filter(Rectangle::isSquare).count();
    }
}
