package model;

public record Application(
    int applicationId,
    int jobId,
    int studentId,
    ApplicationStatus status
) {}
