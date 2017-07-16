//粒
//上下文：使用率
granule gHighUseRatio(WriteUtil){
	external double useRatio;
	{
		if(useRatio >0.5)
			return true;
		else 
			return false;
	}	
}
//影子类
//压缩文件 再追加文件？
class WriteUtil within gHighUseRatio {
	public void write(String msg) {
		System.out.println("gHighUseRatio :disk use ratio is high, start to compress the file...");
		seed.write(msg);
	}
}
