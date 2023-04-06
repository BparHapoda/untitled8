package org.example;

public enum Entities {
    DEPARTMENTS("Department"),EMPLOYEES("Employee"),
    OPERATIONS("Operation");

    String entityName;

    Entities(String entityName){
        this.entityName=entityName;
    }
}
