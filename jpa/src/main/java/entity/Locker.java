package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int lockNo;
    @OneToOne(mappedBy = "locker")
    private Student student;

    public Locker(int lockNo, Student student) {
        this.lockNo = lockNo;
        this.student = student;
        student.setLocker(this);
    }
}
