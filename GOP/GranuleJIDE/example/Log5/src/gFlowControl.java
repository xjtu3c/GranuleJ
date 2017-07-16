//流控粒
//上下文：负载 
granule gFlowControl(AbstractGenerator){
	external double overload;
	{
		if(overload >0.5)
		  return true;
		else 
		  return false;
	}
	

}
//影子类
//抽象类为根类？？
class AbstractGenerator within gFlowControl{
	public void checkStatus(){
		System.out.println(" gFlowControl: AbstractGenerator......");
     		System.out.println("start to flow control in AbstractGenerator...");
		seed.checkStatus();
         }
}
//影子类
//用户日志生成为粒的根类
class UsrLogGenerator within gFlowControl{
	public void produceLog(){
	System.out.println(" gFlowControl: UsrLogGenerator......");
		System.out.println("flow control in UsrLogGenerator");
		seed.produceLog();
	}
}
