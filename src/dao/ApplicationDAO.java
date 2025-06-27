package dao;

import model.Application;
import model.ApplicationStatus;
import dbconnection.DBConnection;
import java.sql.*;
import java.util.Optional;

/**
 * Data Access Object (DAO) for Application operations.
 * This class provides methods to insert, retrieve, update, and delete an application by ID.
 */ 

public class ApplicationDAO {

    public void insert(Application app) {
        String sql = "INSERT INTO Applications (application_id, job_id, student_id, status) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, app.applicationId());
            ps.setInt(2, app.jobId());
            ps.setInt(3, app.studentId());
            ps.setString(4, app.status().name());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Application> getById(int id) {
        String sql = "SELECT * FROM Applications WHERE application_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Application(
                    rs.getInt("application_id"),
                    rs.getInt("job_id"),
                    rs.getInt("student_id"),
                    ApplicationStatus.valueOf(rs.getString("status"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(Application app) {
        String sql = "UPDATE Applications SET job_id = ?, student_id = ?, status = ? WHERE application_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, app.jobId());
            ps.setInt(2, app.studentId());
            ps.setString(3, app.status().name());
            ps.setInt(4, app.applicationId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int applicationId) {
        String sql = "DELETE FROM Applications WHERE application_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, applicationId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}