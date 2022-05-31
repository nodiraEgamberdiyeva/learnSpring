package uz.learn.spring.studentuniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.learn.spring.studentuniversity.entity.UniversityGroup;

import java.util.List;

public interface UniversityGroupRepository extends JpaRepository<UniversityGroup, Integer> {
    //Jpa query
    List<UniversityGroup> findAllByFaculty_UniversityId(Integer faculty_university_id);

    //query
//    @Query(value = "select gr from university_group gr where gr.faculty.university_id=:faculty_university_id", nativeQuery = true)
//    List<UniversityGroup> getgroupByUniversity(Integer faculty_university_id);

    @Query(value = "select *\n" +
            "from university_group\n" +
            "         join faculty f on university_group.faculty_id = f.id\n" +
            "         join university u on f.university_id = u.id\n" +
            "where university_id = :faculty_university_id", nativeQuery = true)
    List<UniversityGroup> findAllByUnivId(Integer faculty_university_id);
}
