package com.example.healthyme_app;

public class UserProgressData {

        private int calories;
        private String date;
        float fat;
        float carb;
        float protein;

        private int editText1,editText2;

    public UserProgressData(int calories, String date) {
        this.calories = calories;
        this.date = date;
    }


        public double getCalories() {
            return calories;
        }
        public void setCalories(int num) {
            this.calories = num;
        }
        public String getDate() {
            return date;
        }
        public void setDate(String num) {
            this.date = num;
        }


    }


