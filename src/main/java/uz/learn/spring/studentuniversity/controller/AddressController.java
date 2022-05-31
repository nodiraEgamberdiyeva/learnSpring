package uz.learn.spring.studentuniversity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.learn.spring.studentuniversity.entity.Address;
import uz.learn.spring.studentuniversity.repository.AddressRepository;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    @PostMapping
    public String addStudent(@RequestBody Address address){
        addressRepository.save(address);
        return "Successfully added";
    }

}
