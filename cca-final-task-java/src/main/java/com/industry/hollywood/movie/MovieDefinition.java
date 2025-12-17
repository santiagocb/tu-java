package com.industry.hollywood.movie;

import com.industry.hollywood.staff.team.StudioStaff;
import thirdparty.Genre;

public class MovieDefinition {
    private final String name;
    private final Genre genre;
    private final Long budget;
    private final int productionScheduleDays;
    private final StudioStaff movieStaff;

    public MovieDefinition(String name, Genre genre, Long budget, int productionScheduleDays, StudioStaff movieStaff) {
        this.name = name;
        this.genre = genre;
        this.budget = budget;
        this.productionScheduleDays = productionScheduleDays;
        this.movieStaff = movieStaff;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Long getBudget() {
        return budget;
    }

    public int getProductionScheduleDaysCount() {
        return productionScheduleDays;
    }

    public StudioStaff getMovieStaff() {
        return movieStaff;
    }
}