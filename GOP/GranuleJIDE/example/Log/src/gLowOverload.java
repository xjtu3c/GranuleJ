granule gLowOverload(AbstractGenerator){
	external double overload;
	{
		if(overload <= 0.8)
			return true;
		else 
			return false;
	}
}

class AbstractGenerator within gLowOverload{
	public void checkStatus(){
     		System.out.println("system overload is low, not use flow control in AbstractGenerator...");
		seed.checkStatus();
         }
}

class UsrLogGenerator within gLowOverload{
	public void produceLog(){
		System.out.println("system overload is low, not use flow control in user log...");
		seed.produceLog();
	}
}
