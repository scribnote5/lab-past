package kr.ac.univ.lab.common.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttachedFileAudit is a Querydsl query type for AttachedFileAudit
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QAttachedFileAudit extends EntityPathBase<AttachedFileAudit> {

    private static final long serialVersionUID = -1813710896L;

    public static final QAttachedFileAudit attachedFileAudit = new QAttachedFileAudit("attachedFileAudit");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public QAttachedFileAudit(String variable) {
        super(AttachedFileAudit.class, forVariable(variable));
    }

    public QAttachedFileAudit(Path<? extends AttachedFileAudit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttachedFileAudit(PathMetadata metadata) {
        super(AttachedFileAudit.class, metadata);
    }

}

