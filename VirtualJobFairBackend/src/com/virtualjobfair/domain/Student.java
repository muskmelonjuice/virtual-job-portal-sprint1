package com.virtualjobfair.domain;

public record Student(
    String name,
    String email,
    String college,
    String degree,
    String resumeLink
) implements User {}
