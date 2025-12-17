package com.industry.hollywood.finance;

import com.industry.hollywood.staff.team.StudioStaff;
import thirdparty.service.BudgetIsOverException;
import thirdparty.staff.StudioEmployee;

import java.util.List;

public interface FinancialService {

    void initializeBudget(Long initialSum);

    void paySalaries(List<StudioEmployee> employees) throws BudgetIsOverException;

    Long getAvailableBudget();

    long calculateUnhiredBudget(StudioStaff staff, int daysInProduction);
}
