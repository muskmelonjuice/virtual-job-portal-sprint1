
package com.virtualjobfair.domain;

import java.time.LocalDate;

public record Application(
        String applicationId,     //  Primary Key
        Student applicant,        //  Foreign Key
        JobPosting job,           //  Foreign Key
        String resumePath,        //  File upload
        LocalDate appliedDate     //  For logs/history
) {}
