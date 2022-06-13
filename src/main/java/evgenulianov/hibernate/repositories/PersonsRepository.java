package evgenulianov.hibernate.repositories;

import evgenulianov.hibernate.model.PersonId;
import evgenulianov.hibernate.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, PersonId> {

    List<Persons> findByCityOfLiving(String city);

    List<Persons> findByPersonIdAgeLessThanOrderByPersonIdAge(int personIdAgeLessThan);

    Optional<Persons> findByPersonIdNameAndAndPersonIdSurname(String personIdName, String personIdSurname);
}
