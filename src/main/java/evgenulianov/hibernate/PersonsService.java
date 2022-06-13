package evgenulianov.hibernate;

import evgenulianov.hibernate.model.PersonId;
import evgenulianov.hibernate.model.Persons;
import evgenulianov.hibernate.repositories.PersonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonsService {

    private final PersonsRepository repository;

    public Persons save(Persons persons){
        repository.save(persons);
        return persons;
    }

    public void delete(Persons persons){
        repository.delete(persons);
    }

    public Optional<Persons> get(PersonId id){
        return repository.findById(id);
    }

    public List<Persons> getPersonsByCity(String city){
        return repository.find(city);
    }

    public List<Persons> findByPersonIdAgeLessThanOrderByPersonIdAge(int personIdAgeLessThan){
        return repository.find(personIdAgeLessThan);
    }

    public List<Persons> findByPersonIdNameAndAndPersonIdSurname(String personIdName, String personIdSurname){
        return repository.find(personIdName, personIdSurname);
    }

    public Optional<Persons> findByPersonIdNameAndAndPersonIdSurname(String personIdName, String personIdSurname, int personIdAge){
        return repository.find(personIdName, personIdSurname, personIdAge);
    }

}
