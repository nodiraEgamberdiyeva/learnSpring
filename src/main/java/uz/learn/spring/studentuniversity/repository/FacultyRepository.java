package uz.learn.spring.studentuniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.spring.studentuniversity.entity.Faculty;
import uz.learn.spring.studentuniversity.entity.Subject;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    boolean existsByNameAndUniversityId(String name, Integer university_id);
    List<Faculty> findAllByUniversityId(Integer university_id);
}
