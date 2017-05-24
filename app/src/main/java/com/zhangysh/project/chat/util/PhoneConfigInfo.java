package com.zhangysh.project.chat.util;

/**
 * 类名称：PhoneConfigInfo 类描述：Phone配置信息类
 */
public class PhoneConfigInfo {

	private static float density = 160;
	private static int width;// 得到宽度
	private static int height;// 得到高度
	private static float density_divisor = 1;

	public static float getDensity_divisor() {
		return density_divisor;
	}

	public static float getDensity() {
		return density;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setDensity(float density) {
		PhoneConfigInfo.density = density;
		PhoneConfigInfo.density_divisor = density / 160;
	}

	public static void setWidth(int width) {
		PhoneConfigInfo.width = width;
	}

	public static void setHeight(int height) {
		PhoneConfigInfo.height = height;
	}

	public static int getViewWidth(double pWidth) {
		int newwidth = (int) ((pWidth / 720.0) * PhoneConfigInfo.width);
		return newwidth;
	}

	public static int getViewHeight(double pHeight) {
		int newwidth = (int) ((pHeight / 1280.0) * PhoneConfigInfo.height);
		return newwidth;
	}

	public static int dipToPx(float dpValue) {
		return (int) (dpValue * density + 0.5f);
	}


	public static int pxToDip(float pxValue) {
		return (int) (pxValue / density + 0.5f);
	}

	public static int getWidthDP(){
		return (int) (width / density + 0.5f);
	}

	public static int getHeightDP(){
		return (int) (height / density + 0.5f);
	}

	public static String getInfo() {
		return "PhoneConfigInfo{width="+width+";height="+height+";density="+density+";widthDP="+getWidthDP()+";heightDP="+getHeightDP()+"}";
	}
}
