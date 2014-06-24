granule gPlainText(SysLogGenerator){
	external double safetyIndex;
	{
           	if(safetyIndex >0.3)
			return true;
		else 
			return false;
	}
}

class SysLogGenerator within gPlainText{
	public LogItem generate() {
		System.out.println("safety index is high, not use encrypt");
		return seed.generate();
	}
}
