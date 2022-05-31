package uz.learn.spring.studentuniversity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.learn.spring.studentuniversity.entity.Address;
import uz.learn.spring.studentuniversity.entity.University;
import uz.learn.spring.studentuniversity.payload.UniversityDto;
import uz.learn.spring.studentuniversity.repository.AddressRepository;
import uz.learn.spring.studentuniversity.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    List<University> getAllUniversities(){
        return universityRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteUniversityById(@PathVariable Integer id){
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()){
            universityRepository.deleteById(id);
            addressRepository.delete(byId.get().getAddress());
            return "successfully deleted";
        }
        else return "id is not found";
    }

    @PostMapping
    String addUniversity(@RequestBody UniversityDto universityDto){
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address savedAddress = addressRepository.save(address);
        University university = new University();
        university.setAddress(savedAddress);
        university.setName(universityDto.getName());
        universityRepository.save(university);
        return "Successfully added";
    }

    @PutMapping("/{id}")
    String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        Optional<University> univById = universityRepository.findById(id);
        if (univById.isPresent()){
            University university = univById.get();
            Address address = university.getAddress();
            address.setStreet(universityDto.getStreet());
            address.setDistrict(universityDto.getDistrict());
            address.setCity(universityDto.getStreet());
            addressRepository.save(address);

            university.setName(universityDto.getName());
            university.setAddress(address);
            universityRepository.save(university);
            return "successfully edited";
        }

       else return "id is not found";
    }
}
