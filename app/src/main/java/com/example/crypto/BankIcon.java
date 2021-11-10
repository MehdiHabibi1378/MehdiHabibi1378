package com.example.crypto;

public class BankIcon {
    private String name;
    private int image;
    private String url;
    public BankIcon (String name,int image , String url){
        this.name=name;
        this.image = image;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }
}
