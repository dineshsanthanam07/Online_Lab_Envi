package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "Faculty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {

    @Column("faculty_id")
    private Long facultyId;

    @Column("name")
    private String name;

    @Column("department")
    private String department;

    @Column("email")
    private String email;

    @Column("designation")
    private String designation;

    @Column("user_id")
    private Long userId;
}
