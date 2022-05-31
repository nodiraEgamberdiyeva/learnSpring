package uz.learn.spring.studentuniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.learn.spring.studentuniversity.entity.Address;
import uz.learn.spring.studentuniversity.entity.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
}
