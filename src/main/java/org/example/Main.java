package org.example;

import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.entity.Operation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Operation.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        Department department = Department.builder().
                title("IT").build();
        Department department1 = Department.builder().title("AUTO").build();

        Employee employee = Employee.builder().
                name("Ivan").
                surname("Ivanov").build();
        Employee employee1 = Employee.builder().name("Denis").surname("Boronin").build();
        Operation operation = Operation.builder().name("Java programming").build();
        Operation operation1 = Operation.builder().name("Car repairing").build();
        employee1.addEmloyeeOperation(operation);
        employee.addEmloyeeOperation(operation1);

        department.addEmloyeeToDepartment(employee1);
        department1.addEmloyeeToDepartment(employee);

        transaction.begin();
        session.save(operation);
        session.save(operation1);
        session.save(department);
        session.save(department1);
        transaction.commit();

        getAll(sessionFactory, Entities.OPERATIONS);
        getAll(sessionFactory, Entities.DEPARTMENTS);
        getAll(sessionFactory, Entities.EMPLOYEES);
        getByName(sessionFactory, "Denis", Entities.EMPLOYEES);
        getByName(sessionFactory, "IT", Entities.DEPARTMENTS);
        getByName(sessionFactory, "Java programming", Entities.OPERATIONS);
        session.close();

    }


    public static void getAll(SessionFactory sessionFactory, Entities entity) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "";
            switch (entity) {
                case DEPARTMENTS:
                    hql = "from Department";
                    break;
                case EMPLOYEES:
                    hql = "from Employee";
                    break;
                case OPERATIONS:
                    hql = "from Operation";
                    break;
            }

            Query<String> query = session.createQuery(hql, String.class);
            List results = query.list();

            for (Object o : results
            ) {
                System.out.println(o);
            }
        }
    }

    public static void getByName(SessionFactory sessionFactory, String name, Entities entity) {

        try (Session session = sessionFactory.openSession()) {
            String hql = "";
            switch (entity) {
                case DEPARTMENTS:
                    hql = "FROM Department WHERE title=:title";
                    break;
                case EMPLOYEES:
                    hql = "FROM Employee WHERE name=:title";
                    break;
                case OPERATIONS:
                    hql = "FROM Operation WHERE name=:title";
                    break;
            }
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("title", name);
            List results = query.list();

            for (Object o : results
            ) {
                System.out.println(o);
            }

        }
    }
}