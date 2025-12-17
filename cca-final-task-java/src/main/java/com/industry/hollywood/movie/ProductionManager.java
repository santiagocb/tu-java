package com.industry.hollywood.movie;

import thirdparty.staff.StudioEmployee;

import java.util.List;

public interface ProductionManager {

    void initMovieProduction(int totalDays);

    boolean hasNextWorkingDay();

    void progress();

    boolean lightsCameraAction(List<StudioEmployee> employees);

    void addMovieToArchive(Movie movie);
}
