package thirdparty;

public enum Roles {

    CAMERA_MAN("cameraman", 6000L),
    ACTOR("actor", 50000L),
    SUPERSTAR("superstar", 100000L),
    RECRUITER("recruiter", 2000L),
    ACCOUNTANT("accountant", 2500L);

    public final String role;
    public final Long proposedSalary;

    Roles(String role, Long proposedSalary) {
        this.role = role;
        this.proposedSalary = proposedSalary;
    }
}
