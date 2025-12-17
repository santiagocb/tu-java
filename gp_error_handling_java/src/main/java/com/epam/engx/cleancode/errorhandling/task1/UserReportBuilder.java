package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.errors.EmptyUserOrdersException;
import com.epam.engx.cleancode.errorhandling.task1.errors.UserDaoNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.errors.UserNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.errors.WrongOrderAmountException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

public class UserReportBuilder {

    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId) {

        if (userDao == null)
            throw new UserDaoNotFoundException("technicalError");

        User user = userDao.getUser(userId);
        if (user == null)
            throw new UserNotFoundException("WARNING: User ID doesn't exist.");

        List<Order> orders = user.getAllOrders();

        if (orders.isEmpty())
            throw new EmptyUserOrdersException("WARNING: User have no submitted orders.");

        double sum = 0.0;
        for (Order order : orders) {

            if (order.isSubmitted()) {
                Double total = order.total();
                if (total < 0)
                    throw new WrongOrderAmountException("ERROR: Wrong order amount.");
                sum += total;
            }
        }

        return sum;
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
