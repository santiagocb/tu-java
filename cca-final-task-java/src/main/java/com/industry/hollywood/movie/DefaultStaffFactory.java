package com.industry.hollywood.movie;

import thirdparty.staff.StudioEmployee;

import java.util.HashMap;
import java.util.Map;

public class DefaultStaffFactory implements StaffFactory {

    // Registry for role creators mapped to their role names
    private final Map<String, RoleCreator> roleRegistry = new HashMap<>();

    public DefaultStaffFactory() {
    }

    // Dynamically register a new role
    public void registerRole(String roleName, RoleCreator roleCreator) {
        roleRegistry.put(roleName.toLowerCase(), roleCreator);
    }

    @Override
    public StudioEmployee createStaff(String role, String name) {
        RoleCreator roleCreator = roleRegistry.get(role.toLowerCase());
        if (roleCreator == null) {
            throw new IllegalArgumentException("Unknown role: " + role);
        }
        return roleCreator.create(name);
    }
}