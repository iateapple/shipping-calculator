package com.mynt.shippingcalculator;

import java.util.Date;

public class Voucher {
    private String code;
    private double discount;
    private Date expiry;

    public Voucher() {
        // Default constructor
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDiscount(final double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setExpiry(final Date expiry) {
        this.expiry = expiry;
    }

    public Date getExpiry() {
        return expiry;
    }
}
