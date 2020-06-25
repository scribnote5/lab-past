package kr.ac.univ.lab.member.domian;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 749774788L;

    public static final QMember member = new QMember("member1");

    public final kr.ac.univ.lab.common.domain.QGeneralAudit _super = new kr.ac.univ.lab.common.domain.QGeneralAudit(this);

    public final EnumPath<kr.ac.univ.lab.common.domain.enums.ActiveStatus> activeStatus = createEnum("activeStatus", kr.ac.univ.lab.common.domain.enums.ActiveStatus.class);

    public final DatePath<java.time.LocalDate> admissionDate = createDate("admissionDate", java.time.LocalDate.class);

    public final EnumPath<kr.ac.univ.lab.member.domian.enums.AuthorityType> authorityType = createEnum("authorityType", kr.ac.univ.lab.member.domian.enums.AuthorityType.class);

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final StringPath contact = createString("contact");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath englishName = createString("englishName");

    public final EnumPath<kr.ac.univ.lab.member.domian.enums.GenderType> gender = createEnum("gender", kr.ac.univ.lab.member.domian.enums.GenderType.class);

    public final DatePath<java.time.LocalDate> graduatedDate = createDate("graduatedDate", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final StringPath introduction = createString("introduction");

    public final StringPath koreanName = createString("koreanName");

    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = createDateTime("lastLoginDate", java.time.LocalDateTime.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath memberId = createString("memberId");

    public final EnumPath<kr.ac.univ.lab.member.domian.enums.MemberStatus> memberStatus = createEnum("memberStatus", kr.ac.univ.lab.member.domian.enums.MemberStatus.class);

    public final EnumPath<kr.ac.univ.lab.member.domian.enums.MemberType> memberType = createEnum("memberType", kr.ac.univ.lab.member.domian.enums.MemberType.class);

    public final StringPath messangerId = createString("messangerId");

    public final StringPath password = createString("password");

    public final StringPath privateEmail = createString("privateEmail");

    public final StringPath webPage = createString("webPage");

    public final StringPath workplace = createString("workplace");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

