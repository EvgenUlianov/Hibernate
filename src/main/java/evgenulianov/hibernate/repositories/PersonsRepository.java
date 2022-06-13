package evgenulianov.hibernate.repositories;

import evgenulianov.hibernate.model.PersonId;
import evgenulianov.hibernate.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, PersonId> {

    @Query(value = "SELECT p FROM Persons p WHERE p.cityOfLiving = ?1")
    List<Persons> find(String city);

    @Query(value = "SELECT p FROM Persons p WHERE p.personId.age < ?1 ORDER BY p.personId.age")
    List<Persons> find(int personIdAgeLessThan);

    @Query(value = "SELECT  p FROM Persons p WHERE p.personId.name = ?1 and p.personId.surname = ?2")
    List<Persons> find(String personIdName, String personIdSurname);

    @Query(value = "SELECT  p FROM Persons p WHERE p.personId.name = ?1 and p.personId.surname = ?2 and p.personId.age = ?3 ")
    Optional<Persons> find(String personIdName, String personIdSurname, int personIdAge);
}
