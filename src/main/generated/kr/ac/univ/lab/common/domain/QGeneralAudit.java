package kr.ac.univ.lab.common.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGeneralAudit is a Querydsl query type for GeneralAudit
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QGeneralAudit extends EntityPathBase<GeneralAudit> {

    private static final long serialVersionUID = -1267844770L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGeneralAudit generalAudit = new QGeneralAudit("generalAudit");

    public final kr.ac.univ.lab.member.domian.QMember createdBy;

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final kr.ac.univ.lab.member.domian.QMember lastModifiedBy;

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public QGeneralAudit(String variable) {
        this(GeneralAudit.class, forVariable(variable), INITS);
    }

    public QGeneralAudit(Path<? extends GeneralAudit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGeneralAudit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGeneralAudit(PathMetadata metadata, PathInits inits) {
        this(GeneralAudit.class, metadata, inits);
    }

    public QGeneralAudit(Class<? extends GeneralAudit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new kr.ac.univ.lab.member.domian.QMember(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new kr.ac.univ.lab.member.domian.QMember(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

