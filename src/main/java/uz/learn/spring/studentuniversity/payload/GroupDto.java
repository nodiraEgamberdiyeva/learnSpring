package uz.learn.spring.studentuniversity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupDto {
    private String name;
    private Integer facultyId;
}
