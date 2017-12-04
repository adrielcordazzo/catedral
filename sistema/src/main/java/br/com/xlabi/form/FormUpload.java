package br.com.xlabi.form;

import org.springframework.web.multipart.MultipartFile;

public class FormUpload {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
