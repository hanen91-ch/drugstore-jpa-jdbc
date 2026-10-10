package persistence;

import model.Drug;
import persistence.IDrugDAO;
import repository.InMemory_MockDB;

import java.util.ArrayList;
import java.util.List;

public class DrugDAO_MockDB implements IDrugDAO {

    InMemory_MockDB datastore = null;

    public DrugDAO_MockDB() {
        this.datastore = InMemory_MockDB.getInstance();
    }

    @Override
    public Drug findById(int id) {
        return datastore.getDrugs()
                . stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Drug findByName(String name) {
        return datastore.getDrugs()
                .stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Drug> findAll() {
        return new ArrayList<>(datastore.getDrugs());
    }

    @Override
    public void add(Drug drug) {
        int newId = datastore.getDrugs().stream()
                .mapToInt(Drug::getId).max().orElse(0) + 1;
        drug.setId(newId);
        datastore.getDrugs().add(drug);
    }

    @Override
    public void delete(int id) {
        datastore.getDrugs().removeIf(d -> d.getId() == id);
    }
}
