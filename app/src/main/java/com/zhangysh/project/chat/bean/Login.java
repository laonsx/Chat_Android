package com.zhangysh.project.chat.bean;

public class Login {
	public Login(String userName, String passWorld) {
		// TODO Auto-generated constructor stub
		super();
		this.userName = userName;
		this.passWord = passWorld;
	}

	public Login() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Login [userName=" + userName + ", passWord=" + passWord + "]";
	}

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	private String passWord;

}
