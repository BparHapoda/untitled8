package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Table(name = "operations")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @ManyToMany(mappedBy = "operations")
    private List<Employee> employeesList;


    @Override
    public String toString() {
        return "Operation{" +
                "name='" + name + '\'' +
                '}';
    }
}
