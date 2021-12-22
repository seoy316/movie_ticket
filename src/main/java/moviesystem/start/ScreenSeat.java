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
@Table(name="SCREEN_SEAT")
public class ScreenSeat {

    @Id @GeneratedValue
    @Column(name="TICKET_SEAT_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SEAT_ID")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TICKET_ID")
    private Ticket ticket;

    public void setTicket(Ticket ticket){
        if(this.ticket != null){
            this.ticket.getScreenSeats().remove(this);
        }
        this.ticket = ticket;
        ticket.getScreenSeats().add(this);
    }

    public void setSeat(Seat seat){
        if(this.seat != null){
            this.seat.getScreenSeats().remove(this);
        }
        this.seat = seat;
        seat.getScreenSeats().add(this);
    }

    public ScreenSeat(Ticket ticket, Seat seat){
        this.ticket = ticket;
        this.seat = seat;

        ticket.getScreenSeats().add(this);
        seat.getScreenSeats().add(this);
    }
}
