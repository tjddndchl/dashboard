package com.future.my.commons.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.future.my.commons.service.MailService;
import com.future.my.commons.vo.MailVO;

@Controller
@EnableAsync
public class MailController {
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/sendMailDo")
	public String sendMailDo(MailVO mailVO) throws UnsupportedEncodingException {
		System.out.println(mailVO);
		List<String> arr = mailVO.getEmail();
		for(String email : arr) {
			mailService.sendMail(email, mailVO.getTitle(), mailVO.getContent());
		}

		return "home";
	}
	
}
