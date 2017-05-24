package com.zhangysh.project.chat.bean;

public class Content {
	/**
	 * 
	 * @param send
	 * @param receive
	 * @param message
	 * @param time
	 * @param type
	 */
	public Content(String send, String receive, String message, String time, String type) {
		// TODO Auto-generated constructor stub
		super();
		this.send = send;
		this.receive = receive;
		this.message = message;
		this.time = time;
		this.type = type;
	}

	public Content() {
		// TODO Auto-generated constructor stub
	}

	private String send;
	private String receive;
	private String message;
	private String time;
	private String type;

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Content [send=" + send + ", receive=" + receive + ", message=" + message + ", time=" + time + ", type="
				+ type + "]";
	}
}
