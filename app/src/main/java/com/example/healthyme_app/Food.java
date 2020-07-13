package com.example.healthyme_app;


public class Food {

    public String fname;
    public String m;
    public int c;

    public Food() {
    }

    public Food(String food_name, String measures,int cal) {
        this.fname = food_name;
        this.m = measures;
        this.c=cal;
    }
    public double getCalories() {
        return c;
    }
    public void setCalories(int c) {
        this.c = c;
    }
}