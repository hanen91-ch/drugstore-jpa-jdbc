package persistence;
import model.Patient;
import java.util.List;

public interface IPatientDAO {
    Patient findById(int id);
    List<Patient> findAll();
    void          add(Patient patient);
    void          delete(int id);
}
