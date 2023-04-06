package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Department {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "department")
    private List<Employee> employeeList;

    public void addEmloyeeToDepartment (Employee employee){
        if(employeeList==null){employeeList=new ArrayList<>();
        }
            employeeList.add(employee);
            employee.setDepartment(this);

    }

    @Override
    public String toString() {
        return "Department{" +
                "title='" + title + '\'' +
                '}';
    }
}
