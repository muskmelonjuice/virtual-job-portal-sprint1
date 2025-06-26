package com.virtualjobfair.domain;

import java.time.LocalDate;

public record JobPosting(
    String jobId,
    String title,
    String description,
    String location,
    Recruiter postedBy,
    LocalDate postedDate
) {}
