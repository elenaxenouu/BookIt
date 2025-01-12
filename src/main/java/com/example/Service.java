package com.example;

public class Service {

    private String name;
    private double cost;


    public Service( String name, double cost) {

        this.name = name;
        this.cost = cost;

    }



    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }



    @Override
    public String toString() {
        return "Service: " + name + ", Cost: " + cost + "€" ;
    }
}
