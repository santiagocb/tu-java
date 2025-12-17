package com.industry.hollywood;

import com.industry.hollywood.finance.FinancialService;
import com.industry.hollywood.movie.*;
import com.industry.hollywood.staff.*;
import thirdparty.service.BudgetIsOverException;
import thirdparty.service.InsufficientBudgetException;
import thirdparty.staff.StudioEmployee;

import java.util.ArrayList;
import java.util.List;

public class MovieStudio {
    private final FinancialService financialService;
    private final StaffingService staffingService;
    private final ProductionManager productionManager;
    private final StaffFactory staffFactory;

    public MovieStudio(FinancialService financialService,
                       StaffingService staffingService,
                       ProductionManager productionManager,
                       StaffFactory staffFactory) {
        this.financialService = financialService;
        this.staffingService = staffingService;
        this.productionManager = productionManager;
        this.staffFactory = staffFactory;
    }

    public Movie createMovie(MovieDefinition definition) throws InsufficientBudgetException {

        staffingService.hire(
                List.of(
                    staffFactory.createStaff("recruiter", "Andrew Carnegie"),
                    staffFactory.createStaff("accountant", "William Deloitte")
                )
        );

        if (!canBeProduced(definition, financialService.calculateUnhiredBudget(definition.getMovieStaff(), definition.getProductionScheduleDaysCount()))) {
            throw new InsufficientBudgetException("Movie cannot be produced due to insufficient budget.");
        }

        financialService.initializeBudget(definition.getBudget());

        staffingService.hire(new ArrayList<>(definition.getMovieStaff().getActorsCollection()));
        staffingService.hire(new ArrayList<>(definition.getMovieStaff().getCameramanCollection()));

        productionManager.initMovieProduction(definition.getProductionScheduleDaysCount());

        Movie movie = new Movie(definition.getName(), definition.getGenre());
        movie.setCrewFromStaffCollection(definition.getMovieStaff());

        while (productionManager.hasNextWorkingDay()) {
            productionManager.lightsCameraAction(staffingService.getStaff());
            movie.updateProgress();

            try {
                financialService.paySalaries(staffingService.getStaff());
            } catch (BudgetIsOverException e) {
                movie.failProduction();
                productionManager.addMovieToArchive(movie);
                return movie; // Failure
            }

            productionManager.progress();
        }

        movie.completeProduction();
        productionManager.addMovieToArchive(movie);
        printMovieStatistics(movie, staffingService.getStaff());
        return movie; // Success
    }

    private boolean canBeProduced(MovieDefinition definition, long estimatedBudget) {
        return definition.getBudget() >= estimatedBudget;
    }

    public void printMovieStatistics(Movie movie, List<StudioEmployee> employees) {
        System.out.printf("## Statistics for '%s' [%s] Status: %s%n",
                movie.getName(),
                movie.getGenre(),
                movie.getProductionStatus()
        );

        System.out.println("Crew:");
        employees.forEach(staff ->
                System.out.printf("- Name: %s | Earned: $%d | Role: %s%n",
                        staff.getName(),
                        staff.getEarnedMoney(),
                        staff.getClass().getSimpleName())
        );

        System.out.printf("Remaining Budget: $%d%n", financialService.getAvailableBudget());
    }
}
