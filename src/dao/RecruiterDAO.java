package dao;

import model.Recruiter;
import dbconnection.DBConnection;
import java.sql.*;
import java.util.Optional;

/**
 * Data Access Object (DAO) for Recruiter operations.
 * This class provides methods to insert, retrieve, update, and delete a recruiter by ID.
 */
public class RecruiterDAO {

    public void insert(Recruiter r) {
        String sql = "INSERT INTO Recruiters (id, name, email, company_name, position) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.id());
            ps.setString(2, r.name());
            ps.setString(3, r.email());
            ps.setString(4, r.companyName());
            ps.setString(5, r.position());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Recruiter> getById(int id) {
        String sql = "SELECT * FROM Recruiters WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Recruiter(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("company_name"),
                    rs.getString("position")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(Recruiter r) {
        String sql = "UPDATE Recruiters SET name = ?, email = ?, company_name = ?, position = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.name());
            ps.setString(2, r.email());
            ps.setString(3, r.companyName());
            ps.setString(4, r.position());
            ps.setInt(5, r.id());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Recruiters WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}