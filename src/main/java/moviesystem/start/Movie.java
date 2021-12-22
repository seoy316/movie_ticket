package moviesystem.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="MOVIES")
public class Movie extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Integer id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date openingDate;
    private Integer runningTime;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private List<MovieWorker> movieWorkers = new ArrayList<MovieWorker>();

    public void addMovieWorker(MovieWorker movieWorker) {
        this.movieWorkers.add(movieWorker);
        if (movieWorker.getMovie() != this) {
            movieWorker.setMovie(this);
        }
    }

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private List<Screen> screens = new ArrayList<Screen>();


    public void addScreen(Screen screen) {
        this.screens.add(screen);
        if (screen.getMovie() != this) {
            screen.setMovie(this);
        }
    }

    public void CreateMovie(String name, Date openingDate, Genre genre, Integer runningTime){

        this.name = name;
        this.openingDate = openingDate;
        this.genre = genre;
        this.runningTime = runningTime;

        setCreateTime(LocalDateTime.now());
    }

    public Movie(String name, Date openingDate, Integer runningTime, Genre genre){
        this.name = name;
        this.openingDate = openingDate;
        this.runningTime = runningTime;
        this.genre = genre;
        this.setCreateTime(LocalDateTime.now());
    }


    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", openingDate=" + openingDate +
                ", runningTime=" + runningTime +
                ", genre=" + genre +
                '}';
    }


}
