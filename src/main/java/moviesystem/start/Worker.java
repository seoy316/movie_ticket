package moviesystem.start;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")

@Entity
@Table(name="WORKERS")
public abstract class Worker {

    @Id @GeneratedValue
    @Column(name = "WORKER_ID")
    private Integer id;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birth;

    private String filmography;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKER_ID")
    private List<MovieWorker> movieWorkers = new ArrayList<MovieWorker>();

    public void addMovieWorker(MovieWorker movieWorker) {
        this.movieWorkers.add(movieWorker);
        if (movieWorker.getWorkers() != this) {
            movieWorker.setWorkers(this);
        }
    }


}
