package com.example.healthyme_app;

public class RecipesData {

        private int calories;
        private String label,dlabel;
        String url,imgurl;
        String rname;
        float fat;
        float carb;
        float protein;

        private int editText1,editText2;

        public RecipesData(String url, String label,String dlabel,String imgurl) {
            this.url = url;
            this.label = label;
            this.dlabel=dlabel;
            this.imgurl=imgurl;
        }
        public String getImgurl(){return imgurl;}
        public String getName() {
            return rname;
        }
        public void setName(String num) {
            this.rname = num;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public String getLabel() {
            return label;
        }
        public void setLabel(String label) {
            this.url = url;
        }
        public String getDlabel() {
            return dlabel;
        }
        public void setDlabel(String url) {
            this.dlabel = dlabel;
        }
        public double getCalories() {
            return calories;
        }
        public void setCalories(int num) {
            this.calories = num;
        }





}
