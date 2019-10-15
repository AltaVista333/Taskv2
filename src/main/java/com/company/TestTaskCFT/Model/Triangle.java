package com.company.TestTaskCFT.Model;

import com.company.TestTaskCFT.Service.Validator;

import java.util.Arrays;
import java.util.Objects;

public class Triangle implements Comparable<Triangle> {

    private final int[] coordinates;
    private final int x1, x2, x3, y1, y2, y3;

    public Triangle(int[] coordinates) {
        this.coordinates = coordinates;
        this.x1 = coordinates[0];
        this.y1 = coordinates[1];
        this.x2 = coordinates[2];
        this.y2 = coordinates[3];
        this.x3 = coordinates[4];
        this.y3 = coordinates[5];
    }

    public static boolean isTriangle(int[] coordinates) {
        if (!Validator.validateCoordinates(coordinates, 6)) {
            return false;
        }
        int x1 = coordinates[0];
        int y1 = coordinates[1];
        int x2 = coordinates[2];
        int y2 = coordinates[3];
        int x3 = coordinates[4];
        int y3 = coordinates[5];

        int a = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        int b = (x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3);
        int c = (x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3);

        return a + b > c || a + c > b || b + c > a;
    }

    public double getArea() {
        return 0.5 * Math.abs((x1 - x3) * (y2 - y3) - (y1 - y3) * (x2 - x3));
    }

    public boolean isTriangleIsosceles() {
        int a = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        int b = (x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3);
        int c = (x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3);

        return a == c || b == c || a == b;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int coordinate : coordinates) {
            builder.append(coordinate);
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (x1 == triangle.x1 &&
                x2 == triangle.x2 &&
                x3 == triangle.x3 &&
                y1 == triangle.y1 &&
                y2 == triangle.y2 &&
                y3 == triangle.y3)
                ||
                (x1 == triangle.x2 &&
                        x2 == triangle.x1 &&
                        x3 == triangle.x3 &&
                        y1 == triangle.y2 &&
                        y2 == triangle.y1 &&
                        y3 == triangle.y3)
                ||
                (x1 == triangle.x3 &&
                        x2 == triangle.x2 &&
                        x3 == triangle.x1 &&
                        y1 == triangle.y3 &&
                        y2 == triangle.y2 &&
                        y3 == triangle.y1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1 + x2 + x3, y1 + y2 + y3);
    }

    @Override
    public int compareTo(Triangle o) {
        return (int) (2 * (this.getArea() - o.getArea()));
    }


}
