package kr.ac.univ.lab.publication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPublication is a Querydsl query type for Publication
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPublication extends EntityPathBase<Publication> {

    private static final long serialVersionUID = -941201944L;

    public static final QPublication publication = new QPublication("publication");

    public final kr.ac.univ.lab.common.domain.QGeneralAudit _super = new kr.ac.univ.lab.common.domain.QGeneralAudit(this);

    public final StringPath authors = createString("authors");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath doiUrl = createString("doiUrl");

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final NumberPath<Double> impactFactor = createNumber("impactFactor", Double.class);

    public final StringPath issn = createString("issn");

    public final EnumPath<kr.ac.univ.lab.publication.domain.enums.KindType> kindType = createEnum("kindType", kr.ac.univ.lab.publication.domain.enums.KindType.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath number = createString("number");

    public final StringPath pages = createString("pages");

    public final EnumPath<kr.ac.univ.lab.publication.domain.enums.PublicationType> publicationType = createEnum("publicationType", kr.ac.univ.lab.publication.domain.enums.PublicationType.class);

    public final DatePath<java.time.LocalDate> publishedDate = createDate("publishedDate", java.time.LocalDate.class);

    public final StringPath publishedIn = createString("publishedIn");

    public final StringPath remark = createString("remark");

    public final StringPath title = createString("title");

    public final StringPath volume = createString("volume");

    public QPublication(String variable) {
        super(Publication.class, forVariable(variable));
    }

    public QPublication(Path<? extends Publication> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPublication(PathMetadata metadata) {
        super(Publication.class, metadata);
    }

}

