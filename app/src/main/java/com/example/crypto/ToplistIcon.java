package com.example.crypto;

public class ToplistIcon {
    private String name;
    private String symbol;
    private String image;
    private String price;
    private double change;

    public ToplistIcon(String name, String symbol, String image) {
        this.name = name;
        this.symbol = symbol;
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public double getChange() {
        return change;
    }
}
