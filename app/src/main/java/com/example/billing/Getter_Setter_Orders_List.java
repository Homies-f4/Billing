package com.example.billing;

public class Getter_Setter_Orders_List {
    String ono;
    String status;
    long tno;

    public Getter_Setter_Orders_List(String ono, String status, long tno) {
        this.ono = ono;
        this.status = status;
        this.tno = tno;
    }
    public String getStatus() {
        return status;
    }

    public  long getTno() {
        return tno;
    }

    public void setTno(long tno) {
        this.tno = tno;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOno() {
        return ono;
    }

    public void setOno(String ono) {
        this.ono = ono;
    }

    public Getter_Setter_Orders_List() {
    }
}
