package kr.ac.univ.lab.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class CommonUtil {
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;

		if ((obj instanceof String) && (((String) obj).trim().length() == 0)) {
			return true;
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}
		if (obj instanceof List) {
			return ((List<?>) obj).isEmpty();
		}
		if (obj instanceof Object[]) {
			return (((Object[]) obj).length == 0);
		}

		return false;
	}

	public static String convertFileSize(long fileSize) {
		String retFormat = "0";
		String[] s = { "bytes", "KB", "MB", "GB", "TB", "PB" };
		DecimalFormat df = new DecimalFormat("#,###.##");

		if (fileSize != 0) {
			int idx = (int) Math.floor(Math.log(fileSize) / Math.log(1024));
			double ret = ((fileSize / Math.pow(1024, Math.floor(idx))));
			retFormat = df.format(ret) + " " + s[idx];
		} else {
			retFormat += " " + s[0];
		}

		return retFormat;
	}

	public static int getByteSize(String str)  {
		int byteSize = 0;
		
		try {
			byteSize = str.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return byteSize;
	}
	
}