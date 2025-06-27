
package com.virtualjobfair.domain;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        testStudent();
        testRecruiter();
        testJobPosting();
        testApplication();

        System.out.println(" All domain model tests passed successfully.");
    }

    static void testStudent() {
        Student student = new Student(
                "S101",
                "Alice Johnson",
                "alice@example.com",
                "securePass123",
                "9876543210",
                "Computer Science",
                "/resumes/alice_johnson.pdf"
        );

        assert student.name().equals("Alice Johnson");
        assert student.email().equals("alice@example.com");
        assert student.resumePath().endsWith(".pdf");
    }

    static void testRecruiter() {
        Recruiter recruiter = new Recruiter(
                "R201",
                "Bob HR",
                "bob@techcorp.com",
                "bobSecure123",
                "HR Manager",
                "TechCorp Pvt Ltd",
                "https://www.techcorp.com",
                "A technology company specializing in AI solutions."
        );

        assert recruiter.email().contains("@");
        assert recruiter.companyName().equals("TechCorp Pvt Ltd");
        assert recruiter.name().equals("Bob HR");
    }

    static void testJobPosting() {
        Recruiter recruiter = new Recruiter(
                "R202",
                "Clara HR",
                "clara@innovate.com",
                "claraPass321",
                "Lead Recruiter",
                "Innovate Inc",
                "https://www.innovate.com",
                "We innovate smart solutions for tomorrow."
        );

        JobPosting job = new JobPosting(
                "J301",
                "Backend Java Intern",
                "Work on real-world Spring Boot projects.",
                "Bangalore",
                "Innovate Inc",
                recruiter,
                LocalDate.now()
        );

        assert job.title().contains("Java");
        assert job.companyName().equals("Innovate Inc");
        assert job.postedBy().name().equals("Clara HR");
    }

    static void testApplication() {
        Student student = new Student(
                "S102",
                "John Smith",
                "john.smith@example.com",
                "johnPass456",
                "9876501234",
                "Information Technology",
                "/resumes/john_smith.pdf"
        );

        Recruiter recruiter = new Recruiter(
                "R203",
                "Eva Recruiter",
                "eva@futuretech.com",
                "evaSecure789",
                "Recruiting Manager",
                "FutureTech",
                "https://futuretech.com",
                "We hire for tomorrow's technologies."
        );

        JobPosting job = new JobPosting(
                "J302",
                "Data Analyst Intern",
                "Analyze large datasets and visualize insights.",
                "Hyderabad",
                "FutureTech",
                recruiter,
                LocalDate.now()
        );

        Application application = new Application(
                "A401",
                student,
                job,
                "/resumes/john_smith.pdf",
                LocalDate.now()
        );

        assert application.applicant().name().equals("John Smith");
        assert application.job().title().equals("Data Analyst Intern");
        assert application.resumePath().endsWith(".pdf");
    }
}
