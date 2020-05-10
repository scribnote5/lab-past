package kr.ac.univ.lab.noticeBoard.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.common.util.CommonUtil;
import kr.ac.univ.lab.noticeBoard.domain.NoticeBoardAttachedFile;
import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardAttachedFileRepository;
import kr.ac.univ.lab.noticeBoard.repository.NoticeBoardAttachedFileRepositoryImpl;


@Service
public class NoticeBoardAttachedFileService {
	private final NoticeBoardAttachedFileRepository noticeBoardAttachedFileRepository;
	private final NoticeBoardAttachedFileRepositoryImpl noticeBoardAttachedFileRepositoryImpl;
	
	public NoticeBoardAttachedFileService(NoticeBoardAttachedFileRepository noticeBoardAttachedFileRepository, NoticeBoardAttachedFileRepositoryImpl noticeBoardAttachedFileRepositoryImpl) {
        this.noticeBoardAttachedFileRepository = noticeBoardAttachedFileRepository;   
        this.noticeBoardAttachedFileRepositoryImpl = noticeBoardAttachedFileRepositoryImpl;
    }

	public List<NoticeBoardAttachedFile> findAttachedFileByPostIdx(Long postIdx) {
		return noticeBoardAttachedFileRepositoryImpl.findAttachedFileByPostId(postIdx);
	}
	
	public void insertAttachedFile(NoticeBoardAttachedFile attachedFile) {
		noticeBoardAttachedFileRepository.save(attachedFile);
	}
	
	public NoticeBoardAttachedFile findAttachedFileByIdx(Long idx) {
		return noticeBoardAttachedFileRepository.findById(idx).orElse(new NoticeBoardAttachedFile());
	}

	public NoticeBoardAttachedFile getAttachedFileByIdx(Long idx) {
		return noticeBoardAttachedFileRepository.getOne(idx);
	}

	public void deleteAttachedFileByIdx(Long idx) {
		noticeBoardAttachedFileRepository.deleteById(idx);
	}

	public Long deleteAttachedFileByPostIdx(Long idx) {
		return noticeBoardAttachedFileRepositoryImpl.deleteAttachedFileByPostId(idx);
	}
	
	/**
	 * 첨부 파일 업로드
	 * @param postId
	 * @param files
	 */
	public void uploadAttachedFile(Long postIdx, MultipartFile[] files) {
		NoticeBoardAttachedFile uploadFile = new NoticeBoardAttachedFile();

		try {
			for (MultipartFile file : files) {
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String savedFileName = uuid + "_" + file.getOriginalFilename();

				// 대체 가능하다
				// File savedFile = new File("./upload/", savedFileName);
				// FileCopyUtils.copy(file.getBytes(), savedFile);

				Path path = Paths.get("./upload/" + savedFileName);
				Files.write(path, file.getBytes());

				uploadFile = NoticeBoardAttachedFile.builder()
						.postIdx(postIdx)
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
		List<NoticeBoardAttachedFile> attachedFileList = findAttachedFileByPostIdx(idx);
		
		for (NoticeBoardAttachedFile attachedFile : attachedFileList) {
			Path path = Paths.get("./upload/" + attachedFile.getSavedFileName());
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		deleteAttachedFileByPostIdx(idx);
	}
	
	/**
	 * 요청한 첨부 파일 삭제
	 * @param deleteFileList
	 */
	public void deleteAttachedFile(List<Long> deleteFileList) {
		for(Long idx : deleteFileList) {
			NoticeBoardAttachedFile attachedFile = findAttachedFileByIdx(idx);
			
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