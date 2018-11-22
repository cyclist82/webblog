package de.awacademy.weblogTilLeif.signup;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class SignupDTO {

	@Size(min = 4)
	private String username;
	@Size(min = 4)
	private String password1;
	private String password2;

	private MultipartFile file1;

	public String getUsername() {
		return username;
	}

	public String getPassword1() {
		return password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
}
