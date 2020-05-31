package kr.ac.univ.lab.member.domian;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberAttachedFile is a Querydsl query type for MemberAttachedFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberAttachedFile extends EntityPathBase<MemberAttachedFile> {

    private static final long serialVersionUID = 1623780132L;

    public static final QMemberAttachedFile memberAttachedFile = new QMemberAttachedFile("memberAttachedFile");

    public final kr.ac.univ.lab.common.domain.QAttachedFileAudit _super = new kr.ac.univ.lab.common.domain.QAttachedFileAudit(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath fileSize = createString("fileSize");

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final NumberPath<Long> memberIdx = createNumber("memberIdx", Long.class);

    public final StringPath savedFileName = createString("savedFileName");

    public QMemberAttachedFile(String variable) {
        super(MemberAttachedFile.class, forVariable(variable));
    }

    public QMemberAttachedFile(Path<? extends MemberAttachedFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberAttachedFile(PathMetadata metadata) {
        super(MemberAttachedFile.class, metadata);
    }

}

