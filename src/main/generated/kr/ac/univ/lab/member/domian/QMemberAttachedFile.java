package kr.ac.univ.lab.member.domian;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberAttachedFile is a Querydsl query type for MemberAttachedFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberAttachedFile extends EntityPathBase<MemberAttachedFile> {

    private static final long serialVersionUID = 1623780132L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberAttachedFile memberAttachedFile = new QMemberAttachedFile("memberAttachedFile");

    public final kr.ac.univ.lab.common.domain.QAttachedFileAudit _super;

    // inherited
    public final QMember createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final StringPath fileSize = createString("fileSize");

    //inherited
    public final NumberPath<Long> idx;

    public final NumberPath<Long> memberIdx = createNumber("memberIdx", Long.class);

    public final StringPath savedFileName = createString("savedFileName");

    public QMemberAttachedFile(String variable) {
        this(MemberAttachedFile.class, forVariable(variable), INITS);
    }

    public QMemberAttachedFile(Path<? extends MemberAttachedFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberAttachedFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberAttachedFile(PathMetadata metadata, PathInits inits) {
        this(MemberAttachedFile.class, metadata, inits);
    }

    public QMemberAttachedFile(Class<? extends MemberAttachedFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new kr.ac.univ.lab.common.domain.QAttachedFileAudit(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.idx = _super.idx;
    }

}

