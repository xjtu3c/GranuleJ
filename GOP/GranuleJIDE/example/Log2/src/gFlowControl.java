granule gFlowControl(AbstractGenerator){
	external double overload;
	{
		if(overload >0.8)
			return true;
		else 
			return false;
	}
}

class AbstractGenerator within gFlowControl{
	public void checkStatus(){
     		System.out.println("start to flow control in AbstractGenerator...");
		seed.checkStatus();
         }
}

class UsrLogGenerator within gFlowControl{
	public void produceLog(){
		System.out.println("flow control in UsrLogGenerator");
		seed.produceLog();
	}
}
