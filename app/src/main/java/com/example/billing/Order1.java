package com.example.billing;

public class Order1 {
    long ono;
    String status;
    long tno;

    public Order1(long ono,String status,long tno) {
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

    public long getOno() {
        return ono;
    }

    public void setOno(int ono) {
        this.ono = ono;
    }

    public Order1() {
    }
}
