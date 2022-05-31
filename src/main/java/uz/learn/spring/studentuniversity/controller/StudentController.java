package uz.learn.spring.studentuniversity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.learn.spring.studentuniversity.entity.Address;
import uz.learn.spring.studentuniversity.entity.Student;
import uz.learn.spring.studentuniversity.entity.Subject;
import uz.learn.spring.studentuniversity.entity.UniversityGroup;
import uz.learn.spring.studentuniversity.payload.StudentDto;
import uz.learn.spring.studentuniversity.repository.AddressRepository;
import uz.learn.spring.studentuniversity.repository.StudentRepository;
import uz.learn.spring.studentuniversity.repository.SubjectRepository;
import uz.learn.spring.studentuniversity.repository.UniversityGroupRepository;

import java.nio.file.attribute.GroupPrincipal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {
   private StudentRepository studentRepository;
   private AddressRepository addressRepository;
   private UniversityGroupRepository universityGroupRepository;
   private SubjectRepository subjectRepository;

   @Autowired
    public StudentController(StudentRepository studentRepository,
                             AddressRepository addressRepository,
                             UniversityGroupRepository universityGroupRepository,
                             SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.universityGroupRepository = universityGroupRepository;
        this.subjectRepository = subjectRepository;
    }
    @GetMapping
    public List<Student> getAllStudents(){
       return studentRepository.findAll();
    }

    //vazirlik
    @GetMapping("/vazirlik")
    public Page<Student> getStudents(@RequestParam int page){
        //select*from student limit 10 offset 10*(page-1);
        Pageable pageable = PageRequest.of(page, 10);
        return studentRepository.findAll(pageable);
    }
    //universitet rektori
    @GetMapping("/rektor/{university_id}")
    public Page<Student> getStudentByUnivId(@PathVariable Integer university_id, @RequestParam int page){
        Pageable pageable = PageRequest.of(10,page);
        return studentRepository.findAllByUniversityGroup_Faculty_UniversityId(university_id, pageable);
    }
    //facultet dekani
    @GetMapping("/dekan/{faculty_id}")
    public Page<Student> getStudentByFacId(@PathVariable Integer faculty_id, @RequestParam int page){
        Pageable pageable = PageRequest.of(10,page);
        return studentRepository.findAllByUniversityGroup_Faculty_UniversityId(faculty_id, pageable);
    }
    //guruh rahbari
    @GetMapping("/tutor/{group_id}")
    public Page<Student> getStudentByGroupId(@PathVariable Integer group_id, @RequestParam int page){
        Pageable pageable = PageRequest.of(10,page);
        return studentRepository.findAllByUniversityGroup_Faculty_UniversityId(group_id, pageable);
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto){
        Student student = new Student();
        Optional<Address> addressById = addressRepository.findById(studentDto.getAddressId());
        Optional<UniversityGroup> groupById = universityGroupRepository.findById(studentDto.getUniversityGroupId());
        addressById.ifPresent(student::setAddress);
        groupById.ifPresent(student::setUniversityGroup);
        Set<Subject> subjects = new HashSet<>();
        for (Integer subjectId : studentDto.getSubjectIds()) {
            Optional<Subject> byId = subjectRepository.findById(subjectId);
            byId.ifPresent(subjects::add);
        }
        student.setSubjects(subjects);
        student.setName(studentDto.getName());
        studentRepository.save(student);
        return "successfully added";
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){
       try {
           Optional<Student> byId = studentRepository.findById(id);
           byId.ifPresent(studentRepository::save);
           return "successfully saved";
       }
       catch (Exception e){
           return "error";
       }
    }
}
