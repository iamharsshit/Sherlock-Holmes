package com.delta.sherlock;

/**
 * Created by Harshit Bansal on 12/22/2016.
 */

public class Episode {
    private String name;
    private String ratings;
    private int thumbnail;
    private String date;

    public Episode() {
    }

    public Episode(String name, String ratings, int thumbnail,String date) {
        this.name = name;
        this.ratings = ratings;
        this.thumbnail = thumbnail;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String numOfSongs) {
        this.ratings = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String name) {
        this.date= name;
    }
}
