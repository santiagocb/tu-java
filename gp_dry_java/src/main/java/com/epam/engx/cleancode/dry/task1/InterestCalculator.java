package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.DateUtil;
import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;

    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return getInterest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return DateUtil.durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    /*
        Calculate interest depending on account age.
        If age >= 60, account is considered senior and calculated with that percentage. Formula = accountBalance * yearsOfActiveAccount * 5.5 / 100.
        If age < 60, calculated with that lesser interest percentage. Formula = accountBalance * yearsOfActiveAccount * 4.5 / 100.

     */
    private BigDecimal getInterest(AccountDetails accountDetails) {
        double interest = 0;
        if (AGE <= accountDetails.getAge()) {
            interest = calculateSeniorInterest(accountDetails);
        } else {
            interest = calculateNormalInterest(accountDetails);
        }
        return BigDecimal.valueOf(interest);
    }

    private Double calculateSeniorInterest(AccountDetails accountDetails) {
        return accountDetails.getBalance().doubleValue()
                * DateUtil.durationBetweenDatesInYears(accountDetails.getStartDate(), new Date()) * SENIOR_PERCENT / 100;
    }

    private Double calculateNormalInterest(AccountDetails accountDetails) {
        return accountDetails.getBalance().doubleValue()
                * DateUtil.durationBetweenDatesInYears(accountDetails.getStartDate(), new Date()) * INTEREST_PERCENT / 100;
    }
}
