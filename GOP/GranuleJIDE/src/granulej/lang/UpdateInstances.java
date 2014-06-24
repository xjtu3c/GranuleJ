package granulej.lang;
import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.lang.Class;
import java.util.Arrays;  
public class UpdateInstances {

	//为stale instance创建new instance  
	public static Object createNewInstance(Class new_c)
	   {
		   Object new_obj=null;
		   Unsafe unsafe=UnsafeUtil.getUnsafe();
		   try{
		   new_obj=unsafe.allocateInstance(new_c);
		   }catch(InstantiationException e)
		   {
			   e.printStackTrace();
		   }
		   return new_obj;
	   }
	
	//上述方法的重载，创建新实例集
	public static Object[] createNewInstance(Class new_c,int count)
	   {
		   Object[] new_obj=new Object[count];
		   Unsafe unsafe=UnsafeUtil.getUnsafe();
		   try{			   
		   for(int i=0;i<count;i++){
		   new_obj[i]=unsafe.allocateInstance(new_c);
		   }
		   }catch(InstantiationException e)
		   {
			   e.printStackTrace();
		   }
		   return new_obj;
	   }
 
      public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException,  
     IllegalAccessException {
		/* System.out.println(unsafe);   
		 byte[] data = new byte[10];  
         System.out.println(Arrays.toString(data));  
         
         long startTime=System.currentTimeMillis();  
     	  for(int i=0;i<10000000;i++){
     	   unsafe.putByte(data, BYTES_OFFSET, (byte) 1);  
           unsafe.putByte(data, BYTES_OFFSET + 5, (byte) 5);
         }
     	 System.out.println(Arrays.toString(data)); 
     	 long endTime=System.currentTimeMillis(); //获取结束时间  
     	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");*/  
            
     }
}
