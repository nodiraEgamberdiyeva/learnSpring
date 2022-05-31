package uz.learn.spring.studentuniversity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.learn.spring.studentuniversity.entity.Subject;
import uz.learn.spring.studentuniversity.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    @PostMapping
    String addSubject(@RequestBody Subject subject){
        if (subjectRepository.existsByName(subject.getName())){
            return "name is already exist";
        }
        else {
            subjectRepository.save(subject);
            return "successfully added";
        }
    }
}
