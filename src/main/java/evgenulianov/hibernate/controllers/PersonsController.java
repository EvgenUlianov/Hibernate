package evgenulianov.hibernate.controllers;

import evgenulianov.hibernate.PersonsService;
import evgenulianov.hibernate.model.Persons;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("persons")
public class PersonsController {

    private final PersonsService service;

    @GetMapping("/by-city")
    public List<Persons> getPersonsByCity(@RequestParam String city) {
        return service.getPersonsByCity(city);
    }

    @PostMapping("/save")
    public Persons save(@RequestBody Persons persons){
        return service.save(persons);
    }

    @PatchMapping ("/save")
    public Persons update(@RequestBody Persons persons){
        return service.save(persons);
    }


    @GetMapping("/by-age")
    public List<Persons> findByPersonIdAgeLessThanOrderByPersonIdAge(@RequestParam(name = "age") int age) {
        return service.findByPersonIdAgeLessThanOrderByPersonIdAge(age);
    }

    @GetMapping("/by-fullName")
    public ResponseEntity<Persons> findByPersonIdNameAndAndPersonIdSurname(
            @RequestParam(name = "name") String personIdName,
            @RequestParam(name = "surname") String personIdSurname) {
        Optional<Persons> result = service.findByPersonIdNameAndAndPersonIdSurname(personIdName, personIdSurname);

        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Persons persons){
        service.delete(persons);
    }

}
