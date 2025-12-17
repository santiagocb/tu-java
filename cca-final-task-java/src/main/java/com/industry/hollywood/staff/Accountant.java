package com.industry.hollywood.staff;

import thirdparty.staff.StudioEmployee;

import static thirdparty.Roles.ACCOUNTANT;

public class Accountant extends StudioEmployee {

    public Accountant(String name) {
        super(name, ACCOUNTANT.proposedSalary, ACCOUNTANT.role);
    }
}
