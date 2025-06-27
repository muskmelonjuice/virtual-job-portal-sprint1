package model;

public final class Student implements User {
    private final int id;
    private final String name;
    private final String email;
    private final String university;
    private final String resumeUrl;

    public Student(int id, String name, String email, String university, String resumeUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.university = university;
        this.resumeUrl = resumeUrl;
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

    public String university() {
        return university;
    }

    public String resumeUrl() {
        return resumeUrl;
    }
}
