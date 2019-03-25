package com.google.codeu.data;

public class User {

    private String email;
    private String aboutMe;
    private double lat;
    private double lng;
    private String content;

    public User(String email, String aboutMe,double lat, double lng, String content) {
        this.email = email;
        this.aboutMe = aboutMe;
        this.lat=lat;
        this.lng=lng;
        this.content=content;
    }
    public User(double lat, double lng, String content) {
        this.lat=lat;
        this.lng=lng;
        this.content=content;
    }
    public User(String email, String aboutMe) {
        this.email = email;
        this.aboutMe = aboutMe;
    }

    public String getEmail(){
        return email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getContent() {
        return content;
    }
}
