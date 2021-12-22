package moviesystem.start;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Embeddable
public class Address {
    @Column(name="HOME_CITY")

    private String city;
    private String street;
    private String zipCode;
}