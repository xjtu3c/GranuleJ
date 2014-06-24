import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

granule gEncrypt(SysLogGenerator){
	external double safetyIndex;
	{
		if(safetyIndex <= 0.3)
			return true;
		else 
			return false;
	}
}
class SysLogGenerator within gEncrypt{

	public LogItem generate() {
		seed.checkStatus();
		seed.produceLog();
		seed.setMsg(encrypt(seed.generate()));
		System.out.println("safety index is low, now incrypt the log ...");
		return seed.generate();
	}

	 public String encrypt(LogItem logItem) {
		String res="";
		sun.misc.BASE64Encoder en=new sun.misc.BASE64Encoder(); 
		byte[] data=logItem.getEventId().getBytes();
		String r=en.encode(data);
		res+=r+"##";
		data=logItem.getEventType().getBytes();
		r=en.encode(data);
		res+=r+"##";
	            data=logItem.getEventTime().getBytes();
		r=en.encode(data);
		res+=r+"##";   
	            data=logItem.getEventSource().getBytes();
		r=en.encode(data);
		res+=r+"##";              
		return res;
	 }

}
