package org.isti.learning.springboot.business.interfaces;

public interface Database {
    String insertData(int id, String name);
    String getData();
    String createTable();
    String dropTable();
}
