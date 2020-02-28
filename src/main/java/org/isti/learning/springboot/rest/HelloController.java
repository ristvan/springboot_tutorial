package org.isti.learning.springboot.rest;

import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
public class HelloController {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    public static final String SQLEXCEPTION_DURING_CLOSING_MESSAGE = "SQLException during closing message %s\n";
    public static final String SQLEXCEPTION_MESSAGE = "SQLException Message %s\n";
    public static final String EXCEPTION_MESSAGE = "Exception Message %s\n";

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/db-insert")
    public String database(@RequestParam("id") int id, @RequestParam("name") String name) {
        Connection connection;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            return e.getMessage();
        }
        Statement stmt;
        String result = "";
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("INSERT INTO PERSON(id, name) VALUES("+id+", '"+name+"')");
            result = String.format("Information is recorded int db (%d, '%s')", id, name);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            result += String.format(SQLEXCEPTION_MESSAGE, e.getLocalizedMessage());
        } catch (Exception e) {
            result += String.format(EXCEPTION_MESSAGE, e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                result += String.format(SQLEXCEPTION_DURING_CLOSING_MESSAGE, e.getMessage());
            }
        }
        return result;
    }

    @GetMapping("/db-get")
    public String readDatabase() {
        Connection connection;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            return e.getMessage();
        }
        Statement stmt;
        String result = "";
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            try (ResultSet rs = stmt.executeQuery("select * from PERSON")) {
                while (rs.next()) {
                    result += String.format("Id %d Name %s\n", rs.getInt("id"), rs.getString("name"));
                }
            }
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            result += String.format(SQLEXCEPTION_MESSAGE, e.getLocalizedMessage());
        } catch (Exception e) {
            result += String.format(EXCEPTION_MESSAGE, e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                result += String.format(SQLEXCEPTION_DURING_CLOSING_MESSAGE, e.getMessage());
            }
        }
        return result;
    }

    @PostMapping("/db-create")
    public String createTable() {
        Connection connection;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            return e.getMessage();
        }
        Statement stmt;
        String result = "";
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
            result += "'PERSON' table is created\n";
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            result += String.format(SQLEXCEPTION_MESSAGE, e.getLocalizedMessage());
        } catch (Exception e) {
            result += String.format(EXCEPTION_MESSAGE, e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                result += String.format(SQLEXCEPTION_DURING_CLOSING_MESSAGE, e.getMessage());
            }
        }
        return result;
    }

    @RequestMapping("/db-drop")
    public String dropTable() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            return e.getMessage();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            return e.getMessage();
        }
        Statement stmt = null;
        String result = "";
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("DROP TABLE PERSON");
            result += "'PERSON' table is dropped\n";
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            result += String.format(SQLEXCEPTION_MESSAGE, e.getLocalizedMessage());
        } catch (Exception e) {
            result += String.format(EXCEPTION_MESSAGE, e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                result += String.format(SQLEXCEPTION_DURING_CLOSING_MESSAGE, e.getMessage());
            }
        }
        return result;
    }
}
