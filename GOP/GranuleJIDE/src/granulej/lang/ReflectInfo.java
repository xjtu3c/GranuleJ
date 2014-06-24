package granulej.lang;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

public class ReflectInfo {
	public static ArrayList s_field_list;

	public static ArrayList i_field_list;

	public static void transformClass(String srcShadow, String destShadow) {
		Class<?> src = null;
		Class<?> dest = null;
		try {
			src = Class.forName(srcShadow);
			dest = Class.forName(destShadow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList srcfset = new ArrayList();
		srcfset = findStaticFieldsOfSrcClass(src);
		ArrayList destfset = new ArrayList();
		destfset = findStaticFieldsOfSrcClass(dest);
		for (Iterator iter = destfset.iterator(); iter.hasNext();) {
			Field df = (Field) iter.next();
			int srcf_size = srcfset.size();
			for (int i = 0; i < srcf_size; i++) {
				Field sf = (Field) srcfset.get(i);
				/*
				 * AccessController.doPrivileged(new PrivilegedExceptionAction() {
				 * public Object run() throws Exception { if(!sf.isAccessible())
				 * sf.setAccessible(true); return null; } });
				 */
				if (sf.getName().equals(df.getName()) && sf.getType().equals(df.getType())) {
					try {
						df.set(dest, sf.get(src));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				System.out.println("shu ju is transformming !");
			}
		}

	}

	public static ArrayList findStaticFieldsOfSrcClass(Class srcShadow) {
		ArrayList list = new ArrayList();
		Field[] field = srcShadow.getDeclaredFields();
		AccessibleObject.setAccessible(field, true);
		int length = field.length;
		for (int i = 0; i < length; i++) {
			if (Modifier.isStatic(field[i].getModifiers()))
				if (!list.contains(field[i]))
					list.add(field[i]);
		}
		Class<?> srcp = srcShadow.getSuperclass();
		if (srcp != null && !srcp.getName().equals("java.lang.Object")) {
			findStaticFieldsOfSrcClass(srcp);
		}
		return list;
	}

	public static void travelSuperClass(String cname) {
		Class<?> src = null;
		try {
			src = Class.forName(cname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Class<?> srcp = src.getSuperclass();
		while (srcp != null && !srcp.getName().equals("java.lang.Object")) {
			System.out.println("the parent of the class is :" + srcp.getName());
			srcp = srcp.getSuperclass();
		}
	}

	public static void reInitializaition(Object sobj, Object dobj) {
		ArrayList srcf = new ArrayList();
		Class<?> srcClazz = sobj.getClass();
		srcf = findObjectAllFields(srcClazz);
		ArrayList destf = new ArrayList();
		Class<?> destClazz = dobj.getClass();
		destf = findObjectAllFields(destClazz);
		int src_size = srcf.size();
		for (int i = 0; i < src_size; i++) {
			Field sr = (Field) srcf.get(i);
			int size = destf.size();
			for (int j = 0; j < size; j++) {
				Field dt = (Field) destf.get(j);
				if (sr.getName().equals(dt.getName()) && sr.getType().equals(dt.getType())) {
					try {
						dt.set(dobj, sr.get(sobj));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static ArrayList findObjectAllFields(Class clazz) {
		ArrayList list = new ArrayList();
		if (clazz == null)
			System.err.println("Class is error !");
		Field[] declaref = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(declaref, true);
		int length = declaref.length;
		for (int i = 0; i < length; i++) {
			if (!Modifier.isStatic(declaref[i].getModifiers()))
				if (!list.contains(declaref[i]))
					list.add(declaref[i]);
		}
		Class clzp = clazz.getSuperclass();
		if (clzp != null && !clzp.getName().equals("java.lang.Object"))
			findObjectAllFields(clzp);
		return list;
	}

	public static Object createInstance(Class mname) {
		Object newObj = null;
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			Unsafe unsafe = (Unsafe) field.get(null);
			newObj = unsafe.allocateInstance(mname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newObj;
	}

	public static Object transformInstance(Object srco, String sname) {
		// TransClassLoader loader=new TransClassLoader();
		Class mname = null;
		try {
			// loader.modifySuperClass("grecursive_C");
			mname = Class.forName(sname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object deso = createInstance(mname);
		/*
		 * Class nname=null; try{ nname=Class.forName("gword_C");
		 * }catch(Exception e){ e.printStackTrace(); }
		 */
		// Object srco=createInstance(nname);
		reInitializaition(srco, deso);
		// printFields(srco,deso);
		return deso;
	}

	private static void printFields(Object srco, Object desto) {
		try {
			Class src = srco.getClass();
			Field[] fields = getAllFields(src);
			for (Field field : fields) {
				System.out.println("field name in source is :");
				System.out.println(field.getName());
				if (!field.isAccessible())
					field.setAccessible(true);
				System.out.println(field.get(srco));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			Class dest = desto.getClass();
			Field[] fields = getAllFields(dest);
			for (Field field : fields) {
				System.out.println("field name in dest is :");
				System.out.println(field.getName());
				if (!field.isAccessible())
					field.setAccessible(true);
				System.out.println(field.get(desto));
			}
		} catch (Throwable a_th) {
			a_th.printStackTrace();
		}
	}

	private static Field[] getAllFields(Class klass) {
		List<Field> fields = new ArrayList<Field>();
		fields.addAll(Arrays.asList(klass.getDeclaredFields()));
		if (klass.getSuperclass() != null) {
			fields.addAll(Arrays.asList(getAllFields(klass.getSuperclass())));
		}
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()))
				fields.remove(field);
		}
		return fields.toArray(new Field[] {});
	}

	public static Object getFileValue(Object object, String filedName) {
		Class classType = object.getClass();
		Field fild = null;
		Object fildValue = null;
		try {
			fild = classType.getDeclaredField(filedName);
			fild.setAccessible(true);
			fildValue = fild.get(object);
		} catch (NoSuchFieldException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fildValue;
	}

	public static Object transData(String destName, Object obj) {
		// C c=new C();
		String srcName = obj.getClass().getName();
		transformClass(srcName, destName);
		// transformClass("C","gword_C");
		Object destobj = transformInstance(obj, destName);
		// Object destobj=transformInstance(c,"gword_C");\
		return destobj;
	}

	public static void main(String[] args) throws Exception {
	}
}