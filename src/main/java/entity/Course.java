package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "Courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @Column("course_id")
    private String courseId;

    @Column("course_name")
    private String courseName;  // Course Name

    @Column("created_at")
    private LocalDateTime createdAt;  // Course Creation Timestamp

    @Column("faculty_id")
    private Long facultyId;  // Faculty assigned to the course


}
