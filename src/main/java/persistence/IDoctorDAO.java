package persistence;

import model.Doctor;
import java.util.List;

public interface IDoctorDAO {
    Doctor findById(int id);
    List<Doctor> findAll();
    void         add(Doctor doctor);
    void         delete(int id);
}
