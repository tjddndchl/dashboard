package com.future.my.commons.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileController {
	private static String CURR_IMATE_PATH = "c:\\dev\\image_repo";
	
	@RequestMapping("/download")
	public void download(@RequestParam("imageFileName") String imageFileName
						, HttpServletResponse response) throws IOException {
		OutputStream out = response.getOutputStream();
		String downFile = CURR_IMATE_PATH + "\\" + imageFileName;
		File file = new File(downFile);
		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "file not found");
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content dispostion", "attachment; fileName=" + imageFileName);
		try (FileInputStream in = new FileInputStream(file)){
			byte[] buffer = new byte[1024 * 8];
			while (true) {
				int count = in.read(buffer);
				if (count == -1) {
					break;
				}
				out.write(buffer, 0, count);
			}
		} catch (IOException ex) {
			// 
		}finally {
			out.close();
		}
	}
	
	@RequestMapping("/multiImgUpload")
	public void multiImgUpload(HttpServletRequest req, HttpServletResponse res) {
		//저장 후 정보 전달내용
		try {
			String sFileInfo = "";
			String fileName = req.getHeader("file-name");
			String prifix = fileName.substring(fileName.lastIndexOf(".") + 1);
			prifix = prifix.toLowerCase();
			String path = CURR_IMATE_PATH;
			File file = new File(path);
			if (!file.exists()) {
				file.mkdir();
			}
			// 서버에 저장될 파일 이름
			String realName = UUID.randomUUID().toString() + "." + prifix;
			InputStream is = req.getInputStream();
			OutputStream os = new FileOutputStream(new File(path + "\\" + realName));
			int read = 0;
			byte b[] = new byte[1024];
			while ((read = is.read(b)) != -1) {
				os.write(b, 0, read);
			}
			if (is != null) {
				is.close();
			}
			os.flush();
			os.close();
			
			sFileInfo += "&bNewLine=true";
			sFileInfo += "&sFileName=" + fileName;
			sFileInfo += "&sFileURL=/my/download?imageFileName=" + realName;
			PrintWriter print = res.getWriter();
			print.print(sFileInfo);
			print.flush();
			print.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
