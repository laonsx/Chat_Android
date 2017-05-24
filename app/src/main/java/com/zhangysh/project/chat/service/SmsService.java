package com.zhangysh.project.chat.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.zhangysh.project.chat.inter.NetInter;
import com.zhangysh.project.chat.socket.HeartBeat;
import com.zhangysh.project.chat.socket.Protocol;

public class SmsService extends Service implements NetInter {

	private MyBinder binder = new MyBinder();
	private OutputStream out;
	private Socket socket;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		startSocketServer();
		return super.onStartCommand(intent, flags, startId);
	}

	public boolean isSocketServerStarted = false;

	/**
	 * 线程切换使用handler
	 */
	private Handler workHandler = new Handler();

	/**
	 * 界面回调handler
	 */
	private Handler helperListener = null;

	/**
	 * 服务销毁是是停止socket监听
	 */
	@Override
	public void onDestroy() {
		stopSocketServer();
		helperListener = null;
		super.onDestroy();
	}

	/**
	 * 启动socket监听
	 */
	public void startSocketServer() {
		if (!isSocketServerStarted) {
			try {
				/**
				 * 启动ServerSocket
				 */
				socket = new Socket();
				isSocketServerStarted = true;
			} catch (Exception e) {
				e.printStackTrace();
			}

			/**
			 * Accept Socket
			 */
			new Thread(new ConnectionRunnable()).start();
		}
	}

	public class ConnectionRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				socket.connect(new InetSocketAddress(WEB_SOCKET_IP, Web_SOCKET_PORT));
				out = socket.getOutputStream();
				new Thread(new HeartBeat(socket)).start();

				Protocol.unpack(socket, new Protocol.ResultInfo() {

					@Override
					public void returnReadInfo(String result) {
						// TODO Auto-generated method stub
						System.out.println(result);

					}

					@Override
					public void connectClosed() {
						// TODO Auto-generated method stub
						System.out.println("connectionclosed");
					}
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 停止socket监听
	 */
	public void stopSocketServer() {
		if (isSocketServerStarted && socket != null) {
			try {
				isSocketServerStarted = false;
				if (socket != null && !socket.isClosed()) {
					socket.close();
					socket = null;
				}
				socket.close();
				socket = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public class MyBinder extends Binder {
		// 发送消息
		public void sendMessage(String message) {
			try {
				if (out == null) {
					return;
				}
				System.out.println("............" + Protocol.packet(message));
				System.out.println(new String(Protocol.packet(message)));
				out.write(Protocol.packet(message));
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
