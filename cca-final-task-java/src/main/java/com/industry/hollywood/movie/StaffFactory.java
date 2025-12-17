package com.industry.hollywood.movie;

import thirdparty.staff.StudioEmployee;

public interface StaffFactory {

    void registerRole(String roleName, RoleCreator roleCreator);
    StudioEmployee createStaff(String role, String name);
}