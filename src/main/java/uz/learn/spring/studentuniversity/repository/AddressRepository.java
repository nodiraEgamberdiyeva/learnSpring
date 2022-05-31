package uz.learn.spring.studentuniversity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.learn.spring.studentuniversity.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
