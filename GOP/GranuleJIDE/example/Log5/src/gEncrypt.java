//加密粒
//上下文：安全索引
granule gEncrypt(SysLogGenerator){
	external double safetyIndex;
	{
		if(safetyIndex <= 0.5)
			return true;
		else 
			return false;
	}
	
}

//影子类
//粒的根类为系统日志生成
class SysLogGenerator within gEncrypt{

	public LogItem generate() {
		seed.checkStatus();
		seed.produceLog();
		seed.setMsg(encrypt(seed.getMsg()));
		System.out.println(" gEncrypt : safety index is low, now incrypt the log ...");
		return seed.generate();
	}

	 public String encrypt(String msg) {
		    msg = msg.substring(msg.length() - 1) +
			  		msg.substring(0, msg.length() - 1);
		    return msg;
	 }
	 
     public static void printA(){
         System.out.println("da hua hua ti shi first in gEncrypt!");
     }
     
     public static void main(String[] args){
           printA();
     }

}
