package kr.ac.univ.lab.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttachedFile is a Querydsl query type for AttachedFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttachedFile extends EntityPathBase<AttachedFile> {

    private static final long serialVersionUID = -681039418L;

    public static final QAttachedFile attachedFile = new QAttachedFile("attachedFile");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> downloadCount = createNumber("downloadCount", Long.class);

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memberId = createString("memberId");

    public final StringPath savedFileName = createString("savedFileName");

    public QAttachedFile(String variable) {
        super(AttachedFile.class, forVariable(variable));
    }

    public QAttachedFile(Path<? extends AttachedFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttachedFile(PathMetadata metadata) {
        super(AttachedFile.class, metadata);
    }

}

