package com.mynt.shippingcalculator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;
import java.util.function.Function;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Rule {
    REJECT("Reject", Condition.WEIGHT_EXCEEDS_50KG, reject()),
    HEAVY_PARCEL("Heavy Parcel", Condition.WEIGHT_EXCEEDS_10KG, weightedParcel(20)),
    SMALL_PARCEL("Small Parcel", Condition.VOLUME_LESS_1500_CM3, sizedParcel(0.03)),
    MEDIUM_PARCEL("Medium Parcel", Condition.VOLUME_LESS_2500_CM3, sizedParcel(0.04)),
    LARGE_PARCEL("Large Parcel", Condition.LARGE_PARCEL, sizedParcel(0.05));

    private final String name;

    private final Condition condition;
    private Function<Parcel, Optional<Double>> costing;

    Rule(final String name, final Condition condition, final Function<Parcel, Optional<Double>> costing) {
        this.name = name;
        this.condition = condition;
        this.costing = costing;
    }

    private static Function<Parcel, Optional<Double>> reject() {
        return parcel -> Optional.empty();
    }

    private static Function<Parcel, Optional<Double>> weightedParcel(final double multiplier) {
        return parcel -> Optional.of(parcel.getWeight()).map(w -> w * multiplier);
    }

    private static Function<Parcel, Optional<Double>> sizedParcel(final double multiplier) {
        return parcel -> Optional.of(parcel.getVolume()).map(v -> v * multiplier);
    }

    public boolean test(final Parcel parcel) {
        return condition.test(parcel);
    }

    public Optional<Double> getCost(final Parcel parcel) {
        return costing.apply(parcel);
    }

    public String getName() {
        return name;
    }

    public Condition getCondition() {
        return condition;
    }
}
