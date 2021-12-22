package moviesystem.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name= "MOVIE_WORKERS")
public class MovieWorker {

    @Id
    @GeneratedValue
    @Column(name= "MOVIE_WORKER_ID")
    private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="MOVIE_ID")
    private Movie movie;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="WORKER_ID")
    private Worker workers;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;


    public MovieWorker(RoleType roleType, Movie movie){
        this.roleType = roleType;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return workers.getName() +
                "(" + roleType + ")";
    }
}