package moviesystem.start;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="THEATERS")
public class Theater {
    @Id @GeneratedValue
    @Column(name = "THEATER_ID")
    private Integer id;

    private String name;
    private Integer floor;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="THEATER_ID")
    private List<Screen> screens = new ArrayList<Screen>();

    public void addScreen(Screen screen){
        this.screens.add(screen);
        if(screen.getTheater() != this)
            screen.setTheater(this);
    }


    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private List<Seat> seats = new ArrayList<Seat>();

    public void addSeat(Seat seat) {
        this.seats.add(seat);
        if (seat.getTheater() != this) {
            seat.setTheater(this);
        }
    }

    public Theater(String name, Integer floor){
        this.name = name;
        this.floor = floor;
    }


}
