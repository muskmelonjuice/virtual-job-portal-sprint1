package com.virtualjobfair.domain;

public record Recruiter(
    String name,
    String email,
    String company,
    String designation
) implements User {}
