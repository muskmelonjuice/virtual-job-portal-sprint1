package com.virtualjobfair.domain;

import java.time.LocalDate;

public record Application(
    String applicationId,
    Student applicant,
    JobPosting job,
    String status,
    LocalDate appliedDate
) {}
