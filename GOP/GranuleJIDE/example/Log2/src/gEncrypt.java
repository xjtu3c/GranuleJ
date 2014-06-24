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
		seed.setMsg(encrypt(seed.getMsg()));
		System.out.println("safety index is low, now incrypt the log ...");
		return seed.generate();
	}

	 public String encrypt(String msg) {
		    msg = msg.substring(msg.length() - 1) +
			  		msg.substring(0, msg.length() - 1);
		    return msg;
	 }
           public static void printA()
           {
           System.out.println("da hua hua ti shi first in gEncrypt!");
           }
           public static void main(String[] args){
           printA();
           }

}
