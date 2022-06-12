package evgenulianov.hibernate;

import evgenulianov.hibernate.model.Persons;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class PersonsService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Persons> getPersonsByCity(String city) {
        Query query = entityManager.createQuery(
                "select p from Persons as p where p.cityOfLiving = :city",
                Persons.class);
        query.setParameter("city", city);
        return query.getResultList();
    }

    @Transactional
    public Persons save(Persons persons){
//        entityManager.getTransaction().begin();
        entityManager.persist(persons);
//        entityManager.getTransaction().commit();
        return persons;
    }

}
