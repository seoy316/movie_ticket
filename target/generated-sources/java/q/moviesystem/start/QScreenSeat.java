package moviesystem.start;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScreenSeat is a Querydsl query type for ScreenSeat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QScreenSeat extends EntityPathBase<ScreenSeat> {

    private static final long serialVersionUID = 822137452L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScreenSeat screenSeat = new QScreenSeat("screenSeat");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QSeat seat;

    public final QTicket ticket;

    public QScreenSeat(String variable) {
        this(ScreenSeat.class, forVariable(variable), INITS);
    }

    public QScreenSeat(Path<? extends ScreenSeat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScreenSeat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScreenSeat(PathMetadata metadata, PathInits inits) {
        this(ScreenSeat.class, metadata, inits);
    }

    public QScreenSeat(Class<? extends ScreenSeat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seat = inits.isInitialized("seat") ? new QSeat(forProperty("seat"), inits.get("seat")) : null;
        this.ticket = inits.isInitialized("ticket") ? new QTicket(forProperty("ticket"), inits.get("ticket")) : null;
    }

}

