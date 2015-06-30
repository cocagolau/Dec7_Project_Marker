package me.dec7.marker.domain.user;

public class PasswordDto {

	private Long id;
	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;

	public PasswordDto() { }

	public PasswordDto(Long id) {
		this.id = id;
	}

	public PasswordDto(Long id, String oldPassword, String newPassword, String newPasswordConfirm) {
		this.id = id;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

}
