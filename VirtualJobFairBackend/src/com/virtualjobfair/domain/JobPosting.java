
package com.virtualjobfair.domain;

import java.time.LocalDate;

public record JobPosting(
        String jobId,              //  Primary Key
        String title,
        String description,
        String location,
        String companyName,
        Recruiter postedBy,        //  Foreign Key
        LocalDate postedDate       //  Useful for sorting/filtering
) {}
