granule DIS_SB_DA expands Count_SB_FIA (Count) {
	external String location;
	{
		return location.equals("b");
	}	
}

class Count within DIS_SB_DA{
	public void disPlayResult() {
		System.out.println("Digital:display the result:" + getResult());
	}	
}
