package com.industry.hollywood.staff;

import thirdparty.staff.StudioEmployee;

import java.util.List;

public interface StaffingService {

    void hire(List<StudioEmployee> newStaff);

    List<StudioEmployee> getStaff();
}
