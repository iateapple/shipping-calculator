package com.mynt.shippingcalculator;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

public class ShippingCost {

    private final Rule rule;

    private final Optional<Double> cost;

    private Optional<Voucher> voucher = Optional.empty();

    ShippingCost(final Rule rule, final Parcel parcel) {
        this.rule = rule;
        this.cost = rule.getCost(parcel);
    }

    public Rule getRule() {
        return rule;
    }

    public Optional<Double> getCost() {
        return cost;
    }

    public void setVoucher(final Voucher voucher) {
        this.voucher = Optional.of(voucher);
    }

    public Optional<Voucher> getVoucher() {
        return voucher;
    }
}
