package com.zhangysh.project.chat.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import com.google.gson.Gson;
import com.zhangysh.project.chat.bean.Output;

public class HeartBeat implements Runnable {
	private Socket socket;

	public HeartBeat(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	public void run() {
		while (true) {
			OutputStream out = null;
			try {
				out = socket.getOutputStream();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				out.write(Protocol.packet(xixi()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					socket.close();
					out.close();
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String xixi() {
		Output.MetaBean meta = new Output.MetaBean();
		meta.setMsgType("hb");
		Output output = new Output();
		output.setMeta(meta);
		output.setContent("heartbeatheartbeatheartbeatheartbeat/n/nheartbeatheartbeatheartbeatheartbeat");
		Gson gson = new Gson();
		gson.toJson(output);
		return gson.toJson(output);
	}

}
