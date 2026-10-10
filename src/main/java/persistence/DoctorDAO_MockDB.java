package persistence;

import model.Doctor;
import repository.InMemory_MockDB;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAO_MockDB implements IDoctorDAO {

    InMemory_MockDB datastore = null;

    public DoctorDAO_MockDB() {
        this.datastore = InMemory_MockDB.getInstance();
    }

    @Override
    public Doctor findById(int id) {
        return datastore.getUsers().stream()
                .filter(u -> u instanceof Doctor && u.getId() == id)
                .map(u -> (Doctor) u)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        for (var u : datastore.getUsers())
            if (u instanceof Doctor d) doctors.add(d);
        return doctors;
    }

    @Override
    public void add(Doctor doctor) {
        int newId = datastore.getUsers().stream()
                .mapToInt(u -> u.getId()).max().orElse(0) + 1;
        doctor.setId(newId);
        datastore.getUsers().add(doctor);
    }

    @Override
    public void delete(int id) {
        datastore.getUsers().removeIf(u -> u instanceof Doctor && u.getId() == id);
    }
}
