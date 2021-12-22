package moviesystem.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@DiscriminatorValue("ACTORS")
@Table(name="ACTORS")

public class Actor extends Worker{
    @Id @GeneratedValue
    @Column(name = "WORKER_ID")
    private Integer id;

    private Integer height;
    private String instagram;

    public Actor(String name, Date birthday, Integer height, String instagram){
        this.setName(name);
        this.setBirth(birthday);
        this.height = height;
        this.instagram = instagram;
    }
}
