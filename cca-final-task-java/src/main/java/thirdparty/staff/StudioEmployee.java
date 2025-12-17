package thirdparty.staff;

public abstract class StudioEmployee {

    private final String name;
    protected Long salary;
    protected Long earnedMoney;
    protected String role;

    public StudioEmployee(String name, Long initialSalary, String role) {
        this.name = name;
        this.salary = initialSalary;
        this.role = role;
        this.earnedMoney = 0L;
    }

    public Long getEarnedMoney() {
        return earnedMoney;
    }

    public String getName() {
        return name;
    }

    public Long getSalary() {
        return salary;
    }

    public void paySalary(Long paidSum) {
        this.earnedMoney += paidSum;
    }

    public String getRole() {
        return role;
    }

}
