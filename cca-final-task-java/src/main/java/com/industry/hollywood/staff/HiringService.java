package com.industry.hollywood.staff;

import thirdparty.staff.StudioEmployee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class HiringService implements StaffingService {
    private final List<StudioEmployee> staff = new ArrayList<>();

    @Override
    public void hire(List<StudioEmployee> newStaff) {
        staff.addAll(newStaff);
    }

    @Override
    public List<StudioEmployee> getStaff() {
        return Collections.unmodifiableList(staff);
    }
}
