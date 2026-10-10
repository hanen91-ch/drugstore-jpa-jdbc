package persistence;

import model.Drug;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugDAO_JDBC implements IDrugDAO {

    private Connection connection;

    public DrugDAO_JDBC(){
        this.connection = ConnectionProvider.getInstance().getConnection();
    }

    @Override
    public Drug findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.FIND_DRUG_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Drug findByName(String name) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.FIND_DRUG_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Drug> findAll() {
        List<Drug> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.FIND_ALL_DRUGS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public void add(Drug drug) {
        try (PreparedStatement stmt = connection.prepareStatement(
                SQL_BOX.ADD_DRUG, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, drug.getName());
            stmt.setDouble(2, drug.getStandardDosePerkg());
            stmt.setString(3, drug.getInteractionDrug());
            stmt.setDouble(4, drug.getInteractionFactor());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) drug.setId(keys.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_BOX.DELETE_DRUG)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Drug mapRow(ResultSet rs) throws SQLException {
        return new Drug(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("standard_dose_per_kg"),
                rs.getString("interaction_drug"),
                rs.getDouble("interaction_factor")
        );
    }


}
