package kr.ac.univ.lab.publication.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.common.util.CommonUtil;
import kr.ac.univ.lab.publication.domain.PublicationAttachedFile;
import kr.ac.univ.lab.publication.repository.PublicationAttachedFileRepository;
import kr.ac.univ.lab.publication.repository.PublicationAttachedFileRepositoryImpl;


@Service
public class PublicationAttachedFileService {
	private final PublicationAttachedFileRepository publicationAttachedFileRepository;
	private final PublicationAttachedFileRepositoryImpl publicationAttachedFileRepositoryImpl;
	
	public PublicationAttachedFileService(PublicationAttachedFileRepository publicationAttachedFileRepository, PublicationAttachedFileRepositoryImpl publicationAttachedFileRepositoryImpl) {
        this.publicationAttachedFileRepository = publicationAttachedFileRepository;   
        this.publicationAttachedFileRepositoryImpl = publicationAttachedFileRepositoryImpl;
    }

	public List<PublicationAttachedFile> findAttachedFileByPublicationIdx(Long publicationIdx) {
		return publicationAttachedFileRepositoryImpl.findAttachedFileByPublicationIdx(publicationIdx);
	}
	
	public void insertAttachedFile(PublicationAttachedFile attachedFile) {
		publicationAttachedFileRepository.save(attachedFile);
	}
	
	public PublicationAttachedFile findAttachedFileByIdx(Long idx) {
		return publicationAttachedFileRepository.findById(idx).orElse(new PublicationAttachedFile());
	}

	public PublicationAttachedFile getAttachedFileByIdx(Long idx) {
		return publicationAttachedFileRepository.getOne(idx);
	}

	public void deleteAttachedFileByIdx(Long idx) {
		publicationAttachedFileRepository.deleteById(idx);
	}

	public Long deleteAttachedFileByPublicationIdx(Long idx) {
		return publicationAttachedFileRepositoryImpl.deleteAttachedFileByPublicationIdx(idx);
	}
	
	/**
	 * 첨부 파일 업로드
	 * @param publicationIdx
	 * @param files
	 */
	public void uploadAttachedFile(Long publicationIdx, MultipartFile[] files) {
		PublicationAttachedFile uploadFile = new PublicationAttachedFile();

		try {
			for (MultipartFile file : files) {
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String savedFileName = uuid + "_" + file.getOriginalFilename();

				// 대체 가능하다
				// File savedFile = new File("./upload/", savedFileName);
				// FileCopyUtils.copy(file.getBytes(), savedFile);

				Path path = Paths.get("./upload/" + savedFileName);
				Files.write(path, file.getBytes());

				uploadFile = PublicationAttachedFile.builder()
						.publicationIdx(publicationIdx)
						.savedFileName(savedFileName)
						.fileSize(CommonUtil.convertFileSize(file.getSize()))
						.build();

				insertAttachedFile(uploadFile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 모든 첨부 파일 삭제
	 * @param idx
	 */
	public void deleteAttachedFile(Long idx) {
		List<PublicationAttachedFile> attachedFileList = findAttachedFileByPublicationIdx(idx);
		
		for (PublicationAttachedFile attachedFile : attachedFileList) {
			Path path = Paths.get("./upload/" + attachedFile.getSavedFileName());
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		deleteAttachedFileByPublicationIdx(idx);
	}
	
	/**
	 * 요청한 첨부 파일 삭제
	 * @param deleteFileList
	 */
	public void deleteAttachedFile(List<Long> deleteFileList) {
		for(Long idx : deleteFileList) {
			PublicationAttachedFile attachedFile = findAttachedFileByIdx(idx);
			
			Path path = Paths.get("./upload/" + attachedFile.getSavedFileName());

			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			deleteAttachedFileByIdx(attachedFile.getIdx());
		}
	}
}