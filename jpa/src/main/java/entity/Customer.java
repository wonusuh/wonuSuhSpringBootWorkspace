package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor // 기본생성자 반드시 필요
public class Customer {
    @Id
    String id;
    String name;
    LocalDate regDate;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        regDate = LocalDate.now();
    }
}
