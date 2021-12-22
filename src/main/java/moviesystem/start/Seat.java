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
@Table(name="SEATS")
public class Seat {
    @Id @GeneratedValue
    @Column(name= "SEAT_ID")
    private Integer id;

    private Integer seatRow;
    private Integer seatColumn;

    private Boolean state;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name= "THEATER_ID")
    private Theater theater;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID")
    private List<ScreenSeat> screenSeats = new ArrayList<ScreenSeat>();

    public void addScreenSeat(ScreenSeat screenSeat){
        this.screenSeats.add(screenSeat);
        if(screenSeat.getSeat() != this)
            screenSeat.setSeat(this);
    }

    public void setTheater(Theater theater){
        if(this.theater != null){
            this.theater.getSeats().remove(this);
        }
        this.theater = theater;
        theater.getSeats().add(this);
    }

    public Seat(Integer seatRow, Integer seatColumn, Theater theater){
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.state = true;
        this.theater = theater;

        theater.getSeats().add(this);
    }

}
