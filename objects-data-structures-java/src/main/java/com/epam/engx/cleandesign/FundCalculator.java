package com.epam.engx.cleandesign;

import java.util.List;
import java.util.Map;

public class FundCalculator {

    private Map<String, Double> zoneTypeWorkPrice;

    public double getFundBalance(List<Assignment> assignments) {
        double salaries = 0.0;
        double bill = 0.0;
        for (Assignment ass : assignments) {
            salaries += ass.getTotalWorkerSalary();
            bill += ass.calculateZonesBill(zoneTypeWorkPrice);
        }
        return bill - salaries;
    }

    public void setZoneTypeWorkPrice(Map<String, Double> zoneTypeWorkPrice) {
        this.zoneTypeWorkPrice = zoneTypeWorkPrice;
    }
}
