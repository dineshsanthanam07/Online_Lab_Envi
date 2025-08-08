package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student")
public class Student {
    @Id
    @Column("roll_no")
    private Long rollNo;

    @Column("name")
    private String name;

    @Column("department")
    private String department;

    @Column("branch")
    private String branch;

    @Column("batch")
    private String batch;

    @Column("user_id")
    private Long userId;
}
