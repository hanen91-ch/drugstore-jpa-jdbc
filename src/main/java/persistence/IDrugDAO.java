package persistence;
import model.Drug;
import java.util.List;

public interface IDrugDAO {
    Drug findById(int id);
    Drug findByName(String name);
    List<Drug> findAll();
    void       add(Drug drug);
    void       delete(int id);
}
