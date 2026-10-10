package persistence;

import model.Patient;
import repository.InMemory_MockDB;

import java.util.ArrayList;
import java.util.List;

public class PatientDAO_MockDB implements IPatientDAO {

    InMemory_MockDB datastore = null;

    public PatientDAO_MockDB() {
        this.datastore = InMemory_MockDB.getInstance();
    }

    @Override
    public Patient findById(int id) {
        return datastore.getUsers().stream()
                .filter(u -> u instanceof Patient && u.getId() == id)
                .map(u -> (Patient) u)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        for (var u : datastore.getUsers())
            if (u instanceof Patient p) patients.add(p);
        return patients;
    }

    @Override
    public void add(Patient patient) {
        int newId = datastore.getUsers().stream()
                .mapToInt(u -> u.getId()).max().orElse(0) + 1;
        patient.setId(newId);
        datastore.getUsers().add(patient);
    }

    @Override
    public void delete(int id) {
        datastore.getUsers().removeIf(u -> u instanceof Patient && u.getId() == id);
    }
}
