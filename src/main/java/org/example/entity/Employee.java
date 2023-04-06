package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emloyees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id",referencedColumnName = "id")
    private Department department;
    @ManyToMany
    @JoinTable(
            name = "Employee_Operation",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Operation> operations;

    public void addEmloyeeOperation (Operation operation){
        if(operations==null){operations=new ArrayList<>();
        }
        operations.add(operation);
        if(operation.getEmployeesList()==null){operation.setEmployeesList(new ArrayList<>());
        }
        operation.getEmployeesList().add(this);

    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department=" + department.getTitle()+
                '}';
    }
}
