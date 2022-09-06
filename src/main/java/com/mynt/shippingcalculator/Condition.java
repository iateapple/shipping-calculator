package com.mynt.shippingcalculator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.function.Predicate;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Condition {

    WEIGHT_EXCEEDS_50KG("Weight exceeds 50kg", weightedParcel(50)),
    WEIGHT_EXCEEDS_10KG("Weight exceeds 10kg", weightedParcel(10)),
    VOLUME_LESS_1500_CM3("Volume is less than 1500cm3", volumedParcel(1500)),
    VOLUME_LESS_2500_CM3("Volume is less than 2500cm3", volumedParcel(2500)),
    LARGE_PARCEL("Large Parcel", parcel -> true);

    private final String name;
    private final Predicate<Parcel> predicate;

    Condition(final String name, final Predicate<Parcel> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    private static Predicate<Parcel> weightedParcel(final long weight) {
        return parcel -> parcel.getWeight() > weight;
    }

    private static Predicate<Parcel> volumedParcel(final long volume) {
        return parcel -> parcel.getVolume() < volume;
    }

    protected boolean test(final Parcel parcel) {
        return predicate.test(parcel);
    }

    public String getName() {
        return name;
    }
}
