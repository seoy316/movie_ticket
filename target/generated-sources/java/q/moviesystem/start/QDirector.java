package moviesystem.start;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDirector is a Querydsl query type for Director
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDirector extends EntityPathBase<Director> {

    private static final long serialVersionUID = -1947077593L;

    public static final QDirector director = new QDirector("director");

    public final QWorker _super = new QWorker(this);

    //inherited
    public final DatePath<java.util.Date> birth = _super.birth;

    public final StringPath birthPlace = createString("birthPlace");

    //inherited
    public final StringPath filmography = _super.filmography;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final ListPath<MovieWorker, QMovieWorker> movieWorkers = _super.movieWorkers;

    //inherited
    public final StringPath name = _super.name;

    public QDirector(String variable) {
        super(Director.class, forVariable(variable));
    }

    public QDirector(Path<? extends Director> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDirector(PathMetadata metadata) {
        super(Director.class, metadata);
    }

}

