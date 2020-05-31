package kr.ac.univ.lab.common.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGeneralAudit is a Querydsl query type for GeneralAudit
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QGeneralAudit extends EntityPathBase<GeneralAudit> {

    private static final long serialVersionUID = -1267844770L;

    public static final QGeneralAudit generalAudit = new QGeneralAudit("generalAudit");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = createDateTime("lastModifiedDate", java.time.LocalDateTime.class);

    public QGeneralAudit(String variable) {
        super(GeneralAudit.class, forVariable(variable));
    }

    public QGeneralAudit(Path<? extends GeneralAudit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeneralAudit(PathMetadata metadata) {
        super(GeneralAudit.class, metadata);
    }

}

