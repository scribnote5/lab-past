package kr.ac.univ.lab.publication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.univ.lab.publication.domain.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

	Page<Publication> findAllByTitleContaining(Pageable pageable, String keyword);
	
	Page<Publication> findAllByAuthorsContaining(Pageable pageable, String keyword);
	
	Page<Publication> findAllByPublishedInContaining(Pageable pageable, String keyword);
}