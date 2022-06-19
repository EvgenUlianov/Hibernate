package evgenulianov.hibernate.controllers;

import evgenulianov.hibernate.PersonsService;
import evgenulianov.hibernate.model.Persons;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("persons")
public class PersonsController {

    private final PersonsService service;

    @GetMapping("/by-city")
    @RolesAllowed({"PERSONS_READ"})
    public List<Persons> getPersonsByCity(@RequestParam String city) {
        return service.getPersonsByCity(city);
    }

    @PostMapping("/save")
    @RolesAllowed({"PERSONS_WRITE"})
    public Persons save(@RequestBody Persons persons){
        return service.save(persons);
    }

    @PatchMapping ("/save")
    @RolesAllowed({"PERSONS_WRITE"})
    public Persons update(@RequestBody Persons persons){
        return service.save(persons);
    }


    @GetMapping("/by-age")
    @RolesAllowed({"PERSONS_READ"})
    public List<Persons> findByPersonIdAgeLessThanOrderByPersonIdAge(@RequestParam(name = "age") int age) {
        return service.findByPersonIdAgeLessThanOrderByPersonIdAge(age);
    }

    @GetMapping("/by-fullName")
    @RolesAllowed({"PERSONS_READ"})
    public List<Persons> findByPersonIdNameAndAndPersonIdSurname(
            @RequestParam(name = "name") String personIdName,
            @RequestParam(name = "surname") String personIdSurname) {
        return service.findByPersonIdNameAndAndPersonIdSurname(personIdName, personIdSurname);
    }

    @GetMapping("/by-fullNameAge")
    @RolesAllowed({"PERSONS_READ"})
    public ResponseEntity<Persons> findByPersonIdNameAndAndPersonIdSurname(
            @RequestParam(name = "name") String personIdName,
            @RequestParam(name = "surname") String personIdSurname,
            @RequestParam(name = "age") int personIdAge) {
        Optional<Persons> result = service.findByPersonIdNameAndAndPersonIdSurname(personIdName, personIdSurname, personIdAge);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @RolesAllowed({"PERSONS_DELETE"})
    public void delete(@RequestBody Persons persons){
        service.delete(persons);
    }

}
