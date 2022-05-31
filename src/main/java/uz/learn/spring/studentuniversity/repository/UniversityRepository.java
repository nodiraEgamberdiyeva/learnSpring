package uz.learn.spring.studentuniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.spring.studentuniversity.entity.Address;
import uz.learn.spring.studentuniversity.entity.University;

public interface UniversityRepository extends JpaRepository<University, Integer> {
    boolean existsByName(String name);
}
