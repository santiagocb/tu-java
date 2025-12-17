package com.industry.hollywood.staff;

import com.industry.hollywood.staff.profile.ActorFunctionality;
import thirdparty.Roles;
import thirdparty.staff.StudioEmployee;

import java.util.Random;

import static thirdparty.Roles.ACTOR;

public class Actor extends StudioEmployee implements ActorFunctionality {

    private final boolean isSuperStar;

    public Actor(String name, boolean isSuperStar) {
        super(name, isSuperStar ? Roles.SUPERSTAR.proposedSalary : Roles.ACTOR.proposedSalary, ACTOR.role);
        this.isSuperStar = isSuperStar;
    }

    public boolean isSuperStar() {
        return isSuperStar;
    }

    @Override
    public boolean act() {
        double successRate = isSuperStar ? 0.99 : 0.96;
        return new Random().nextDouble() <= successRate;
    }
}
