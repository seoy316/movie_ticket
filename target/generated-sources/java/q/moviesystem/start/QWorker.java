package moviesystem.start;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorker is a Querydsl query type for Worker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorker extends EntityPathBase<Worker> {

    private static final long serialVersionUID = -239116583L;

    public static final QWorker worker = new QWorker("worker");

    public final DatePath<java.util.Date> birth = createDate("birth", java.util.Date.class);

    public final StringPath filmography = createString("filmography");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<MovieWorker, QMovieWorker> movieWorkers = this.<MovieWorker, QMovieWorker>createList("movieWorkers", MovieWorker.class, QMovieWorker.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QWorker(String variable) {
        super(Worker.class, forVariable(variable));
    }

    public QWorker(Path<? extends Worker> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorker(PathMetadata metadata) {
        super(Worker.class, metadata);
    }

}

