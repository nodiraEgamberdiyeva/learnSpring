package uz.learn.spring.studentuniversity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.learn.spring.studentuniversity.entity.Faculty;
import uz.learn.spring.studentuniversity.entity.UniversityGroup;
import uz.learn.spring.studentuniversity.payload.GroupDto;
import uz.learn.spring.studentuniversity.repository.FacultyRepository;
import uz.learn.spring.studentuniversity.repository.UniversityGroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("groups")
public class GroupController {
    @Autowired
    private UniversityGroupRepository groupRepository;
    @Autowired
    private FacultyRepository facultyRepository;

    @GetMapping("/by-university/{universityId}")
    List<UniversityGroup> getGroupByUniversityId(@PathVariable Integer universityId){
        return groupRepository.findAllByFaculty_UniversityId(universityId);
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){
        UniversityGroup universityGroup = new UniversityGroup();
        universityGroup.setName(groupDto.getName());
        Optional<Faculty> byId = facultyRepository.findById(groupDto.getFacultyId());
        byId.ifPresent(universityGroup::setFaculty);
        groupRepository.save(universityGroup);
        return "Successfully added";
    }
}
