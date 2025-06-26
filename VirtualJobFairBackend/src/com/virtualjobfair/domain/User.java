package com.virtualjobfair.domain;

public sealed interface User permits Student, Recruiter {
    String name();
    String email();
}
