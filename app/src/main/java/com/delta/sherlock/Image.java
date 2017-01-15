package com.delta.sherlock;

/**
 * Created by Harshit Bansal on 1/8/2017.
 */

public class Image {
    int id;
    private int image;
    private String name;
    public Image(int id,int image,String name){
        this.id=id;
        this.image=image;
        this.name=name;
    }
    public Image(int image,String name){
        this.image=image;
        this.name=name;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    public void setImage(int image){
        this.image=image;
    }
    public int getImage(){
        return this.image;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

}
