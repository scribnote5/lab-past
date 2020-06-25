package kr.ac.univ.lab.publication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.univ.lab.publication.domain.PublicationAttachedFile;

@Repository
public interface PublicationAttachedFileRepository extends JpaRepository<PublicationAttachedFile, Long> {

}