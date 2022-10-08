package com.example.billing;

public class Getter_Setter_Items {
    private String dname;
    private int qty;
    private int sno;

    public Getter_Setter_Items() {
    }

    public Getter_Setter_Items(String dname, int qty, int sno) {
        this.dname = dname;
        this.qty = qty;
        this.sno = sno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
}
