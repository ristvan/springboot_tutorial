package org.isti.learning.springboot.rest;

import org.isti.learning.springboot.business.interfaces.Database;
import org.isti.learning.springboot.database.DatabaseHandler;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

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
    public String readDatabase() {
        return dbHandler.getData();
    }

    @PostMapping("/db-create")
    public String createTable() {
        return dbHandler.createTable();
    }

    @DeleteMapping("/db-drop")
    public String dropTable() {
        return dbHandler.dropTable();
    }
}
