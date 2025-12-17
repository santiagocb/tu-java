package com.industry.hollywood.finance;

import com.industry.hollywood.staff.team.StudioStaff;
import thirdparty.Roles;
import thirdparty.service.BudgetIsOverException;
import thirdparty.staff.StudioEmployee;

import java.util.List;

public class BudgetManagementService implements FinancialService {
    private Long currentBudget;

    @Override
    public void initializeBudget(Long initialSum) {
        currentBudget = initialSum;
    }

    @Override
    public void paySalaries(List<StudioEmployee> employees) throws BudgetIsOverException {
        for (StudioEmployee employee : employees) {
            if (currentBudget < employee.getSalary()) {
                throw new BudgetIsOverException("Budget exceeded!");
            }
            currentBudget -= employee.getSalary();
            employee.paySalary(employee.getSalary());
        }
    }

    @Override
    public Long getAvailableBudget() {
        return currentBudget;
    }

    @Override
    public long calculateUnhiredBudget(StudioStaff staff, int daysInProduction) {
        return daysInProduction * (
                staff.getActorsCollection().size() * Roles.ACTOR.proposedSalary +
                        staff.getCameramanCollection().size() * Roles.CAMERA_MAN.proposedSalary
        );
    }
}
