package com.epam.engx.cleandesign;

import java.util.List;
import java.util.Map;

public class Assignment {

    private static final double SENIOR_BONUS_FACTOR = 1.5;

    private Worker worker;
    private List<Zone> zones;
    private double vendorBonus;

    public void setVendorBonus(double vendorBonus) {
        this.vendorBonus = vendorBonus;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public double getTotalWorkerSalary() {
        return worker.calculateSalary(getTotalArea()) + getBonus();
    }

    public Double calculateZonesBill(Map<String, Double> zoneTypeWorkPrice) {
        return zones.stream().mapToDouble(z -> z.calculateZoneBillPrice(zoneTypeWorkPrice)).sum();
    }

    private double getBonus() {
        if (worker.isJunior()) {
            return vendorBonus;
        } else {
            return vendorBonus * SENIOR_BONUS_FACTOR;
        }
    }

    private double getTotalArea() {
        return zones.stream().mapToDouble(Zone::getArea).sum();
    }

}
