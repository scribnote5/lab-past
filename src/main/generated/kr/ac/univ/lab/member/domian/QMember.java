package kr.ac.univ.lab.member.domian;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 749774788L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final kr.ac.univ.lab.common.domain.QGeneralAudit _super;

    public final EnumPath<kr.ac.univ.lab.common.domain.enums.ActiveStatus> activeStatus = createEnum("activeStatus", kr.ac.univ.lab.common.domain.enums.ActiveStatus.class);

    public final DatePath<java.time.LocalDate> admissionDate = createDate("admissionDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final StringPath contact = createString("contact");

    // inherited
    public final QMember createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final StringPath email = createString("email");

    public final StringPath englishName = createString("englishName");

    public final EnumPath<kr.ac.univ.lab.member.domian.enums.GenderType> gender = createEnum("gender", kr.ac.univ.lab.member.domian.enums.GenderType.class);

    public final DatePath<java.time.LocalDate> graduationDate = createDate("graduationDate", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> idx;

    public final StringPath introduction = createString("introduction");

    public final StringPath koreanName = createString("koreanName");

    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = createDateTime("lastLoginDate", java.time.LocalDateTime.class);

    // inherited
    public final QMember lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate;

    public final StringPath memberId = createString("memberId");

    public final EnumPath<kr.ac.univ.lab.member.domian.enums.MemberStatus> memberStatus = createEnum("memberStatus", kr.ac.univ.lab.member.domian.enums.MemberStatus.class);

    public final EnumPath<kr.ac.univ.lab.member.domian.enums.MemberType> memberType = createEnum("memberType", kr.ac.univ.lab.member.domian.enums.MemberType.class);

    public final StringPath messangerId = createString("messangerId");

    public final StringPath password = createString("password");

    public final EnumPath<kr.ac.univ.lab.member.domian.enums.PermissionType> permissionType = createEnum("permissionType", kr.ac.univ.lab.member.domian.enums.PermissionType.class);

    public final StringPath webPage = createString("webPage");

    public final StringPath workplace = createString("workplace");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new kr.ac.univ.lab.common.domain.QGeneralAudit(type, metadata, inits);
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.idx = _super.idx;
        this.lastModifiedBy = _super.lastModifiedBy;
        this.lastModifiedDate = _super.lastModifiedDate;
    }

}

