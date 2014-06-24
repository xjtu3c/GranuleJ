granule ML(Site){	
	external String Loc;
    {
		return Loc.equals("UNKNOWN");
	}
	class Site {
		public void work(){
			System.out.println("Default work");
		}
		public void show(){
			System.out.println("Default show");
		}
   }
}

