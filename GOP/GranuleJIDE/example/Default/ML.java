package Default;
granule ML(Site){
	external String Loc;
	{
		return Loc.equals("UNKNOWN");
//		return !(Loc.equals("Site A")||Loc.equals("Site B")||Loc.equals("Site C"));
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

