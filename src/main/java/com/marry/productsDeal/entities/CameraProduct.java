package com.marry.productsDeal.entities;

public class CameraProduct extends Product {
    private double megapixel;
    private boolean isDigital;
    private final int DSCNT_FOR_NON_DIGITAL = 20;

    @Override
    protected int calcDiscount(int quantity) {
        int def = super.calcDiscount(quantity);

        int DSCNT_FOR_NON_DIGITAL = 20;
        def += DSCNT_FOR_NON_DIGITAL;

        return def;
    }

    public void setMegapixel(double megapixel) {
        this.megapixel = megapixel;
    }

    public void setDigital(boolean digital) {
        isDigital = digital;
    }
}
