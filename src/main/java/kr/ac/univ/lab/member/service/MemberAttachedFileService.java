
package kr.ac.univ.lab.member.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.univ.lab.common.util.CommonUtil;
import kr.ac.univ.lab.member.domian.MemberAttachedFile;
import kr.ac.univ.lab.member.repository.MemberAttachedFileRepository;
import kr.ac.univ.lab.member.repository.MemberAttachedFileRepositoryImpl;

@Service
public class MemberAttachedFileService {
	private final MemberAttachedFileRepository memberAttachedFileRepository;
	private final MemberAttachedFileRepositoryImpl memberAttachedFileRepositoryImpl;
	
	public MemberAttachedFileService(MemberAttachedFileRepository memberAttachedFileRepository, MemberAttachedFileRepositoryImpl memberAttachedFileRepositoryImpl) {
        this.memberAttachedFileRepository = memberAttachedFileRepository;   
        this.memberAttachedFileRepositoryImpl = memberAttachedFileRepositoryImpl;
    }

	public List<MemberAttachedFile> findAttachedFileByMemberIdx(Long memberIdx) {
		return memberAttachedFileRepositoryImpl.findAttachedFileByMemberId(memberIdx);
	}
	
	public void insertAttachedFile(MemberAttachedFile memberAttachedFile) {
		memberAttachedFileRepository.save(memberAttachedFile);
	}
	
	public MemberAttachedFile findAttachedFileByIdx(Long idx) {
		return memberAttachedFileRepository.findById(idx).orElse(new MemberAttachedFile());
	}

	public MemberAttachedFile getAttachedFileByIdx(Long idx) {
		return memberAttachedFileRepository.getOne(idx);
	}

	public void deleteAttachedFileByIdx(Long idx) {
		memberAttachedFileRepository.deleteById(idx);
	}

	public Long deleteAttachedFileByMemberIdx(Long idx) {
		return memberAttachedFileRepositoryImpl.deleteAttachedFileByMemberId(idx);
	}
	
	/**
	 * 첨부 파일 업로드 
	 * @param memberIdx
	 * @param files
	 */
	public void uploadAttachedFile(Long memberIdx, MultipartFile[] files) {
		MemberAttachedFile uploadFile = new MemberAttachedFile();

		try {
			for (MultipartFile file : files) {
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String savedFileName = uuid + "_" + file.getOriginalFilename();

				Path path = Paths.get("./upload/" + savedFileName);
				Files.write(path, file.getBytes());

				uploadFile = MemberAttachedFile.builder()
						.memberIdx(memberIdx)
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
	 * 첨부 파일 삭제
	 * @param savedFileName
	 */
	public void deleteAttachedFile(Long idx) {
		List<MemberAttachedFile> attachedFileList = findAttachedFileByMemberIdx(idx);
		
		for (MemberAttachedFile attachedFile : attachedFileList) {
			Path path = Paths.get("./upload/" + attachedFile.getSavedFileName());
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		deleteAttachedFileByMemberIdx(idx);
	}
}