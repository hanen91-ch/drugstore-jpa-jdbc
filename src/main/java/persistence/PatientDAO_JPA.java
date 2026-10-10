package persistence;

import model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class PatientDAO_JPA implements IPatientDAO {

    private EntityManager em;

    public PatientDAO_JPA() {
        this.em = EntityManagerProvider.getInstance().getEntityManager();
    }

    @Override
    public Patient findById(int id) {
        return em.find(Patient.class, id);
    }

    @Override
    public List<Patient> findAll() {
        Query query = em.createQuery(SQL_BOX.FIND_ALL_PATIENTS_JPQL, Patient.class);
        return query.getResultList();
    }

    @Override
    public void add(Patient patient) {
        try {
            em.getTransaction().begin();
            em.persist(patient);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(SQL_BOX.DELETE_PATIENT_JPQL);
            query.setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
