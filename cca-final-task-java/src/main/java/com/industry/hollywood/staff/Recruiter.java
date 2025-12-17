package com.industry.hollywood.staff;

import thirdparty.staff.StudioEmployee;

import static thirdparty.Roles.RECRUITER;

public class Recruiter extends StudioEmployee {

    public Recruiter(String name) {
        super(name, RECRUITER.proposedSalary, RECRUITER.role);
    }
}
