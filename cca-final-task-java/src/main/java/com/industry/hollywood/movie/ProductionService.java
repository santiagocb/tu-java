package com.industry.hollywood.movie;

import com.industry.hollywood.staff.Actor;
import com.industry.hollywood.staff.CameraMan;
import thirdparty.Roles;
import thirdparty.staff.StudioEmployee;

import java.util.ArrayList;
import java.util.List;

public class ProductionService implements ProductionManager {
    private int daysRemaining;
    private final List<Movie> movieArchive = new ArrayList<>();

    @Override
    public void initMovieProduction(int totalDays) {
        this.daysRemaining = totalDays;
    }

    @Override
    public boolean hasNextWorkingDay() {
        return daysRemaining > 0;
    }

    @Override
    public void progress() {
        daysRemaining--;
    }

    @Override
    public boolean lightsCameraAction(List<StudioEmployee> employees) {
        boolean actorPerformance = employees.stream()
                .filter(e -> e.getRole().equals(Roles.ACTOR.role))
                .map(actor -> ((Actor) actor).act())
                .reduce(true, (crewSuccess, crewAction) -> crewSuccess && crewAction);

        boolean cameraPerformance = employees.stream()
                .filter(e -> e.getRole().equals(Roles.CAMERA_MAN.role))
                .map(cameraman -> ((CameraMan) cameraman).shoot())
                .reduce(true, (crewSuccess, crewAction) -> crewSuccess && crewAction);

        return actorPerformance && cameraPerformance;
    }

    @Override
    public void addMovieToArchive(Movie movie) {
        movieArchive.add(movie);
    }

}