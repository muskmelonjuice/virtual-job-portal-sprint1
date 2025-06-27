package model;

public final class Recruiter implements User {
    private final int id;
    private final String name;
    private final String email;
    private final String companyName;
    private final String position;

    public Recruiter(int id, String name, String email, String companyName, String position) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.position = position;
    }

    public int id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    public String companyName() {
        return companyName;
    }

    public String position() {
        return position;
    }
}
