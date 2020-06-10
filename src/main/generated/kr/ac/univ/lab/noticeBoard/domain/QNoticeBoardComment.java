package kr.ac.univ.lab.noticeBoard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeBoardComment is a Querydsl query type for NoticeBoardComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNoticeBoardComment extends EntityPathBase<NoticeBoardComment> {

    private static final long serialVersionUID = -407150953L;

    public static final QNoticeBoardComment noticeBoardComment = new QNoticeBoardComment("noticeBoardComment");

    public final kr.ac.univ.lab.common.domain.QGeneralAudit _super = new kr.ac.univ.lab.common.domain.QGeneralAudit(this);

    public final EnumPath<kr.ac.univ.lab.common.domain.enums.ActiveStatus> activeStatus = createEnum("activeStatus", kr.ac.univ.lab.common.domain.enums.ActiveStatus.class);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> noticeBoardIdx = createNumber("noticeBoardIdx", Long.class);

    public QNoticeBoardComment(String variable) {
        super(NoticeBoardComment.class, forVariable(variable));
    }

    public QNoticeBoardComment(Path<? extends NoticeBoardComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeBoardComment(PathMetadata metadata) {
        super(NoticeBoardComment.class, metadata);
    }

}

