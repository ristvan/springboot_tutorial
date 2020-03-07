package org.isti.learning.springboot.business.interfaces;

import org.isti.learning.springboot.business.domain.Person;

import java.util.List;

public interface Database {
    String insertData(int id, String name);
    List<Person> getData();
    String createTable();
    String dropTable();
}
