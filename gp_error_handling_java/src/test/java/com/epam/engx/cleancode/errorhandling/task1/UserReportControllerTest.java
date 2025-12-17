package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.stubs.*;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserReportControllerTest {

    private static final String USER_ID = "123";
    private final List<Order> orders = new ArrayList<>(asList(new SubmittedOrderStub(), new AnotherSubmittedOrderStub(), new NotSubmittedOrderStub()));
    private UserReportController userReportController = new UserReportController();
    private final UserReportBuilder userReportBuilder = new UserReportBuilder();

    @BeforeEach
    public void setUp() throws Exception {
        userReportController.setUserReportBuilder(userReportBuilder);
        userReportBuilder.setUserDao(new UserDao() {
            @Override
            public User getUser(String userId) {
                if (isNotExistUser(userId))
                    return null;
                return new User() {
                    @Override
                    public List<Order> getAllOrders() {
                        return orders;
                    }
                };
            }
        });
    }

    private boolean isNotExistUser(String userId) {
        return !Objects.equals(userId, USER_ID);
    }

    @Test
    public void shouldCalculateSumOfAllSubmittedOrders() {

        Model model = new ModelStub();
        String amount = userReportController.getUserTotalOrderAmountView("123", model);

        assertEquals("userTotal", amount);
        assertEquals("User Total: 363.15$", model.getAttribute("userTotalMessage"));
    }

    @Test
    public void shouldGetWarningMessageWhenUserDoesntExist() {
        Model model = new ModelStub();
        String amount = userReportController.getUserTotalOrderAmountView("0001", model);

        assertEquals("userTotal", amount);
        assertEquals("WARNING: User ID doesn't exist.", model.getAttribute("userTotalMessage"));
    }

    @Test
    public void shouldGetErrorMessageWhenOrderHaveNegativeAmount() {

        orders.add(new SubmittedNegativeOrderStub());

        Model model = new ModelStub();
        String amount = userReportController.getUserTotalOrderAmountView("123", model);

        assertEquals("userTotal", amount);
        assertEquals("ERROR: Wrong order amount.", model.getAttribute("userTotalMessage"));
    }


    @Test
    public void shouldGetWarningMessageWhenUserHaveNoSubmittedOrders() {

        orders.clear();

        Model model = new ModelStub();
        String amount = userReportController.getUserTotalOrderAmountView("123", model);

        assertEquals("userTotal", amount);
        assertEquals("WARNING: User have no submitted orders.", model.getAttribute("userTotalMessage"));
    }

    @Test
    public void shouldRedirectToErrorPageWhenConnectionToDbIsNull() {

        userReportBuilder.setUserDao(null);

        String amount = userReportController.getUserTotalOrderAmountView("123", new ModelStub());

        assertEquals("technicalError", amount);
    }
}
