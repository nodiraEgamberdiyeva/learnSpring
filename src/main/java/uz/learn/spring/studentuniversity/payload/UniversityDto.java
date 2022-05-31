package uz.learn.spring.studentuniversity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UniversityDto {
    private String name;
    private String city;
    private String district;
    private String street;

}
