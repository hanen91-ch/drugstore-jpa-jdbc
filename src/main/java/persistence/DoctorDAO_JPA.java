package persistence;

import model.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class DoctorDAO_JPA implements IDoctorDAO {

    private EntityManager em;

    public DoctorDAO_JPA() {
        this.em = EntityManagerProvider.getInstance().getEntityManager();
    }

    @Override
    public Doctor findById(int id) {
        return em.find(Doctor.class, id);
    }

    @Override
    public List<Doctor> findAll() {
        Query query = em.createQuery(SQL_BOX.FIND_ALL_DOCTORS_JPQL, Doctor.class);
        return query.getResultList();
    }

    @Override
    public void add(Doctor doctor) {
        try {
            em.getTransaction().begin();
            em.persist(doctor);
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
            Query query = em.createQuery(SQL_BOX.DELETE_DOCTOR_JPQL);
            query.setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
