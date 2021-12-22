package moviesystem.start;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="SCREENS")
public class Screen {

    @Id @GeneratedValue
    @Column(name="SCREEN_ID")
    private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    private LocalTime startTime;
    private LocalTime endTime;

    public void setMovie(Movie movie){
        if(this.movie != null){
            this.movie.getScreens().remove(this);
        }
        this.movie = movie;
        movie.getScreens().add(this);
    }

    public void setTheater(Theater theater){
        if(this.theater != null){
            this.theater.getScreens().remove(this);
        }
        this.theater = theater;
        theater.getScreens().add(this);
    }

    public Screen(LocalTime startTime, LocalTime endTime, Movie movie, Theater theater){

        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;

        movie.getScreens().add(this);
        theater.getScreens().add(this);
    }


}
