package model;

import java.time.LocalDate;

public record JobPosting(
    int jobId,
    int recruiterId,
    String title,
    String description,
    LocalDate postedDate
) {}
