package uz.learn.spring.studentuniversity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.spring.studentuniversity.entity.Address;
import uz.learn.spring.studentuniversity.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAllByUniversityGroup_Faculty_UniversityId(Integer university_id, Pageable pageable);

    Page<Student> findAllByUniversityGroup_FacultyId(Integer faculty_id, Pageable pageable);

    Page<Student> findAllByUniversityGroupId(Integer group_id, Pageable pageable);
}
