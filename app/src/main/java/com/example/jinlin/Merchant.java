package com.example.jinlin;

public class Merchant {
    int id;
    String merchantname;
    String merchantimage;
    int amount;
    String description;



    public Merchant(int id, int amount, String merchantname, String merchantimage, String description){
        this.id = id;
        this.amount = amount;
        this.merchantname = merchantname;
        this.description = description;
        this.merchantimage = merchantimage;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getName() {
        return merchantname;
    }

    public String getMerchantImage() {
        return merchantimage;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    //setter
    public void setName(String merchantname) {
        this.merchantname = merchantname;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
