granule DIS_SB_DB(Count) expands Count_SB_FIB {
	external String location;
	{
		return location.equals("b");
	}	
}

class Count within DIS_SB_DB{
	//数字化显示结果--输出在console
	public void disPlayResult() {
		System.out.println("Digital:display the result:" + getResult());
	}	
}
