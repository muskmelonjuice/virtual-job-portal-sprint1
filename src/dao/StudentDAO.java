package dao;

import model.Student;
import dbconnection.DBConnection;
import java.sql.*;
import java.util.Optional;

/**
 * Data Access Object (DAO) for Student operations.
 * This class provides methods to insert, retrieve, update, and delete a student by ID.
 */
public class StudentDAO {

    public void insert(Student s) {
        String sql = "INSERT INTO Students (id, name, email, university, resume_url) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.id());
            ps.setString(2, s.name());
            ps.setString(3, s.email());
            ps.setString(4, s.university());
            ps.setString(5, s.resumeUrl());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Student> getById(int id) {
        String sql = "SELECT * FROM Students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("university"),
                    rs.getString("resume_url")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(Student s) {
        String sql = "UPDATE Students SET name = ?, email = ?, university = ?, resume_url = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.name());
            ps.setString(2, s.email());
            ps.setString(3, s.university());
            ps.setString(4, s.resumeUrl());
            ps.setInt(5, s.id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}