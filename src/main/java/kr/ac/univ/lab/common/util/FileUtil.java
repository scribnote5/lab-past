package kr.ac.univ.lab.common.util;

public class FileUtil {
	public static String getExtension(String fileName) {
		return (fileName.substring(fileName.lastIndexOf("."))).toLowerCase();
	}
	
}
