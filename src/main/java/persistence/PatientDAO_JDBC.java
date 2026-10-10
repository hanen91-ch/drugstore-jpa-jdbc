package persistence;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO_JDBC implements IPatientDAO {

    private Connection connection;

    public PatientDAO_JDBC() {
        this.connection = ConnectionProvider.getInstance().getConnection();
    }

    @Override
    public Patient findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.FIND_PATIENT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.FIND_ALL_PATIENTS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void add(Patient patient) {
        try (PreparedStatement stmt = connection.prepareStatement(
                SQL_BOX.ADD_PATIENT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getTel());
            stmt.setInt(3, patient.getWeight());
            stmt.setInt(4, patient.getAge());
            stmt.setString(5, patient.getAllergies());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) patient.setId(keys.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.DELETE_PATIENT)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Patient mapRow(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("tel"),
                rs.getInt("weight"),
                rs.getInt("age"),
                rs.getString("allergies"),
                new ArrayList<>()
        );
    }
}
