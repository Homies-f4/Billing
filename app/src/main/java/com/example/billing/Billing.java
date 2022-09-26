package com.example.billing;

public class Billing {
    private String dish;
    private Float cost;
    private int sno;

    public Billing(String dish, Float cost, int sno) {
        this.dish = dish;
        this.cost = cost;
        this.sno = sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getSno() {
        return sno;
    }

    Billing()
    {

    }
    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getDish() {
        return dish;
    }

    public Float getCost() {
        return cost;
    }
}
