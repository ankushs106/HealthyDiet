package com.example.healthyme_app.HelperClasses.DinnerAdapter;

public class Dinner {

    int image;
    String title;
    String description;

    public Dinner(int image, String title,String description) {
        this.image = image;
        this.title = title;
        this.description = description;

    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
