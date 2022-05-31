package uz.learn.spring.studentuniversity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.learn.spring.studentuniversity.entity.Faculty;
import uz.learn.spring.studentuniversity.entity.University;
import uz.learn.spring.studentuniversity.payload.FacultyDto;
import uz.learn.spring.studentuniversity.repository.FacultyRepository;
import uz.learn.spring.studentuniversity.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping
    List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }
    @GetMapping("/by-university/{univesityId}")
    List<Faculty> getByUniversityId(@PathVariable Integer universityId){
        return facultyRepository.findAllByUniversityId(universityId);
    }

    @PostMapping
    String addFaculty(@RequestBody FacultyDto facultyDto){
        if (facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId())){
            return "is already exist";
        }
        else {
            Optional<University> byId = universityRepository.findById(facultyDto.getUniversityId());
            if (byId.isPresent()) {
                Faculty faculty = new Faculty();
                faculty.setName(facultyDto.getName());
                faculty.setUniversity(byId.get());
                facultyRepository.save(faculty);
                return "successfully added";
            }
            else return "university id is not found";
        }

    }
}
