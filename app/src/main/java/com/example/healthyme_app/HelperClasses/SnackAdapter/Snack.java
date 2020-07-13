package com.example.healthyme_app.HelperClasses.SnackAdapter;

public class Snack {


    int image;
    String title;
    String description;

    public Snack(int image, String title,String description) {
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
