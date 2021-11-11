package com.example.crypto;

public class CurrencyIcon {
    private String name;
    private String symbol;
    private int image;
    private String price;
    private int change;

    public CurrencyIcon(String name, String symbol, int image) {
        this.name = name;
        this.symbol = symbol;
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public int getChange() {
        return change;
    }
}
