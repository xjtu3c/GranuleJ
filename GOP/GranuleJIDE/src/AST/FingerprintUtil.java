package AST;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.io.File;
import java.util.*;
import beaver.*;
import java.util.ArrayList;
import java.util.zip.*;
import java.io.*;
import java.util.Stack;
import java.util.regex.Pattern;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Collection;

/**
 * @ast class
 * @declaredat :0
 */
public class FingerprintUtil extends java.lang.Object {

	
	public static String FileFingerprintWithSHA512(File file){
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			FileInputStream input=new FileInputStream(file);
			Long length=file.length();
			byte[] bytes=new byte[length.intValue()];
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



	public static String StringFingerprintWithSHA512(String s){
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			byte[] bytes=s.getBytes();
			messageDigest.update(bytes);
			return BinaryToHexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	private static String hexStr =  "0123456789ABCDEF";


	public static String BinaryToHexString(byte[] bytes){
		String result = "";
		String hex = "";
		for(int i=0;i<bytes.length;i++){
			hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
			hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));
			result +=hex;
		}
		return result;
	}



	public static byte[] HexStringToBinary(String hexString){
		int len = hexString.length()/2;
		byte[] bytes = new byte[len];
		byte high = 0;
		byte low = 0;
		for(int i=0;i<len;i++){
			 high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
			 low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
			 bytes[i] = (byte) (high|low);
		}
		return bytes;
	}


}
