package com.pateo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:sss");

	public static void logInfo(String string) {
		System.out.println(sdf.format(new Date()) + " : " + string);
	}
	public static void logInfo(Object obj) {
		System.out.println(sdf.format(new Date()) + " : " + obj.toString());
	}
}
