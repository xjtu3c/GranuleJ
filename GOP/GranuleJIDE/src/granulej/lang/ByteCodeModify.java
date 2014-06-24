package granulej.lang;

import javassist.*;
import java.io.File;

public class ByteCodeModify {
	private String curpath;

	private String tarpath;

	private static ByteCodeModify instance = null;

	public static synchronized ByteCodeModify getInstance() {
		if (instance == null)
			instance = new ByteCodeModify();
		return instance;
	}

	private ByteCodeModify() {
		this.curpath = System.getProperty("user.dir") + File.separator;
	}

	public static void modifySuperClass(String curname, String supername) {
		try {
			ByteCodeModify bcm = ByteCodeModify.getInstance();
			ClassPool pool = ClassPool.getDefault();
			pool.insertClassPath(bcm.getTargetDir());			
			CtClass cc = pool.get(curname);
			pool.insertClassPath(bcm.getCurrentDir());
			cc.setSuperclass(pool.get(supername));
			cc.writeFile(bcm.getCurrentDir());
			cc.detach();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCurrentDir() {
		return curpath;
	}

	public void setCurrentDir(String curpath) {
		this.curpath = curpath;
	}

	public String getTargetDir() {
		return tarpath;
	}

	public void setTargetDir(String tarpath) {
		this.tarpath = tarpath;
	}

	public static void main(String args[]) {
//		ByteCodeModify bcm = ByteCodeModify.getInstance();
//		bcm.setTargetDir("D:/test/Test10/Test/bin/");
//		modifySuperClass("gEncrypt", "B");
	}

}
