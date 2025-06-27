package org.example.jobportallogging.logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

    private static final Logger logger = LoggerFactory.getLogger(AppLogger.class);

    public static void logLogin(String username, String ipAddress) {
        logger.info("USER_LOGIN: User '{}' logged in from IP: {}", username, ipAddress);
    }

    public static void logJobApplication(String studentId, String jobId) {
        logger.info("JOB_APPLICATION: Student '{}' applied for Job ID: {}", studentId, jobId);
    }

    public static void logError(String errorMessage, Exception e) {

        logger.error("APPLICATION_ERROR: {}. Details: {}", errorMessage, e.getMessage(), e);
    }

    public static void main(String[] args) {
        System.out.println("Starting application logging example...");

        AppLogger.logLogin("jane.smith@example.com", "203.0.113.45");
        AppLogger.logJobApplication("S98765", "J1234");


        try {
            int dividend = 10;
            int divisor = 0;
            int result = dividend / divisor;
        } catch (Exception e) {
            AppLogger.logError("Failed to perform a critical calculation.", e);
        }

        AppLogger.logLogin("recruiter.xyz@example.com", "198.51.100.20");

        System.out.println("Finished application logging example. Check IntelliJ console and project's 'logs' folder.");
    }
}
