package com.zhangysh.project.chat.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Protocol {

	private static final int HEADERLENGTH = 7;
	private static final int DATALENGTH = 4;
	private static final String HEAD = "Headers";

	// 封包
	public static byte[] packet(String message) {
		byte[] re;
		try {
			byte[] s = message.getBytes("utf-8");
			byte[] sss = intToByteArray(s.length);
			byte[] ss = "Headers".getBytes("utf-8");
			re = new byte[s.length + sss.length + ss.length];
			System.arraycopy(ss, 0, re, 0, ss.length);
			System.arraycopy(sss, 0, re, ss.length, sss.length);
			System.arraycopy(s, 0, re, sss.length + ss.length, s.length);
			return re;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static byte[] test_unpack(byte[] buffer) {
		int length = buffer.length;
		int i = 0;
		if (length < HEADERLENGTH + DATALENGTH) {
			return buffer;
		}

		if (new String(subBytes(buffer, 0, HEADERLENGTH)).equals(HEAD)) {
			int messageLength = byteArrayToInt(subBytes(buffer, HEADERLENGTH, DATALENGTH));
			if (length < HEADERLENGTH + DATALENGTH + messageLength) {
				return buffer;
			}
			byte[] data = subBytes(buffer, HEADERLENGTH + DATALENGTH, messageLength);
			buffer = subBytes(buffer, HEADERLENGTH + DATALENGTH + messageLength,
					length - HEADERLENGTH - DATALENGTH - messageLength);
			System.out.println(new String(data));
			return buffer;
		}
		return buffer;
	}

	private static byte[] intToByteArray(int i) {
		byte[] result = new byte[4];
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	private static int byteArrayToInt(byte[] b) {
		byte[] a = new byte[4];
		int i = a.length - 1, j = b.length - 1;
		for (; i >= 0; i--, j--) {
			if (j >= 0)
				a[i] = b[j];
			else
				a[i] = 0;
		}
		int v0 = (a[0] & 0xff) << 24;
		int v1 = (a[1] & 0xff) << 16;
		int v2 = (a[2] & 0xff) << 8;
		int v3 = (a[3] & 0xff);
		return v0 + v1 + v2 + v3;
	}

	private static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++)
			bs[i - begin] = src[i];
		return bs;
	}

	private static volatile byte[] bytes = new byte[0];

	private static byte[] mergebyte(byte[] a, byte[] b, int begin, int end) {
		byte[] add = new byte[a.length + end - begin];
		int i = 0;
		for (i = 0; i < a.length; i++) {
			add[i] = a[i];
		}
		for (int k = begin; k < end; k++, i++) {
			add[i] = b[k];
		}
		return add;
	}

	public static void unpack(Socket socket, ResultInfo resultInfo) throws IOException {
		InputStream in = socket.getInputStream();
		int check = 1;
		int haveCheck = 0;
		while (true) {
			if (socket.isConnected()) {

			}
			if (socket.isClosed()) {
				resultInfo.connectClosed();
				in.close();
				return;
			}
			if (bytes.length < HEADERLENGTH) {
				byte[] head = new byte[HEADERLENGTH - bytes.length];
				int couter = in.read(head);
				if (couter < 0) {
					continue;
				}
				bytes = mergebyte(bytes, head, 0, couter);
				byte[] header = subBytes(bytes, 0, HEADERLENGTH);
				if (!(new String(header)).equals(HEAD)) {
					socket.getOutputStream().write("Protocol error".getBytes("utf-8"));
					socket.close();
				}
				if (couter < HEADERLENGTH + DATALENGTH) {
					continue;
				}
			}
			if (bytes.length < HEADERLENGTH + DATALENGTH) {
				byte[] headers = new byte[HEADERLENGTH + DATALENGTH - bytes.length];
				int couter = in.read(headers);
				if (couter < 0) {
					continue;
				}
				bytes = mergebyte(bytes, headers, 0, couter);
				if (couter < HEADERLENGTH + DATALENGTH) {
					continue;
				}
			}
			byte[] temp = subBytes(bytes, HEADERLENGTH, DATALENGTH);
			int messageLength = byteArrayToInt(temp);
			if (bytes.length < HEADERLENGTH + DATALENGTH + messageLength) {
				int ll = HEADERLENGTH + DATALENGTH + messageLength - bytes.length;
				byte[] body = new byte[ll];
				int couter = in.read(body);
				if (couter < 0) {
					continue;
				}
				bytes = mergebyte(bytes, body, 0, couter);
				if (couter < body.length) {
					continue;
				}
			}
			byte[] body = subBytes(bytes, HEADERLENGTH + DATALENGTH, messageLength);
			resultInfo.returnReadInfo(new String(body));
			bytes = new byte[0];
		}
	}

	public interface ResultInfo {
		void returnReadInfo(String result);

		void connectClosed();
	}
}
