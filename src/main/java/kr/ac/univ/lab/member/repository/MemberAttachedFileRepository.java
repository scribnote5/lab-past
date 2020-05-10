package kr.ac.univ.lab.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.univ.lab.member.domian.MemberAttachedFile;

@Repository
public interface MemberAttachedFileRepository extends JpaRepository<MemberAttachedFile, Long> {

}