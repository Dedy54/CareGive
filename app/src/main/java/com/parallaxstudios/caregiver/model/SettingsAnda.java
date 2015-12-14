package com.parallaxstudios.caregiver.model;


public class SettingsAnda {

    String title;
    String excerpt;
    String image;

    public SettingsAnda(String title, String excerpt, String image) {
        this.title = title;
        this.excerpt = excerpt;
        this.image = image;
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
