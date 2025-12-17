package com.epam.engx.cleandesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Zone {

    private static final double MATERIAL_AREA_FACTOR = 10;
    private static final int ONE_DAY_MAX_AREA = 50;
    private static final double MULTI_DAY_PRICE_FACTOR = 1.1;

    private double height;
    private double width;
    private String type;
    private List<Aperture> apertures;

    public Zone(String type, double height, double width) {
        this.height = height;
        this.width = width;
        this.type = type;
        this.apertures = new ArrayList<>();
    }

    public void setApertures(List<Aperture> apertures) {
        this.apertures = apertures;
    }

    public double getArea() {
        return height * width - (apertures.stream().mapToDouble(Aperture::getArea).sum());
    }

    public Double calculateZoneBillPrice(Map<String, Double> zoneTypeWorkPrice) {
        validateZone(zoneTypeWorkPrice);
        return getZoneBillPrice(zoneTypeWorkPrice.get(type));
    }

    private Double getZoneBillPrice(Double zonePrice) {
        return (getArea() * MATERIAL_AREA_FACTOR) + getWorkPrice(getArea(), zonePrice);
    }

    private void validateZone(Map<String, Double> zoneTypeWorkPrice) {
        if (isNotContainsKey(zoneTypeWorkPrice))
            throw new WrongZoneTypeException();
    }

    private boolean isNotContainsKey(Map<String, Double> zoneTypeWorkPrice) {
        return !zoneTypeWorkPrice.containsKey(type);
    }

    private double getWorkPrice(double area, double zonePrice) {
        double price = area * zonePrice;
        if (area < ONE_DAY_MAX_AREA) {
            return price;
        }
        return price * MULTI_DAY_PRICE_FACTOR;
    }

}
