package com.virtualjobfair.domain;

public record Recruiter(
        String recruiterId,       // ✅ Primary Key
        String name,
        String email,
        String password,          // ✅ For login
        String designation,       // ✅ From recruiter form
        String companyName,
        String companyWebsite,
        String companyDescription
) implements User {}

