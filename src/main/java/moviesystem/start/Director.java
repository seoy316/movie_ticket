package moviesystem.start;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@DiscriminatorValue("DIRECTORS")
@Table(name="DIRECTORS")

public class Director extends Worker{
    @Id @GeneratedValue
    @Column(name = "WORKER_ID")
    private Integer id;

    private String birthPlace;

    public Director(String name, Date birthday, String birthPlace){
        this.setName(name);
        this.setBirth(birthday);
        this.birthPlace = birthPlace;

    }

}
