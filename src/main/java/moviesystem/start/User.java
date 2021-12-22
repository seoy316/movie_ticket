package moviesystem.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="USERS")
public class User extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Integer id;
    private String name;
    private Integer age;

    @Embedded
    private Address address;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        if (ticket.getUser() != this) {
            ticket.setUser(this);
        }
    }

    public User(String name, Integer age, String city, String street, String zipCode){
        this.name = name;
        this.age = age;
        Address address = new Address(city, street, zipCode);
        this.address = address;
        this.setCreateTime(LocalDateTime.now());
    }



}
