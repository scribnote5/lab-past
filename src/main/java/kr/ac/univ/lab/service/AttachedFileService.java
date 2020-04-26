package kr.ac.univ.lab.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.domain.AttachedFile;
import kr.ac.univ.lab.repository.AttachedFileRepository;
import kr.ac.univ.lab.repository.AttachedFileRepositoryImpl;

@Service
public class AttachedFileService {
	@Autowired
	private AttachedFileRepository attachedFileRepositroy;

	@Autowired
	private AttachedFileRepositoryImpl attachedFileRepositroyImpl;
	
	public AttachedFile findUploadFileById(Long id) {
		return attachedFileRepositroy.findById(id).orElse(new AttachedFile());
	}

	public List<AttachedFile> findUploadFileByBoardId(Long boardId) {
		return attachedFileRepositroyImpl.findAttachedFileByBoardId(boardId);
	}

	public AttachedFile findUploadFile(Long id) {
		return attachedFileRepositroy.findById(id).orElse(new AttachedFile());
	}

	public void insertUploadFile(AttachedFile attachedFile) {
		attachedFileRepositroy.save(attachedFile);
	}

	public AttachedFile getUploadFileById(Long id) {
		return attachedFileRepositroy.getOne(id);
	}

	public void deleteUploadFileById(Long id) {
		attachedFileRepositroy.deleteById(id);
	}

	public Long deleteUploadFileByBoardId(Long id) {
		return attachedFileRepositroyImpl.deleteAttachedFileByBoardId(id);
	}

	/**
	 * 첨부 파일 업로드 
	 * @param boardId
	 * @param files
	 */
	public void uploadAttachedFile(Long boardId, MultipartFile[] files) {
		AttachedFile uploadFile = new AttachedFile();

		try {
			for (MultipartFile file : files) {
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String savedFileName = uuid + "_" + file.getOriginalFilename();

				// 대체 가능하다
				// File savedFile = new File("./upload/", savedFileName);
				// FileCopyUtils.copy(file.getBytes(), savedFile);

				Path path = Paths.get("./upload/" + savedFileName);
				Files.write(path, file.getBytes());

				uploadFile = AttachedFile.builder()
						.boardId(boardId)
						.savedFileName(savedFileName)
						.fileSize(file.getSize())
						.build();

				insertUploadFile(uploadFile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 첨부 파일 다운로드
	 * @param fileName
	 * @return
	 */
	public ByteArrayResource downloadAttachedFile(String fileName) {
		Path path = Paths.get("./upload/" + fileName);
		ByteArrayResource byteArrayResource = null; 
		
        try {
        	byteArrayResource = new ByteArrayResource(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return byteArrayResource;
	}
	
	/**
	 * 첨부 파일 삭제
	 * @param savedFileName
	 */
	
	public void deleteAttachedFile(String savedFileName) {
		Path path = Paths.get("./upload/" + savedFileName);

		try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
}