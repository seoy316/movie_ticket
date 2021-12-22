package moviesystem.start;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    private LocalDateTime createTime;
    private LocalDateTime EditTime;

}
