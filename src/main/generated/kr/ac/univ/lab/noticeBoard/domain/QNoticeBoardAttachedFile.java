package kr.ac.univ.lab.noticeBoard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeBoardAttachedFile is a Querydsl query type for NoticeBoardAttachedFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNoticeBoardAttachedFile extends EntityPathBase<NoticeBoardAttachedFile> {

    private static final long serialVersionUID = -1860278264L;

    public static final QNoticeBoardAttachedFile noticeBoardAttachedFile = new QNoticeBoardAttachedFile("noticeBoardAttachedFile");

    public final kr.ac.univ.lab.common.domain.QAttachedFileAudit _super = new kr.ac.univ.lab.common.domain.QAttachedFileAudit(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath fileSize = createString("fileSize");

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final NumberPath<Long> postIdx = createNumber("postIdx", Long.class);

    public final StringPath savedFileName = createString("savedFileName");

    public QNoticeBoardAttachedFile(String variable) {
        super(NoticeBoardAttachedFile.class, forVariable(variable));
    }

    public QNoticeBoardAttachedFile(Path<? extends NoticeBoardAttachedFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeBoardAttachedFile(PathMetadata metadata) {
        super(NoticeBoardAttachedFile.class, metadata);
    }

}

