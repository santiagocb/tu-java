package com.industry.hollywood.staff;

import com.industry.hollywood.staff.profile.CameraManFunctionality;
import thirdparty.staff.StudioEmployee;

import java.util.Random;

import static thirdparty.Roles.CAMERA_MAN;

public class CameraMan extends StudioEmployee implements CameraManFunctionality {

    public CameraMan(String name) {
        super(name, CAMERA_MAN.proposedSalary, CAMERA_MAN.role);
    }

    @Override
    public boolean shoot() {
        // like an actor, cameraman with 4% probability may ruin the whole day
        return new Random().nextDouble() > 0.04;
    }
}
