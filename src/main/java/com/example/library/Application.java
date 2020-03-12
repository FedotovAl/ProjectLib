package com.example.library;

public class Application {
    public static void main(String[] args) {
        Car car = new Car();
        car.setA(4);
        car.setB(5);
        System.out.println(car.getA() + " " + car.getB());

        System.out.println();
    }
}
