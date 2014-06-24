granule gLowUseRatio(WriteUtil){
	external double useRatio;
	{
		if(useRatio <= 0.6)
			return true;
		else 
			return false;
	}
}

  class WriteUtil within gLowUseRatio{
	public void write(String msg) {
		System.out.println("disk use ratio is low, not to ues compress...");
		seed.write(msg);
	}
}
