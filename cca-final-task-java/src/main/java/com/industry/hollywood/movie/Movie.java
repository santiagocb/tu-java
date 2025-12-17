package com.industry.hollywood.movie;

import com.industry.hollywood.staff.team.StudioStaff;
import thirdparty.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movie {
    private final String name;
    private final Genre genre;
    private boolean isProductionSuccess;
    private int daysSpentInProduction;
    private final Map<String, Integer> crew;
    private final List<String> superstars;

    public Movie(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
        this.isProductionSuccess = false;
        this.daysSpentInProduction = 0;
        this.crew = new HashMap<>();
        this.superstars = new ArrayList<>();
    }

    public void setCrewFromStaffCollection(StudioStaff staff) {
        crew.put("Actor", staff.getActorsCollection().size());
        crew.put("Cameraman", staff.getCameramanCollection().size());

        staff.getActorsCollection().forEach(actor -> {
            if (actor.isSuperStar()) {
                superstars.add(actor.getName());
            }
        });
    }

    public void updateProgress() {
        daysSpentInProduction++;
    }

    public void failProduction() {
        isProductionSuccess = false;
    }

    public void completeProduction() {
        isProductionSuccess = true;
    }

    public String getProductionStatus() {
        return isProductionSuccess ? "SUCCESS" : "FAILURE";
    }

    public int getDaysSpentInProduction() {
        return daysSpentInProduction;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return String.format("Movie: %s [%s] Status: %s, Days Spent: %d",
                name, genre, getProductionStatus(), daysSpentInProduction);
    }
}