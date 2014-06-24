package granulej.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;

import sun.misc.Unsafe;

public class ClassStateTransform {
	
	private static HashMap<String, Class> c_map=new HashMap<String,Class>();
	private static ArrayList<Class> transClasses=new ArrayList<Class>();
	private static ClassHashMap<Field> f_map=new ClassHashMap<Field>();
	
	private static List<Field> non_static_f=new ArrayList<Field>();	
	
	//获取老对象的祖先类
	private static void getAncestorClasses(Object obj)
	{
		Class<?> cl=obj.getClass();
	    String name=cl.getName();
	    if(!c_map.containsKey(name)){
	      c_map.put(name, cl);    	
	    }
		
		Class<?> cl_p = cl.getSuperclass();
		while (cl_p != null && cl_p !=java.lang.Object.class) {
		    name=cl_p.getName();
		    if(!c_map.containsKey(name)){
			      c_map.put(name, cl_p);    	
			 }			
			cl_p = cl_p.getSuperclass();
		
		} 	
	}
	
	//获取新对象中要转换的类	
	private static void getTransformClasses(Object newobj)
    {
	    Class<?> new_cl=newobj.getClass();
	    String name=new_cl.getName();
	    if(c_map.containsKey(name)){
		  transClasses.add(c_map.get(name));   	
		}
	    
		Class<?> new_cl_p =new_cl.getSuperclass();
		while (new_cl_p != null && new_cl_p!=java.lang.Object.class) {
		    name= new_cl_p.getName();
		    if(c_map.containsKey(name)){
			      transClasses.add(c_map.get(name));   	
			 }
			new_cl_p = new_cl_p.getSuperclass();		
		} 	
	}
	
	//搜集静态域和非静态域
    public static void collectFields(Object old_obj, Object new_obj)
    {
    	getAncestorClasses(old_obj);
    	getTransformClasses(new_obj);
 	
    	for(int i=0; i<transClasses.size();i++){
    	    Class<?> cl=(Class)transClasses.get(i);	
    	    Field[] declaredFields = cl.getDeclaredFields();
    	   if(declaredFields!=null){
    	    for(Field f:declaredFields){
    	    	 int modifiers=f.getModifiers();
    	    	 if(Modifier.isTransient(modifiers))
    	         continue;
    	    	 if(Modifier.isStatic(modifiers))
    	    	 continue;
    	    	 if(f.isSynthetic())
    	    	 continue;   	    	
    	    	else {
    	    	  non_static_f.add(f);        	    		
    	    	}
    	       }
    	   }
    	} 
    	//搜集并排序
    	final Unsafe unsafe=UnsafeUtil.getUnsafe();
    	 Field[] allFieldsArray = non_static_f.toArray(new Field[] {});
           Comparator<Field> fieldOffsetComparator = new Comparator<Field>() {
                   public int compare(Field f1, Field f2) {
                           long offset1 = unsafe.objectFieldOffset(f1);
                           long offset2 = unsafe.objectFieldOffset(f2);
                           if (offset1 < offset2)
                                   return -1;
                           if (offset1 == offset2)
                                   return 0;
                           return 1;
                   }
           };

           Arrays.sort(allFieldsArray, fieldOffsetComparator);
           non_static_f =Arrays.asList(allFieldsArray);    	
    }
    
   public static void migrateInstanceState(Object old_obj,Object new_obj)
    {
    	Unsafe unsafe=UnsafeUtil.getUnsafe();
    	for(int i=0, n=non_static_f.size(); i<n; i++)
    	{
    	    Field f=(Field)non_static_f.get(i);   	    
    	    long offset=unsafe.objectFieldOffset(f);
    	    Class<?> type=f.getType();
    	    setObjectFields(old_obj,new_obj,offset,type);
    	 }    	
    }   
    
    private static void setObjectFields(Object old_obj,Object new_obj, long offset, Class<?> type)
    {
    	Unsafe unsafe=UnsafeUtil.getUnsafe();
    	if (type.isPrimitive()) {
             if (type == boolean.class){
             unsafe.putBoolean(new_obj, offset, unsafe.getBoolean(old_obj, offset)); 
             }                
             else if (type == byte.class){
             unsafe.putByte(new_obj, offset, unsafe.getByte(old_obj, offset)); 
             }                
             else if (type == char.class){
              unsafe.putChar(new_obj, offset, unsafe.getChar(old_obj, offset)); 
             }                     
             else if (type == short.class){
              unsafe.putShort(new_obj, offset, unsafe.getShort(old_obj, offset)); 
             }                    
             else if (type == int.class){
              unsafe.putInt(new_obj, offset, unsafe.getInt(old_obj, offset)); 
             }                    
             else if (type == long.class){
              unsafe.putLong(new_obj, offset, unsafe.getLong(old_obj, offset)); 
             }                     
             else if (type == float.class){
              unsafe.putFloat(new_obj, offset, unsafe.getFloat(old_obj, offset)); 
             }                     
             else if (type == double.class){
              unsafe.putDouble(new_obj, offset, unsafe.getDouble(old_obj, offset)); 
             }              
        }
    	else{
    		unsafe.putObject(new_obj, offset, unsafe.getObject(old_obj, offset));   		
    	}    	
    }   	 

    private int fieldSizeOf(Class<?> clazz) {
            if(clazz == int.class || clazz == float.class)
                    return 4;

            if(clazz == long.class || clazz == double.class)
                    return 8;

            if(clazz == byte.class || clazz == boolean.class)
                    return 1;
           
            if(clazz == short.class || clazz == char.class)
                    return 2;
           
            //引用的大小
            return UnsafeUtil.getUnsafe().addressSize();
    } 

}
