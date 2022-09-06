package com.mynt.shippingcalculator;

public final class Parcel {
    private final long weight;
    private final long height;
    private final long width;
    private final long length;

    public Parcel(final long weight, final long height, final long width, final long length) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public long getWeight() {
        return weight;
    }

    public long getHeight() {
        return height;
    }

    public long getWidth() {
        return width;
    }

    public long getLength() {
        return length;
    }

    public long getVolume() {
        return height * width * length;
    }
}
