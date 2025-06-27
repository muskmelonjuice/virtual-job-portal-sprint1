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
