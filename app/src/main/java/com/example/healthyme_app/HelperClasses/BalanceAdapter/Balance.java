package com.example.healthyme_app.HelperClasses.BalanceAdapter;


public class Balance {
    String Plan_name,food_name,recipe,type;
    int cal,proteins,carbs,fats;
    int image;
    String title;


    public Balance(int cal, String food,int image, String title){
        this.cal=cal;
        food_name=food;
        this.image = image;
        this.title = title;
    }


    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
    public int getCal() { return cal; }

    public String getPlan_name() { return Plan_name; }

    public int getCarbs() {
        return carbs;
    }

    public int getFats() {
        return fats;
    }

    public String getFood_name() {
        return food_name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public void setPlan_name(String plan_name) {
        Plan_name = plan_name;
    }

    public int getProteins() {
        return proteins;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getType() {
        return type;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public void setType(String type) {
        this.type = type;
    }
}
