package persistence;

import model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO_JDBC implements IDoctorDAO {
    private Connection connection;

    public DoctorDAO_JDBC() {
        this.connection = ConnectionProvider.getInstance().getConnection();
    }

    @Override
    public Doctor findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.FIND_DOCTOR_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("tel"),
                        rs.getString("skill"),
                        new ArrayList<>()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("DoctorDAO_JDBC.findById() : " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.FIND_ALL_DOCTORS)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("tel"),
                        rs.getString("skill"),
                        new ArrayList<>()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("DoctorDAO_JDBC.findAll() : " + e.getMessage(), e);
        }
        return doctors;
    }

    @Override
    public void add(Doctor doctor) {
        try (PreparedStatement stmt = connection.prepareStatement(
                SQL_BOX.ADD_DOCTOR, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getTel());
            stmt.setString(3, doctor.getSkill());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                doctor.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("DoctorDAO_JDBC.add() : " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.DELETE_DOCTOR)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DoctorDAO_JDBC.delete() : " + e.getMessage(), e);
        }
    }
}
