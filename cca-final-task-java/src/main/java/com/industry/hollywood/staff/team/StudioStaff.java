package com.industry.hollywood.staff.team;

import com.industry.hollywood.staff.Actor;
import com.industry.hollywood.staff.CameraMan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudioStaff {
    private final List<Actor> actorsCollection;
    private final List<CameraMan> cameramanCollection;

    public StudioStaff(Actor[] actors, CameraMan[] cameramen) {
        this.actorsCollection = new ArrayList<>(Arrays.asList(actors));
        this.cameramanCollection = new ArrayList<>(Arrays.asList(cameramen));
    }

    public List<Actor> getActorsCollection() {
        return Collections.unmodifiableList(actorsCollection);
    }

    public List<CameraMan> getCameramanCollection() {
        return Collections.unmodifiableList(cameramanCollection);
    }
}
