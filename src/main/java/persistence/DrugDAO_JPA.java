package persistence;

import model.Drug;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class DrugDAO_JPA implements IDrugDAO {

    private EntityManager em;

    public DrugDAO_JPA() {
        this.em = EntityManagerProvider.getInstance().getEntityManager();
    }

    @Override
    public Drug findById(int id) {
        return em.find(Drug.class, id);
    }

    @Override
    public Drug findByName(String name) {
        Query query = em.createQuery(SQL_BOX.FIND_DRUG_BY_NAME_JPQL, Drug.class);
        query.setParameter("name", name);
        return (Drug) query.getResultList()
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<Drug> findAll() {
        Query query = em.createQuery(SQL_BOX.FIND_ALL_DRUGS_JPQL, Drug.class);
        return query.getResultList();
    }

    @Override
    public void add(Drug drug) {
        try {
            em.getTransaction().begin();
            em.persist(drug);
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
            Query query = em.createQuery(SQL_BOX.DELETE_DRUG_JPQL);
            query.setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
