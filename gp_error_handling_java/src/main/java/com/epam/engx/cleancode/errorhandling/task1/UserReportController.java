package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.errors.TechnicalExpection;
import com.epam.engx.cleancode.errorhandling.task1.errors.UserException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

public class UserReportController {

    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model){
        String totalMessage;
        try {
            totalMessage = getUserTotalMessage(userId);
        } catch (TechnicalExpection e) {
            return "technicalError";
        }

        model.addAttribute("userTotalMessage", totalMessage);
        return "userTotal";
    }

    private String getUserTotalMessage(String userId) {
        double amount;
        try {
            amount = userReportBuilder.getUserTotalOrderAmount(userId);
        } catch (UserException e) {
            return e.getMessage();
        }

        return "User Total: " + amount + "$";
    }


    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
