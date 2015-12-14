package com.parallaxstudios.caregiver.model;


public class BerandaNews {

    String title;
    String date;
    String excerpt;
    String image;

    public BerandaNews(String title, String date, String excerpt, String image) {
        this.title = title;
        this.date = date;
        this.excerpt = excerpt;
        this.image = image;
    }
    public String getDate() {
        return date;
    }
    
    public String getTitle() {
        return title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getImage() {
        return image;
    }
}
