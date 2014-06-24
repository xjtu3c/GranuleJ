package granulej.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class FingerprintUtil {

	public static String FileFingerprintWithSHA512(File file) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			FileInputStream input = new FileInputStream(file);
			Long length = file.length();
			byte[] bytes = new byte[length.intValue()];
			input.read(bytes);
			messageDigest.update(bytes);
			return BinaryToHexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String StringFingerprintWithSHA512(String s) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			byte[] bytes = s.getBytes();
			messageDigest.update(bytes);
			return BinaryToHexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String hexStr = "0123456789ABCDEF";

	public static String BinaryToHexString(byte[] bytes) {
		String result = "";
		String hex = "";
		int length = bytes.length;
		for (int i = 0; i < length; i++) {
			hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
			hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
			result += hex;
		}
		return result;
	}

	public static byte[] HexStringToBinary(String hexString) {
		int len = hexString.length() / 2;
		byte[] bytes = new byte[len];
		byte high = 0;
		byte low = 0;
		for (int i = 0; i < len; i++) {
			high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
			low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
			bytes[i] = (byte) (high | low);
		}
		return bytes;
	}
}
