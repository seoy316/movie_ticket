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
@Table(name="TICKETS")
public class Ticket {

    @Id @GeneratedValue
    @Column(name = "TICKET_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID")
    private User user;

    @OneToOne(fetch= FetchType.LAZY)    // 주 테이블에 외래키 설정
    @JoinColumn(name = "SCREEN_ID")
    private Screen screen;

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID")
    private List<ScreenSeat> screenSeats = new ArrayList<ScreenSeat>();

    public void setUser(User user){
        if(this.user != null){
            this.user.getTickets().remove(this);
        }
        this.user = user;
        user.getTickets().add(this);
    }

    public void addScreenSeat(ScreenSeat screenSeat){
        this.screenSeats.add(screenSeat);
        if(screenSeat.getTicket() != this)
            screenSeat.setTicket(this);
    }

    private Boolean state;

    public Ticket(User user, Screen screen){
        this.user = user;
        this.screen = screen;
        this.state = true;

        user.getTickets().add(this);
    }








}
