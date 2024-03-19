package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="major_tbl")
@ToString(exclude = "students")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long majorId;
    private String name;
    private String category;
    @OneToMany(mappedBy = "major")
    private List<Student> students = new ArrayList<>(); // 양뱡향은 거의 읽기전용
    public Major(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
