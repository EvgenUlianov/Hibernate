package evgenulianov.hibernate.controllers;

import evgenulianov.hibernate.PersonsService;
import evgenulianov.hibernate.model.Persons;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//        return persons;
    }



}
