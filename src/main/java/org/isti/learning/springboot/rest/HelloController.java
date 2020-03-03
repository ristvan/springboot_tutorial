package org.isti.learning.springboot.rest;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.isti.learning.springboot.business.interfaces.Database;
import org.isti.learning.springboot.database.DatabaseHandler;
import org.isti.learning.springboot.rest.dto.PersonDto;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.*;

@RestController
public class HelloController {
    private final Database dbHandler = new DatabaseHandler();

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/db-insert")
    public String database(@RequestParam("id") int id, @RequestParam("name") String name) {
        return dbHandler.insertData(id, name);
    }

    @GetMapping("/db-get")
    public @ResponseBody() List<PersonDto> readDatabase() {
        List<PersonDto> people = new LinkedList<>();
        dbHandler.getData().forEach(item-> {
            PersonDto person = new PersonDto();
            person.id = item.getId();
            person.name = item.getName();
            people.add(person);
        });
        return people;
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
        Random random = new Random();
        person.id = random.nextInt(2000) + id;
        byte[] array = new byte[7]; // length is bounded by 7
        String randomName = "";
        for (int i = 0; i < 7; i++)
            randomName += (char)(random.nextInt() % ('Z'-'A' + 1) + 'a');
        person.name = randomName;
        return person;
    }
}
