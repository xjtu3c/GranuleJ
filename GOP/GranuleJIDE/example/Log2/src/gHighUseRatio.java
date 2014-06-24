granule gHighUseRatio(WriteUtil){
	external double useRatio;
	{
		if(useRatio >0.6)
			return true;
		else 
			return false;
	}
}


class WriteUtil within gHighUseRatio {
	public void write(String msg) {
		System.out.println("disk use ratio is high, start to compress the file...");
		seed.write(msg);
	}
}
