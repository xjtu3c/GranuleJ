//解密粒
//上下文：安全索引
granule gDecrypt( FileLogGenerator){
	external double safetyIndex;
	{
		if(safetyIndex > 0.5)
			return true;
		else 
			return false;
	}
	
}

class  FileLogGenerator within gDecrypt{

	public LogItem generate() {
		seed.checkStatus();
		seed.produceLog();
		seed.setMsg(decrypt(seed.getMsg()));
		System.out.println("safety index is high, now decrypt the log ...");
		return seed.generate();
	}

	 public String decrypt(String msg) {
		    msg =msg.substring(0, msg.length() - 1) + msg.substring(msg.length() - 1);
		    return msg;
	 }
	 
   public static void printA(){
       System.out.println("now in gDecrypt!");
   }
   
   public static void main(String[] args){
         printA();
   }

}
