package kr.ac.univ.lab.noticeBoard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeBoard is a Querydsl query type for NoticeBoard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNoticeBoard extends EntityPathBase<NoticeBoard> {

    private static final long serialVersionUID = 315579048L;

    public static final QNoticeBoard noticeBoard = new QNoticeBoard("noticeBoard");

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

    public final StringPath title = createString("title");

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public QNoticeBoard(String variable) {
        super(NoticeBoard.class, forVariable(variable));
    }

    public QNoticeBoard(Path<? extends NoticeBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeBoard(PathMetadata metadata) {
        super(NoticeBoard.class, metadata);
    }

}

