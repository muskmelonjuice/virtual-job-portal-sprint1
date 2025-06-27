package dao;

import model.JobPosting;
import dbconnection.DBConnection;
import java.sql.*;
import java.util.Optional;

/**
 * Data Access Object (DAO) for JobPosting operations.
 * This class provides methods to insert, retrieve, update, and delete a job posting by ID.
 */
public class JobPostingDAO {

    public void insert(JobPosting j) {
        String sql = "INSERT INTO JobPostings (job_id, recruiter_id, title, description, posted_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, j.jobId());
            ps.setInt(2, j.recruiterId());
            ps.setString(3, j.title());
            ps.setString(4, j.description());
            ps.setDate(5, Date.valueOf(j.postedDate()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<JobPosting> getById(int id) {
        String sql = "SELECT * FROM JobPostings WHERE job_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new JobPosting(
                    rs.getInt("job_id"),
                    rs.getInt("recruiter_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("posted_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(JobPosting j) {
        String sql = "UPDATE JobPostings SET recruiter_id = ?, title = ?, description = ?, posted_date = ? WHERE job_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, j.recruiterId());
            ps.setString(2, j.title());
            ps.setString(3, j.description());
            ps.setDate(4, Date.valueOf(j.postedDate()));
            ps.setInt(5, j.jobId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int jobId) {
        String sql = "DELETE FROM JobPostings WHERE job_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}