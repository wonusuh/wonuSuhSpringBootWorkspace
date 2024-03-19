package entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="student_tbl")
@ToString(exclude = "major") // 항상 연관관계가 있는 필드는 ToString으로 제외 해줘야한다. 안그러면 무한루프
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String name;
    private String grade;
    @ManyToOne(fetch = FetchType.LAZY) // 관계 구성
    @JoinColumn(name="majorId") // 테이블 컬럼의 fk 이름
    private Major major;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn( unique = true ) // name 값 안주면 클래스이름_id로 생성됨
    private Locker locker;
    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}
