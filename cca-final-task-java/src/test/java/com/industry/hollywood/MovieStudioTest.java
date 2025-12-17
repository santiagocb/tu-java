package com.industry.hollywood;

import com.industry.hollywood.finance.BudgetManagementService;
import com.industry.hollywood.finance.FinancialService;
import com.industry.hollywood.movie.*;
import com.industry.hollywood.staff.*;
import com.industry.hollywood.staff.team.StudioStaff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thirdparty.Genre;
import thirdparty.service.InsufficientBudgetException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static thirdparty.Roles.*;

public class MovieStudioTest {
    private MovieStudio movieStudio;
    private FinancialService financialService;
    private StaffingService staffingService;
    private ProductionManager productionManager;
    private StaffFactory staffFactory;

    @BeforeEach
    public void setUp() {
        financialService = new BudgetManagementService();
        staffingService = new HiringService();
        productionManager = new ProductionService();
        staffFactory = new DefaultStaffFactory();
        registerRoles2StaffFactory();
        movieStudio = new MovieStudio(financialService, staffingService, productionManager, staffFactory);
    }

    private void registerRoles2StaffFactory() {
        // Register default roles during initialization
        staffFactory.registerRole(RECRUITER.role, Recruiter::new);
        staffFactory.registerRole(ACCOUNTANT.role, Accountant::new);
        staffFactory.registerRole(ACTOR.role, name -> new Actor(name, false));
        staffFactory.registerRole(SUPERSTAR.role, name -> new Actor(name, true));
        staffFactory.registerRole(CAMERA_MAN.role, CameraMan::new);
    }

    @Test
    public void CreateMovie_Titanic_WithValidMovieDefinition_ReturnsTrue_OnSuccessfulCompletion() throws InsufficientBudgetException {
        StudioStaff staff = new StudioStaff(
                new Actor[]{
                        new Actor("Leonardo DiCaprio", true),
                        new Actor("Kate Winslet", true),
                        new Actor("Billy Zane", false),
                        new Actor("Kathy Bates", false),
                        new Actor("Frances Fisher", false),
                        new Actor("Bernard Hill", false),
                        new Actor("Jonathan Hyde", false),
                        new Actor("Danny Nucci", false),
                        new Actor("David Warner", false),
                        new Actor("Bill Paxton", false)
                },
                new CameraMan[]{
                        new CameraMan("Guy Norman Bee"),
                        new CameraMan("Marcis Cole"),
                        new CameraMan("Tony Guerin")
                }
        );

        MovieDefinition titanicMovie = new MovieDefinition(
                "Titanic",
                Genre.DRAMA,
                160_000_000L, // Large budget
                160,           // Production schedule days
                staff
        );

        Movie movie = movieStudio.createMovie(titanicMovie);

        assertEquals("SUCCESS", movie.getProductionStatus()); // Check production result
        assertEquals(160, movie.getDaysSpentInProduction()); // Check it used the full schedule
    }

    @Test
    public void CreateMovie_StarWars_WithValidMovieDefinition_ReturnsTrue_OnSuccessfulCompletion() throws InsufficientBudgetException {
        StudioStaff staff = new StudioStaff(
                new Actor[]{
                        new Actor("Mark Hamill", true),
                        new Actor("Harrison Ford", true),
                        new Actor("Carrie Fisher", false),
                        new Actor("Billy Dee Williams", false),
                        new Actor("Anthony Daniels", false),
                        new Actor("David Prowse", false),
                        new Actor("Peter Mayhew", false)
                },
                new CameraMan[]{
                        new CameraMan("John Campbell"),
                        new CameraMan("Bill Neill")
                }
        );

        MovieDefinition starWarsMovie = new MovieDefinition(
                "Star Wars: Episode VI – Return of the Jedi",
                Genre.SCIFI,
                60_000_000L, // Medium budget
                90,           // Production schedule days
                staff
        );

        Movie movie = movieStudio.createMovie(starWarsMovie);

        assertEquals("SUCCESS", movie.getProductionStatus()); // Check production result
        assertEquals(90, movie.getDaysSpentInProduction());   // Ensure it used the full schedule
    }

    @Test
    public void CreateMovie_ShouldCreateEmptyMovie_WithEmptyMovieDefinition_ReturnsTrue() throws InsufficientBudgetException {
        StudioStaff staff = new StudioStaff(
                new Actor[]{},    // No actors
                new CameraMan[]{} // No cameramen
        );

        MovieDefinition emptyMovie = new MovieDefinition(
                "NoName",
                Genre.COMEDY,
                1L,  // Minimal budget
                1,    // Minimal schedule days
                staff
        );

        Movie movie = movieStudio.createMovie(emptyMovie);

        assertEquals("FAILURE", movie.getProductionStatus()); // Check production result
    }

    @Test
    public void CreateMovie_WhenBudgetExceeds_ReturnsFalse_OnUnsuccessfulCompletion() throws InsufficientBudgetException {
        StudioStaff staff = new StudioStaff(
                new Actor[]{
                        new Actor("Taylor Kitsch", false),
                        new Actor("Lynn Collins", false),
                        new Actor("Samantha Morton", false),
                        new Actor("Mark Strong", true),
                        new Actor("Ciarán Hinds", false),
                        new Actor("Dominic West", false),
                        new Actor("James Purefoy", false),
                        new Actor("Willem Dafoe", true)
                },
                new CameraMan[]{
                        new CameraMan("Carver Christians"),
                        new CameraMan("Scott Bourke"),
                        new CameraMan("Quentin Herriot"),
                        new CameraMan("Brandon Wyman")
                }
        );

        MovieDefinition johnCarter = new MovieDefinition(
                "John Carter",
                Genre.FANTASY,
                100000000L, // Insufficient budget for large crew size
                200,         // Overly long production schedule days
                staff
        );

        Movie movie = movieStudio.createMovie(johnCarter);

        assertEquals("FAILURE", movie.getProductionStatus()); // Check production result
        assertTrue(movie.getDaysSpentInProduction() < 200);   // Ensure it fails before finishing
    }

    @Test
    public void CreateMovie_WhenInsufficientBudget_Throws_InsufficientBudgetException() {
        StudioStaff staff = new StudioStaff(
                new Actor[]{
                        new Actor("Channing Tatum", true),
                        new Actor("Taylor Kitsch", true),
                        new Actor("Keanu Reeves", true),
                        new Actor("Josh Holloway", true),
                        new Actor("Léa Seydoux", true),
                        new Actor("Hugh Jackman", true),
                        new Actor("Rebecca Ferguson", false),
                        new Actor("Abbey Lee", true)
                },
                new CameraMan[]{
                        new CameraMan("Carver Christians"),
                        new CameraMan("Scott Bourke"),
                        new CameraMan("Quentin Herriot"),
                        new CameraMan("Brandon Wyman")
                }
        );

        MovieDefinition gambitMovie = new MovieDefinition(
                "Gambit",
                Genre.FANTASY,
                100_000_000L, // Budget too low for long schedule and large crew
                250,           // Extremely long schedule days
                staff
        );

        assertThrows(InsufficientBudgetException.class, () -> movieStudio.createMovie(gambitMovie)); // Fails feasibility check
    }
}