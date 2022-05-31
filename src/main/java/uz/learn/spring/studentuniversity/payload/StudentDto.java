package uz.learn.spring.studentuniversity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private String name;
    private Integer addressId;
    private Integer universityGroupId;
    private Set<Integer> subjectIds;
}
