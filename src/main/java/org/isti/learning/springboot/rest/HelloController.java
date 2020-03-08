package org.isti.learning.springboot.rest;

import org.isti.learning.springboot.business.interfaces.Database;
import org.isti.learning.springboot.database.DatabaseHandler;
import org.isti.learning.springboot.rest.dto.GetRequestAnswer;
import org.isti.learning.springboot.rest.dto.PersonDto;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class HelloController {
    private final Database dbHandler = new DatabaseHandler();
    private Random random = new Random();

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/db-insert")
    public String database(@RequestParam("id") int id, @RequestParam("name") String name) {
        return dbHandler.insertData(id, name);
    }

    @GetMapping("/db-get")
    public @ResponseBody() GetRequestAnswer readDatabase() {
        GetRequestAnswer answer = new GetRequestAnswer();
        dbHandler.getData().forEach(item-> {
            PersonDto person = new PersonDto();
            person.id = item.getId();
            person.name = item.getName();
            answer.addPerson(person);
        });
        return answer;
    }

    @PostMapping("/db-create")
    public String createTable() {
        return dbHandler.createTable();
    }

    @DeleteMapping("/db-drop")
    public String dropTable() {
        return dbHandler.dropTable();
    }

    @GetMapping("db/test/{id}")
    public PersonDto getPerson(@PathVariable(value = "id") int id) {
        PersonDto person = new PersonDto();
        person.id = random.nextInt(2000) + id;
        StringBuilder randomName = new StringBuilder();
        for (int i = 0; i < 7; i++)
            randomName.append((char)(random.nextInt() % ('Z'-'A' + 1) + 'a'));
        person.name = randomName.toString();
        return person;
    }
}
