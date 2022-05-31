package uz.learn.spring.studentuniversity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "faculty.university_id"}))
public class UniversityGroup {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    private Faculty faculty;
}
