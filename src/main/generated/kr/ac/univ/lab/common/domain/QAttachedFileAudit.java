package kr.ac.univ.lab.common.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttachedFileAudit is a Querydsl query type for AttachedFileAudit
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QAttachedFileAudit extends EntityPathBase<AttachedFileAudit> {

    private static final long serialVersionUID = -1813710896L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttachedFileAudit attachedFileAudit = new QAttachedFileAudit("attachedFileAudit");

    public final kr.ac.univ.lab.member.domian.QMember createdBy;

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public QAttachedFileAudit(String variable) {
        this(AttachedFileAudit.class, forVariable(variable), INITS);
    }

    public QAttachedFileAudit(Path<? extends AttachedFileAudit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttachedFileAudit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttachedFileAudit(PathMetadata metadata, PathInits inits) {
        this(AttachedFileAudit.class, metadata, inits);
    }

    public QAttachedFileAudit(Class<? extends AttachedFileAudit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new kr.ac.univ.lab.member.domian.QMember(forProperty("createdBy"), inits.get("createdBy")) : null;
    }

}

