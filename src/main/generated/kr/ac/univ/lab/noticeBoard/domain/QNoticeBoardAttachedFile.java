package kr.ac.univ.lab.noticeBoard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeBoardAttachedFile is a Querydsl query type for NoticeBoardAttachedFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNoticeBoardAttachedFile extends EntityPathBase<NoticeBoardAttachedFile> {

    private static final long serialVersionUID = -1860278264L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeBoardAttachedFile noticeBoardAttachedFile = new QNoticeBoardAttachedFile("noticeBoardAttachedFile");

    public final kr.ac.univ.lab.common.domain.QAttachedFileAudit _super;

    // inherited
    public final kr.ac.univ.lab.member.domian.QMember createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final StringPath fileSize = createString("fileSize");

    //inherited
    public final NumberPath<Long> idx;

    public final NumberPath<Long> postIdx = createNumber("postIdx", Long.class);

    public final StringPath savedFileName = createString("savedFileName");

    public QNoticeBoardAttachedFile(String variable) {
        this(NoticeBoardAttachedFile.class, forVariable(variable), INITS);
    }

    public QNoticeBoardAttachedFile(Path<? extends NoticeBoardAttachedFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeBoardAttachedFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeBoardAttachedFile(PathMetadata metadata, PathInits inits) {
        this(NoticeBoardAttachedFile.class, metadata, inits);
    }

    public QNoticeBoardAttachedFile(Class<? extends NoticeBoardAttachedFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new kr.ac.univ.lab.common.domain.QAttachedFileAudit(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.idx = _super.idx;
    }

}

