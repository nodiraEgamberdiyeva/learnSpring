package uz.learn.spring.studentuniversity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToOne
    private Address address;
    @ManyToOne
    private UniversityGroup universityGroup;
    @ManyToMany
    private Set<Subject> subjects;
}
