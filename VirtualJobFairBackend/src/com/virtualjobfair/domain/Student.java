
package com.virtualjobfair.domain;


public record Student(
        String studentId,       //  Primary Key
        String name,
        String email,
        String password,        //  Required for login
        String phoneNumber,     //  Present in apply.html
        String major,           //  From studentregister.html
        String resumePath       //  For job application
) implements User {}
