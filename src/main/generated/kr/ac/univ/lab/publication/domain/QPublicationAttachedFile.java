package kr.ac.univ.lab.publication.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPublicationAttachedFile is a Querydsl query type for PublicationAttachedFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPublicationAttachedFile extends EntityPathBase<PublicationAttachedFile> {

    private static final long serialVersionUID = -1648454840L;

    public static final QPublicationAttachedFile publicationAttachedFile = new QPublicationAttachedFile("publicationAttachedFile");

    public final kr.ac.univ.lab.common.domain.QAttachedFileAudit _super = new kr.ac.univ.lab.common.domain.QAttachedFileAudit(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath fileSize = createString("fileSize");

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final NumberPath<Long> publicationIdx = createNumber("publicationIdx", Long.class);

    public final StringPath savedFileName = createString("savedFileName");

    public QPublicationAttachedFile(String variable) {
        super(PublicationAttachedFile.class, forVariable(variable));
    }

    public QPublicationAttachedFile(Path<? extends PublicationAttachedFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPublicationAttachedFile(PathMetadata metadata) {
        super(PublicationAttachedFile.class, metadata);
    }

}

