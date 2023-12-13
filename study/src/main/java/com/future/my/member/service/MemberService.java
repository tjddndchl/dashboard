package com.future.my.member.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.future.my.board.vo.BoardVO;
import com.future.my.member.dao.IMemberDAO;
import com.future.my.member.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	IMemberDAO dao;
	
	public void registMember(MemberVO member) throws Exception{
		
		int result = dao.registMember(member);
		
		if(result == 0) {
			throw new Exception();
		}
	}
	
	public MemberVO loginMember(MemberVO member) {
		MemberVO result = dao.loginMember(member);
		System.out.println(result);
		
		if (result == null) {
			return null;
		}
		return result;
	}
	
	public void updateMember(MemberVO member) throws Exception{
		int result = dao.updateMember(member);
		
		if (result == 0) {
			throw new Exception();
		}
		
	}
	
	public void deleteMember(MemberVO member) throws Exception{
		
		int result = dao.deleteMember(member);
		
		if (result == 0) {
			throw new Exception();
		}
	}
	
	public String updateProfilePicture(MemberVO member, String uploadDir, String webPath, MultipartFile file) throws Exception{
		String originalFilename = file.getOriginalFilename();
		String storedFilename = UUID.randomUUID().toString() + "_" + originalFilename; //중복방지
		String dbFilePath = webPath + storedFilename;
		Path filePath = Paths.get(uploadDir, storedFilename);
		
		try {
			//파일을 물리적으로 저장하는 부분
			Files.copy(file.getInputStream(), filePath);
		} catch (Exception e) {
			throw new Exception("Failed to save the file", e);
		}
		member.setProfileImg(dbFilePath);
		int result = dao.profileUpload(member);
		if (result == 0) {
			throw new Exception();
		}
		
		return dbFilePath;
	}
}
