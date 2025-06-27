-- 1. Users table (central login table)
CREATE TABLE users (
    user_id     VARCHAR2(50) PRIMARY KEY,
    name        VARCHAR2(100) NOT NULL,
    email       VARCHAR2(100) UNIQUE NOT NULL,
    password    VARCHAR2(255) NOT NULL,
    user_type   VARCHAR2(20) CHECK (user_type IN ('STUDENT', 'RECRUITER')),
    created_at  DATE DEFAULT SYSDATE
);

-- 2. Students table
CREATE TABLE students (
    student_id      VARCHAR2(50) PRIMARY KEY,
    name            VARCHAR2(100) NOT NULL,
    email           VARCHAR2(100) UNIQUE NOT NULL,
    password        VARCHAR2(255) NOT NULL,
    phone_number    VARCHAR2(20),
    major           VARCHAR2(100),
    resume_path     VARCHAR2(255),
    FOREIGN KEY (student_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 3. Recruiters table
CREATE TABLE recruiters (
    recruiter_id         VARCHAR2(50) PRIMARY KEY,
    name                 VARCHAR2(100) NOT NULL,
    email                VARCHAR2(100) UNIQUE NOT NULL,
    password             VARCHAR2(255) NOT NULL,
    designation          VARCHAR2(100),
    company_name         VARCHAR2(100) NOT NULL,
    company_website      VARCHAR2(255),
    company_description  CLOB,
    FOREIGN KEY (recruiter_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 4. Job Postings table
CREATE TABLE job_postings (
    job_id           VARCHAR2(50) PRIMARY KEY,
    title            VARCHAR2(100) NOT NULL,
    description      CLOB,
    location         VARCHAR2(100),
    company_name     VARCHAR2(100),
    posted_by        VARCHAR2(50) NOT NULL,
    posted_date      DATE DEFAULT SYSDATE,
    FOREIGN KEY (posted_by) REFERENCES recruiters(recruiter_id) ON DELETE CASCADE
);

-- 5. Applications table
CREATE TABLE applications (
    application_id   VARCHAR2(50) PRIMARY KEY,
    student_id       VARCHAR2(50) NOT NULL,
    job_id           VARCHAR2(50) NOT NULL,
    resume_path      VARCHAR2(255),
    applied_date     DATE DEFAULT SYSDATE,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (job_id) REFERENCES job_postings(job_id) ON DELETE CASCADE
);

--===========================================================================================================================================================================================================--
---===================================================================================  MOCK DATA VALIDATION FOR THE ABOVE TABLES ============================================================================--
--=========================================================================================                                    ===============================================================================--
--                                                                                        =======================================

--======================--Inserting Mock data into users--=====================--
------ USERS TABLE----------
INSERT INTO users (user_id, name, email, password, user_type) VALUES 
('stu001', 'Aarav Mehta', 'aarav@student.com', 'pass123', 'STUDENT');

INSERT INTO users (user_id, name, email, password, user_type) VALUES 
('stu002', 'Isha Reddy', 'isha@student.com', 'pass456', 'STUDENT');

INSERT INTO users (user_id, name, email, password, user_type) VALUES 
('rec001', 'Devansh Rao', 'devansh@company.com', 'recruit123', 'RECRUITER');

INSERT INTO users (user_id, name, email, password, user_type) VALUES 
('rec002', 'Sana Kapoor', 'sana@startup.com', 'recruit456', 'RECRUITER');



--======================--Inserting Mock data into students--=====================--
INSERT INTO students (student_id, name, email, password, phone_number, major, resume_path) VALUES
('stu001', 'Aarav Mehta', 'aarav@student.com', 'pass123', '9876543210', 'Computer Science', 'resumes/aarav.pdf');

INSERT INTO students (student_id, name, email, password, phone_number, major, resume_path) VALUES
('stu002', 'Isha Reddy', 'isha@student.com', 'pass456', '9876501234', 'Data Science', 'resumes/isha.pdf');




--======================--Inserting Mock data into recruiters--=====================--
INSERT INTO recruiters (recruiter_id, name, email, password, designation, company_name, company_website, company_description) VALUES
('rec001', 'Devansh Rao', 'devansh@company.com', 'recruit123', 'HR Manager', 'TechNova', 'https://technova.com', 'We build scalable tech solutions.');

INSERT INTO recruiters (recruiter_id, name, email, password, designation, company_name, company_website, company_description) VALUES
('rec002', 'Sana Kapoor', 'sana@startup.com', 'recruit456', 'Talent Head', 'StartSmart', 'https://startsmart.io', 'Empowering startups through smart hiring.');



--======================--Inserting Mock data into jobpostings--=====================--
INSERT INTO job_postings (job_id, title, description, location, company_name, posted_by) VALUES
('job101', 'Java Developer', 'Looking for a Java backend developer', 'Bangalore', 'TechNova', 'rec001');

INSERT INTO job_postings (job_id, title, description, location, company_name, posted_by) VALUES
('job102', 'Data Analyst Intern', 'Internship for Data Analysis', 'Hyderabad', 'StartSmart', 'rec002');

INSERT INTO job_postings (job_id, title, description, location, company_name, posted_by) VALUES
('job103', 'Frontend Engineer', 'React-based frontend dev role', 'Remote', 'TechNova', 'rec001');



--======================--Inserting Mock data into application--=====================--
INSERT INTO applications (application_id, student_id, job_id, resume_path) VALUES
('app001', 'stu001', 'job101', 'resumes/aarav.pdf');

INSERT INTO applications (application_id, student_id, job_id, resume_path) VALUES
('app002', 'stu001', 'job102', 'resumes/aarav.pdf');

INSERT INTO applications (application_id, student_id, job_id, resume_path) VALUES
('app003', 'stu002', 'job101', 'resumes/isha.pdf');

INSERT INTO applications (application_id, student_id, job_id, resume_path) VALUES
('app004', 'stu002', 'job103', 'resumes/isha.pdf');




--============================== Validating Queries ===================================--

--========= Mapping queries between students and thier application ============--
SELECT s.name AS student_name, j.title AS job_applied, a.applied_date
FROM applications a
JOIN students s ON a.student_id = s.student_id
JOIN job_postings j ON a.job_id = j.job_id;



--======================= All jobs posted by each recruiter =============================--
SELECT r.name AS recruiter_name, j.title AS job_title, j.location
FROM job_postings j
JOIN recruiters r ON j.posted_by = r.recruiter_id;


--======================= Couting applications count per job ===========================--
SELECT j.title, COUNT(a.application_id) AS total_applications
FROM job_postings j
LEFT JOIN applications a ON j.job_id = a.job_id
GROUP BY j.title;

